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
 *  offerKuboScore: Tasa kubo final de oferta 
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
 *  totalAmountSelectedDebts: Es el monto total de las deudas seleccionadas
 *  totalAmountToConsolidate: Es el monto total a recibir, se calcula restandole la comision al monto de oferta
 *  totalAmountToReceive: Es el monto total de las deudas consolidables sin importar que esten seleccionadas o no.
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
 *  includeCommissionInOfferAmount: Parametro utilizado para agregar la comisión al monto total de deudas
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
  private String offerKuboScore;
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
  private double totalAmountSelectedDebts;
  private double totalAmountToConsolidate;
  private double totalAmountToReceive;
  private double excedentAmount;
  private double consolidableMissingAmount;
  private double monthlyExternalPayment;
  private double monthlyKuboPayment;
  private int totalDiagnosableDebts;
  private int totalUndiagnosableDebts;
  private int totalSelectedDebts;
  private double maxDebtsRate;
  private CatSimulation catSimulation;
  private boolean includeCommissionInOfferAmount;

  private SimulatorOfferDto simulatorOffer;
  private ConsolidationOfferDto consolidationOffer;

  private double discountWeightedRate;
  private double minimumRateOffer;

  public FlexibleConsolidations(
      ConsolidationOfferDto consolidationOffer) {
    setConsolidationOffer(consolidationOffer);
    setSimulatorOffer(this.consolidationOffer);

    this.discountWeightedRate = 0;
    this.minimumRateOffer = 0;
    this.includeCommissionInOfferAmount = true;
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

    this.offerKuboScore = "";
    this.discountWeightedRate = 0;
    this.minimumRateOffer = 0;
    this.includeCommissionInOfferAmount = true;
  }

  public FlexibleConsolidations(
      ConsolidationOfferDto consolidationOffer,
      double discountWeightedRate,
      double minimumRateOffer) {
    setConsolidationOffer(consolidationOffer);
    setSimulatorOffer(this.consolidationOffer);

    this.discountWeightedRate = discountWeightedRate;
    this.minimumRateOffer = minimumRateOffer;
    this.includeCommissionInOfferAmount = true;
  }

  public FlexibleConsolidations(
      double offerAmount,
      double offerRate,
      String offerKuboScore,
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
      ConsolidationOfferDto consolidationOffer,
      double discountWeightedRate,
      double minimumRateOffer) {
    this.offerAmount = offerAmount;
    this.offerRate = offerRate;
    this.offerKuboScore = offerKuboScore;
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

    this.discountWeightedRate = discountWeightedRate;
    this.minimumRateOffer = minimumRateOffer;
    this.includeCommissionInOfferAmount = true;
  }

  public double getOfferAmount() {
    return offerAmount;
  }

  public double getOfferRate() {
    return offerRate;
  }

  public String getOfferKuboScore() {
    if (offerKuboScore == null) {
      offerKuboScore = "";
    }
    return offerKuboScore;
  }

  public double getOfferCommission() {
    return offerCommission;
  }

  public double getOfferCommissionAmount() {
    return offerCommissionAmount;
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

  public double getTotalAmountSelectedDebts() {
    return totalAmountSelectedDebts;
  }

  public double getTotalAmountToConsolidate() {
    return totalAmountToConsolidate;
  }

  public double getTotalAmountToReceive() {
    return totalAmountToReceive;
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

  public boolean getIncludeCommissionInOfferAmount() {
    return includeCommissionInOfferAmount;
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
   * Funcion que permite planchar la comisión, el kubo.score tasas calculada
   * automaticamente.
   * 
   * @param offerRate Tasa que el asesor decide definir manualmente, solo se
   * pueden agregar tasas que estan dentro de la lista de asistidas.
   */
  public void setOfferRate(double offerRate) {
    if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
      return;
    }

    if (offerRate == getConsolidationOffer().getRate()) {
      setOriginalRateToOfferRate();
      updateOfferRateOnBuroDebts();

      this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
      return;
    }

    double[] rates = getConsolidationOffer().getAssistedRates();
    double[] comissions = getConsolidationOffer().getCommissionRateList();
    String[] kuboScores = getConsolidationOffer().getKuboScores();

    for (int i = 0; i < rates.length; i++) {
      if (offerRate == rates[i]) {
        this.offerRate = rates[i];
        this.offerCommission = comissions[i];
        this.offerKuboScore = kuboScores[i];
        this.offerCommissionAmount = GenericUtilities
            .round(LoanSimulator.cashCommission(comissions[i], getOfferAmount(), true));
        updateOfferRateOnBuroDebts();

        this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
        break;
      }
    }
  }

  /*
   * Permite planchar el kubo.score, la comisión y tasas calculada
   * automaticamente en funcion del kubo.score ingresado.
   * 
   * @param offerKuboScore kubo.score que el asesor decide definir manualmente,
   * solo se pueden agregar kubo.scores que estan dentro de la lista de asistidas.
   */
  public void setOfferKuboScore(String offerKuboScore) {
    if (this.offerStatus == STATUS_EXCEEDED_AMOUNT) {
      return;
    }

    if (offerKuboScore == getConsolidationOffer().getKuboScore()) {
      setOriginalRateToOfferRate();
      updateOfferRateOnBuroDebts();

      this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
      return;
    }

    double[] rates = getConsolidationOffer().getAssistedRates();
    double[] comissions = getConsolidationOffer().getCommissionRateList();
    String[] kuboScores = getConsolidationOffer().getKuboScores();

    for (int i = 0; i < kuboScores.length; i++) {
      if (offerKuboScore == kuboScores[i]) {
        this.offerRate = rates[i];
        this.offerCommission = comissions[i];
        this.offerKuboScore = kuboScores[i];
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

    if (offerCommission == getConsolidationOffer().getCommissionRate()) {
      setOriginalRateToOfferRate();
      updateOfferRateOnBuroDebts();

      this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
      return;
    }

    double[] rates = getConsolidationOffer().getAssistedRates();
    double[] comissions = getConsolidationOffer().getCommissionRateList();
    String[] kuboScores = getConsolidationOffer().getKuboScores();

    for (int i = 0; i < comissions.length; i++) {
      if (offerCommission == comissions[i]) {
        this.offerRate = rates[i];
        this.offerCommission = comissions[i];
        this.offerKuboScore = kuboScores[i];
        this.offerCommissionAmount = GenericUtilities
            .round(LoanSimulator.cashCommission(comissions[i], getOfferAmount(), true));

        updateOfferRateOnBuroDebts();

        this.offerStatus = STATUS_RATE_MODIFIED_BY_ADVISOR;
        break;
      }
    }
  }

  public void setOriginalRateToOfferRate() {
    this.offerRate = getConsolidationOffer().getRate();
    this.offerCommission = 0;
    this.offerCommissionAmount = 0;
    this.offerKuboScore = getConsolidationOffer().getKuboScore();
  }

  /*
   * Elimina la tasa agregada manualmente por el asesor por la tasa generada
   * automaticamenten por la logica del simulador.
   */
  public void removeRateModifiedByAdvisor() {
    if (this.offerStatus == STATUS_RATE_MODIFIED_BY_ADVISOR) {
      this.offerStatus = STATUS_RATE_AUTOMATICALLY_CALCULATED;
      calculateOfferRate();
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
          getConsolidationOffer().getLoanType(),
          getConsolidationOffer().getSubLoanType(),
          getConsolidationOffer().getMinPayment(),
          getConsolidationOffer().getMaxPayment(),
          getConsolidationOffer().getMinPaymentTerm(),
          getConsolidationOffer().getMaxPaymentTerm(),
          getConsolidationOffer().getFrequencies());
    }
  }

  public void setConsolidationOffer(ConsolidationOfferDto consolidationOffer) {
    if (consolidationOffer != null) {
      this.consolidationOffer = consolidationOffer;
    }
  }

  public void setDiscountWeightedRate(double discountWeightedRate) {
    this.discountWeightedRate = discountWeightedRate;
  }

  public void updateCatSimulation(int suggestedPaymentTerm, char frequency) {
    catSimulation = new CatSimulation(suggestedPaymentTerm, frequency, getSimulatorOffer());
  }

  public void minimumRateOffer(double minimumRateOffer) {
    this.minimumRateOffer = minimumRateOffer;
  }

  /*
   * Se debe de invocar para inicializar la oferta de consolidación
   */
  public void initOffer() {
    updateConsolidatedDebts();
    updateOffer();
  }

  /*
   * Se debe de invocar cada cuando hay algun cambio de forma manual dentro de los
   * parametros de esta clase o las deudas
   */
  public void updateOffer(boolean calculateOfferRate) {
    if (calculateOfferRate) {
      this.offerStatus = STATUS_ORIGINAL_OFFER;
    }

    calculateWeightedRate();
    if (calculateOfferRate) {
      calculateOfferRate();
    }

    calculateGlobalAmounts();
    updateBuroDebtsStatistics();
    calculateSaving();

    getSimulatorOffer().setMinAmount(this.offerAmount);
    getSimulatorOffer().setMaxAmount(this.offerAmount);
    getSimulatorOffer().setRate(this.offerRate);
    getSimulatorOffer().setCommissionRate(this.offerCommission);

    catSimulation = new CatSimulation(monthlyKuboPayment, getSimulatorOffer());
  }

  public void updateOffer() {
    updateOffer(true);
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

  public void addCommissionToOfferAmount(boolean includeCommision) {
    this.includeCommissionInOfferAmount = includeCommision;
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
        double externalRate = debt.getExternalRate();

        switch (debt.getTypeDebt()) {
          case 'I':
            if (debt.isSelected()) {
              if (externalRate > 0 && debt.getAmountAwarded() > 0) {
                amountRate += debt.getAmountAwarded() * getRateForWeighing(debt);
                totalAmounts += debt.getAmountAwarded();
              }
            }

            if (externalRate > 0) {
              hasRate++;
            } else {
              hasNoRate++;
            }
            break;

          case 'R':
            if (debt.isSelected()) {
              if (externalRate > 0 && debt.getBalance() > 0
                  && !debt.getRevolverType().equals(DebtDto.REVOLVER_TRANSACTOR_TYPE)) {
                amountRate += debt.getBalance() * getRateForWeighing(debt);
                totalAmounts += debt.getBalance();
              }
            }

            if (!debt.getRevolverType().equals(DebtDto.REVOLVER_TRANSACTOR_TYPE)) {
              if (externalRate > 0) {
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
      double weightedRateWithDiscount = this.weightedRate * (1 - this.discountWeightedRate);

      if (this.weightedRate <= minimumRateOffer || weightedRateWithDiscount <= minimumRateOffer) {
        this.weightedRate = minimumRateOffer;
      } else if (this.weightedRate >= getConsolidationOffer().getRate()
          || weightedRateWithDiscount >= getConsolidationOffer().getRate()) {
        this.weightedRate = getConsolidationOffer().getRate();
      } else {
        this.weightedRate = weightedRateWithDiscount;
      }
    }

    if (totalRates == hasRate) {
      this.weightedRateType = WEIGHTED_RATE_TYPE_KNOWN;
    } else if (totalRates == hasNoRate) {
      this.weightedRateType = WEIGHTED_RATE_TYPE_UNKNOWN;
    } else {
      this.weightedRateType = WEIGHTED_RATE_TYPE_MIXED;
    }
  }

  private double getRateForWeighing(DebtDto debt) {

    if (!debt.isUploadedDocuments()) {
      return getConsolidationOffer().getRate();
    }

    if (debt.getExternalRate() < minimumRateOffer) {
      return minimumRateOffer;
    }

    // if (debt.getExternalRate() > getConsolidationOffer().getRate()) {
    // return getConsolidationOffer().getRate();
    // }
    return debt.getExternalRate();
  }

  /*
   * Calcula la tasa kubo de forma automatica, con respecto a la lista de tasas
   * seleccionadas
   * 
   * Casos de error: Cuando se detecta un error se queda con la tasa y kubo.score
   * de la Consulta Completa. La comisión deberá ser CERO
   */
  public void calculateOfferRate() {

    if (this.offerStatus == STATUS_RATE_MODIFIED_BY_ADVISOR) {
      return;
    }

    double[] ratesList = getConsolidationOffer().getAssistedRates();
    double[] commissionsList = getConsolidationOffer().getCommissionRateList();
    String[] kuboScores = getConsolidationOffer().getKuboScores();

    if (ratesList.length > 0
        && ratesList.length != commissionsList.length
        && ratesList.length != kuboScores.length) {

      setOriginalRateToOfferRate();
      updateOfferRateOnBuroDebts();
      // STATUS: ERROR DE CALCULO
      return;
    }

    if (this.weightedRate >= getConsolidationOffer().getRate()) {
      setOriginalRateToOfferRate();
      updateOfferRateOnBuroDebts();
      return;
    }

    int previousPositioRate = 0;
    int positionRate = 0;

    for (int i = 0; i < ratesList.length; i++) {

      previousPositioRate = i == 0 ? i : i - 1;
      positionRate = i;

      if (this.weightedRate > ratesList[i]) {
        if (kuboScores[i] == "A1") {
          if (getConsolidationOffer().getRate() != ratesList[i]) {
            positionRate = previousPositioRate;
          }
        }
        break;
      }
    }

    this.offerRate = this.weightedRate;
    if (ratesList.length > 0) {
      this.offerCommission = commissionsList[positionRate];
      this.offerKuboScore = kuboScores[positionRate];
      this.offerCommissionAmount = GenericUtilities
          .round(LoanSimulator.cashCommission(commissionsList[positionRate], getOfferAmount(), true));
    } else {
      this.offerCommission = 0;
      this.offerCommissionAmount = 0;
      this.offerKuboScore = getConsolidationOffer().getKuboScore();
    }

    // System.out.println("positionRate: " + positionRate +
    // " offerRate: " + offerRate +
    // " offerCommission: " + offerCommission +
    // " offerKuboScore: " + offerKuboScore +
    // " offerCommissionAmount: " + offerCommissionAmount);

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

    for (DebtDto debt : getConsolidationOffer().getBuroDebts()) {

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
    double totalAmountSelectedDebts = 0;
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

        double balance = debt.getBalance() > 0 ? debt.getBalance() : 0;
        double amountAwarded = debt.getAmountAwarded() > 0 ? debt.getAmountAwarded() : 0;

        if (debt.getTypeDebt() == 'R') {
          totalAmountSelectedDebts += balance;
        } else {
          totalAmountSelectedDebts += amountAwarded;
        }

        if (debt.getTypeDebt() != 'R'
            && debt.getAmountAwarded() > debt.getBalance()) {
          excedentAmount += debt.getAmountAwarded() - debt.getBalance();
        }
      }
    }

    this.totalAmountSelectedDebts = GenericUtilities.round(totalAmountSelectedDebts);
    this.excedentAmount = GenericUtilities.round(excedentAmount);
    this.monthlyExternalPayment = GenericUtilities.round(monthlyExternalPayment);
    this.monthlyKuboPayment = monthlyKuboPayment;
    this.totalAmountToConsolidate = GenericUtilities.round(totalAmountToConsolidate);
    this.consolidableMissingAmount = GenericUtilities.round(totalAmountToConsolidate - this.totalAmountSelectedDebts);

    if (includeCommissionInOfferAmount) {
      this.offerAmount = GenericUtilities.round(LoanSimulator.addCommissionToAmount(totalAmountSelectedDebts,
          getOfferCommission(), true));
    } else {
      this.offerAmount = totalAmountSelectedDebts;
    }

    this.offerCommissionAmount = GenericUtilities
        .round(LoanSimulator.cashCommission(this.offerAmount, getOfferCommission(), true));
    this.totalAmountToReceive = GenericUtilities.round(this.offerAmount - this.offerCommissionAmount);

    if (this.totalAmountSelectedDebts > getConsolidationOffer().getMaxAmount()) {
      this.offerStatus = STATUS_EXCEEDED_AMOUNT;
    }
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
        ", \"totalAmountSelectedDebts\": " + GenericUtilities.round(totalAmountSelectedDebts) +
        ", \"totalAmountToConsolidate\": " + GenericUtilities.round(totalAmountToConsolidate) +
        ", \"totalAmountToReceive\": " + GenericUtilities.round(totalAmountToReceive) +
        ", \"consolidableMissingAmount\": " + GenericUtilities.round(consolidableMissingAmount) +
        ", \"minPaymentTerm\": " + getSimulatorOffer().getMinPaymentTerm() +
        ", \"maxPaymentTerm\": " + getSimulatorOffer().getMaxPaymentTerm() +
        ", \"cat\": " + catSimulation.getCat() +
        ", \"catCalculationDate\": \"" + catSimulation.getCalculationDate() +
        "\" , \"buroDebts\":" + getConsolidationOffer().buroDebtsToJSONString() +
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
        ", \"totalAmountSelectedDebts\":" + totalAmountSelectedDebts +
        ", \"totalAmountToConsolidate\":" + totalAmountToConsolidate +
        ", \"totalAmountToReceive\": " + totalAmountToReceive +
        ", \"excedentAmount\":" + excedentAmount +
        ", \"consolidableMissingAmount\":" + consolidableMissingAmount +
        ", \"monthlyExternalPayment\":" + monthlyExternalPayment +
        ", \"totalDiagnosableDebts\":" + totalDiagnosableDebts +
        ", \"totalUndiagnosableDebts\":" + totalUndiagnosableDebts +
        ", \"totalSelectedDebts\":" + totalSelectedDebts +
        ", \"maxDebtsRate\":" + maxDebtsRate +
        ", \"catSimulation\":" + catSimulation +
        ", \"simulatorOffer\":" + getSimulatorOffer().toJSONString() +
        ", \"consolidationOffer\":" + getConsolidationOffer().toJSONString() +
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
        ", \"totalAmountSelectedDebts\":" + totalAmountSelectedDebts +
        ", \"totalAmountToConsolidate\":" + totalAmountToConsolidate +
        ", \"totalAmountToReceive\": " + totalAmountToReceive +
        ", \"excedentAmount\":" + excedentAmount +
        ", \"consolidableMissingAmount\":" + consolidableMissingAmount +
        ", \"monthlyExternalPayment\":" + monthlyExternalPayment +
        ", \"totalDiagnosableDebts\":" + totalDiagnosableDebts +
        ", \"totalUndiagnosableDebts\":" + totalUndiagnosableDebts +
        ", \"totalSelectedDebts\":" + totalSelectedDebts +
        ", \"maxDebtsRate\":" + maxDebtsRate +
        ", \"catSimulation\":" + catSimulation +
        ", \"simulatorOffer\":" + simulatorOffer +
        ", \"consolidationOffer\":" + getConsolidationOffer() +
        "}";
  }

  public static void main(String[] args) {
  }
}