package com.kubofinanciero.dto;

import com.kubofinanciero.utils.LoanSimulator;

public class DebtDto {

  /**
   * @STATUS_ORIGINAL_DEBT Estatus por defecto
   * @STATUS_INFO_UPDATED Se actualiza información de la deuda, pero no modifica
   *                      la oferta
   * @STATUS_UPDATED_DEBT Se actualizan parametros de la deuda donde se modifica
   *                      la oferta (tasa, monto, plazo)
   */

  // DEBT_CONDITION_STATUS
  public static final int STATUS_ORIGINAL_DEBT = 0;
  public static final int STATUS_INFO_UPDATED = 1;
  public static final int STATUS_UPDATED_DEBT = 2;

  public static final String REVOLVER_TRANSACTOR_TYPE = "Transactor";
  public static final String REVOLVER_LIGH_TYPE = "Ligh Revolver";
  public static final String REVOLVER_HIGH_TYPE = "High Revolver";

  private long registry;
  private String financialEntity;
  private double amountAwarded;
  private double payment;
  private double balance;
  private double externalRate;
  private double kuboRate;
  private int awardedPaymentTerms;
  private int remainingPaymentTerms;
  private char externalFrequency;
  private double progress;
  private String startDate;
  private char typeDebt;
  private double monthlySaving;
  private double totalSaving;
  private String statusRate;
  private String revolverType;
  private boolean consolidatedDebt;
  private double remainingTotalSavings;
  private int statusDebt;
  private boolean isSelected;

  public DebtDto(
      long registry,
      String financialEntity,
      double amountAwarded,
      double payment,
      double balance,
      double externalRate,
      double kuboRate,
      int awardedPaymentTerms,
      int remainingPaymentTerms,
      char externalFrequency,
      double progress,
      String startDate,
      char typeDebt,
      double monthlySaving,
      double totalSaving,
      String statusRate,
      String revolverType,
      boolean consolidatedDebt,
      double remainingTotalSavings,
      int statusDebt,
      boolean isSelected) {

    this.registry = registry;
    this.financialEntity = financialEntity;
    this.payment = payment;
    this.balance = balance;
    this.amountAwarded = amountAwarded;
    this.externalRate = externalRate;
    this.kuboRate = kuboRate;
    this.awardedPaymentTerms = awardedPaymentTerms;
    this.remainingPaymentTerms = remainingPaymentTerms;
    this.externalFrequency = externalFrequency;
    this.progress = progress;
    this.startDate = startDate;
    this.monthlySaving = monthlySaving;
    this.totalSaving = totalSaving;
    this.remainingTotalSavings = remainingTotalSavings;
    this.statusRate = statusRate;
    this.revolverType = revolverType;
    this.typeDebt = typeDebt;
    this.consolidatedDebt = consolidatedDebt;
    this.statusDebt = statusDebt;
    this.isSelected = isSelected;
  }

  public long getRegistry() {
    return registry;
  }

  public String getFinancialEntity() {
    return financialEntity;
  }

  public double getPayment() {
    return payment;
  }

  public double getBalance() {
    return balance;
  }

  public double getAmountAwarded() {
    return amountAwarded;
  }

  public double getExternalRate() {
    return externalRate;
  }

  public double getKuboRate() {
    return kuboRate;
  }

  public int getAwardedPaymentTerms() {
    return awardedPaymentTerms;
  }

  public int getRemainingPaymentTerms() {
    return remainingPaymentTerms;
  }

  public char getExternalFrequency() {
    return externalFrequency;
  }

  public double getProgress() {
    return progress;
  }

  public String getStartDate() {
    return startDate;
  }

  public double getMonthlySaving() {
    return monthlySaving;
  }

  public double getTotalSaving() {
    return totalSaving;
  }

  public double getRemainingTotalSavings() {
    return remainingTotalSavings;
  }

  public String getStatusRate() {
    return statusRate;
  }

  public String getRevolverType() {
    return revolverType;
  }

  public boolean getConsolidatedDebt() {
    return consolidatedDebt;
  }

  public char getTypeDebt() {
    return typeDebt;
  }

