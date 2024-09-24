package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
    public static void main(String[] args) {
        double discountWeightedRate = 0.1;
        double minimumRateOffer = 0.22;
        double globalMinAmount = 25000;
        double globalMaxAmount = 200000;
        int extendedPaymentTerm = 0;
        String jsonOffer = "{\"Prospecto\": 3422804, \"monto_maximo_cliente\": 199155.0, \"cuota_externa_total\": 60870.0, \"capacidad_maxima_pago\": 17789.660000000003, \"bursolnum\": 7779521, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 57535.64839225935, \"monto_max\": 199155.0, \"cuota_min\": 1931.3392821562938, \"monto_min\": 25000, \"plazo_max\": 26.0, \"plazo_min\": 4, \"tasa\": 0.6255, \"kubo_score\": \"C3\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.616, 0.6061, 0.5587, 0.5475, 0.5351, 0.5197, 0.5064, 0.4682, 0.4486, 0.4206, 0.3783], \"comisiones\": [0.065, 0.065, 0.065, 0.065, 0.065, 0.065, 0.065, 0.065, 0.065, 0.065, 0.065], \"kubo_scores\": ['C2', 'C1', 'B5', 'B4', 'B3', 'B2', 'B1', 'A5', 'A4', 'A3', 'A2'], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":6038.0, \"cuota\":590.0, \"saldo\":6038.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2018-08-26\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":143.78305469443887, \"ahorro_total\":3450.793312666533, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":6, \"monto_otorgado\":239641.0, \"cuota\":11347.0, \"saldo\":177650.0, \"tasa_externa\":0.4893, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":48.0, \"numero_pagos_restante\":31.0, \"frecuencia_externa\":\"M\", \"avance\":74.1317, \"fecha_inicio\":\"2023-03-24\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":-152707.60777729037, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":5, \"monto_otorgado\":239641.0, \"cuota\":14009.0, \"saldo\":238851.0, \"tasa_externa\":0.6535, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":48.0, \"numero_pagos_restante\":41.0, \"frecuencia_externa\":\"M\", \"avance\":99.6703, \"fecha_inicio\":\"2024-02-14\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":-88819.60777729037, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":10, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2023-11-30\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":7, \"monto_otorgado\":69272.0, \"cuota\":4366.0, \"saldo\":69272.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2021-06-03\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-753.3011320316045, \"ahorro_total\":-18079.22716875851, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":1, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":-1.0, \"fecha_inicio\":\"2016-02-24\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"SERVICIOS\", \"registro\":8, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2018-08-25\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":324311.0, \"cuota\":12648.0, \"saldo\":324311.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2011-05-17\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":-186036.06299698632, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"SERVICIOS\", \"registro\":9, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"B\", \"avance\":-1.0, \"fecha_inicio\":\"2013-11-13\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":111690.0, \"cuota\":17910.0, \"saldo\":189406.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6255, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"1997-08-01\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":9655.94723067603, \"ahorro_total\":231742.73353622475, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}]}]}" ;
        ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

        FlexibleConsolidations flexCons = new FlexibleConsolidations(
                consolidationOffer,
                discountWeightedRate,
                minimumRateOffer,
                globalMinAmount,
                globalMaxAmount,
                extendedPaymentTerm);
        flexCons.initOffer();

        flexCons.getConsolidationOffer().getBuroDebts().get(9)
                .setAmountAwarded(14215)
                .setExternalRate(0.6324)
                .setSelected(true)
                .setUploadedDocuments(true);

        // flexCons.getConsolidationOffer().getBuroDebts().get(3)
        //         .setBalance(20000)
        //         .setPayment(2000)
        //         .setExternalRate(0.6193)
        //         .setSelected(true)
        //         .setUploadedDocuments(false);
        
        flexCons.updateOffer();

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
        System.out.println("Offer Amount: " + flexCons.getOfferAmount());
        System.out.println("Status Offer: " + flexCons.getOfferStatus());
        System.out.println("Weighted Rate: " + flexCons.getWeightedRate());

        // System.out.println(" ");
        // System.out.println("====== SIMULACION INICIAL ======");
        // System.out.println(flexCons.getCatSimulation());

        System.out.println(" ");
        System.out.println("====== SIMULACIONES ACTUALIZADAS ======");
        flexCons.updateCatSimulation(52, 'K');
        System.out.println(flexCons.getCatSimulation());
        System.out.println(" ");

        // flexCons.updateCatSimulation(25, 'W');
        // System.out.println(flexCons.getCatSimulation());
        // System.out.println(" ");

        // flexCons.updateCatSimulation(12, 'S');
        // System.out.println(flexCons.getCatSimulation());
        // System.out.println(" ");

        flexCons.updateCatSimulation(22, 'M');
        System.out.println(flexCons.getCatSimulation());

        System.out.println(" ");
        System.out.println("====== OBJETO DE CONSOLIDACIONES FLEXIBLES ======");
        System.out.println(flexCons);

        // System.out.println(" ");
        // System.out.println("====== OBJETO PARA PDF V2 ======");
        // System.out.println(flexCons.toJSONStringPdfV2("José", "jose@mail.com"));
    }
}
