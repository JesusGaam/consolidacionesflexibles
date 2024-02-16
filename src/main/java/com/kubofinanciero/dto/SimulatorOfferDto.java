package com.kubofinanciero.dto;

public class SimulatorOfferDto {
  private String loanType;
  private String subLoanType;
  private double minPayment;
  private double maxPayment;
  private double minAmount;
  private double maxAmount;
  private int minPaymentTerm;
  private int maxPaymentTerm;
  private double rate;
  private double commissionRate;
  private int segment;
  private char[] frequencies;

  public SimulatorOfferDto() {
  }

  public SimulatorOfferDto(
      String loanType,
      String subLoanType,
      double minPayment,
      double maxPayment,
      int minPaymentTerm,
      int maxPaymentTerm,
      char[] frequencies) {
    this.loanType = loanType;
    this.subLoanType = subLoanType;
    this.minPayment = minPayment;
    this.maxPayment = maxPayment;
    this.minPaymentTerm = minPaymentTerm;
    this.maxPaymentTerm = maxPaymentTerm;
    this.frequencies = frequencies;
  }

  public SimulatorOfferDto(
      String loanType,
      String subLoanType,
      double minAmount,
      double maxAmount,
      double minPayment,
      double maxPayment,
      int minPaymentTerm,
      int maxPaymentTerm,
      double rate,
      double commissionRate,
      int segment,
      char[] frequencies) {
    this.loanType = loanType;
    this.subLoanType = subLoanType;
    this.minAmount = minAmount;
    this.maxAmount = maxAmount;
    this.minPayment = minPayment;
    this.maxPayment = maxPayment;
    this.minPaymentTerm = minPaymentTerm;
    this.maxPaymentTerm = maxPaymentTerm;
    this.rate = rate;
    this.commissionRate = commissionRate;
    this.segment = segment;
    this.frequencies = frequencies;
  }

  public String getLoanType() {
    if (loanType == null) {
      loanType = "";
    }
    return loanType;
  }

  public String getSubLoanType() {
    if (subLoanType == null) {
      subLoanType = "";
    }
    return subLoanType;
  }

  public double getMinAmount() {
    return minAmount;
  }

  public double getMaxAmount() {
    return maxAmount;
  }

  public double getMinPayment() {
    return minPayment;
  }

  public double getMaxPayment() {
    return maxPayment;
  }

  public int getMinPaymentTerm() {
    return minPaymentTerm;
  }

  public int getMaxPaymentTerm() {
    return maxPaymentTerm;
  }

  public double getRate() {
    return rate;
  }

  public double getCommissionRate() {
    return commissionRate;
  }

  public int getSegment() {
    return segment;
  }

  public char[] getFrequencies() {
    if (frequencies == null) {
      frequencies = new char[] {};
    }
    return frequencies;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public void setSubLoanType(String subLoanType) {
    this.subLoanType = subLoanType;
  }

  public void setMinAmount(double minAmount) {
    this.minAmount = minAmount;
  }

  public void setMaxAmount(double maxAmount) {
    this.maxAmount = maxAmount;
  }

  public void setMinPayment(double minPayment) {
    this.minPayment = minPayment;
  }

  public void setMaxPayment(double maxPayment) {
    this.maxPayment = maxPayment;
  }

  public void setMinPaymentTerm(int minPaymentTerm) {
    this.minPaymentTerm = minPaymentTerm;
  }

  public void setMaxPaymentTerm(int maxPaymentTerm) {
    this.maxPaymentTerm = maxPaymentTerm;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public void setCommissionRate(double commissionRate) {
    this.commissionRate = commissionRate;
  }

  public void setFrequencies(char[] frequencies) {
    this.frequencies = frequencies;
  }

  private String frequenciesToString() {
    String frequencies = "";
    for (int i = 0; i < getFrequencies().length; i++) {
      frequencies += "\"" + getFrequencies()[i] + "\"";
      if (i < getFrequencies().length - 1) {
        frequencies += ",";
      }
    }
    return "[" + frequencies + "]";
  }

  public String toJSONString() {
    return "{\"loanType\": \"" + getLoanType() +
        "\", \"subLoanType\": \"" + getSubLoanType() +
        "\", \"minAmount\": " + minAmount +
        ", \"maxAmount\": " + maxAmount +
        ", \"minPayment\": " + minPayment +
        ", \"maxPayment\": " + maxPayment +
        ", \"minPaymentTerm\": " + minPaymentTerm +
        ", \"maxPaymentTerm\": " + maxPaymentTerm +
        ", \"rate\": " + rate +
        ", \"commissionRate\": " + commissionRate +
        ", \"frequencies\": " + frequenciesToString() + "}";
  }

  @Override
  public String toString() {
    return "{\"loan_type\": \"" + getLoanType() +
        "\", \"sub_loan_type\": \"" + getSubLoanType() +
        "\", \"monto_min\": " + minAmount +
        ", \"monto_max\": " + maxAmount +
        ", \"cuota_min\": " + minPayment +
        ", \"cuota_max\": " + maxPayment +
        ", \"plazo_min\": " + minPaymentTerm +
        ", \"plazo_max\": " + maxPaymentTerm +
        ", \"tasa\": " + rate +
        ", \"comision\": " + commissionRate +
        ", \"frecuencia\": " + frequenciesToString() + "}";
  }
}
