package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    String jsonOffer = "{\"Prospecto\": 4472745, \"monto_maximo_cliente\": 51000.0, \"cuota_externa_total\": 6374.0, \"capacidad_maxima_pago\": 18144.2, \"bursolnum\": 7486138, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 14647.892763039756, \"monto_max\": 51000.0, \"cuota_min\": 557.935284484828, \"monto_min\": 5000, \"plazo_max\": 13.0, \"plazo_min\": 4, \"tasa\": 0.5991, \"kubo_score\": \"D1\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914], \"comisiones\": [0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['C5', 'C4', 'C3', 'C2', 'C1', 'B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"FINANCIERA\", \"registro\":1, \"monto_otorgado\":21500.0, \"cuota\":1100.0, \"saldo\":21500.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2023-08-02\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1185.2124477259044, \"ahorro_total\":-15407.761820436757, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":2, \"monto_otorgado\":10600.0, \"cuota\":2584.0, \"saldo\":7091.0, \"tasa_externa\":0.5991, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":4.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":66.8962, \"fecha_inicio\":\"2024-02-16\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":18945.382544342807, \"estatus_tasa\":\"tasa_kubo\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":4, \"monto_otorgado\":2979.0, \"cuota\":100.0, \"saldo\":2979.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-04-02\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-167.68478519885898, \"ahorro_total\":-2179.9022075851667, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":5, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2023-03-14\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":6, \"monto_otorgado\":14826.0, \"cuota\":87.0, \"saldo\":14826.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2021-07-07\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-834.5399883713607, \"ahorro_total\":-10849.01984882769, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":7, \"monto_otorgado\":4999.0, \"cuota\":72.0, \"saldo\":4999.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-04-12\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-281.38846633403693, \"ahorro_total\":-3658.05006234248, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":8, \"monto_otorgado\":7292.0, \"cuota\":2431.0, \"saldo\":4862.0, \"tasa_externa\":0.0008, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":3.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":66.6758, \"fecha_inicio\":\"2024-02-25\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":1655.940968892219, \"ahorro_total\":21527.232595598845, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":9, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2024-03-24\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer);
    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setExternalRate(.70)
        .setPayment(100)
        .setSelected(true)
        .setUploadedDocuments(true);
    flexCons.updateOffer();

    // System.out.println(flexCons);
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

    System.out.println(" ");
    System.out.println("====== SIMULACIONES ACTUALIZADAS ======");
    flexCons.updateCatSimulation(18, 'K');
    System.out.println(flexCons.getCatSimulation());
    System.out.println(" ");

    flexCons.updateCatSimulation(30, 'W');
    System.out.println(flexCons.getCatSimulation());
    System.out.println(" ");

    flexCons.updateCatSimulation(20, 'S');
    System.out.println(flexCons.getCatSimulation());
    System.out.println(" ");

    flexCons.updateCatSimulation(2, 'F');
    System.out.println(flexCons.getCatSimulation());

    System.out.println(" ");
    System.out.println("====== OBJETO DE CONSOLIDACIONES FLEXIBLES ======");
    System.out.println(flexCons);
  }
}
