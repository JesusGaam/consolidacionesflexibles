package com.kubofinanciero.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kubofinanciero.dto.AmortizationTableData;
import com.kubofinanciero.dto.FrequencyDaysDto;

public class LoanSimulator {
  public static final HashMap<Character, FrequencyDaysDto> frequencyDays = new HashMap<Character, FrequencyDaysDto>() {
    {
      put('W', new FrequencyDaysDto('W', "Semana", 7));
      put('K', new FrequencyDaysDto('K', "Catorcena", 14));
      put('S', new FrequencyDaysDto('S', "Quincena", 15));
      put('M', new FrequencyDaysDto('M', "Mes", 30));
      put('Y', new FrequencyDaysDto('Y', "Anual", 360));
      put('H', new FrequencyDaysDto('H', "Semestre", 180));
      put('Q', new FrequencyDaysDto('Q', "Trimestre", 90));
      put('B', new FrequencyDaysDto('B', "Bimestre", 60));
      put('D', new FrequencyDaysDto('D', "Diario", 1));
      put('V', new FrequencyDaysDto('V', "Variable", 0));
      put('P', new FrequencyDaysDto('P', "Cada cuando recibe mi salario", 0));
    }
  };

  public LoanSimulator() {
  }

  public static FrequencyDaysDto getFrequency(char frequency) {
    FrequencyDaysDto freq = frequencyDays.get(frequency);
    if (freq == null) {
      return frequencyDays.get('M');
    }
    return freq;
  }

  public static double periodsPerYear(char frequency, boolean periodsForCAT) {
    double periodsPerYear = getFrequency(frequency).periodsPerYear;

    if (periodsForCAT) {
      return Math.ceil(periodsPerYear);
    }

    return periodsPerYear;
  }

  public static double rateFrequency(double annualRate, char frequency, boolean iva) {
    double ivaPercentage = iva ? 1.16 : 1;
    double frequencyNumber = periodsPerYear(frequency, false);
    return (annualRate * ivaPercentage) / frequencyNumber;
  }

  public static double presentValue(double rateFrequency, int totalPayments, double payment) {
    return (((payment *
        ((1 - Math.pow(1 + rateFrequency, -totalPayments)) / rateFrequency)) /
        100) *
        100);
  }

  public static double futureValue(double payment, int totalPayments) {
    return payment * totalPayments;
  }

  public static double cashCommission(double amount, double commissionPercent, boolean iva) {
    double ivaPercentage = iva ? 1.16 : 1;
    return amount * commissionPercent * ivaPercentage;
  }

  public static double disbursement(double amount, double commissionPercent, boolean iva) {
    double commission = cashCommission(amount, commissionPercent, iva);
    return amount - commission;
  }

  public static double payment(double amount, int totalPayments, double rateFrequency) {
    return ((rateFrequency * amount) / (1 - Math.pow(1 + rateFrequency, -totalPayments)));
  }

  public static int totalPayments(double amount, double payment, double rateFrequency) {
    if (payment > 0) {
      payment = -payment;
    }

    double totalPayments = Math.log((amount * rateFrequency) / payment + 1) /
        Math.log(1 + rateFrequency);

    return (int) Math.ceil(totalPayments < 0 ? -1 * totalPayments : totalPayments);
  }

  public static double interests(double amount, double rateFrequency) {
    // (annualRate * ivaPercentage) / frequencyNumber

    return amount * rateFrequency;
  }

  public static List<AmortizationTableData> amortizationTable(
      double amount,
      double rateFrequency,
      double payment,
      int totalPayments) {
    List<AmortizationTableData> amortizationTable = new ArrayList<AmortizationTableData>();

    double initialBalance = 0;
    double finalBalance = 0;
    double sumCapital = 0;
    double interest;
    double capital = 0;

    payment = GenericUtilities.round(payment);
    for (int i = 1; i <= totalPayments; i++) {
      initialBalance = i == 1 ? amount : finalBalance;

      if (i < totalPayments) {
        interest = GenericUtilities.round(interests(initialBalance, rateFrequency));
        capital = GenericUtilities.round(payment - interest);
        sumCapital = GenericUtilities.round(sumCapital + capital);
      } else {
        capital = GenericUtilities.round(amount - sumCapital);
        interest = GenericUtilities.round(interests(capital, rateFrequency));
      }

      finalBalance = GenericUtilities.round(initialBalance - capital);
      AmortizationTableData amortizationData = new AmortizationTableData(
          i,
          initialBalance,
          payment,
          interest,
          capital,
          finalBalance);

      amortizationTable.add(amortizationData);
    }

    return amortizationTable;
  }

  public static void main(String[] args) {

    /**
     * DATOS DE ENTRADA
     * 
     * @rate Tasa Anual en porcentaje
     * @amount Monto solicitado
     * @suggestedPayment Pago sugerido
     * @frequency Frecuencia de pago
     */

    double amount = 186600;
    double suggestedPayment = 4000;
    char frequency = 'Y';
    double rate = 0.22;
    double commissionRate = 0.05;

    /**
     * CALCULOS DE SIMULACIÓN
     */
    double ratefrequency = LoanSimulator.rateFrequency(rate, frequency, false);
    int totalPayments = LoanSimulator.totalPayments(amount, suggestedPayment, ratefrequency);
    double realPayment = LoanSimulator.payment(amount, totalPayments, ratefrequency);
    List<AmortizationTableData> amortizationTable = LoanSimulator.amortizationTable(
        amount,
        ratefrequency,
        realPayment,
        totalPayments);

    /**
     * CALCULOS SIN IVA PARA EL CAT
     */
    double commission = LoanSimulator.cashCommission(amount, commissionRate, false);
    double catFrequency = LoanSimulator.rateFrequency(rate, frequency, false);
    double catPayment = LoanSimulator.payment(amount, totalPayments, catFrequency);
    CAT cat = new CAT(
        amount,
        commission,
        catPayment,
        totalPayments,
        12);

    System.out.println("=====================================");
    System.out.println("SIMULADOR DE PRÉSTAMOS");
    System.out.println("=====================================");
    System.out.println("Real payment: " + realPayment);
    System.out.println("Total payments: " + totalPayments);
    System.out.println("CAT:" + cat.getCAT());
    System.out.println("Amortization table:" + amortizationTable);

  }
}
