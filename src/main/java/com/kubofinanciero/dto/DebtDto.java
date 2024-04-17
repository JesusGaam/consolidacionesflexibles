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
  private boolean isUploadedDocuments;

  private int minPaymentTerm;
  private int maxPaymentTerm;

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
      boolean isSelected,
      boolean isUploadedDocuments) {

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
    this.isUploadedDocuments = isUploadedDocuments;

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
    if (this.getTypeDebt() == 'R') {
      return REVOLVER_FREQUENCY;
    }

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

  public boolean isUploadedDocuments() {
    return isUploadedDocuments;
  }

  public int getMinPaymentTerm() {
    return minPaymentTerm;
  }

  public int getMaxPaymentTerm() {
    return maxPaymentTerm;
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

      this.payment = payment;
      validateStatusRate();
      calculateSavings();
    }

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

      this.balance = balance;
      validateStatusRate();
      calculateSavings();
    }

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
      this.amountAwarded = amountAwarded;
      validateStatusRate();
      calculateSavings();
    }

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

      this.externalRate = externalRate;
      validateStatusRate();
      calculateSavings();
    }

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

    if (this.kuboRate != kuboRate) {
      this.kuboRate = kuboRate;
      calculateSavings();
    }

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

      this.awardedPaymentTerms = awardedPaymentTerms;
      validateStatusRate();
      calculateSavings();
      calculateProgress();
    }

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

      this.remainingPaymentTerms = remainingPaymentTerms;
      validateStatusRate();
      calculateSavings();
      calculateProgress();
    }

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
      this.externalFrequency = externalFrequency;
      validateStatusRate();
      calculateSavings();
    }

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

  public DebtDto setUploadedDocuments(boolean isUploadedDocuments) {
    this.isUploadedDocuments = isUploadedDocuments;
    return this;
  }

  public DebtDto setMinPaymentTerm(int minPaymentTerm) {
    this.minPaymentTerm = minPaymentTerm;
    return this;
  }

  public DebtDto setMaxPaymentTerm(int maxPaymentTerm) {
    this.maxPaymentTerm = maxPaymentTerm;
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

    double amount = getAmountAwarded();
    if (this.typeDebt == 'R') {
      amount = getBalance();
      calculateRevolverPaymentTerms();
    }

    if (getExternalFrequency() == 'V' || getExternalFrequency() == 'P' || amount <= 0) {
      this.monthlyKuboPayment = 0;
      this.monthlySaving = 0;
      this.totalSaving = 0;
      this.remainingTotalSavings = 0;

      return;
    }

    double rate = LoanSimulator.rateFrequency(kuboRate, getExternalFrequency(), true);
    double kuboPayment = LoanSimulator.payment(amount, getAwardedPaymentTerms(), rate);
    double saving = getPayment() > kuboPayment ? getPayment() - kuboPayment : 0;

    // System.out.println("=================");
    // System.out.println("Registro: " + registry + ", Monto deuda:" + amount);
    // System.out.println("Tasa externa: " + externalRate + ", Tasa kubo: " +
    // kuboRate + ", Plazo: "
    // + getAwardedPaymentTerms() + ", Frecuencia: " + getExternalFrequency());
    // System.out.println("Pago externo: " + getPayment() + ", Pago kubo:" +
    // kuboPayment + ", Ahorro mensual: " + saving);
    // System.out.println("Ahorro tota: " + saving * getAwardedPaymentTerms());
    // System.out.println("=================");

    this.monthlyKuboPayment = convertToMonthlyPayment(kuboPayment);
    this.monthlySaving = convertToMonthlyPayment(saving);
    this.totalSaving = saving * getAwardedPaymentTerms();
    this.remainingTotalSavings = saving * getRemainingPaymentTerms();
  }

  private void calculateRevolverPaymentTerms() {

    if (getBalance() <= 0 || getPayment() <= 0 || getExternalRate() <= 0) {
      return;
    }

    double payment = 0;
    double rate = LoanSimulator.rateFrequency(getExternalRate(), getExternalFrequency(), true);
    int paymentTerms = LoanSimulator.totalPayments(getBalance(), getPayment(), rate);

    if (paymentTerms > getMaxPaymentTerm()) {
      paymentTerms = getMaxPaymentTerm();
    } else if (paymentTerms < getMinPaymentTerm()) {
      paymentTerms = getMinPaymentTerm();
    }
    payment = LoanSimulator.payment(getBalance(), paymentTerms, rate);

    this.awardedPaymentTerms = paymentTerms;
    this.remainingPaymentTerms = paymentTerms;
    this.payment = payment;

  }

  private double convertToMonthlyPayment(double saving) {
    switch (this.externalFrequency) {
      case 'W':
        return saving * 4;
      case 'K':
        return (saving / 14) * 30;
      case 'S':
        return saving * 2;
      case 'Y':
        return saving / 12;
      case 'H':
        return saving / 6;
      case 'Q':
        return saving / 3;
      case 'B':
        return saving / 2;
      case 'D':
        return saving * 30;
      case 'V':
      case 'P':
        return 0;
      default:
        return saving;
    }
  }

  public int getMonthlyPaymentTerm() {

    switch (this.externalFrequency) {
      case 'W':
        return (int) Math.ceil(getAwardedPaymentTerms() / 4);
      case 'K':
      case 'S':
        return (int) Math.ceil(getAwardedPaymentTerms() / 2);
      case 'Y':
        return getAwardedPaymentTerms() * 12;
      case 'H':
        return getAwardedPaymentTerms() * 6;
      case 'Q':
        return getAwardedPaymentTerms() * 3;
      case 'B':
        return getAwardedPaymentTerms() * 2;
      case 'D':
        return (int) Math.ceil(getAwardedPaymentTerms() / 30);
      case 'V':
      case 'P':
        return 0;
      default:
        return getAwardedPaymentTerms();
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

    this.progress = (double) remainingPaymentTerms / awardedPaymentTerms;
  }

  public boolean editableDept() {
    if (this.financialEntity.equals("KUBO FINANCIERO")) {
      return false;
    }

    return true;
  }

  private void validateStatusRate() {
    double amount = getAmountAwarded();
    boolean payment = this.typeDebt == 'I' ? this.payment > 0 : true;

    if (this.typeDebt == 'R') {
      amount = getBalance();
    }

    if (externalRate > 0 && amount > 0 && (this.typeDebt == 'R' && payment)) {
      this.statusRate = "Calculable";
    }
  }

  public String toJSONString() {
    boolean showRevolverSaving = true;

    if (getTypeDebt() == 'R') {
      showRevolverSaving = canBeSelected();
    }

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
        "\", \"monthlySaving\":" + (showRevolverSaving ? GenericUtilities.round(monthlySaving) : -1) +
        ", \"totalSaving\":" + (showRevolverSaving ? GenericUtilities.round(totalSaving) : -1) +
        ", \"statusRate\":\"" + statusRate +
        "\", \"revolverType\":\"" + revolverType +
        "\", \"consolidatedDebt\": " + (consolidatedDebt ? 1 : 0) +
        ", \"remainingTotalSavings\":" + GenericUtilities.round(remainingTotalSavings) +
        ", \"statusDebt\":" + statusDebt +
        ", \"isSelected\":" + isSelected +
        ", \"isUploadedDocuments\":" + isUploadedDocuments +
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
        ", \"documento_subido\":" + isUploadedDocuments +
        ", \"puede_seleccionarse\":" + canBeSelected() +
        "}";
  }

}
