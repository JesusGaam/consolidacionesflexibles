package com.kubofinanciero.dto;

/**
 * @param payment Contiene el valor cuota simulada por el cliente (la cuota puede ser semanal, catorcenal, quinceal o mensual)
 * @param paymentTerm Contiene el plazo simulado por el cliente (el plazo es expresado en semanal, catorcenal, quinceal o mensual)
 * @param frequency Contiene la frecuencia seleccionada en la simulacion por el cliente
 */
public class SimulatorOfferDto {
  private String loanType;
  private String subLoanType;
  private double minPayment;
  private double maxPayment;
  private double payment;
  private double minAmount;
  private double maxAmount;
  private int minPaymentTerm;
  private int maxPaymentTerm;
  private int paymentTerm;
  private double rate;
  private double commissionRate;
  private int segment;
  private char[] frequencies;
  private char frequency;

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

  public double getPayment() {
    return payment;
  }

  public int getMinPaymentTerm() {
    return minPaymentTerm;
  }

  public int getMaxPaymentTerm() {
    return maxPaymentTerm;
  }

  public int getPaymentTerm() {
    return paymentTerm;
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

  public char getFrequency() {
    return frequency;
  }

  public SimulatorOfferDto setLoanType(String loanType) {
    this.loanType = loanType;
    return this;
  }

  public SimulatorOfferDto setSubLoanType(String subLoanType) {
    this.subLoanType = subLoanType;
    return this;
  }

  public SimulatorOfferDto setMinAmount(double minAmount) {
    this.minAmount = minAmount;
    return this;
  }

  public SimulatorOfferDto setMaxAmount(double maxAmount) {
    this.maxAmount = maxAmount;
    return this;
  }

  public SimulatorOfferDto setMinPayment(double minPayment) {
    this.minPayment = minPayment;
    return this;
  }

  public SimulatorOfferDto setMaxPayment(double maxPayment) {
    this.maxPayment = maxPayment;
    return this;
  }

  public SimulatorOfferDto setPayment(double payment) {
    this.payment = payment;
    return this;
  }

  public SimulatorOfferDto setMinPaymentTerm(int minPaymentTerm) {
    this.minPaymentTerm = minPaymentTerm;
    return this;
  }

  public SimulatorOfferDto setMaxPaymentTerm(int maxPaymentTerm) {
    this.maxPaymentTerm = maxPaymentTerm;
    return this;
  }

  public SimulatorOfferDto setPaymentTerm(int paymentTerm) {
    this.paymentTerm = paymentTerm;
    return this;
  }

  public SimulatorOfferDto setRate(double rate) {
    this.rate = rate;
    return this;
  }

  public SimulatorOfferDto setCommissionRate(double commissionRate) {
    this.commissionRate = commissionRate;
    return this;
  }

  public SimulatorOfferDto setFrequencies(char[] frequencies) {
    this.frequencies = frequencies;
    return this;
  }

  public SimulatorOfferDto setFrequency(char frequency) {
    this.frequency = frequency;
    return this;
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
        ", \"payment\": " + payment +
        ", \"minPaymentTerm\": " + minPaymentTerm +
        ", \"maxPaymentTerm\": " + maxPaymentTerm +
        ", \"paymentTerm\": " + paymentTerm +
        ", \"rate\": " + rate +
        ", \"commissionRate\": " + commissionRate +
        ", \"frequencies\": " + frequenciesToString() +
        ", \"frequency\": \"" + frequency + "\"" +
        "}";
  }

  @Override
  public String toString() {
    return "{\"loan_type\": \"" + getLoanType() +
        "\", \"sub_loan_type\": \"" + getSubLoanType() +
        "\", \"monto_min\": " + minAmount +
        ", \"monto_max\": " + maxAmount +
        ", \"cuota_min\": " + minPayment +
        ", \"cuota_max\": " + maxPayment +
        ", \"cuota_defecto\": " + this.payment +
        ", \"plazo_min\": " + minPaymentTerm +
        ", \"plazo_max\": " + maxPaymentTerm +
        ", \"plazo_defecto\": " + this.paymentTerm +
        ", \"tasa\": " + rate +
        ", \"comision\": " + commissionRate +
        ", \"frecuencia\": " + frequenciesToString() +
        ", \"frecuencia_defecto\": \"" + this.frequency + "\"" +
        "}";
  }
}
