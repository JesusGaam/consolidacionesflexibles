package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    String jsonOffer = "{\"Prospecto\": 1360950, \"monto_maximo_cliente\": 70378.0, \"cuota_externa_total\": 33180.0, \"capacidad_maxima_pago\": 17920.0, \"bursolnum\": 7439045, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 2842.5496999139004, \"monto_max\": 70378.0, \"cuota_min\": 249.09435350839087, \"monto_min\": 5000, \"plazo_max\": 15.0, \"plazo_min\": 4, \"tasa\": 0.4818, \"kubo_score\": \"B5\", \"comision\": 0.0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914], \"comisiones\": [0, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"COMUNICACIONES\", \"registro\":6, \"monto_otorgado\":10000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2020-01-16\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":10, \"monto_otorgado\":53000.0, \"cuota\":3724.0, \"saldo\":22085.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.5833, \"fecha_inicio\":\"2022-09-19\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":10.032892732850996, \"ahorro_total\":240.7894255884239, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":9, \"monto_otorgado\":36000.0, \"cuota\":2628.0, \"saldo\":25338.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":10.0, \"frecuencia_externa\":\"M\", \"avance\":0.2962, \"fecha_inicio\":\"2023-03-09\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":105.30536110155936, \"ahorro_total\":2527.3286664374245, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":7, \"monto_otorgado\":871.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2020-01-16\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":5, \"monto_otorgado\":871.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2020-01-16\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":8, \"monto_otorgado\":10000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2018-09-19\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":14, \"monto_otorgado\":12863.0, \"cuota\":4248.0, \"saldo\":2872.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":13.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"W\", \"avance\":0.7767, \"fecha_inicio\":\"2023-12-13\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-445.11245256584425, \"ahorro_total\":-1335.3373576975328, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":11, \"monto_otorgado\":30200.0, \"cuota\":3374.0, \"saldo\":13928.0, \"tasa_externa\":0.5872, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":12.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.5388, \"fecha_inicio\":\"2021-07-26\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":32.19289597740044, \"ahorro_total\":386.3147517288053, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":1, \"monto_otorgado\":10000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2020-11-24\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":24100.0, \"cuota\":1640.0, \"saldo\":23315.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0326, \"fecha_inicio\":\"2022-05-12\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":24955.00000000005, \"ahorro_total\":-24955.00000000005, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":3, \"monto_otorgado\":4000.0, \"cuota\":17566.0, \"saldo\":17566.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.4818, \"numero_pagos_otorgado\":1.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-3.3915, \"fecha_inicio\":\"2023-10-31\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":13379.70399999999, \"ahorro_total\":13379.70399999999, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer);
    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(7)
        .setExternalRate(0.32)
        .setUploadedDocuments(true)
        .setSelected(true);

    flexCons.getConsolidationOffer().getBuroDebts().get(9)
        .setExternalRate(0.798)
        .setUploadedDocuments(true)
        .setSelected(true);

    flexCons.getConsolidationOffer().getBuroDebts().get(10)
        .setExternalRate(0.62)
        .setUploadedDocuments(true)
        .setSelected(true);

    flexCons.updateOffer();

    // System.out.println(flexCons);
    // System.out.println(flexCons.toJSONStringErrorOffer());
    // System.out.println(flexCons.toJSONString());
    // System.out.println(flexCons.toJSONStringPdf("Juan", null));

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
    System.out.println("====== SIMULACION ======");
    System.out.println(flexCons.getCatSimulation());

    // System.out.println(" ");
    // System.out.println("====== SIMULACION ACTUALIZADA ======");
    // flexCons.updateCatSimulation(0, 'W');
    // System.out.println(flexCons.getCatSimulation());

    // System.out.println(" ");
    // System.out.println(flexCons);
  }
}