  public int getStatusDebt() {
    return statusDebt;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public DebtDto setFinancialEntity(String financialEntity) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.financialEntity != financialEntity && statusDebt != STATUS_UPDATED_DEBT) {
      this.statusDebt = STATUS_INFO_UPDATED;
    }
    this.financialEntity = financialEntity;
    return this;
  }

  public DebtDto setPayment(double payment) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.payment != payment) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }
    this.payment = payment;
    calculateSavings();
    return this;
  }

  public DebtDto setBalance(double balance) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.balance != balance) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }
    this.balance = balance;
    return this;
  }

  public DebtDto setAmountAwarded(double amountAwarded) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.amountAwarded != amountAwarded) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }
    this.amountAwarded = amountAwarded;
    calculateSavings();
    return this;
  }

  public DebtDto setExternalRate(double externalRate) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.externalRate != externalRate) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }
    this.externalRate = externalRate;
    calculateSavings();
    return this;
  }

  public DebtDto setKuboRate(double kuboRate) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    this.kuboRate = kuboRate;
    calculateSavings();
    return this;
  }

  public DebtDto setAwardedPaymentTerms(int awardedPaymentTerms) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.awardedPaymentTerms != awardedPaymentTerms) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }
    this.awardedPaymentTerms = awardedPaymentTerms;
    calculateSavings();
    calculateProgress();
    return this;
  }

  public DebtDto setRemainingPaymentTerms(int remainingPaymentTerms) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    this.remainingPaymentTerms = remainingPaymentTerms;
    calculateSavings();
    calculateProgress();
    return this;
  }

  public DebtDto setExternalFrequency(char externalFrequency) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.externalFrequency != externalFrequency) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }
    this.externalFrequency = externalFrequency;
    calculateSavings();
    return this;
  }

  public DebtDto setStatusDebt(int statusDebt) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    this.statusDebt = statusDebt;
    return this;
  }

  public DebtDto setSelected(boolean isSelected) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }
    if (this.consolidatedDebt) {
      this.isSelected = isSelected;
    }
    return this;
  }

  public void validateConsolidatedDebt() {
    if (typeDebt != 'R' && typeDebt != 'I') {
      consolidatedDebt = false;
      return;
    }
    if (financialEntity.equals("KUBO FINANCIERO")) {
      consolidatedDebt = false;
      return;
    }

    if (typeDebt == 'R') {
      consolidatedDebt = true;
      return;
    }

    // ACA EN ADELANTE SOLO ENTRAN LAS DEUDAS A PAGOS FIJOS
    if (statusRate.equals("Calculable")) {
      consolidatedDebt = true;
      return;
    }

    if (amountAwarded > 0 && externalRate > 0) {
      consolidatedDebt = true;
      return;
    }

    consolidatedDebt = false;
  }

  public void calculateSavings() {
    LoanSimulator ls = new LoanSimulator();

    if (!consolidatedDebt || this.typeDebt != 'I') {
      return;
    }

    double rate = ls.rateFrequency(kuboRate, externalFrequency, true);
    double kuboPayment = ls.payment(amountAwarded, awardedPaymentTerms, rate);
    double saving = payment > kuboPayment ? payment - kuboPayment : 0;

    this.monthlySaving = convertToMonthlySaving(saving);
    this.totalSaving = saving * awardedPaymentTerms;
    this.remainingTotalSavings = saving * remainingPaymentTerms;
  }

  private double convertToMonthlySaving(double saving) {
    switch (this.externalFrequency) {
      case 'W':
        return saving * 4;
      case 'K':
        return (saving / 14) * 30;
      case 'S':
        return saving * 2;
      default:
        return saving;
    }
  }

  public void calculateProgress() {
    this.progress = remainingPaymentTerms / awardedPaymentTerms;
  }

  public boolean editableDept() {
    if (this.financialEntity.equals("KUBO FINANCIERO")) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {

    return "{\"registro\":" + registry + ", \"entidad\":\"" + financialEntity
        + "\", \"cuota\":" + payment + ", \"saldo\":" + balance + ", \"monto_otorgado\":" + amountAwarded
        + ", \"tasa_externa\":" + externalRate + ", \"tasa_kubo\":" + kuboRate + ", \"numero_pagos_otorgado\":"
        + awardedPaymentTerms + ", \"numero_pagos_restante\":" + remainingPaymentTerms + ", \"frecuencia_externa\":\""
        + externalFrequency + "\", \"avance\":" + progress + ", \"fecha_inicio\":\"" + startDate
        + "\", \"ahorro_cuota_mensual\":" + monthlySaving + ", \"ahorro_total\":" + totalSaving
        + ", \"ahorro_total_restante\":" + remainingTotalSavings + ", \"tipo_deuda\": \"" + typeDebt
        + "\", \"deuda_consolidable\": " + (consolidatedDebt ? 1 : 0) + ", \"estatus_deuda\":" + statusDebt
        + ", \"estatus_tasa\":\"" + statusRate + "\", \"tipo_revolvencia\":\"" + revolverType + "\", \"seleccionado\":"
        + isSelected + "}";
  }

}
