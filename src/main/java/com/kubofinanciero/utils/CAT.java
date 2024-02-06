package com.kubofinanciero.utils;

import java.lang.Math;

/*************
 * Calculo del CAT según las formulas otorgadas por Banxico.
 * 
 * CONSIDERACIONES:
 * Para este calculo se considera que todos los pagos del credito tienen el
 * mismo monto.
 * El CAT debe ser expresado a un decimail.
 * 
 * Documentacion oficial en:
 * https://www.banxico.org.mx/sistema-financiero/d/%7B5BD610E5-EE24-04AA-A21E-53B2176C2228%7D.pdf
 *
 *************/

public class CAT {
  private double amount;
  private double commission;
  private double fee;
  private int totalFees;
  private int periodsPerYear;

  public CAT(
      double amount,
      double commission,
      double fee,
      int totalFees,
      int periodsPerYear) {
    this.amount = amount;
    this.commission = commission;
    this.fee = fee;
    this.totalFees = totalFees;
    this.periodsPerYear = periodsPerYear;
  }

  /*************
   *
   * Devuelve el CAT segun los siguientes parametros:
   * 
   * @param amount         Monto total del prestamo (sin descontar comisiones)
   * @param commission     Cargos por apertura (SIN IVA)
   * @param fee            Cuota periodica (mensual, quincenal, catorcenal o
   *                       semanal SIN IVA)
   * @param totalFees      Numero total de pagos del credito
   * @param periodsPerYear Número pagos al año dada la frecuencia segun Banxico
   *                       (semanas: 52, catorcenal: 26, quincenas: 24, meses: 12)
   * @return cat
   *
   *************/

  public Double getCAT() {
    Double cat = 50d;
    Double minCat = 0d;
    Double maxCat = 0d;
    Double tempCat;

    for (int counter = 0; counter <= 25; counter++) {
      Double calculedCAT = estimateCAT(cat);

      if (calculedCAT > 0) {
        tempCat = cat;
        cat = (cat + minCat) / 2;
        maxCat = tempCat;
      } else if (maxCat == 0) {
        minCat = cat;
        cat = cat + 50;
      } else {
        tempCat = cat;
        cat = (cat + maxCat) / 2;
        minCat = tempCat;
      }
    }

    return cat;
  }

  private Double estimateCAT(double cat) {
    Double value = 0d;
    for (double i = 0d; i <= this.totalFees; i++) {
      value += (i > 0 ? fee : commission) / Math.pow(1 + cat / 100, i / periodsPerYear);
    }

    return amount - value;
  }

  public static void main(String[] args) {
    Double cat1 = new CAT(245000, 11025.0, 10867.143576385397, 38, 12).getCAT();
    System.out.println(cat1);
    System.out.println((double) Math.round(cat1 * 10) / 10);

    Double cat2 = new CAT(30000, 1000.0, 2000, 18, 12).getCAT();
    System.out.println(cat2);
    System.out.println((double) Math.round(cat2 * 10) / 10);

    Double cat3 = new CAT(180000, 8400.0, 8000, 36, 12).getCAT();
    System.out.println(cat3);
    System.out.println((double) Math.round(cat3 * 10) / 10);

    Double cat4 = new CAT(180000, 8400.0, 8000, 36, 24).getCAT();
    System.out.println(cat4);
    System.out.println((double) Math.round(cat4 * 10) / 10);

    Double cat5 = new CAT(15000, 100, 962.33, 24, 12).getCAT();
    System.out.println(cat5);
    System.out.println((double) Math.round(cat5 * 10) / 10);

    Double cat6 = new CAT(24000, 0, 925.05, 47, 12).getCAT();
    System.out.println(cat6);
    System.out.println((double) Math.round(cat6 * 10) / 10);

    Double cat7 = new CAT(10000, 0, 636, 20, 12).getCAT();
    System.out.println(cat7);
    System.out.println((double) Math.round(cat7 * 10) / 10);

    Double cat8 = new CAT(50000, 2155.1724, 2050.44, 24, 52).getCAT();
    System.out.println(cat8);
    System.out.println((double) Math.round(cat8 * 10) / 10);
  }
}