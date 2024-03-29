package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.dto.DebtDto;
import com.kubofinanciero.dto.SimulatorOfferDto;
import com.kubofinanciero.utils.GenericUtilities;
import com.kubofinanciero.utils.LoanSimulator;

/*
 * FlexibleConsolidations: Clase que contiene la logica principal de la oferta para consolidaciones flexibles
 * Atributos:
 *  offerAmount: Monto final de la oferta
 *  offerRate: Tasa kubo final de oferta 
 *  offerCommission: Tasa de comision final de oferta
 *  offerCommissionAmount: monto de comisión de oferta
 *  offerStatus: Status global de oferta 
 *  weightedRate: Tasa ponderada (promedio), obtenida de las deudas seleccionadas
 *  weightedRateType: Tipo de tasa ponderada, determina si para el calculo de la tasa ponderada se conocieron todas las tasas de las deudas
 *  totalSaving: Ahorro total, obtenida de las deudas seleccionadas
 *  monthlySavings: Ahorro mensual, obtenida de las deudas seleccionadas
 *  totalSavingAllDebts: Ahorro total, obtenida de las deudas consolidables seleccionadas y no seleccionadas.
 *  monthlySavingAllDebts: Ahorro mensual, obtenida de las deudas consolidables seleccionadas y no seleccionadas
 *  totalSavingMissingDebts: Ahorro total, de las deudas NO seleccionadas
 *  monthlySavingsMissingDebts: Ahorro mensual, obtenida de las deudas NO seleccionadas
 *  totalAmountToConsolidate: Es el monto total de las deudsa consolidables sin importar que esten seleccionadas o no.
 *  excedentAmount : El Colchoncito es lo que le sobrara despues de pagar todas sus deudas. Se calcula restando el saldo del monto otorgado,
 *  consolidableMissingAmount: Monto restante para consolidar, es el monto a consolidar de las deudas no seleccionadas. 
 *  monthlyExternalPayment: Es lo que el cliente esta pagando mensualmente de sus deudas seleccionadas
 *  monthlyKuboPayment: Es lo que el cliente pagaría mensualmente en sus deudas seleccionadas con la tasa kubo
 *  totalDiagnosableDebts: Numero de deudas consolidables
 *  totalUndiagnosableDebts: Numero de dudas pueden ser consolidables pero el cliente no complemento la info
 *  totalSelectedDebts: Numero de deudas seleccionadas 
 *  maxDebtsRate: Tasa externa mas alta, obtenida de las deudas seleccionadas
 *  simulatorOffer: Objeto que funge como inputs para el simulador
 *  consolidationOffer: Objeto que almacena el estatus actual de la oferta entregada por data
 *  catSimulation: Simulación utilizada para estimar el CAT. Tambien podría ser utilizada como una oferta previa.
 */
public class FlexibleConsolidations {

  public static final int STATUS_ORIGINAL_OFFER = 0;
  public static final int STATUS_RATE_AUTOMATICALLY_CALCULATED = 1;
  public static final int STATUS_RATE_MODIFIED_BY_ADVISOR = 2;
  public static final int STATUS_EXCEEDED_AMOUNT = 3;

  public static final int WEIGHTED_RATE_TYPE_MIXED = 0;
  public static final int WEIGHTED_RATE_TYPE_KNOWN = 1;
  public static final int WEIGHTED_RATE_TYPE_UNKNOWN = 2;

  private double offerAmount;
  private double offerRate;
  private double offerCommission;
  private double offerCommissionAmount;
  private int offerStatus;
  private double weightedRate;
  private int weightedRateType;
  private double totalSaving;
  private double monthlySavings;
  private double totalSavingAllDebts;
  private double monthlySavingAllDebts;
  private double totalSavingMissingDebts;
  private double monthlySavingsMissingDebts;
  private double totalAmountToConsolidate;
  private double excedentAmount;
  private double consolidableMissingAmount;
  private double monthlyExternalPayment;
  private double monthlyKuboPayment;
  private int totalDiagnosableDebts;
  private int totalUndiagnosableDebts;
  private int totalSelectedDebts;
  private double maxDebtsRate;
  private CatSimulation catSimulation;

