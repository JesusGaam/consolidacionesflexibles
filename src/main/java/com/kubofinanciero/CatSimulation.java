package com.kubofinanciero;

import java.util.Date;

import com.kubofinanciero.dto.SimulatorOfferDto;
import com.kubofinanciero.utils.CAT;
import com.kubofinanciero.utils.GenericUtilities;
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
  private String calculationDate;
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

    getCalculationDate();
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

  public String getCalculationDate() {
    if (calculationDate == null) {
      calculationDate = GenericUtilities.toStringDate(new Date());
    }

    return calculationDate;
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

    this.payment = GenericUtilities.round(payment);
  }

  private void findCat() {
    if (amount <= 0 || paymentTerm <= 0) {
      cat = 0;
    }

    if (commissionRate <= 0) {
      commissionRate = 0;
    }

    double cashCommission = LoanSimulator.cashCommission(amount, commissionRate, false);
    double ratefrequency = loan.rateFrequency(rate, frequency, false);
    double paymentCat = loan.payment(amount, paymentTerm, ratefrequency);
    int periodsPerYear = loan.getFrequency(frequency).getPeriodsPerYearForCat();

    double fullCat = new CAT(amount, cashCommission, paymentCat, paymentTerm, periodsPerYear).getCAT();
    cat = GenericUtilities.round(fullCat, 1);
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
        ", \"calculationDate\": \"" + calculationDate +
        "\"}";
  }

  public static void main(String[] args) {

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
