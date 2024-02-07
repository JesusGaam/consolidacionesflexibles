package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.dto.DebtDto;
import com.kubofinanciero.dto.SimulatorOfferDto;

/*
 * FlexibleConsolidations: Clase que contiene la logica principal de la oferta para consolidaciones flexibles
 * Atributos:
 *  offerAmount: Monto final de la oferta
 *  offerRate: Tasa kubo final de oferta 
 *  offerComission: Tasa de comision final de oferta
 *  offerStatus: Status global de oferta 
 *  wightedRate: Tasa ponderada (promedio), obtenida de las deudas seleccionadas
 *  totalSaving: Ahorro total, obtenida de las deudas seleccionadas
 *  monthlySavings: Ahorro mensual, obtenida de las deudas seleccionadas
 *  monthlyExternalPayment: Es lo que el cliente esta pagando mensualmente de sus deudas seleccionadas
 *  totalDiagnosableDebts: Numero de deudas consolidables
 *  totalUndiagnosableDebts: Numero de dudas pueden ser consolidables pero el cliente no complemento la info
 *  totalSelectedDebts: Numero de deudas seleccionadas 
 *  maxDebtsRate: Tasa externa mas alta, obtenida de las deudas seleccionadas
 * simulatorOffer: Objeto que funge como inputs para el simulador
 * consolidationOffer: Objeto que almacena el estatus actual de la oferta entregada por data
 */
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

  /*
   * Funcion que permite planchar la la comisión y tasas calculada
   * automaticamnete.
   * 
   * @param offerRate Tasa que el asesor decide definir manualmente, solo se
   * pueden agregar tasas que estan dentro de la lista de asistidas.
   */
  public void setOfferRate(double offerRate) {
    if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
      return;
    }

    double[] rates = consolidationOffer.getAssistedRates();
    double[] comissions = consolidationOffer.getCommissionRateList();

    for (int i = 0; i < rates.length; i++) {
      if (offerRate == rates[i]) {
        this.offerRate = rates[i];
        this.offerComission = comissions[i];
        updateOfferRateOnBuroDebts();

        this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
        break;
      }
    }
  }

  /*
   * Funcion que permite planchar la la comisión y tasas calculada
   * automaticamnete. Al agregar una comisión, asignará la tasa que corresmonde a
   * la misma.
   * 
   * @param offerComission Comissión que el asesor decide definir manualmente,
   * solo se pueden agregar comisiones que estan dentro de la lista de asistidas.
   */
  public void setOfferComission(double offerComission) {
    if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
      return;
    }

    double[] rates = consolidationOffer.getAssistedRates();
    double[] comissions = consolidationOffer.getCommissionRateList();

    for (int i = 0; i < comissions.length; i++) {
      if (offerComission == comissions[i]) {
        this.offerRate = rates[i];
        this.offerComission = comissions[i];
        updateOfferRateOnBuroDebts();

        this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
        break;
      }
    }
  }

  /*
   * Elimina la tasa agregada manualmente por el asesor por la tasa generada
   * automaticamenten por la logica del simulador.
   */
  public void removeRateModifiedByAdvisor() {
    if (this.offerStatus == STATUS_RATE_MODIFIED_BY_ADVISOR) {
      this.offerStatus = STATUS_RATE_AUTOMATICALLY_CALCULATED;
      calculateOfferRate(false);
      calculateSaving();
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

  /*
   * Inicializa el objeto para el simulador de oferta
   */
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

  /*
   * Se debe de invocar para inicializar la oferta de consolidación
   */
  public void initOffer() {
    updateConsolidatedDebts();
    updateOffer(true);
  }

  /*
   * Se debe de invocar cada cuando hay algun cambio de forma manual dentro de los
   * parametros de esta clase o las deudas
   */
  private void updateOffer(boolean defaultRate) {
    updateBuroDebtsStatistics();
    calculateMaxDebtsRate();
    calculateOfferAmount();
    calculateWightedRate();
    calculateOfferRate(defaultRate);
    calculateSaving();

    simulatorOffer.setMinAmount(this.offerAmount);
    simulatorOffer.setMaxAmount(this.offerAmount);
    simulatorOffer.setRate(this.offerRate);
    simulatorOffer.setCommissionRate(this.offerComission);
  }

  public void updateOffer() {
    updateOffer(false);
  }

  /**
   * Solo se utiliza para definir la oferta inicial de Data.
   * Cuando regresa nuevamente ya no es necesario invocarla.
   */
  public void updateConsolidatedDebts() {
    for (DebtDto debt : consolidationOffer.getBuroDebts()) {

      if (debt.getConsolidatedDebt() && debt.getTypeDebt() == 'I') {
        debt.setSelected(true);
      }
    }
  }

  /*
   * Se encarga de actualizar las estadisticas relacionadas a la lista de deudas
   * consolidables
   */
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

  /*
   * Se encarga de calcular el monto de oferta de forma automatica, tomando como
   * referencia las deudas seleccionadas.
   */
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
      System.out.println("MONTO EXCEDIDO");
      this.offerStatus = STATUS_EXCEEDED_AMOUNT;
    }
  }

  /*
   * Calcula la tasa ponderada con respecto a la tasa externa de la lista de
   * deudas seleccionadas.
   */
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

  /*
   * Estima la tasa externa mas cara de la lista de deudas seleccionadas
   */
  public void calculateMaxDebtsRate() {
    double maxDebtsRate = 0;
    for (DebtDto debt : consolidationOffer.getBuroDebts()) {
      if (debt.isSelected() && debt.getExternalRate() > maxDebtsRate) {
        maxDebtsRate = debt.getExternalRate();
      }
    }
    this.maxDebtsRate = maxDebtsRate;
  }

  /*
   * Calucla la tasa kubo de forma automatica, con respecto a la lista de tasas
   * seleccionadas
   */
  public void calculateOfferRate(boolean defaultRate) {

    if (this.offerStatus == STATUS_RATE_MODIFIED_BY_ADVISOR) {
      return;
    }

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
      updateOfferRateOnBuroDebts();

      if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
        return;
      }

      this.offerStatus = STATUS_ORIGINAL_OFFER;
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

    updateOfferRateOnBuroDebts();
    if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
      return;
    }

    this.offerStatus = STATUS_RATE_AUTOMATICALLY_CALCULATED;
  }

  /*
   * Se encarga de actualizar la tasa kubo dentro de cada una de las deudas
   * consolidables, cuando esta es calculada automaticamente o modificada
   * manualmente por el asesor.
   */
  private void updateOfferRateOnBuroDebts() {
    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {
      debt.setKuboRate(this.offerRate);
    }
  }

  /*
   * Calcula los ahorros dada la tasa externa, tasa kubo en la lista de deudas
   * seleccionadas.
   */
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
  }
}