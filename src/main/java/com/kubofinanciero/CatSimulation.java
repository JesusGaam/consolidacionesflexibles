package com.kubofinanciero;

import java.util.ArrayList;
import java.util.List;

import com.kubofinanciero.dto.AmortizationTableData;
import com.kubofinanciero.dto.SimulatorOfferDto;
import com.kubofinanciero.utils.CAT;
import com.kubofinanciero.utils.LoanSimulator;

public class CatSimulation {
  private double amount;
  private double rate;
  private double commissionRate;
  private double payment;
  private double suggestedPayment;
  private int paymentTerm;
  private char frequency;
  private double cat;
  private List<AmortizationTableData> amortizationTable;
  private LoanSimulator loan = new LoanSimulator();
  private SimulatorOfferDto simulatorOffer;
  private double ratefrequency;

  public CatSimulation() {
    frequency = 'M';
    amortizationTable = new ArrayList<AmortizationTableData>();
  }

  public CatSimulation(
      double suggestedPayment,
      char frequency,
      SimulatorOfferDto simulatorOffer) {

    this.simulatorOffer = simulatorOffer;
    this.amount = simulatorOffer.getMaxAmount();
    this.rate = simulatorOffer.getRate();
    this.commissionRate = simulatorOffer.getCommissionRate();
    this.suggestedPayment = suggestedPayment;
    this.frequency = frequency;
    ratefrequency = loan.rateFrequency(rate, frequency, true);

    validateSuggestedPayment();
    findPaymentTerm();
    findPayment();
    findCat();
    buildAmortizationTable();
  }

  public double getAmount() {
    return amount;
  }

  public double getRate() {
    return rate;
  }

  public double getCommissionRate() {
    return commissionRate;
  }

  public double getPayment() {
    return payment;
  }

  public int getPaymentTerm() {
    return paymentTerm;
  }

  public char getFrequency() {
    return frequency;
  }

  public double getCat() {
    return cat;
  }

  public List<AmortizationTableData> getAmortizationTable() {
    return amortizationTable;
  }

  private void validateSuggestedPayment() {
    if (suggestedPayment > simulatorOffer.getMaxPayment()) {
      suggestedPayment = simulatorOffer.getMaxPayment();
    }

    if (suggestedPayment < simulatorOffer.getMinPayment()) {
      suggestedPayment = simulatorOffer.getMinPayment();
    }
  }

  private void findPaymentTerm() {
    paymentTerm = loan.totalPayments(amount, suggestedPayment, ratefrequency);
    validatePaymentTerm();
  }

  private void validatePaymentTerm() {
    if (paymentTerm < simulatorOffer.getMinPaymentTerm()) {
      paymentTerm = simulatorOffer.getMinPaymentTerm();
      return;
    }

    if (paymentTerm > simulatorOffer.getMaxPaymentTerm()) {
      paymentTerm = simulatorOffer.getMaxPaymentTerm();
    }
  }

  private void findPayment() {
    double payment = loan.payment(amount, paymentTerm, ratefrequency);

    while (payment > simulatorOffer.getMaxPayment()) {
      payment = loan.payment(amount, ++paymentTerm, ratefrequency);
    }

    while (payment < simulatorOffer.getMinPayment()) {
      payment = loan.payment(amount, --paymentTerm, ratefrequency);
    }

    this.payment = payment;
  }

  private void findCat() {
    double cashCommission = loan.cashCommission(amount, commissionRate, false);
    double paymentCat = loan.payment(amount, paymentTerm, ratefrequency);
    int periodsPerYear = loan.getFrequency(frequency).getPeriodsPerYearForCat();

    cat = new CAT(amount, cashCommission, paymentCat, paymentTerm, periodsPerYear).getCAT();
  }

  private void buildAmortizationTable() {
    double ratefrequency = loan.rateFrequency(rate, frequency, true);
    amortizationTable = loan.amortizationTable(amount, ratefrequency, payment, paymentTerm);
  }

  public String toJSONString(boolean includeAmortizationTable) {
    String amortizationTable = "";
    if (includeAmortizationTable) {
      amortizationTable = ", \"amortizationTable\":" + this.amortizationTable;
    }

    return "{ \"amount\":" + amount +
        ", \"rate\":" + rate +
        ", \"commissionRate\":" + commissionRate +
        ", \"payment\":" + payment +
        ", \"paymentTerm\":" + paymentTerm +
        ", \"frequency\": \"" + frequency +
        "\", \"cat\":" + cat +
        amortizationTable +
        "}";
  }

  @Override
  public String toString() {
    return toJSONString(true);
  }
}