  private SimulatorOfferDto simulatorOffer;
  private ConsolidationOfferDto consolidationOffer;

  public FlexibleConsolidations(ConsolidationOfferDto consolidationOffer) {
    setConsolidationOffer(consolidationOffer);
    setSimulatorOffer(this.consolidationOffer);
  }

  public FlexibleConsolidations(
      double offerAmount,
      double offerRate,
      double offerCommission,
      int offerStatus,
      double weightedRate,
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
    this.offerCommission = offerCommission;
    this.offerStatus = offerStatus;
    this.weightedRate = weightedRate;
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

  public double getOfferCommission() {
    return offerCommission;
  }

  public int getOfferStatus() {
    return offerStatus;
  }

  public double getWeightedRate() {
    return weightedRate;
  }

  public double getWeightedRateType() {
    return weightedRateType;
  }

  public double getTotalSaving() {
    return totalSaving;
  }

  public double getMonthlySavings() {
    return monthlySavings;
  }

  public double getTotalSavingAllDebts() {
    return totalSavingAllDebts;
  }

  public double getMonthlySavingAllDebts() {
    return monthlySavingAllDebts;
  }

  public double getTotalSavingMissingDebts() {
    return totalSavingMissingDebts;
  }

  public double getMonthlySavingsMissingDebts() {
    return monthlySavingsMissingDebts;
  }

  public double getTotalAmountToConsolidate() {
    return totalAmountToConsolidate;
  }

  public double getExcedentAmount() {
    return excedentAmount;
  }

  public double getConsolidableMissingAmount() {
    return consolidableMissingAmount;
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

  public CatSimulation getCatSimulation() {
    if (catSimulation == null) {
      catSimulation = new CatSimulation();
    }

    return catSimulation;
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

    double[] rates = getConsolidationOffer().getAssistedRates();
    double[] comissions = getConsolidationOffer().getCommissionRateList();

    for (int i = 0; i < rates.length; i++) {
      if (offerRate == rates[i]) {
        this.offerRate = rates[i];
        this.offerCommission = comissions[i];
        this.offerCommissionAmount = GenericUtilities
            .round(LoanSimulator.cashCommission(comissions[i], getOfferAmount(), true));
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
   * @param offerCommission Comissión que el asesor decide definir manualmente,
   * solo se pueden agregar comisiones que estan dentro de la lista de asistidas.
   */
  public void setOfferCommission(double offerCommission) {
    if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
      return;
    }

    double[] rates = getConsolidationOffer().getAssistedRates();
    double[] comissions = getConsolidationOffer().getCommissionRateList();

    for (int i = 0; i < comissions.length; i++) {
      if (offerCommission == comissions[i]) {
        this.offerRate = rates[i];
        this.offerCommission = comissions[i];
        this.offerCommissionAmount = GenericUtilities
            .round(LoanSimulator.cashCommission(comissions[i], getOfferAmount(), true));

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
    this.offerStatus = STATUS_ORIGINAL_OFFER;
    calculateOfferAmount();
    calculateWeightedRate();
    calculateOfferRate(defaultRate);

    updateBuroDebtsStatistics();
    calculateGlobalAmounts();
    calculateSaving();

    getSimulatorOffer().setMinAmount(this.offerAmount);
    getSimulatorOffer().setMaxAmount(this.offerAmount);
    getSimulatorOffer().setRate(this.offerRate);
    getSimulatorOffer().setCommissionRate(this.offerCommission);

    catSimulation = new CatSimulation(
        monthlyKuboPayment,
        'M',
        getSimulatorOffer());
  }

  public void updateOffer() {
    updateOffer(false);
  }

  /**
   * Solo se utiliza para definir la oferta inicial de Data.
   * Cuando regresa nuevamente ya no es necesario invocarla.
   */
  public void updateConsolidatedDebts() {
    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {

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
    double maxDebtsRate = 0;

    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {
      if (debt.getConsolidatedDebt()) {
        totalDiagnosableDebts++;

        if (debt.isSelected()) {
          totalSelectedDebts++;
        }
      }
      totalUndiagnosableDebts = getConsolidationOffer().getBuroDebts().size() - totalDiagnosableDebts;

      if (debt.isSelected() && debt.getExternalRate() > maxDebtsRate) {
        maxDebtsRate = debt.getExternalRate();
      }
    }

    this.totalDiagnosableDebts = totalDiagnosableDebts;
    this.totalUndiagnosableDebts = totalUndiagnosableDebts;
    this.totalSelectedDebts = totalSelectedDebts;
    this.maxDebtsRate = maxDebtsRate;
  }

  /*
   * Se encarga de calcular el monto de oferta de forma automatica, tomando como
   * referencia las deudas seleccionadas.
   */
  public void calculateOfferAmount() {
    double offerAmount = 0;
    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {
      if (debt.isSelected()) {
        double balance = debt.getBalance() > 0 ? debt.getBalance() : 0;
        double amountAwarded = debt.getAmountAwarded() > 0 ? debt.getAmountAwarded() : 0;

        if (debt.getTypeDebt() == 'R') {
          offerAmount += balance;
        } else {
          offerAmount += amountAwarded;
        }
      }
    }
    this.offerAmount = GenericUtilities.round(offerAmount);
    this.offerCommissionAmount = GenericUtilities
        .round(LoanSimulator.cashCommission(this.offerAmount, getOfferCommission(), true));

    if (this.offerAmount > getConsolidationOffer().getMaxAmount()) {
      this.offerStatus = STATUS_EXCEEDED_AMOUNT;
    }
  }

  /*
   * Calcula la tasa ponderada con respecto a la tasa externa de la lista de
   * deudas seleccionadas.
   */
  public void calculateWeightedRate() {
    double amountRate = 0;
    double totalAmounts = 0;

    int totalRates = 0;
    int hasRate = 0;
    int hasNoRate = 0;

    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {

      if (debt.getConsolidatedDebt()) {
        totalRates++;

        switch (debt.getTypeDebt()) {
          case 'I':
            if (debt.isSelected()) {
              if (debt.getExternalRate() > 0 && debt.getAmountAwarded() > 0) {
                amountRate += debt.getAmountAwarded() * debt.getExternalRate();
                totalAmounts += debt.getAmountAwarded();
              }
            }

            if (debt.getExternalRate() > 0) {
              hasRate++;
            } else {
              hasNoRate++;
            }
            break;

          case 'R':
            if (debt.isSelected()) {
              double revolvingRate = debt.getExternalRate();
              if (revolvingRate > 0 && debt.getRevolverType() != DebtDto.REVOLVER_TRANSACTOR_TYPE) {
                amountRate += debt.getBalance() * revolvingRate;
                totalAmounts += debt.getBalance();
              }
            }

            if (debt.getRevolverType() != DebtDto.REVOLVER_TRANSACTOR_TYPE) {
              if (debt.getExternalRate() > 0) {
                hasRate++;
              } else {
                hasNoRate++;
              }
            }
            break;
        }

      }
    }

    if (!Double.isNaN(amountRate / totalAmounts)) {
      this.weightedRate = amountRate / totalAmounts;
    }

    if (totalRates == hasRate) {
      this.weightedRateType = WEIGHTED_RATE_TYPE_KNOWN;
    } else if (totalRates == hasNoRate) {
      this.weightedRateType = WEIGHTED_RATE_TYPE_UNKNOWN;
    } else {
      this.weightedRateType = WEIGHTED_RATE_TYPE_MIXED;
    }
  }

  /*
   * Calcula la tasa kubo de forma automatica, con respecto a la lista de tasas
   * seleccionadas
   */
  public void calculateOfferRate(boolean defaultRate) {

    if (this.offerStatus == STATUS_RATE_MODIFIED_BY_ADVISOR) {
      return;
    }

    double[] ratesList = getConsolidationOffer().getAssistedRates();
    double[] commissionsList = getConsolidationOffer().getCommissionRateList();

    if (ratesList.length > 0 && ratesList.length != commissionsList.length) {
      this.offerRate = 0;
      this.offerCommission = 0;
      this.offerCommissionAmount = 0;
      // STATUS: ERROR DE CALCULO
      return;
    }

    double kuboRate = ratesList[0];
    double kuboRateComission = commissionsList[0];
    double minFlexibleRate = ratesList[ratesList.length - 1];
    double minFlexibleComission = commissionsList[commissionsList.length - 1];
    double flexibleRate = this.weightedRate * 0.9;

    if (defaultRate || kuboRate * 1.1 <= this.weightedRate) {
      this.offerRate = kuboRate;
      this.offerCommission = kuboRateComission;
      this.offerCommissionAmount = GenericUtilities
          .round(LoanSimulator.cashCommission(kuboRateComission, getOfferAmount(), true));

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
          this.offerCommission = commission;
          this.offerCommissionAmount = GenericUtilities
              .round(LoanSimulator.cashCommission(commission, getOfferAmount(), true));

          break;
        }
      }
    } else {
      this.offerRate = minFlexibleRate;
      this.offerCommission = minFlexibleComission;
      this.offerCommissionAmount = GenericUtilities
          .round(LoanSimulator.cashCommission(minFlexibleComission, getOfferAmount(), true));
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
    double totalSavingAllDebts = 0;
    double monthlySavingAllDebts = 0;

    for (DebtDto debt : consolidationOffer.getBuroDebts()) {

      if (!debt.getConsolidatedDebt()) {
        continue;
      }

      if (debt.getTypeDebt() == 'R' && !debt.canBeSelected()) {
        continue;
      }

      if (debt.getTotalSaving() > 0) {
        totalSavingAllDebts += debt.getTotalSaving();

        if (debt.isSelected()) {
          totalSaving += debt.getTotalSaving();
        }
      }

      if (debt.getMonthlySaving() > 0) {
        monthlySavingAllDebts += debt.getMonthlySaving();

        if (debt.isSelected()) {
          monthlySaving += debt.getMonthlySaving();
        }
      }
    }

    this.totalSaving = GenericUtilities.round(totalSaving);
    this.monthlySavings = GenericUtilities.round(monthlySaving);

    this.totalSavingAllDebts = GenericUtilities.round(totalSavingAllDebts);
    this.monthlySavingAllDebts = GenericUtilities.round(monthlySavingAllDebts);

    this.totalSavingMissingDebts = GenericUtilities.round(totalSavingAllDebts - totalSaving);
    this.monthlySavingsMissingDebts = GenericUtilities.round(monthlySavingAllDebts - monthlySaving);
    // this.benefitType
  }

  /*
   * Calcula el monto total de todas las deudas que pueden ser consolidables
   */
  private void calculateGlobalAmounts() {
    double totalAmountToConsolidate = 0;
    double monthlyExternalPayment = 0;
    double monthlyKuboPayment = 0;
    double excedentAmount = 0;

    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {
      if (debt.getConsolidatedDebt()) {
        switch (debt.getTypeDebt()) {
          case 'I':
            totalAmountToConsolidate += debt.getAmountAwarded();
            break;
          case 'R':
            totalAmountToConsolidate += debt.getBalance();
            break;
        }
      }
      if (debt.isSelected()) {
        totalSaving += debt.getTotalSaving();
        monthlyExternalPayment += debt.getPayment();
        monthlyKuboPayment += debt.getMonthlyKuboPayment();

        if (debt.getTypeDebt() != 'R'
            && debt.getAmountAwarded() > debt.getBalance()) {
          excedentAmount += debt.getAmountAwarded() - debt.getBalance();
        }
      }
    }

    this.excedentAmount = GenericUtilities.round(excedentAmount);
    this.monthlyExternalPayment = GenericUtilities.round(monthlyExternalPayment);
    this.monthlyKuboPayment = monthlyKuboPayment;
    this.totalAmountToConsolidate = GenericUtilities.round(totalAmountToConsolidate);
    this.consolidableMissingAmount = GenericUtilities.round(totalAmountToConsolidate - this.offerAmount);
  }

  private int calculateAvgPaymentTerm() {
    int nPaymentTerm = 0;
    int avgPaymentTerm = 0;
    int minPaymentTerm = getConsolidationOffer().getMinPaymentTerm();
    int maxPaymentTerm = getConsolidationOffer().getMaxPaymentTerm();

    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {
      if (debt.canBeSelected() && debt.getTypeDebt() == 'I') {
        nPaymentTerm++;
        avgPaymentTerm += debt.getMonthlyPaymentTerm();
      }
    }

    if (avgPaymentTerm == 0 || nPaymentTerm == 0) {
      return 0;
    }

    avgPaymentTerm = (int) Math.ceil(avgPaymentTerm / nPaymentTerm);

    if (avgPaymentTerm > maxPaymentTerm) {
      avgPaymentTerm = maxPaymentTerm;
    } else if (avgPaymentTerm < minPaymentTerm) {
      avgPaymentTerm = minPaymentTerm;
    }

    return avgPaymentTerm;
  }

  /**
   * 
   * @return Retorna JSON que se necesita para el email de notificación de error
   *         de oferta
   */
  public String toJSONStringErrorOffer() {
    return "{" +
        "\"prospectusId\": " + getConsolidationOffer().getProspectusId() +
        ", \"bursolnum\": " + getConsolidationOffer().getBursolnum() +
        ", \"maxAmount\": \"" + GenericUtilities.toCurrencyFormat(getConsolidationOffer().getMaxAmount()) +
        "\" , \"minPaymentTerm\":" + getConsolidationOffer().getMinPaymentTerm() +
        ", \"maxPaymentTerm\": " + getConsolidationOffer().getMaxPaymentTerm() +
        ", \"minPayment\": \"" + GenericUtilities.toCurrencyFormat(getConsolidationOffer().getMinPayment()) +
        "\" , \"maxPayment\": \"" + GenericUtilities.toCurrencyFormat(getConsolidationOffer().getMaxPayment()) +
        "\"}";
  }

  /**
   * 
   * @return Retorna JSON que se necesita para los endpoints de generación de
   *         documento PDF de propuesta
   */
  public String toJSONStringPdf(String firstname, String email) {
    if (firstname != null) {
      firstname = " \"firstname\": \"" + firstname + "\",";
    } else {
      firstname = "";
    }

    if (email != null) {
      email = " \"email\": \"" + email + "\",";
    } else {
      email = "";
    }

    return "{" +
        firstname +
        email +
        "\"offerAmount\": " + GenericUtilities.round(offerAmount) +
        ", \"offerRate\": " + offerRate +
        ", \"monthlyExternalPayment\": " + GenericUtilities.round(monthlyExternalPayment) +
        ", \"maxDebtsRate\":" + maxDebtsRate +
        ", \"totalSelectedDebts\":" + totalSelectedDebts +
        ", \"totalDiagnosableDebts\":" + totalDiagnosableDebts +
        ", \"totalSaving\": " + GenericUtilities.round(totalSaving) +
        ", \"monthlySavings\": " + GenericUtilities.round(monthlySavings) +
        ", \"totalSavingAllDebts\": " + GenericUtilities.round(totalSavingAllDebts) +
        ", \"monthlySavingAllDebts\": " + GenericUtilities.round(monthlySavingAllDebts) +
        ", \"totalSavingMissingDebts\": " + GenericUtilities.round(totalSavingMissingDebts) +
        ", \"monthlySavingsMissingDebts\": " + GenericUtilities.round(monthlySavingsMissingDebts) +
        ", \"totalAmountToConsolidate\": " + GenericUtilities.round(totalAmountToConsolidate) +
        ", \"consolidableMissingAmount\": " + GenericUtilities.round(consolidableMissingAmount) +
        ", \"minPaymentTerm\": " + getSimulatorOffer().getMinPaymentTerm() +
        ", \"maxPaymentTerm\": " + getSimulatorOffer().getMaxPaymentTerm() +
        ", \"cat\": " + catSimulation.getCat() +
        ", \"catCalculationDate\": \"" + catSimulation.getCalculationDate() +
        "\" , \"buroDebts\":" + consolidationOffer.buroDebtsToJSONString() +
        "}";
  }

  /**
   * 
   * @return Retorna JSON global de FlexibleConsolidacion bajo la convensión de
   *         vairables en inglés
   */
  public String toJSONString() {
    return "{\"offerAmount\":" + offerAmount +
        ", \"offerRate\":" + offerRate +
        ", \"offerCommission\":" + offerCommission +
        ", \"offerCommissionAmount\":" + offerCommissionAmount +
        ", \"offerStatus\":" + offerStatus +
        ", \"weightedRate\":" + weightedRate +
        ", \"weightedRateType\":" + weightedRateType +
        ", \"totalSaving\":" + totalSaving +
        ", \"monthlySavings\":" + monthlySavings +
        ", \"totalSavingAllDebts\":" + totalSavingAllDebts +
        ", \"monthlySavingAllDebts\":" + monthlySavingAllDebts +
        ", \"totalSavingMissingDebts\":" + totalSavingMissingDebts +
        ", \"monthlySavingsMissingDebts\":" + monthlySavingsMissingDebts +
        ", \"totalAmountToConsolidate\":" + totalAmountToConsolidate +
        ", \"excedentAmount\":" + excedentAmount +
        ", \"consolidableMissingAmount\":" + consolidableMissingAmount +
        ", \"monthlyExternalPayment\":" + monthlyExternalPayment +
        ", \"totalDiagnosableDebts\":" + totalDiagnosableDebts +
        ", \"totalUndiagnosableDebts\":" + totalUndiagnosableDebts +
        ", \"totalSelectedDebts\":" + totalSelectedDebts +
        ", \"maxDebtsRate\":" + maxDebtsRate +
        ", \"catSimulation\":" + catSimulation +
        ", \"simulatorOffer\":" + getSimulatorOffer().toJSONString() +
        ", \"consolidationOffer\":" + consolidationOffer.toJSONString() +
        "}";
  }

  @Override
  public String toString() {
    return "{\"offerAmount\":" + offerAmount +
        ", \"offerRate\":" + offerRate +
        ", \"offerCommission\":" + offerCommission +
        ", \"offerCommissionAmount\":" + offerCommissionAmount +
        ", \"offerStatus\":" + offerStatus +
        ", \"weightedRate\":" + weightedRate +
        ", \"weightedRateType\":" + weightedRateType +
        ", \"totalSaving\":" + totalSaving +
        ", \"monthlySavings\":" + monthlySavings +
        ", \"totalSavingAllDebts\":" + totalSavingAllDebts +
        ", \"monthlySavingAllDebts\":" + monthlySavingAllDebts +
        ", \"totalSavingMissingDebts\":" + totalSavingMissingDebts +
        ", \"monthlySavingsMissingDebts\":" + monthlySavingsMissingDebts +
        ", \"totalAmountToConsolidate\":" + totalAmountToConsolidate +
        ", \"excedentAmount\":" + excedentAmount +
        ", \"consolidableMissingAmount\":" + consolidableMissingAmount +
        ", \"monthlyExternalPayment\":" + monthlyExternalPayment +
        ", \"totalDiagnosableDebts\":" + totalDiagnosableDebts +
        ", \"totalUndiagnosableDebts\":" + totalUndiagnosableDebts +
        ", \"totalSelectedDebts\":" + totalSelectedDebts +
        ", \"maxDebtsRate\":" + maxDebtsRate +
        ", \"catSimulation\":" + catSimulation +
        ", \"simulatorOffer\":" + simulatorOffer +
        ", \"consolidationOffer\":" + consolidationOffer +
        "}";
  }

  public static void main(String[] args) {
  }
}