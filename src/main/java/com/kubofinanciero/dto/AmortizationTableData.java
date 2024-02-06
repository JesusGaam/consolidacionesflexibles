package com.kubofinanciero.dto;

public class AmortizationTableData {
  public int paymentNumber;
  public double initialBalance;
  public double payment;
  public double interest;
  public double capital;
  public double finalBalance;

  public AmortizationTableData(
      int paymentNumber,
      double initialBalance,
      double payment,
      double interest,
      double capital,
      double finalBalance) {
    this.paymentNumber = paymentNumber;
    this.initialBalance = initialBalance;
    this.payment = payment;
    this.interest = interest;
    this.capital = capital;
    this.finalBalance = finalBalance;
  }

  @Override
  public String toString() {
    return String.format(
        "{\"paymentNumber\":%s, \"initialBalance\":%s, \"payment\":%s, \"interest\":%s, \"capital\":%s, \"finalBalance\":%s}",
        this.paymentNumber,
        this.initialBalance,
        this.payment,
        this.interest,
        this.capital,
        this.finalBalance);
  }
}
