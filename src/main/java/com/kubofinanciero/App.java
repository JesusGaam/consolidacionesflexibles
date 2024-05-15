package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    String jsonOffer = "{\"Prospecto\": 4837594, \"monto_maximo_cliente\": 55000.0, \"cuota_externa_total\": 17577.0, \"capacidad_maxima_pago\": 21723.4, \"bursolnum\": 7505998, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 15683.621960007607, \"monto_max\": 58000.0, \"cuota_min\": 520.7090576485808, \"monto_min\": 5000, \"plazo_max\": 14.0, \"plazo_min\": 4, \"tasa\": 0.5668, \"kubo_score\": \"C5\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914], \"comisiones\": [0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417], \"kubo_scores\": ['C4', 'C3', 'C2', 'C1', 'B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":15000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":0.5668, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":7.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":0.0, \"fecha_inicio\":\"2023-09-11\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":-22058.524151752328, \"estatus_tasa\":\"tasa_kubo\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":2, \"monto_otorgado\":13753.0, \"cuota\":358.0, \"saldo\":13753.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2024-01-19\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-756.9732507573797, \"ahorro_total\":-10597.625510603315, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":3, \"monto_otorgado\":50000.0, \"cuota\":5814.0, \"saldo\":48087.0, \"tasa_externa\":0.6739, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":12.0, \"numero_pagos_restante\":9.0, \"frecuencia_externa\":\"M\", \"avance\":96.174, \"fecha_inicio\":\"2024-02-14\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":7867.586160825578, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":4, \"monto_otorgado\":50000.0, \"cuota\":5921.0, \"saldo\":11263.0, \"tasa_externa\":0.714, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":12.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":22.526, \"fecha_inicio\":\"2023-05-09\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":9365.586160825578, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":13955.0, \"cuota\":250.0, \"saldo\":13955.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-12-20\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-768.0914501795414, \"ahorro_total\":-10753.28030251358, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":6, \"monto_otorgado\":5622.0, \"cuota\":708.0, \"saldo\":5622.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-06-22\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":117.46179628023049, \"ahorro_total\":1644.4651479232268, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":8, \"monto_otorgado\":1458.0, \"cuota\":1458.0, \"saldo\":1458.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-10-09\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":1304.8508180321196, \"ahorro_total\":18267.911452449676, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":9, \"monto_otorgado\":159.0, \"cuota\":159.0, \"saldo\":159.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-10-18\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":142.29854599938753, \"ahorro_total\":1992.1796439914253, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":10, \"monto_otorgado\":1391.0, \"cuota\":465.0, \"saldo\":1391.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-11-09\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":318.88853764244055, \"ahorro_total\":4464.439526994168, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":11, \"monto_otorgado\":204.0, \"cuota\":102.0, \"saldo\":204.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-12-02\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":80.5717193954406, \"ahorro_total\":1128.0040715361683, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":12, \"monto_otorgado\":2576.0, \"cuota\":644.0, \"saldo\":2576.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-12-30\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":373.4154370718381, \"ahorro_total\":5227.8161190057335, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":13, \"monto_otorgado\":15009.0, \"cuota\":1698.0, \"saldo\":15009.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2024-03-24\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":121.4457666969015, \"ahorro_total\":1700.240733756621, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer);
    flexCons.initOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(6)
    // .setExternalRate(.70)
    // .setPayment(100)
    // .setSelected(true)
    // .setUploadedDocuments(true);
    // flexCons.updateOffer();

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
