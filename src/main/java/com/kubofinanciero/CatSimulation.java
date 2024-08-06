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
  private int monthlyMaxPaymentTerm;
  private char frequency;
  private double cat;
  private String calculationDate;
  private SimulatorOfferDto simulatorOffer;
  private int extendedPaymentTerm;
  private double ratefrequency;
  private double globalMinAmount;

  private final int MAX_PAYMENT_TERM = 60;

  public CatSimulation() {
    frequency = 'M';
  }

  public CatSimulation(double suggestedPayment, SimulatorOfferDto simulatorOffer, int extendedPaymentTerm, double globalMinAmount) {
    this.simulatorOffer = simulatorOffer;
    this.amount = simulatorOffer.getMaxAmount();
    this.rate = simulatorOffer.getRate();
    this.commissionRate = simulatorOffer.getCommissionRate();
    this.suggestedPayment = suggestedPayment;
    this.globalMinAmount = globalMinAmount;

    getCalculationDate();
    validateExtendedPaymentTerm(extendedPaymentTerm);
    defineFrequency(frequency);
    validateSuggestedPayment();
    updatePaymentTerms();
    findPaymentTerm();
    findPayment();
    findCommissionAmount();
    findCat();
  }

  public CatSimulation(
      double suggestedPayment,
      int suggestedPaymentTerm,
      char frequency,
      SimulatorOfferDto simulatorOffer,
      int extendedPaymentTerm, 
      double globalMinAmount) {

    this.simulatorOffer = simulatorOffer;
    this.amount = simulatorOffer.getMaxAmount();
    this.rate = simulatorOffer.getRate();
    this.commissionRate = simulatorOffer.getCommissionRate();
    this.suggestedPayment = suggestedPayment;
    this.suggestedPaymentTerm = suggestedPaymentTerm;
    this.globalMinAmount = globalMinAmount;

    getCalculationDate();
    validateExtendedPaymentTerm(extendedPaymentTerm);
    defineFrequency(frequency);
    validateSuggestedPayment();
    updatePaymentTerms();
    validateSuggestedPaymentTerm();
    findPayment();
    findCommissionAmount();
    findCat();
  }

  private void validateExtendedPaymentTerm(int extendedPaymentTerm) {
    if (extendedPaymentTerm <= 0) {
      this.extendedPaymentTerm = 0;
      return;
    }

    int finalPaymentTerm = simulatorOffer.getMaxPaymentTerm() + extendedPaymentTerm;
    if (finalPaymentTerm > MAX_PAYMENT_TERM) {
      this.extendedPaymentTerm = extendedPaymentTerm - (finalPaymentTerm - MAX_PAYMENT_TERM);
    } else {
      this.extendedPaymentTerm = extendedPaymentTerm;
    }
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
        suggestedPayment = suggestedPayment / 2;
        extendedPaymentTerm = extendedPaymentTerm * 2;
        break;

      case 'W':
        minPaymentTerm = simulatorOffer.getMinPaymentTerm() * 4;
        maxPaymentTerm = simulatorOffer.getMaxPaymentTerm() * 4;
        maxPayment = simulatorOffer.getMaxPayment() / 4;
        minPayment = simulatorOffer.getMinPayment() / 4;
        suggestedPayment = suggestedPayment / 4;
        extendedPaymentTerm = extendedPaymentTerm * 4;
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

  public int getMonthlyMaxPaymentTerm() {
    return monthlyMaxPaymentTerm;
  }

  public char getFrequency() {
    return frequency;
  }

  public double getCat() {
    return cat;
  }

  public boolean validAmount() {
    return amount <= 0 || amount < globalMinAmount;
  }

  public String getCalculationDate() {
    if (calculationDate == null) {
      calculationDate = GenericUtilities.toStringDate(new Date());
    }

    return calculationDate;
  }

  private void validateSuggestedPayment() {
    if (suggestedPayment <= 0) {
      suggestedPayment = LoanSimulator.payment(amount, maxPaymentTerm, ratefrequency);
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
      paymentTerm = maxPaymentTerm;
      return;
    }

    if (suggestedPaymentTerm > maxPaymentTerm) {
      paymentTerm = maxPaymentTerm;
      return;
    }

    if (suggestedPaymentTerm < minPaymentTerm) {
      paymentTerm = minPaymentTerm;
    }
  }

  private void updatePaymentTerms() {
    int minPaymentTerm = 0;
    int maxPaymentTerm = 0;
    double minPayment = this.minPayment;
    int extendedPaymentTerm = this.maxPaymentTerm;
    double paymentFromMaxPaymentTerm = LoanSimulator.payment(amount, this.maxPaymentTerm, ratefrequency);

    if (suggestedPayment < paymentFromMaxPaymentTerm) {
      extendedPaymentTerm = this.maxPaymentTerm + this.extendedPaymentTerm;

      if (suggestedPayment >= this.minPayment) {
        minPayment = suggestedPayment;
      }
    }

    for (int paymentTerm = extendedPaymentTerm; paymentTerm >= this.minPaymentTerm; paymentTerm--) {
      double payment = LoanSimulator.payment(amount, paymentTerm, ratefrequency);

      if (GenericUtilities.round(payment) >= GenericUtilities.round(minPayment)) {
        if (paymentTerm < extendedPaymentTerm) {
          maxPaymentTerm = paymentTerm + 1;
        } else {
          maxPaymentTerm = paymentTerm;
        }
        break;
      }
    }

    for (int paymentTerm = this.minPaymentTerm; paymentTerm <= extendedPaymentTerm; paymentTerm++) {
      double payment = LoanSimulator.payment(amount, paymentTerm, ratefrequency);

      if (GenericUtilities.round(payment) <= GenericUtilities.round(this.maxPayment)) {
        minPaymentTerm = paymentTerm;
        break;
      }
    }

    if (minPaymentTerm == 0 || maxPaymentTerm == 0) {
      this.minPaymentTerm = 0;
      this.maxPaymentTerm = 0;
      this.monthlyMaxPaymentTerm = 0;
      return;
    }

    this.minPaymentTerm = minPaymentTerm;
    this.maxPaymentTerm = maxPaymentTerm;
    monthlyMaxPaymentTerm = convertPaymentTermToMonthly(maxPaymentTerm);
  }

  private int convertPaymentTermToMonthly(int paymentTerm) {
    switch (this.frequency) {
      case 'S':
      case 'K':
        return (int) Math.ceil((double) paymentTerm / 2);

      case 'W':
        return (int) Math.ceil((double) paymentTerm / 4);

      default:
        return paymentTerm;

    }
  }

  private void findPaymentTerm() {
    if (validAmount() || suggestedPayment <= 0) {
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
    this.commissionAmount = LoanSimulator.cashCommission(amount, commissionRate, false);
  }

  private void validatePaymentTerm() {
    if (paymentTerm > maxPaymentTerm || paymentTerm <= 0) {
      paymentTerm = maxPaymentTerm;
      return;
    }
    if (paymentTerm < minPaymentTerm) {
      paymentTerm = minPaymentTerm;
    }

  }

  private void findPayment() {
    if (validAmount() || paymentTerm <= 0) {
      payment = 0;
      return;
    }

    double payment = LoanSimulator.payment(amount, paymentTerm, ratefrequency);
    this.payment = GenericUtilities.round(payment);
  }

  private void findCat() {
    if (validAmount() || paymentTerm <= 0) {
      cat = 0;
      return;
    }

    if (commissionRate <= 0) {
      commissionRate = 0;
    }

    double cashCommission = LoanSimulator.cashCommission(amount, commissionRate / 1.16, false);
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
        ", \"monthlyMaxPaymentTerm\":" + monthlyMaxPaymentTerm +
        ", \"frequency\": \"" + frequency +
        "\", \"cat\":" + cat +
        ", \"calculationDate\": \"" + calculationDate +
        "\"}";
  }

  public static void main(String[] args) {

    SimulatorOfferDto so = new SimulatorOfferDto()
        .setMaxAmount(83000.0)
        .setMinAmount(83000.0)
        .setRate(0.3014)
        .setMinPayment(7304.446370693487)
        .setMaxPayment(22283.093204490007)
        .setMinPaymentTerm(4)
        .setMaxPaymentTerm(14)
        .setFrequencies(new char[] { 'M', 'S', 'K', 'W' });

    CatSimulation cat = new CatSimulation(6000, so, 6, 25000);
    System.out.println(cat);

    CatSimulation cat2 = new CatSimulation(6000, 24, 'W', so, 6, 25000);
    System.out.println("SIMULACION EN SEMANAS");
    System.out.println(cat2);

    CatSimulation cat3 = new CatSimulation(6000, 28, 'K', so, 6, 25000);
    System.out.println("SIMULACION EN CATORCENAS");
    System.out.println(cat3);

  }
}
