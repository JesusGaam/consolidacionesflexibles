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
    }
  };

  public LoanSimulator() {
  }

  public final FrequencyDaysDto getFrequency(char frequency) {
    FrequencyDaysDto freq = frequencyDays.get(frequency);
    if (freq == null) {
      return frequencyDays.get('M');
    }
    return freq;
  }

  public double periodsPerYear(char frequency, boolean periodsForCAT) {
    double periodsPerYear = getFrequency(frequency).periodsPerYear;

    if (periodsForCAT) {
      return Math.ceil(periodsPerYear);
    }

    return periodsPerYear;
  }

  public double rateFrequency(double annualRate, char frequency, boolean iva) {
    double ivaPercentage = iva ? 1.16 : 1;
    double frequencyNumber = periodsPerYear(frequency, false);
    return (annualRate * ivaPercentage) / frequencyNumber;
  }

  public double presentValue(double rateFrequency, int totalPayments, double payment) {
    return (((payment *
        ((1 - Math.pow(1 + rateFrequency, -totalPayments)) / rateFrequency)) /
        100) *
        100);
  }

  public double futureValue(double payment, int totalPayments) {
    return payment * totalPayments;
  }

  public double cashCommission(double amount, double commissionPercent, boolean iva) {
    double ivaPercentage = iva ? 1.16 : 1;
    return amount * commissionPercent * ivaPercentage;
  }

  public double disbursement(double amount, double commissionPercent, boolean iva) {
    double commission = cashCommission(amount, commissionPercent, iva);
    return amount - commission;
  }

  public double payment(double amount, int totalPayments, double rateFrequency) {
    return ((rateFrequency * amount) / (1 - Math.pow(1 + rateFrequency, -totalPayments)));
  }

  public int totalPayments(double amount, double payment, double rateFrequency) {
    if (payment > 0) {
      payment = -payment;
    }

    double totalPayments = Math.log((amount * rateFrequency) / payment + 1) /
        Math.log(1 + rateFrequency);

    return (int) Math.ceil(totalPayments < 0 ? -1 * totalPayments : totalPayments);
  }

  public double interests(double amount, double rateFrequency) {
    // (annualRate * ivaPercentage) / frequencyNumber

    return amount * rateFrequency;
  }

  public List<AmortizationTableData> amortizationTable(
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

    payment = round(payment, 2);
    for (int i = 1; i <= totalPayments; i++) {
      initialBalance = i == 1 ? amount : finalBalance;

      if (i < totalPayments) {
        interest = round(interests(initialBalance, rateFrequency), 2);
        capital = round(payment - interest, 2);
        sumCapital = round(sumCapital + capital, 2);
      } else {
        capital = round(amount - sumCapital, 2);
        interest = round(interests(capital, rateFrequency), 2);
      }

      finalBalance = round(initialBalance - capital, 2);
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

  public static double round(double number, int nDigits) {
    nDigits = nDigits > 0 ? (int) Math.pow(10, nDigits) : 1;
    return (double) Math.round(number * nDigits) / nDigits;
  };

  public static double round(double number) {
    return round(number, 2);
  };

  public static void main(String[] args) {

    /**
     * DATOS DE ENTRADA
     * 
     * @rate Tasa Anual en porcentaje
     * @amount Monto solicitado
     * @suggestedPayment Pago sugerido
     * @frequency Frecuencia de pago
     */

    double amount = 20000;
    double suggestedPayment = 1500;
    char frequency = 'M';
    double rate = 0.593;
    double commissionRate = 0.05;

    /**
     * CALCULOS DE SIMULACIÓN
     */
    LoanSimulator loan = new LoanSimulator();
    double ratefrequency = loan.rateFrequency(rate, frequency, true);
    int totalPayments = loan.totalPayments(amount, suggestedPayment, ratefrequency);
    double realPayment = loan.payment(amount, totalPayments, ratefrequency);
    List<AmortizationTableData> amortizationTable = loan.amortizationTable(
        amount,
        ratefrequency,
        realPayment,
        totalPayments);

    /**
     * CALCULOS SIN IVA PARA EL CAT
     */
    double commission = loan.cashCommission(amount, commissionRate, false);
    double catFrequency = loan.rateFrequency(rate, frequency, false);
    double catPayment = loan.payment(amount, totalPayments, catFrequency);
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
    System.out.println("Amortization table:");
    for (AmortizationTableData amor : amortizationTable) {
      System.out.println(amor);
    }
  }
}
