package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    double globalMaxAmount = 200000;
    int extendedPaymentTerm = 6;
    String jsonOffer = "{\"Prospecto\": 4441848, \"monto_maximo_cliente\": 103972.0, \"cuota_externa_total\": 23514.0, \"capacidad_maxima_pago\": 864.2200000000012, \"bursolnum\": 7558821, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 32023.802598587252, \"monto_max\": 111000.0, \"cuota_min\": 397.9006237998881, \"monto_min\": 5000, \"plazo_max\": 24.0, \"plazo_min\": 4, \"tasa\": 0.6193, \"kubo_score\": \"D2\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5991, 0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3913, 0.3717, 0.3437, 0.3014], \"comisiones\": [0.0481, 0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['D1', 'C5', 'C4', 'C3', 'C2', 'C1', 'B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"SERVICIOS\", \"registro\":11, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"B\", \"avance\":-1.0, \"fecha_inicio\":\"2020-04-20\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":10, \"monto_otorgado\":9804.0, \"cuota\":2451.0, \"saldo\":9804.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2024-03-29\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":1907.3541592088222, \"ahorro_total\":45776.499821011734, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":9, \"monto_otorgado\":1164.0, \"cuota\":0.0, \"saldo\":1164.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2024-01-18\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-6.345467021718761, \"ahorro_total\":-152.29120852125027, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":8, \"monto_otorgado\":2947.0, \"cuota\":2947.0, \"saldo\":2947.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-12-29\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":2783.584629456181, \"ahorro_total\":66806.03110694834, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":7, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":3.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2016-07-04\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":28183.0, \"cuota\":1333.0, \"saldo\":28183.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-02-11\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-153.63771226211315, \"ahorro_total\":-3687.3050942907157, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"GUBERNAMENTALES\", \"registro\":4, \"monto_otorgado\":51094.0, \"cuota\":2839.0, \"saldo\":29042.0, \"tasa_externa\":0.0002, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":18.0, \"numero_pagos_restante\":9.0, \"frecuencia_externa\":\"M\", \"avance\":56.8403, \"fecha_inicio\":\"2023-08-24\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":5.76452576658221, \"ahorro_total\":138.34861839797304, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"GUBERNAMENTALES\", \"registro\":3, \"monto_otorgado\":10780.0, \"cuota\":599.0, \"saldo\":4304.0, \"tasa_externa\":0.0002, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":18.0, \"numero_pagos_restante\":9.0, \"frecuencia_externa\":\"M\", \"avance\":39.9258, \"fecha_inicio\":\"2023-08-24\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":1.2335614311613199, \"ahorro_total\":29.605474347871677, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":2, \"monto_otorgado\":30000.0, \"cuota\":2789.0, \"saldo\":26763.0, \"tasa_externa\":0.7407, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":18.0, \"numero_pagos_restante\":12.0, \"frecuencia_externa\":\"M\", \"avance\":89.21, \"fecha_inicio\":\"2023-12-08\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":55086.09281147826, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":85298.0, \"cuota\":7346.0, \"saldo\":79808.0, \"tasa_externa\":1.0263, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":97.0, \"numero_pagos_restante\":42.0, \"frecuencia_externa\":\"S\", \"avance\":93.5637, \"fecha_inicio\":\"2023-11-22\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":135605.10508778243, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"HIPOTECAGOBIERNO\", \"registro\":6, \"monto_otorgado\":0.0, \"cuota\":3210.0, \"saldo\":280802.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":360.0, \"numero_pagos_restante\":305.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2019-10-29\", \"tipo_deuda\":\"M\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":110268.0, \"estatus_tasa\":\"Falta monto\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}]}]}";
    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer,
        globalMaxAmount,
        extendedPaymentTerm);

    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(1)
        .setExternalRate(0.6193)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(2)
        .setExternalRate(0.6193)
        .setSelected(true)
        .setPayment(1000)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(3)
        .setExternalRate(0.6193)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(4)
        .setExternalRate(0.6193)
        .setBalance(10000)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(5)
        .setExternalRate(0.6193)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setExternalRate(0.6193)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(7)
        .setExternalRate(0.6193)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.updateOffer();

    // System. .println(flexCons);
    // System.out.println(flexCons.toJSONStringErrorOffer());
    // System.out.println(flexCons.toJSONString());
    // System.out.println(flexCons.toJSONStringPdf("Juan", null));

    System.out.println(" ");
    System.out.println("Original Rate: " + flexCons.getConsolidationOffer().getRate());
    System.out.println("Original kubo.score: " + flexCons.getConsolidationOffer().getKuboScore());
    System.out.println("Original Commission: " + flexCons.getConsolidationOffer().getCommissionRate());

    System.out.println(" ");
    System.out.println("Offer Rate: " + flexCons.getOfferRate());
    System.out.println("Offer kubo.score: " + flexCons.getOfferKuboScore());
    System.out.println("Offer Commission: " + flexCons.getOfferCommission());
    System.out.println("Offer Commission Amount: " + flexCons.getOfferCommissionAmount());

    System.out.println(" ");
    System.out.println("Status Offer: " + flexCons.getOfferStatus());
    System.out.println("Weighted Rate: " + flexCons.getWeightedRate());
    System.out.println("Total Savings: " + flexCons.getTotalSaving());

    // System.out.println(" ");
    // System.out.println("====== ACTAUALIZACION MANUAL DE TASA ======");
    // flexCons.setOfferRate(0.5991);
    // flexCons.updateOffer(false);

    // System.out.println("Offer Rate: " + flexCons.getOfferRate());
    // System.out.println("Offer kubo.score: " + flexCons.getOfferKuboScore());
    // System.out.println("Offer Commission: " + flexCons.getOfferCommission());
    // System.out.println("Offer Commission Amount: " +
    // flexCons.getOfferCommissionAmount());

    // System.out.println(" ");
    // System.out.println("Status Offer: " + flexCons.getOfferStatus());
    // System.out.println("Weighted Rate: " + flexCons.getWeightedRate());
    // System.out.println("Total Savings: " + flexCons.getTotalSaving());

    System.out.println(" ");
    System.out.println("====== SIMULACION INICIAL ======");
    System.out.println(flexCons.getCatSimulation());

    // System.out.println(" ");
    // System.out.println("====== SIMULACIONES ACTUALIZADAS ======");
    // flexCons.updateCatSimulation(18, 'K');
    // System.out.println(flexCons.getCatSimulation());
    // System.out.println(" ");

    // flexCons.updateCatSimulation(30, 'W');
    // System.out.println(flexCons.getCatSimulation());
    // System.out.println(" ");

    // flexCons.updateCatSimulation(20, 'S');
    // System.out.println(flexCons.getCatSimulation());
    // System.out.println(" ");

    // flexCons.updateCatSimulation(2, 'F');
    // System.out.println(flexCons.getCatSimulation());

    System.out.println(" ");
    System.out.println("====== OBJETO DE CONSOLIDACIONES FLEXIBLES ======");
    System.out.println(flexCons);
  }
}
