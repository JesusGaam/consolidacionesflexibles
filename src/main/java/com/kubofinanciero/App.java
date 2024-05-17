package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    double globalMaxAmount = 200000;
    String jsonOffer = "{\"Prospecto\": 287957, \"monto_maximo_cliente\": 189000.0, \"cuota_externa_total\": 17038.0, \"capacidad_maxima_pago\": 21440.0, \"bursolnum\": 7518833, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 53562.0940598344, \"monto_max\": 200000.0, \"cuota_min\": 349.1410938376448, \"monto_min\": 5000, \"plazo_max\": 27.0, \"plazo_min\": 4, \"tasa\": 0.5391, \"kubo_score\": \"C2\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3913, 0.3717, 0.3437, 0.3014], \"comisiones\": [0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['C1', 'B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":8, \"monto_otorgado\":8301.0, \"cuota\":1363.0, \"saldo\":8301.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2016-04-08\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":944.6259696719455, \"ahorro_total\":25504.90118114253, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":7, \"monto_otorgado\":11000.0, \"cuota\":1264.0, \"saldo\":6600.0, \"tasa_externa\":0.648, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":12.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":60.0, \"fecha_inicio\":\"2023-09-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":19159.07011113936, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":6, \"monto_otorgado\":30127.0, \"cuota\":1859.0, \"saldo\":30127.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2024-01-12\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":340.5860243713655, \"ahorro_total\":9195.82265802687, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":2, \"monto_otorgado\":113174.0, \"cuota\":3188.0, \"saldo\":81294.0, \"tasa_externa\":0.0092, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":142.0, \"numero_pagos_restante\":26.0, \"frecuencia_externa\":\"W\", \"avance\":71.831, \"fecha_inicio\":\"2023-07-06\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-2516.01909509062, \"ahorro_total\":-67932.51556744674, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":3, \"monto_otorgado\":2840.0, \"cuota\":80.0, \"saldo\":2040.0, \"tasa_externa\":0.0092, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":142.0, \"numero_pagos_restante\":26.0, \"frecuencia_externa\":\"W\", \"avance\":71.831, \"fecha_inicio\":\"2023-07-06\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-63.13724203489633, \"ahorro_total\":-1704.705534942201, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":1, \"monto_otorgado\":4610.0, \"cuota\":3798.0, \"saldo\":3798.0, \"tasa_externa\":0.5391, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":1.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":82.3861, \"fecha_inicio\":\"2024-04-02\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":3565.6539838799745, \"ahorro_total\":96272.65756475931, \"estatus_tasa\":\"tasa_kubo\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":82, \"monto_otorgado\":10000.0, \"cuota\":2976.0, \"saldo\":6696.0, \"tasa_externa\":0.8949, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":16.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"W\", \"avance\":66.96, \"fecha_inicio\":\"2017-06-05\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":2471.995626637689, \"ahorro_total\":66743.8819192176, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":14942.0, \"cuota\":1030.0, \"saldo\":14942.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-12-26\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":276.9166653220349, \"ahorro_total\":7476.7499636949415, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":83, \"monto_otorgado\":5000.0, \"cuota\":1480.0, \"saldo\":2595.0, \"tasa_externa\":0.8658, \"tasa_kubo\":0.5391, \"numero_pagos_otorgado\":16.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"W\", \"avance\":51.9, \"fecha_inicio\":\"2017-02-10\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":1227.9978133188445, \"ahorro_total\":33155.9409596088, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}]}]}";
    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer,
        globalMaxAmount);

    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(0)
        .setExternalRate(0.6246)
        .setSelected(true)
        .setUploadedDocuments(true);

    flexCons.getConsolidationOffer().getBuroDebts().get(3)
        .setPayment(100)
        .setExternalRate(0.8)
        .setSelected(true)
    // .setUploadedDocuments(false)
    ;

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setPayment(615)
        .setSelected(true)
    // .setUploadedDocuments(false)
    ;

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
