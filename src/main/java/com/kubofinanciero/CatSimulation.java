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
      return;
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

    while (payment > simulatorOffer.getMaxPayment() && paymentTerm < simulatorOffer.getMaxPaymentTerm()) {
      this.paymentTerm = paymentTerm + 1;
      payment = loan.payment(amount, paymentTerm, ratefrequency);
    }

    while (payment < simulatorOffer.getMinPayment() && paymentTerm > simulatorOffer.getMinPaymentTerm()) {
      this.paymentTerm = paymentTerm - 1;
      payment = loan.payment(amount, paymentTerm, ratefrequency);
    }

    this.payment = LoanSimulator.round(payment);
  }

  private void findCat() {
    if (amount <= 0 || paymentTerm <= 0) {
      cat = 0;
    }

    if (commissionRate <= 0) {
      commissionRate = 0;
    }

    double cashCommission = loan.cashCommission(amount, commissionRate, false);
    double ratefrequency = loan.rateFrequency(rate, frequency, false);
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

  public static void main(String[] args) {
    // SimulatorOfferDto so = new SimulatorOfferDto()
    // .setMaxAmount(613600.0)
    // .setMinAmount(613600.0)
    // .setRate(0.6193)
    // .setMinPayment(308.7596227438041)
    // .setMaxPayment(31287.480213814375)
    // .setMinPaymentTerm(4)
    // .setMaxPaymentTerm(54)
    // .setFrequencies(new char[] { 'M', 'Q' });

    SimulatorOfferDto so = new SimulatorOfferDto()
        .setMaxAmount(30000.0)
        .setMinAmount(30000.0)
        .setRate(0.3582)
        .setMinPayment(1861)
        .setMaxPayment(3100)
        .setMinPaymentTerm(12)
        .setMaxPaymentTerm(24)
        .setFrequencies(new char[] { 'M', 'Q' });
    CatSimulation cat = new CatSimulation(
        1200,
        'M',
        so);
    System.out.println(cat);

  }
}
