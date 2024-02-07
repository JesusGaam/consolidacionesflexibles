package com.kubofinanciero.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsolidationOfferDto {
  /**
   * INPUTS MODELO DE RIESGOS
   */
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
  private double proba;
  private int segment;
  private char[] frequencies;
  private double[] assistedRates;
  private double[] commissionRateList;
  private List<DebtDto> buroDebts;
  private long prospectusId;
  private long bursolnum;
  private double maxClientAmount;

  public ConsolidationOfferDto() {

  }

  public ConsolidationOfferDto(
      String loanType,
      String subLoanType,
      double minPayment,
      double maxPayment,
      double minAmount,
      double maxAmount,
      int minPaymentTerm,
      int maxPaymentTerm,
      double rate,
      double commissionRate,
      double proba,
      int segment,
      char[] frequencies,
      double[] assistedRates,
      double[] commissionRateList,
      List<DebtDto> buroDebts,
      long prospectusId,
      long bursolnum,
      double maxClientAmount) {
    this.loanType = loanType;
    this.subLoanType = subLoanType;
    this.minPayment = minPayment;
    this.maxPayment = maxPayment;
    this.minAmount = minAmount;
    this.maxAmount = maxAmount;
    this.minPaymentTerm = minPaymentTerm;
    this.maxPaymentTerm = maxPaymentTerm;
    this.rate = rate;
    this.commissionRate = commissionRate;
    this.proba = proba;
    this.segment = segment;
    this.frequencies = frequencies;
    this.assistedRates = assistedRates;
    this.commissionRateList = commissionRateList;
    this.buroDebts = buroDebts;
    this.prospectusId = prospectusId;
    this.bursolnum = bursolnum;
    this.maxClientAmount = maxClientAmount;
  }

  public String getLoanType() {
    return loanType;
  }

  public String getSubLoanType() {
    return subLoanType;
  }

  public double getMinPayment() {
    return minPayment;
  }

  public double getMaxPayment() {
    return maxPayment;
  }

  public double getMinAmount() {
    return minAmount;
  }

  public double getMaxAmount() {
    return maxAmount;
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

  public double getProba() {
    return proba;
  }

  public int getSegment() {
    return segment;
  }

  public char[] getFrequencies() {
    return frequencies;
  }

  public double[] getAssistedRates() {
    if (assistedRates == null) {
      assistedRates = new double[] {};
    }
    return assistedRates;
  }

  public double[] getCommissionRateList() {
    if (commissionRateList == null) {
      commissionRateList = new double[] {};
    }
    return commissionRateList;
  }

  public List<DebtDto> getBuroDebts() {
    if (buroDebts == null) {
      buroDebts = new ArrayList<DebtDto>();
    }
    return buroDebts;
  }

  public long getProspectusId() {
    return prospectusId;
  }

  public long getBursolnum() {
    return bursolnum;
  }

  public double getMaxClientAmount() {
    return maxClientAmount;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public ConsolidationOfferDto setSubLoanType(String subLoanType) {
    this.subLoanType = subLoanType;
    return this;
  }

  public ConsolidationOfferDto setMinPayment(double minPayment) {
    this.minPayment = minPayment;
    return this;
  }

  public ConsolidationOfferDto setMaxPayment(double maxPayment) {
    this.maxPayment = maxPayment;
    return this;
  }

  public ConsolidationOfferDto setMinAmount(double minAmount) {
    this.minAmount = minAmount;
    return this;
  }

  public ConsolidationOfferDto setMaxAmount(double maxAmount) {
    this.maxAmount = maxAmount;
    return this;
  }

  public ConsolidationOfferDto setMinPaymentTerm(int minPaymentTerm) {
    this.minPaymentTerm = minPaymentTerm;
    return this;
  }

  public ConsolidationOfferDto setMaxPaymentTerm(int maxPaymentTerm) {
    this.maxPaymentTerm = maxPaymentTerm;
    return this;
  }

  public ConsolidationOfferDto setRate(double rate) {
    this.rate = rate;
    return this;
  }

  public ConsolidationOfferDto setCommissionRate(double commissionRate) {
    this.commissionRate = commissionRate;
    return this;
  }

  public ConsolidationOfferDto setProba(double proba) {
    this.proba = proba;
    return this;
  }

  public ConsolidationOfferDto setSegment(int segment) {
    this.segment = segment;
    return this;
  }

  public ConsolidationOfferDto setFrequencies(char[] frequencies) {
    this.frequencies = frequencies;
    return this;
  }

  public ConsolidationOfferDto setAssistedRates(double[] assistedRates) {
    this.assistedRates = assistedRates;
    return this;
  }

  public ConsolidationOfferDto setCommissionRateList(double[] commissionRateList) {
    this.commissionRateList = commissionRateList;
    return this;
  }

  public ConsolidationOfferDto setBuroDebts(List<DebtDto> buroDebts) {
    this.buroDebts = buroDebts;
    return this;
  }

  public ConsolidationOfferDto setProspectusId(long prospectusId) {
    this.prospectusId = prospectusId;
    return this;
  }

  public ConsolidationOfferDto setBursolnum(long bursolnum) {
    this.bursolnum = bursolnum;
    return this;
  }

  public ConsolidationOfferDto setMaxClientAmount(double maxClientAmount) {
    this.maxClientAmount = maxClientAmount;
    return this;
  }

  public void addDept(DebtDto debt) {
    if (debt != null) {
      getBuroDebts().add(debt);
    }
  }

  @Override
  public String toString() {

    String frequencies = "";
    for (int i = 0; i < this.frequencies.length; i++) {
      frequencies += "\"" + this.frequencies[i] + "\"";
      if (i < this.frequencies.length - 1) {
        frequencies += ",";
      }
    }
    frequencies = "[" + frequencies + "]";

    return "{ \"Prospecto\":" + prospectusId + ", \"monto_maximo_cliente\":" + maxClientAmount + ", \"bursolnum\":"
        + bursolnum + ", \"Oferta Automatico\":[" + "{\"loan_type\": \"" + loanType + "\", \"sub_loan_type\": \""
        + subLoanType + "\", \"cuota_min\":" + minPayment + ", \"cuota_max\":" + maxPayment + ", \"monto_min\":"
        + minAmount + ", \"monto_max\":" + maxAmount + ", \"plazo_min\":" + minPaymentTerm + ",\"plazo_max\":"
        + maxPaymentTerm + ", \"tasa\":" + rate + ", \"comision\":" + commissionRate + ", \"proba\":" + proba
        + ", \"segmento\":" + segment + ", \"frecuencia\":" + frequencies + ", \"tasa_asistida\":"
        + Arrays.toString(assistedRates) + ", \"comisiones\": " + Arrays.toString(commissionRateList)
        + ", \"deudas_buro\":" + buroDebts + " }]}";
  }

}
