package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.dto.DebtDto;
import com.kubofinanciero.dto.SimulatorOfferDto;

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

  public void initOffer() {
    updateConsolidatedDebts();
    updateOffer();
  }

  public void updateOffer() {
    updateBuroDebtsStatistics();
    calculateOfferAmount();
    calculateWightedRate();
    calculateOfferRate();
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

  public void calculateOfferRate() {
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

    if (kuboRate * 1.1 <= this.wightedRate) {
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
  }
}