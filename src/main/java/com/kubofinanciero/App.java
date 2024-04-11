package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    double discountWeightedRate = 0.1;
    double minimumRateOffer = 0.22;
    String jsonOffer = "{\"Prospecto\":24059,\"monto_maximo_cliente\":962287,\"cuota_externa_total\":41450,\"capacidad_maxima_pago\":524380,\"bursolnum\":6149272,\"Oferta Automatico\":[{\"loan_type\":\"CON\",\"sub_loan_type\":\"CONSOLIDACION_FLEXIBLE\",\"cuota_max\":19056.680029461608,\"monto_max\":962287,\"cuota_min\":189.49186879271792,\"monto_min\":5000,\"plazo_max\":59,\"plazo_min\":4,\"tasa\":0.6265,\"kubo_score\":\"D3\",\"comision\":0.0423,\"proba\":0,\"segmento\":26,\"tipo_flujo\":\"Tradicional\",\"comprobante_tasa\":1,\"comprobante_ingresos\":1,\"frecuencia\":[\"M\",\"S\",\"K\",\"W\"],\"tasa_asistida\":[0.6193,0.5991,0.5668,0.5504,0.5486,0.5391,0.5292,0.4818,0.4706,0.4582,0.4428,0.4295,0.3813,0.3617,0.3337,0.2914],\"comisiones\":[0.0514,0.0508,0.0502,0.0495,0.0489,0.0483,0.0477,0.047,0.0464,0.0458,0.0451,0.0445,0.0441,0.0432,0.0423,0.0417],\"kubo_scores\":[\"D2\",\"D1\",\"C5\",\"C4\",\"C3\",\"C2\",\"C1\",\"B5\",\"B4\",\"B3\",\"B2\",\"B1\",\"A5\",\"A4\",\"A3\",\"A2\"],\"deudas_buro\":[{\"entidad\":\"TIENDA COMERCIAL\",\"registro\":1,\"monto_otorgado\":90000,\"cuota\":6131,\"saldo\":36957,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":0.5894,\"fecha_inicio\":\"2007-09-19\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":43087.999999999905,\"ahorro_total\":-43087.999999999905,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"Light Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"CIA Q' OTORGA\",\"registro\":2,\"monto_otorgado\":259758,\"cuota\":0,\"saldo\":316127,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"M\",\"avance\":-0.217,\"fecha_inicio\":\"2021-03-19\",\"tipo_deuda\":\"O\",\"ahorro_cuota_mensual\":316126.9999999992,\"ahorro_total\":-316126.9999999992,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"CIA Q' OTORGA\",\"registro\":3,\"monto_otorgado\":25000,\"cuota\":13291,\"saldo\":6666,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":0.7334,\"fecha_inicio\":\"2022-04-23\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":19956.99999999998,\"ahorro_total\":-19956.99999999998,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"Transactor\",\"deuda_consolidable\":1},{\"entidad\":\"BANCO\",\"registro\":4,\"monto_otorgado\":500000,\"cuota\":20063,\"saldo\":410362,\"tasa_externa\":0.3741,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":48,\"numero_pagos_restante\":22,\"frecuencia_externa\":\"M\",\"avance\":0.1793,\"fecha_inicio\":\"2022-02-15\",\"tipo_deuda\":\"I\",\"ahorro_cuota_mensual\":-558.320581278731,\"ahorro_total\":-26799.387901379087,\"estatus_tasa\":\"Calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"BANCO\",\"registro\":5,\"monto_otorgado\":188100,\"cuota\":116,\"saldo\":83383,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":0.5567,\"fecha_inicio\":\"2020-02-11\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":83498.9999999998,\"ahorro_total\":-83498.9999999998,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"BANCO\",\"registro\":6,\"monto_otorgado\":418000,\"cuota\":1,\"saldo\":11913,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":0.9715,\"fecha_inicio\":\"2023-03-09\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":11913.999999999969,\"ahorro_total\":-11913.999999999969,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"TIENDA COMERCIAL\",\"registro\":9,\"monto_otorgado\":21000,\"cuota\":1050,\"saldo\":7241,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":0.6552,\"fecha_inicio\":\"2013-12-23\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":8290.999999999982,\"ahorro_total\":-8290.999999999982,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"Light Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"KUBO FINANCIERO\",\"registro\":7,\"monto_otorgado\":200000,\"cuota\":15001,\"saldo\":31618,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":25,\"numero_pagos_restante\":6,\"frecuencia_externa\":\"M\",\"avance\":0.8419,\"fecha_inicio\":\"2021-04-20\",\"tipo_deuda\":\"I\",\"ahorro_cuota_mensual\":3224.5802279166437,\"ahorro_total\":80614.50569791609,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":0},{\"entidad\":\"BANCO\",\"registro\":8,\"monto_otorgado\":380000,\"cuota\":39530,\"saldo\":0,\"tasa_externa\":-1,\"tasa_kubo\":0.3337,\"numero_pagos_otorgado\":900,\"numero_pagos_restante\":802,\"frecuencia_externa\":\"Z\",\"avance\":1,\"fecha_inicio\":\"2016-02-12\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":39530,\"ahorro_total\":35577000,\"estatus_tasa\":\"No calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":0}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(
        consolidationOffer,
        discountWeightedRate,
        minimumRateOffer);
    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(0)
        .setExternalRate(0.4130)
        .setUploadedDocuments(true)
        .setSelected(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(3)
        .setExternalRate(0.5918)
        .setUploadedDocuments(true)
        .setSelected(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(4)
        .setExternalRate(0.724)
        .setUploadedDocuments(true)
        .setSelected(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(5)
        .setExternalRate(0.3912)
        .setUploadedDocuments(true)
        .setSelected(true);

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setExternalRate(0.5343)
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

    System.out.println(" ");
    System.out.println("====== ACTAUALIZACION MANUAL DE TASA ======");
    flexCons.setOfferRate(0.5991);
    flexCons.updateOffer(false);

    System.out.println("Offer Rate: " + flexCons.getOfferRate());
    System.out.println("Offer kubo.score: " + flexCons.getOfferKuboScore());
    System.out.println("Offer Commission: " + flexCons.getOfferCommission());
    System.out.println("Offer Commission Amount: " + flexCons.getOfferCommissionAmount());

    System.out.println(" ");
    System.out.println("Status Offer: " + flexCons.getOfferStatus());
    System.out.println("Weighted Rate: " + flexCons.getWeightedRate());
    System.out.println("Total Savings: " + flexCons.getTotalSaving());

    System.out.println(" ");
    System.out.println("====== SIMULACION ======");
    System.out.println(flexCons.getCatSimulation());

    System.out.println(" ");
    System.out.println("====== SIMULACION ACTUALIZADA ======");
    flexCons.updateCatSimulation(0, 'W');
    System.out.println(flexCons.getCatSimulation());

    // System.out.println(" ");
    // System.out.println(flexCons);
  }
}
