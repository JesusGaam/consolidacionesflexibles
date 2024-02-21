package com.kubofinanciero;

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
  private LoanSimulator loan = new LoanSimulator();
  private SimulatorOfferDto simulatorOffer;
  private double ratefrequency;

  public CatSimulation() {
    frequency = 'M';
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

  private void validateSuggestedPayment() {

    if (suggestedPayment <= 0) {
      return;
    }

    if (suggestedPayment > simulatorOffer.getMaxPayment()) {
      suggestedPayment = simulatorOffer.getMaxPayment();
    }

    if (suggestedPayment < simulatorOffer.getMinPayment()) {
      suggestedPayment = simulatorOffer.getMinPayment();
    }
  }

  private void findPaymentTerm() {

    if (amount <= 0 || suggestedPayment <= 0) {
      paymentTerm = 0;
      return;
    }

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
    if (amount <= 0 || paymentTerm <= 0) {
      payment = 0;
      return;
    }

    double payment = loan.payment(amount, paymentTerm, ratefrequency);

    while (payment > simulatorOffer.getMaxPayment()) {
      payment = loan.payment(amount, ++paymentTerm, ratefrequency);
    }

    while (payment < simulatorOffer.getMinPayment()) {
      payment = loan.payment(amount, --paymentTerm, ratefrequency);
    }

    this.payment = LoanSimulator.round(payment);
  }

  private void findCat() {
    if (amount <= 0 || commissionRate <= 0 || paymentTerm <= 0) {
      cat = 0;
      return;
    }

    double cashCommission = loan.cashCommission(amount, commissionRate, false);
    double paymentCat = loan.payment(amount, paymentTerm, ratefrequency);
    int periodsPerYear = loan.getFrequency(frequency).getPeriodsPerYearForCat();

    double fullCat = new CAT(amount, cashCommission, paymentCat, paymentTerm, periodsPerYear).getCAT();
    cat = LoanSimulator.round(fullCat, 1);
  }

  @Override
  public String toString() {
    return "{ \"amount\":" + amount +
        ", \"rate\":" + rate +
        ", \"commissionRate\":" + commissionRate +
        ", \"payment\":" + payment +
        ", \"paymentTerm\":" + paymentTerm +
        ", \"frequency\": \"" + frequency +
        "\", \"cat\":" + cat +
        "}";
  }
}
