package com.kubofinanciero.dto;

import java.util.HashMap;

import com.kubofinanciero.utils.GenericUtilities;
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

  public static final char REVOLVER_FREQUENCY = 'M';

  private long registry;
  private String financialEntity;
  private double amountAwarded;
  private double payment;
  private double monthlyKuboPayment;
  private double balance;
  private double externalRate;
  private double kuboRate;
  private int awardedPaymentTerms;
  private int remainingPaymentTerms;
  private char externalFrequency;
  private double progress;
  private String startDate;
  private char typeDebt;
  private String typeDebtName;
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

    HashMap<Character, String> typeDebtList = new HashMap<Character, String>();
    typeDebtList.put('I', "Crédito personal");
    typeDebtList.put('R', "Tarjeta de crédito");
    typeDebtList.put('M', "Hipoteca");
    // O: Son creditos no tienen linea definida (p.ej: telefonia, internet, etc)
    typeDebtList.put('O', "Servicios");
    // X: Se refiere a creditos para personas morales o fisicas con Act. empresarial
    typeDebtList.put('X', "Crédito empresarial");

    typeDebtName = typeDebtList.get(typeDebt);
    if (typeDebtName == null) {
      typeDebtName = "No clasificado";
    }
  }

  public long getRegistry() {
    return registry;
  }

  public String getFinancialEntity() {
    if (financialEntity == null) {
      financialEntity = "";
    }
    return financialEntity;

  }

  public double getPayment() {
    return payment;
  }

  public double getMonthlyKuboPayment() {
    return monthlyKuboPayment;
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
    if (startDate == null) {
      startDate = "";
    }
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
    if (statusRate == null) {
      statusRate = "";
    }
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

  public String getTypeDebtName() {
    return typeDebtName;
  }

  public int getStatusDebt() {
    return statusDebt;
  }

  public boolean isSelected() {
    return isSelected;
  }

  private int getRevolverPaymentTerm() {
    return 12;
  }

  /*
   * Permite renombrar la entidad financiera, solo puede editarse para deudas
   * consolidables y editables.
   */
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

  /*
   * Permite cambiar la cuota de la deuda, solo puede editarse para deudas
   * consolidables y editables.
   */
  public DebtDto setPayment(double payment) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.payment != payment) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }

    this.payment = payment;
    validateStatusRate();
    calculateSavings();

    return this;
  }

  /*
   * Permite cambiar el saldo de la deuda revolvente, solo puede editarse para
   * deudas consolidables y editables.
   */
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

  /*
   * Permite cambiar el monto otorgado por la institucion financiera, solo puede
   * editarse para deudas consolidables y editables.
   */
  public DebtDto setAmountAwarded(double amountAwarded) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.amountAwarded != amountAwarded) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }

    this.amountAwarded = amountAwarded;
    validateStatusRate();
    calculateSavings();

    return this;
  }

  /*
   * Permite cambiar la tasa externa, solo puede editarse para deudas
   * consolidables y editables.
   * NOTA: Este cambio requiere subir documento comprobatorio
   */
  public DebtDto setExternalRate(double externalRate) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.externalRate != externalRate) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }

    this.externalRate = externalRate;
    validateStatusRate();
    calculateSavings();

    return this;
  }

  /*
   * Permite actualizar la tasa kubo asignada, solo puede editarse para deudas
   * consolidables y editables.
   * Se utiliza para calcular los ahorros.
   */
  public DebtDto setKuboRate(double kuboRate) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    this.kuboRate = kuboRate;
    calculateSavings();

    return this;
  }

  /*
   * Permite actualizar el plazo de la deuda externa, solo puede editarse para
   * deudas consolidables y editables.
   * 
   * NOTA: al actualizarse, se recalculan los ahorros y el progreso de la deuda.
   */
  public DebtDto setAwardedPaymentTerms(int awardedPaymentTerms) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.awardedPaymentTerms != awardedPaymentTerms) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }

    this.awardedPaymentTerms = awardedPaymentTerms;
    validateStatusRate();
    calculateSavings();
    calculateProgress();

    return this;
  }

  /*
   * Permite actualizar el plazo restante de la deuda externa, solo puede editarse
   * para deudas consolidables y editables.
   * 
   * NOTA: Al actualizarse, se recalculan los ahorros y el progreso de la deuda.
   */
  public DebtDto setRemainingPaymentTerms(int remainingPaymentTerms) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    if (this.remainingPaymentTerms != remainingPaymentTerms) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }

    this.remainingPaymentTerms = remainingPaymentTerms;
    validateStatusRate();
    calculateSavings();
    calculateProgress();

    return this;
  }

  /*
   * Permite actualizar la frecuencia externa, solo puede editarse
   * para deudas consolidables, editables y que no sean revolventes.
   * 
   * NOTAS:
   * - Al actualizarse, se recalculan los ahorros.
   * - Las deudas revolventes no manejan frecuencias de pago, por defecto se usa
   * la mensual para calculos de ahorros.
   */
  public DebtDto setExternalFrequency(char externalFrequency) {
    if (!this.consolidatedDebt || !editableDept() || typeDebt == 'R') {
      return this;
    }

    if (this.externalFrequency != externalFrequency) {
      this.statusDebt = STATUS_UPDATED_DEBT;
    }

    this.externalFrequency = externalFrequency;
    validateStatusRate();
    calculateSavings();

    return this;
  }

  /*
   * Permite actualizar el estatus de la deuda, solo puede editarse
   * para deudas consolidables y editables.
   */
  public DebtDto setStatusDebt(int statusDebt) {
    if (!this.consolidatedDebt || !editableDept()) {
      return this;
    }

    this.statusDebt = statusDebt;
    return this;
  }

  /*
   * Permite seleccionar o quitar selección la deuda siempre y cuando sean
   * consolidables, editables y con tasas calculables.
   */
  public DebtDto setSelected(boolean isSelected) {

    if (canBeSelected() && editableDept()) {
      this.isSelected = isSelected;
    }
    return this;
  }

  public boolean canBeSelected() {
    return this.consolidatedDebt && statusRate.equals("Calculable");
  }

  /*
   * FUNCION DEPRECIADA para validar si la deuda es consolidable
   */
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

  /**
   * Calcula los ahorros para deudas de pagos fijos
   */
  public void calculateSavings() {

    if (!consolidatedDebt || !statusRate.equals("Calculable")) {
      return;
    }

    LoanSimulator ls = new LoanSimulator();
    double amount = getAmountAwarded();
    int paymentTerms = getAwardedPaymentTerms();
    int remainingPaymentTerms = getRemainingPaymentTerms();
    char externalFrequency = getExternalFrequency();
    double payment = getPayment();

    if (this.typeDebt == 'R') {
      amount = getBalance();
      paymentTerms = getRevolverPaymentTerm();
      remainingPaymentTerms = getRevolverPaymentTerm();
      externalFrequency = REVOLVER_FREQUENCY;

      double rate = ls.rateFrequency(getExternalRate(), externalFrequency, true);
      payment = ls.payment(amount, paymentTerms, rate);
    }

    double rate = ls.rateFrequency(kuboRate, externalFrequency, true);
    double kuboPayment = ls.payment(amount, paymentTerms, rate);
    double saving = payment > kuboPayment ? payment - kuboPayment : 0;

    this.monthlyKuboPayment = convertToMonthlyPayment(kuboPayment);
    this.monthlySaving = convertToMonthlyPayment(saving);
    this.totalSaving = saving * paymentTerms;
    this.remainingTotalSavings = saving * remainingPaymentTerms;
  }

  private double convertToMonthlyPayment(double saving) {
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

  /**
   * Calcula el avance de la deuda externa, toma en cuenta el plazo total y el
   * numero de pagos realizados
   */
  public void calculateProgress() {
    if (remainingPaymentTerms < 0 || awardedPaymentTerms <= 0 || remainingPaymentTerms > awardedPaymentTerms) {
      this.progress = 0;
      return;
    }

    this.progress = remainingPaymentTerms / awardedPaymentTerms;
  }

  public boolean editableDept() {
    if (this.financialEntity.equals("KUBO FINANCIERO")) {
      return false;
    }

    return true;
  }

  private void validateStatusRate() {
    double amount = getAmountAwarded();
    double paymentTerm = getAwardedPaymentTerms();

    if (this.typeDebt == 'R') {
      amount = getBalance();
      paymentTerm = getRevolverPaymentTerm();
    }

    if (externalRate > 0 && payment > 0 && amount > 0 && paymentTerm > 0) {
      this.statusRate = "Calculable";
    }
  }

  public String toJSONFormattedString() {

    return "{\"registry\":" + registry +
        ", \"financialEntity\":\"" + financialEntity +
        "\", \"amountAwarded\": \"" + GenericUtilities.toCurrencyFormat(amountAwarded) +
        "\", \"payment\": \"" + GenericUtilities.toCurrencyFormat(payment) +
        "\", \"monthlyKuboPayment\": \"" + GenericUtilities.toCurrencyFormat(monthlyKuboPayment) +
        "\", \"balance\": \"" + GenericUtilities.toCurrencyFormat(balance) +
        "\", \"externalRate\": \"" + GenericUtilities.round(externalRate * 100) + "%" +
        "\", \"kuboRate\": \"" + GenericUtilities.round(kuboRate * 100) + "%" +
        "\", \"awardedPaymentTerms\":" + awardedPaymentTerms +
        ", \"remainingPaymentTerms\":" + remainingPaymentTerms +
        ", \"externalFrequency\":\"" + externalFrequency +
        "\", \"progress\":" + progress +
        ", \"startDate\":\"" + startDate +
        "\", \"typeDebt\": \"" + typeDebt +
        "\", \"typeDebtName\": \"" + typeDebtName +
        "\", \"monthlySaving\": \"" + GenericUtilities.toCurrencyFormat(monthlySaving) +
        "\", \"totalSaving\": \"" + GenericUtilities.toCurrencyFormat(totalSaving) +
        "\", \"statusRate\": \"" + statusRate +
        "\", \"revolverType\": \"" + revolverType +
        "\", \"consolidatedDebt\": " + (consolidatedDebt ? 1 : 0) +
        ", \"remainingTotalSavings\": \"" + GenericUtilities.toCurrencyFormat(remainingTotalSavings) +
        "\", \"statusDebt\":" + statusDebt +
        ", \"isSelected\":" + isSelected +
        ", \"canBeSelected\":" + canBeSelected() +
        "}";
  }

  public String toJSONString() {

    return "{\"registry\":" + registry +
        ", \"financialEntity\":\"" + financialEntity +
        "\", \"amountAwarded\":" + GenericUtilities.round(amountAwarded) +
        ", \"payment\":" + GenericUtilities.round(payment) +
        ", \"monthlyKuboPayment\":" + GenericUtilities.round(monthlyKuboPayment) +
        ", \"balance\":" + GenericUtilities.round(balance) +
        ", \"externalRate\":" + externalRate +
        ", \"kuboRate\":" + kuboRate +
        ", \"awardedPaymentTerms\":" + awardedPaymentTerms +
        ", \"remainingPaymentTerms\":" + remainingPaymentTerms +
        ", \"externalFrequency\":\"" + externalFrequency +
        "\", \"progress\":" + progress +
        ", \"startDate\":\"" + startDate +
        "\", \"typeDebt\": \"" + typeDebt +
        "\", \"typeDebtName\": \"" + typeDebtName +
        "\", \"monthlySaving\":" + GenericUtilities.round(monthlySaving) +
        ", \"totalSaving\":" + GenericUtilities.round(totalSaving) +
        ", \"statusRate\":\"" + statusRate +
        "\", \"revolverType\":\"" + revolverType +
        "\", \"consolidatedDebt\": " + (consolidatedDebt ? 1 : 0) +
        ", \"remainingTotalSavings\":" + GenericUtilities.round(remainingTotalSavings) +
        ", \"statusDebt\":" + statusDebt +
        ", \"isSelected\":" + isSelected +
        ", \"canBeSelected\":" + canBeSelected() +
        "}";
  }

  @Override
  public String toString() {

    return "{\"registro\":" + registry +
        ", \"entidad\":\"" + financialEntity +
        "\", \"monto_otorgado\":" + amountAwarded +
        ", \"cuota\":" + payment +
        ", \"cuota_mensual_kubo\":" + monthlyKuboPayment +
        ", \"saldo\":" + balance +
        ", \"tasa_externa\":" + externalRate +
        ", \"tasa_kubo\":" + kuboRate +
        ", \"numero_pagos_otorgado\":" + awardedPaymentTerms +
        ", \"numero_pagos_restante\":" + remainingPaymentTerms +
        ", \"frecuencia_externa\":\"" + externalFrequency +
        "\", \"avance\":" + progress +
        ", \"fecha_inicio\":\"" + startDate +
        "\", \"tipo_deuda\": \"" + typeDebt +
        "\", \"nombre_tipo_deuda\": \"" + typeDebtName +
        "\", \"ahorro_cuota_mensual\":" + monthlySaving +
        ", \"ahorro_total\":" + totalSaving +
        ", \"estatus_tasa\":\"" + statusRate +
        "\", \"tipo_revolvencia\":\"" + revolverType +
        "\", \"deuda_consolidable\": " + (consolidatedDebt ? 1 : 0) +
        ", \"ahorro_total_restante\":" + remainingTotalSavings +
        ", \"estatus_deuda\":" + statusDebt +
        ", \"seleccionado\":" + isSelected +
        ", \"puede_seleccionarse\":" + canBeSelected() +
        "}";
  }

}
