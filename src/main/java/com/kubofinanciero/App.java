package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    String jsonOffer = "{\"Prospecto\": 189189, \"monto_maximo_cliente\": 528488.0, \"cuota_externa_total\": 32607.0, \"capacidad_maxima_pago\": 505681.0, \"bursolnum\": 7438900, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 35517.0132982702, \"monto_max\": 528488.0, \"cuota_min\": 299.79381766541826, \"monto_min\": 5000, \"plazo_max\": 30.0, \"plazo_min\": 4, \"tasa\": 0.5991, \"kubo_score\": \"D1\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914], \"comisiones\": [0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['C5', 'C4', 'C3', 'C2', 'C1', 'B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"KUBO FINANCIERO\", \"registro\":12, \"monto_otorgado\":78000.0, \"cuota\":4897.0, \"saldo\":59756.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":36.0, \"numero_pagos_restante\":18.0, \"frecuencia_externa\":\"M\", \"avance\":0.2339, \"fecha_inicio\":\"2022-10-27\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-305.75790950674855, \"ahorro_total\":-11007.284742242948, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":9, \"monto_otorgado\":1921.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2012-02-10\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":4, \"monto_otorgado\":20000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2021-10-18\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":13, \"monto_otorgado\":15000.0, \"cuota\":8126.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2015-09-19\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":8126.0, \"ahorro_total\":-8126.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":11, \"monto_otorgado\":80000.0, \"cuota\":4428.0, \"saldo\":79840.0, \"tasa_externa\":0.6591, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":144.0, \"numero_pagos_restante\":67.0, \"frecuencia_externa\":\"S\", \"avance\":0.002, \"fecha_inicio\":\"2023-12-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-286.90077940367246, \"ahorro_total\":-20656.856117064417, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":10, \"monto_otorgado\":50.0, \"cuota\":53.0, \"saldo\":51.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":1.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-0.02, \"fecha_inicio\":\"2024-02-17\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":0.1043500000001032, \"ahorro_total\":0.1043500000001032, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":8, \"monto_otorgado\":80000.0, \"cuota\":4456.0, \"saldo\":73348.0, \"tasa_externa\":0.2063, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":43.0, \"numero_pagos_restante\":17.0, \"frecuencia_externa\":\"S\", \"avance\":0.0831, \"fecha_inicio\":\"2023-12-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-2067.5869610299123, \"ahorro_total\":-45486.91314265807, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":7, \"monto_otorgado\":80000.0, \"cuota\":4456.0, \"saldo\":73348.0, \"tasa_externa\":0.2063, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":43.0, \"numero_pagos_restante\":17.0, \"frecuencia_externa\":\"S\", \"avance\":0.0831, \"fecha_inicio\":\"2023-12-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-2067.5869610299123, \"ahorro_total\":-45486.91314265807, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":6, \"monto_otorgado\":80000.0, \"cuota\":4428.0, \"saldo\":79188.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":43.0, \"numero_pagos_restante\":19.0, \"frecuencia_externa\":\"S\", \"avance\":0.0101, \"fecha_inicio\":\"2024-01-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-2095.5869610299123, \"ahorro_total\":-46102.91314265807, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":80000.0, \"cuota\":4428.0, \"saldo\":79188.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":43.0, \"numero_pagos_restante\":19.0, \"frecuencia_externa\":\"S\", \"avance\":0.0101, \"fecha_inicio\":\"2024-01-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-2095.5869610299123, \"ahorro_total\":-46102.91314265807, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":80000.0, \"cuota\":4428.0, \"saldo\":79188.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":43.0, \"numero_pagos_restante\":19.0, \"frecuencia_externa\":\"S\", \"avance\":0.0101, \"fecha_inicio\":\"2024-01-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-2095.5869610299123, \"ahorro_total\":-46102.91314265807, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":82620.0, \"cuota\":4131.0, \"saldo\":46157.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.4413, \"fecha_inicio\":\"2019-09-19\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":50287.99999999995, \"ahorro_total\":-50287.99999999995, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":1, \"monto_otorgado\":22000.0, \"cuota\":5000.0, \"saldo\":2281.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5991, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.8963, \"fecha_inicio\":\"2017-04-20\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":7280.999999999997, \"ahorro_total\":-7280.999999999997, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer);
    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(4)
        .setUploadedDocuments(true)
        .setSelected(true);

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setUploadedDocuments(true)
        .setSelected(true);

    flexCons.getConsolidationOffer().getBuroDebts().get(7)
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

    // System.out.println(" ");
    // System.out.println("====== SIMULACION ======");
    // System.out.println(flexCons.getCatSimulation());

    // System.out.println(" ");
    // System.out.println("====== SIMULACION ACTUALIZADA ======");
    // flexCons.updateCatSimulation(0, 'W');
    // System.out.println(flexCons.getCatSimulation());

    System.out.println(" ");
    System.out.println(flexCons);
  }
}
