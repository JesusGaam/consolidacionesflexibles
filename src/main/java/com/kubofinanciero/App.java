package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    double globalMaxAmount = 200000;
    int extendedPaymentTerm = 6;
    String jsonOffer = "{\"Prospecto\": 2975182, \"monto_maximo_cliente\": 81533.0, \"cuota_externa_total\": 6995.0, \"capacidad_maxima_pago\": 19656.0, \"bursolnum\": 7558283, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 24340.068748853366, \"monto_max\": 87000.0, \"cuota_min\": 299.56297084790833, \"monto_min\": 5000, \"plazo_max\": 33.0, \"plazo_min\": 4, \"tasa\": 0.4818, \"kubo_score\": \"B5\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.4706, 0.4582, 0.4428, 0.4295, 0.3913, 0.3717, 0.3437, 0.3014], \"comisiones\": [0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"FINANCIERA\", \"registro\":7, \"monto_otorgado\":670.0, \"cuota\":287.0, \"saldo\":286.0, \"tasa_externa\":1.6633, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":3.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":42.6866, \"fecha_inicio\":\"2023-12-03\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":257.30435416777976, \"ahorro_total\":8491.043687536732, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Alta Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":4, \"monto_otorgado\":7768.0, \"cuota\":972.0, \"saldo\":7768.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2023-03-08\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":627.7077957840493, \"ahorro_total\":20714.357260873625, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Alta Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":5, \"monto_otorgado\":675.0, \"cuota\":163.0, \"saldo\":475.0, \"tasa_externa\":1.4291, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":6.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":70.3704, \"fecha_inicio\":\"2024-02-15\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":133.08274487052435, \"ahorro_total\":4391.730580727303, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Alta Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":2, \"monto_otorgado\":118432.0, \"cuota\":3533.0, \"saldo\":50116.0, \"tasa_externa\":0.4818, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":21.0, \"frecuencia_externa\":\"M\", \"avance\":42.3163, \"fecha_inicio\":\"2024-03-11\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":-51479.198563479615, \"estatus_tasa\":\"tasa_kubo\", \"tipo_revolvencia\":\"Alta Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":1, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2023-12-20\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Alta Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":3, \"monto_otorgado\":72420.0, \"cuota\":2040.0, \"saldo\":46410.0, \"tasa_externa\":0.0092, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":142.0, \"numero_pagos_restante\":23.0, \"frecuencia_externa\":\"W\", \"avance\":64.0845, \"fecha_inicio\":\"2023-05-08\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1169.7890614468529, \"ahorro_total\":-38603.03902774615, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Alta Revolvencia\", \"deuda_consolidable\":1}]}]}";
    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer,
        globalMaxAmount,
        extendedPaymentTerm);

    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(0)
        .setExternalRate(0.4818)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(2)
        .setExternalRate(0.4818)
        .setSelected(true)
        .setUploadedDocuments(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(5)
        .setExternalRate(0.4818)
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
