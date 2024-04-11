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
  private double commissionAmount;
  private double payment;
  private double maxPayment;
  private double minPayment;
  private double suggestedPayment;
  private int suggestedPaymentTerm;
  private int paymentTerm;
  private int minPaymentTerm;
  private int maxPaymentTerm;
  private char frequency;
  private double cat;
  private String calculationDate;
  private SimulatorOfferDto simulatorOffer;
  private double ratefrequency;

  public CatSimulation() {
    frequency = 'M';
  }

  public CatSimulation(double suggestedPayment, SimulatorOfferDto simulatorOffer) {
    this.simulatorOffer = simulatorOffer;
    this.amount = simulatorOffer.getMaxAmount();
    this.rate = simulatorOffer.getRate();
    this.commissionRate = simulatorOffer.getCommissionRate();
    this.suggestedPayment = suggestedPayment;

    getCalculationDate();
    defineFrequency(frequency);
    validateSuggestedPayment();
    updatePaymentTerms();
    findPaymentTerm();
    findPayment();
    findCommissionAmount();
    findCat();
  }

  public CatSimulation(
      int suggestedPaymentTerm,
      char frequency,
      SimulatorOfferDto simulatorOffer) {

    this.simulatorOffer = simulatorOffer;
    this.amount = simulatorOffer.getMaxAmount();
    this.rate = simulatorOffer.getRate();
    this.commissionRate = simulatorOffer.getCommissionRate();
    this.suggestedPayment = 0;
    this.suggestedPaymentTerm = suggestedPaymentTerm;

    getCalculationDate();
    defineFrequency(frequency);
    updatePaymentTerms();
    validateSuggestedPaymentTerm();
    findPayment();
    findCommissionAmount();
    findCat();
  }

  private void defineFrequency(char frequency) {
    if (new String(simulatorOffer.getFrequencies()).indexOf(frequency) >= 0) {
      this.frequency = frequency;
    } else {
      this.frequency = 'M';
    }

    ratefrequency = LoanSimulator.rateFrequency(rate, this.frequency, true);

    switch (this.frequency) {
      case 'S':
      case 'K':
        minPaymentTerm = simulatorOffer.getMinPaymentTerm() * 2;
        maxPaymentTerm = simulatorOffer.getMaxPaymentTerm() * 2;
        maxPayment = simulatorOffer.getMaxPayment() / 2;
        minPayment = simulatorOffer.getMinPayment() / 2;
        break;

      case 'W':
        minPaymentTerm = simulatorOffer.getMinPaymentTerm() * 4;
        maxPaymentTerm = simulatorOffer.getMaxPaymentTerm() * 4;
        maxPayment = simulatorOffer.getMaxPayment() / 4;
        minPayment = simulatorOffer.getMinPayment() / 4;
        break;

      default:
        minPaymentTerm = simulatorOffer.getMinPaymentTerm();
        maxPaymentTerm = simulatorOffer.getMaxPaymentTerm();
        maxPayment = simulatorOffer.getMaxPayment();
        minPayment = simulatorOffer.getMinPayment();
        break;
    }
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

  public double getCommissionAmount() {
    return commissionAmount;
  }

  public double getPayment() {
    return payment;
  }

  public double getMinPayment() {
    return minPayment;
  }

  public double getMaxPayment() {
    return maxPayment;
  }

  public int getPaymentTerm() {
    return paymentTerm;
  }

  public int getMinPaymentTerm() {
    return minPaymentTerm;
  }

  public int getMaxPaymentTerm() {
    return maxPaymentTerm;
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
      suggestedPayment = this.maxPayment;
      return;
    }

    if (suggestedPayment > this.maxPayment) {
      suggestedPayment = this.maxPayment;
      return;
    }

    if (suggestedPayment < this.minPayment) {
      suggestedPayment = this.minPayment;
    }
  }

  private void validateSuggestedPaymentTerm() {
    paymentTerm = suggestedPaymentTerm;

    if (suggestedPaymentTerm <= 0) {
      suggestedPaymentTerm = maxPaymentTerm;
      paymentTerm = maxPaymentTerm;
      return;
    }

    if (suggestedPaymentTerm > maxPaymentTerm) {
      suggestedPaymentTerm = maxPaymentTerm;
      paymentTerm = maxPaymentTerm;
      return;
    }

    if (suggestedPaymentTerm < minPaymentTerm) {
      suggestedPaymentTerm = minPaymentTerm;
      paymentTerm = minPaymentTerm;
    }
  }

  private void updatePaymentTerms() {
    int minPaymentTerm = 0;
    int maxPaymentTerm = 0;

    for (int paymentTerm = this.minPaymentTerm; paymentTerm <= this.maxPaymentTerm; paymentTerm++) {
      double payment = LoanSimulator.payment(amount, paymentTerm, ratefrequency);
      if (payment <= this.maxPayment && minPaymentTerm == 0) {
        minPaymentTerm = paymentTerm;
      }

      if (payment <= this.minPayment && maxPaymentTerm == 0) {
        maxPaymentTerm = paymentTerm;
        break;
      }
    }

    if (maxPaymentTerm == 0) {
      maxPaymentTerm = this.maxPaymentTerm;
    }

    this.minPaymentTerm = minPaymentTerm;
    this.maxPaymentTerm = maxPaymentTerm;
  }

  private void findPaymentTerm() {

    if (amount <= 0 || suggestedPayment <= 0) {
      paymentTerm = 0;
      return;
    }

    paymentTerm = LoanSimulator.totalPayments(amount, suggestedPayment, ratefrequency);
    validatePaymentTerm();
  }

  private void findCommissionAmount() {
    if (commissionRate <= 0) {
      this.commissionAmount = 0;
      return;
    }
    this.commissionAmount = LoanSimulator.cashCommission(amount, commissionRate, true);
  }

  private void validatePaymentTerm() {
    if (paymentTerm < minPaymentTerm) {
      paymentTerm = minPaymentTerm;
      return;
    }

    if (paymentTerm > maxPaymentTerm) {
      paymentTerm = maxPaymentTerm;
    }
  }

  private void findPayment() {
    if (amount <= 0 || paymentTerm <= 0) {
      payment = 0;
      return;
    }

    double payment = LoanSimulator.payment(amount, paymentTerm, ratefrequency);
    this.payment = GenericUtilities.round(payment);
  }

  private void findCat() {
    if (amount <= 0 || paymentTerm <= 0) {
      cat = 0;
      return;
    }

    if (commissionRate <= 0) {
      commissionRate = 0;
    }

    double cashCommission = LoanSimulator.cashCommission(amount, commissionRate, false);
    double ratefrequency = LoanSimulator.rateFrequency(rate, frequency, false);
    double paymentCat = LoanSimulator.payment(amount, paymentTerm, ratefrequency);
    int periodsPerYear = LoanSimulator.getFrequency(frequency).getPeriodsPerYearForCat();

    double fullCat = new CAT(amount, cashCommission, paymentCat, paymentTerm, periodsPerYear).getCAT();
    cat = GenericUtilities.round(fullCat, 1);
  }

  @Override
  public String toString() {
    return "{ \"amount\":" + amount +
        ", \"rate\":" + rate +
        ", \"commissionRate\":" + commissionRate +
        ", \"commissionAmount\":" + commissionAmount +
        ", \"payment\":" + payment +
        ", \"maxPayment\":" + maxPayment +
        ", \"minPayment\":" + minPayment +
        ", \"paymentTerm\":" + paymentTerm +
        ", \"minPaymentTerm\":" + minPaymentTerm +
        ", \"maxPaymentTerm\":" + maxPaymentTerm +
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
        .setMinPayment(2800)
        .setMaxPayment(3200)
        .setMinPaymentTerm(8)
        .setMaxPaymentTerm(40)
        .setFrequencies(new char[] { 'M', 'S', 'K', 'W' });

    CatSimulation cat = new CatSimulation(2650, so);
    System.out.println(cat);

    CatSimulation cat2 = new CatSimulation(60, 'K', so);
    System.out.println(cat2);

  }
}
