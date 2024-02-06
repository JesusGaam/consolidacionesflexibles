package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.dto.DebtDto;
import com.kubofinanciero.dto.SimulatorOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class FlexibleConsolidations {

  public static final int STATUS_ORIGINAL_OFFER = 0;
  public static final int STATUS_RATE_AUTOMATICALLY_CALCULATED = 1;
  public static final int STATUS_RATE_MODIFIED_BY_ADVISOR = 2;
  public static final int STATUS_EXCEEDED_AMOUNT = 3;

  private double offerAmount;
  private double offerRate;
  private double offerComission;
  private int offerStatus;
  private double wightedRate;
  private double totalSaving;
  private double monthlySavings;
  private double monthlyExternalPayment;
  private int totalDiagnosableDebts;
  private int totalUndiagnosableDebts;
  private int totalSelectedDebts;
  private double maxDebtsRate;

  private SimulatorOfferDto simulatorOffer;
  private ConsolidationOfferDto consolidationOffer;

  public FlexibleConsolidations(ConsolidationOfferDto consolidationOffer) {
    setConsolidationOffer(consolidationOffer);
    setSimulatorOffer(this.consolidationOffer);
  }

  public FlexibleConsolidations(
      double offerAmount,
      double offerRate,
      double offerComission,
      int offerStatus,
      double wightedRate,
      double totalSaving,
      double monthlySavings,
      double monthlyExternalPayment,
      int totalDiagnosableDebts,
      int totalUndiagnosableDebts,
      int totalSelectedDebts,
      SimulatorOfferDto simulatorOffer,
      ConsolidationOfferDto consolidationOffer) {

    this.offerAmount = offerAmount;
    this.offerRate = offerRate;
    this.offerComission = offerComission;
    this.offerStatus = offerStatus;
    this.wightedRate = wightedRate;
    this.totalSaving = totalSaving;
    this.monthlySavings = monthlySavings;
    this.monthlyExternalPayment = monthlyExternalPayment;
    this.totalDiagnosableDebts = totalDiagnosableDebts;
    this.totalUndiagnosableDebts = totalUndiagnosableDebts;
    this.totalSelectedDebts = totalSelectedDebts;
    this.simulatorOffer = simulatorOffer;
    this.consolidationOffer = consolidationOffer;
  }

  public double getOfferAmount() {
    return offerAmount;
  }

  public double getOfferRate() {
    return offerRate;
  }

  public double getOfferComission() {
    return offerComission;
  }

  public int getOfferStatus() {
    return offerStatus;
  }

  public double getWightedRate() {
    return wightedRate;
  }

  public double getTotalSaving() {
    return totalSaving;
  }

  public double getMonthlySavings() {
    return monthlySavings;
  }

  public double getMonthlyExternalPayment() {
    return monthlyExternalPayment;
  }

  public double getTotalDiagnosableDebts() {
    return totalDiagnosableDebts;
  }

  public double getTotalUndiagnosableDebts() {
    return totalUndiagnosableDebts;
  }

  public double getTotalSelectedDebts() {
    return totalSelectedDebts;
  }

  public double getMaxDebtsRate() {
    return maxDebtsRate;
  }

  public SimulatorOfferDto getSimulatorOffer() {
    if (simulatorOffer == null) {
      simulatorOffer = new SimulatorOfferDto();
    }

    return simulatorOffer;
  }

  public ConsolidationOfferDto getConsolidationOffer() {
    if (consolidationOffer == null) {
      consolidationOffer = new ConsolidationOfferDto();
    }

    return consolidationOffer;
  }

  // public void setOfferAmount(double offerAmount) {
  // this.offerAmount = offerAmount;
  // }

  public void setOfferRate(double offerRate) {
    double[] rates = consolidationOffer.getAssistedRates();
    double[] comissions = consolidationOffer.getCommissionRateList();

    for (int i = 0; i < rates.length; i++) {
      if (offerRate == rates[i]) {
        this.offerRate = rates[i];
        this.offerComission = comissions[i];
        this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
        break;
      }
    }
  }

  public void setOfferComission(double offerComission) {
    double[] rates = consolidationOffer.getAssistedRates();
    double[] comissions = consolidationOffer.getCommissionRateList();

    for (int i = 0; i < comissions.length; i++) {
      if (offerComission == comissions[i]) {
        this.offerRate = rates[i];
        this.offerComission = comissions[i];
        this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
        break;
      }
    }
  }

  public void setOfferStatus(int offerStatus) {
    this.offerStatus = offerStatus;
  }

  public void setSimulatorOffer(SimulatorOfferDto simulatorOffer) {
    if (simulatorOffer != null) {
      this.simulatorOffer = simulatorOffer;
    }
  }

  public void setSimulatorOffer(ConsolidationOfferDto consolidationOffer) {
    if (consolidationOffer != null) {
      this.simulatorOffer = new SimulatorOfferDto(
          consolidationOffer.getLoanType(),
          consolidationOffer.getSubLoanType(),
          consolidationOffer.getMinPayment(),
          consolidationOffer.getMaxPayment(),
          consolidationOffer.getMinPaymentTerm(),
          consolidationOffer.getMaxPaymentTerm(),
          consolidationOffer.getFrequencies());
    }
  }

  public void setConsolidationOffer(ConsolidationOfferDto consolidationOffer) {
    if (consolidationOffer != null) {
      this.consolidationOffer = consolidationOffer;
    }
  }

  private void initOffer() {
    updateConsolidatedDebts();
    updateOffer();
  }

  private void updateOffer() {
    updateBuroDebtsStatistics();
    calculateOfferAmount();
    calculateWightedRate();
    calculateOfferRate(false);
    calculateSaving();
    calculateMaxDebtsRate();

    simulatorOffer.setMinAmount(this.offerAmount);
    simulatorOffer.setMaxAmount(this.offerAmount);
    simulatorOffer.setRate(this.offerRate);
    simulatorOffer.setCommissionRate(this.offerComission);
  }

  /**
   * Solo se utiliza para definir la oferta inicial de Data.
   * Cuando regresa nuevamente ya no es necesario invocarla.
   */
  public void updateConsolidatedDebts() {
    for (DebtDto debt : consolidationOffer.getBuroDebts()) {
      debt.validateConsolidatedDebt();

      if (debt.getConsolidatedDebt() && debt.getTypeDebt() == 'I') {
        debt.setSelected(true);
      }
    }
  }

  public void updateBuroDebtsStatistics() {
    int totalDiagnosableDebts = 0;
    int totalUndiagnosableDebts = 0;
    int totalSelectedDebts = 0;

    for (DebtDto debt : consolidationOffer.getBuroDebts()) {
      if (debt.getConsolidatedDebt()) {
        totalDiagnosableDebts++;

        if (debt.isSelected()) {
          totalSelectedDebts++;
        }
      }
      totalUndiagnosableDebts = consolidationOffer.getBuroDebts().size() - totalDiagnosableDebts;
    }
    this.totalDiagnosableDebts = totalDiagnosableDebts;
    this.totalUndiagnosableDebts = totalUndiagnosableDebts;
    this.totalSelectedDebts = totalSelectedDebts;
  }

  public void calculateOfferAmount() {
    double offerAmount = 0;
    for (DebtDto debt : consolidationOffer.getBuroDebts()) {
      if (debt.isSelected()) {
        double balance = debt.getBalance() > 0 ? debt.getBalance() : 0;
        double amountAwarded = debt.getAmountAwarded() > 0 ? debt.getAmountAwarded() : 0;

        if (debt.getTypeDebt() == 'R') {
          offerAmount += balance > 0 ? balance : amountAwarded;
        } else {
          offerAmount += amountAwarded;
        }
      }
    }
    this.offerAmount = offerAmount;

    if (this.offerAmount > consolidationOffer.getMaxClientAmount()) {
      this.offerStatus = STATUS_EXCEEDED_AMOUNT;
    }
  }

  public void calculateWightedRate() {
    double amountRate = 0;
    double totalAmounts = 0;

    for (DebtDto debt : consolidationOffer.getBuroDebts()) {
      if (debt.isSelected()) {

        switch (debt.getTypeDebt()) {
          case 'I':
            amountRate += debt.getAmountAwarded() * debt.getExternalRate();
            totalAmounts += debt.getAmountAwarded();
            break;

          case 'R':
            double revolvingRate = debt.getExternalRate();
            if (revolvingRate > 0 && debt.getRevolverType() != DebtDto.REVOLVER_TRANSACTOR_TYPE) {
              amountRate += debt.getBalance() * revolvingRate;
              totalAmounts += debt.getBalance();
            }

            break;
        }
      }
    }
    this.wightedRate = amountRate / totalAmounts;
  }

  public void calculateMaxDebtsRate() {
    double maxDebtsRate = 0;
    for (DebtDto debt : consolidationOffer.getBuroDebts()) {
      if (debt.isSelected() && debt.getExternalRate() > maxDebtsRate) {
        maxDebtsRate = debt.getExternalRate();
      }
    }
    this.maxDebtsRate = maxDebtsRate;
  }

  public void calculateOfferRate(boolean defaultRate) {
    double[] ratesList = consolidationOffer.getAssistedRates();
    double[] commissionsList = consolidationOffer.getCommissionRateList();

    if (ratesList.length > 0 && ratesList.length != commissionsList.length) {
      this.offerRate = 0;
      this.offerComission = 0;
      // STATUS: ERROR DE CALCULO
      return;
    }

    double kuboRate = ratesList[0];
    double kuboRateComission = commissionsList[0];
    double minFlexibleRate = ratesList[ratesList.length - 1];
    double minFlexibleComission = commissionsList[commissionsList.length - 1];
    double flexibleRate = this.wightedRate * 0.9;

    if (defaultRate || kuboRate * 1.1 <= this.wightedRate) {
      this.offerRate = kuboRate;
      this.offerComission = kuboRateComission;
      this.offerStatus = STATUS_ORIGINAL_OFFER;

      updateOfferRateOnBuroDebts();
      return;
    }

    if (flexibleRate > minFlexibleRate) {
      for (int i = 0; i < ratesList.length; i++) {

        double rate = ratesList[i];
        double commission = commissionsList[i];

        if (flexibleRate > rate) {
          this.offerRate = rate;
          this.offerComission = commission;

          break;
        }
      }
    } else {
      this.offerRate = minFlexibleRate;
      this.offerComission = minFlexibleComission;
    }

    this.offerStatus = STATUS_RATE_AUTOMATICALLY_CALCULATED;
    updateOfferRateOnBuroDebts();
  }

  private void updateOfferRateOnBuroDebts() {
    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {
      debt.setKuboRate(this.offerRate);
    }
  }

  public void calculateSaving() {
    double totalSaving = 0;
    double monthlySaving = 0;
    double monthlyExternalPayment = 0;

    for (DebtDto dept : consolidationOffer.getBuroDebts()) {
      if (dept.isSelected()) {
        totalSaving += dept.getTotalSaving();
        monthlySaving += dept.getMonthlySaving();
        monthlyExternalPayment += dept.getPayment();
      }
    }

    this.totalSaving = totalSaving;
    this.monthlySavings = monthlySaving;
    this.monthlyExternalPayment = monthlyExternalPayment;
  }

  @Override
  public String toString() {
    return "{\"offerAmount\":" + offerAmount + ", \"offerRate\":" + offerRate + ", \"offerComission\":" + offerComission
        + ", \"offerStatus\":" + offerStatus + ", \"wightedRate\":" + wightedRate + ", \"totalSaving\":" + totalSaving
        + ", \"monthlySavings\":" + monthlySavings + ", \"monthlyExternalPayment\":" + monthlyExternalPayment
        + ", \"totalDiagnosableDebts\":" + totalDiagnosableDebts + ", \"totalUndiagnosableDebts\":"
        + totalUndiagnosableDebts + ", \"totalSelectedDebts\":" + totalSelectedDebts + ", \"maxDebtsRate\":"
        + maxDebtsRate + ", \"simulatorOffer\":" + simulatorOffer + ", \"consolidationOffer\":" + consolidationOffer
        + "}";
  }

  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 300943, \"monto_maximo_cliente\": 74100.0, \"cuota_externa_total\": 4039.0, \"capacidad_maxima_pago\": 10936.0, \"bursolnum\": 7242992, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 3723.22624375344, \"monto_max\": 91500.0, \"cuota_min\": 141.1247705288141, \"monto_min\": 5000, \"plazo_max\": 36.0, \"plazo_min\": 4, \"tasa\": 0.5668, \"comision\": 0.0481, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.2306, 0.2], \"comisiones\": [0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":22905.0, \"cuota\":400.0, \"saldo\":11969.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.4459, \"fecha_inicio\":\"2021-02-26\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":19099.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2019-06-24\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":3, \"monto_otorgado\":74100.0, \"cuota\":3019.0, \"saldo\":37442.0, \"tasa_externa\":0.6075, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":36, \"numero_pagos_restante\":17.0, \"frecuencia_externa\":\"M\", \"avance\":0.2512, \"fecha_inicio\":\"2022-07-14\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":91500.0, \"cuota\":4039.0, \"saldo\":50311.0, \"tasa_externa\":0.3351, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":36, \"numero_pagos_restante\":16.0, \"frecuencia_externa\":\"M\", \"avance\":0.4502, \"fecha_inicio\":\"2022-06-15\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":5, \"monto_otorgado\":74100.0, \"cuota\":3645.0, \"saldo\":52462.0, \"tasa_externa\":0.507, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":46, \"numero_pagos_restante\":20.0, \"frecuencia_externa\":\"M\", \"avance\":0.292, \"fecha_inicio\":\"2021-12-20\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";
    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();
    // flexCons.getConsolidationOffer().getBuroDebts().get(0).setSelected(true);
    // flexCons.getConsolidationOffer().getBuroDebts().get(2)
    // .setSelected(true)
    // .setExternalRate(0.32);

    // flexCons.getConsolidationOffer().getBuroDebts().get(5)
    // .setSelected(true)
    // .setExternalRate(0.2);
    // flexCons.getConsolidationOffer().getBuroDebts().get(6).setExternalRate(0.5);

    // flexCons.updateOffer();
    System.out.println(flexCons);
  }
}