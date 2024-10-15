package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
    public static void main(String[] args) {
        double discountWeightedRate = 0.1;
        double minimumRateOffer = 0.3;
        double globalMinAmount = 25000;
        double globalMaxAmount = 200000;
        int extendedPaymentTerm = 0;
        String jsonOffer = "{\"Prospecto\":4253694,\"bursolnum\":7822495,\"Oferta Automatico\":[{\"tasa\":0.6273,\"proba\":0,\"comision\":0,\"segmento\":26,\"cuota_max\":8863.993528701914,\"cuota_min\":1888.5594872511808,\"loan_type\":\"CON\",\"monto_max\":31950,\"monto_min\":25000,\"plazo_max\":20,\"plazo_min\":4,\"comisiones\":[0.065,0.045,0.065,0.065,0.065,0.065,0.065,0.065,0.065,0.065,0.065,0.065,0.065],\"frecuencia\":[\"M\",\"S\",\"K\",\"W\"],\"kubo_score\":\"C4\",\"tipo_flujo\":\"Tradicional\",\"deudas_buro\":[{\"cuota\":50,\"saldo\":1838,\"avance\":-1,\"entidad\":\"TIENDA COMERCIAL\",\"registro\":1,\"tasa_kubo\":0.6273,\"tipo_deuda\":\"R\",\"ahorro_total\":-1383.5495457330237,\"estatus_tasa\":\"No calculable\",\"fecha_inicio\":\"2018-04-11\",\"tasa_externa\":-1,\"monto_otorgado\":1838,\"tipo_revolvencia\":\"Alta Revolvencia\",\"deuda_consolidable\":1,\"frecuencia_externa\":\"Z\",\"ahorro_cuota_mensual\":-69.17747728665117,\"numero_pagos_otorgado\":14,\"numero_pagos_restante\":-1},{\"cuota\":0,\"saldo\":0,\"avance\":-1,\"entidad\":\"COMUNICACIONES\",\"registro\":4,\"tasa_kubo\":0.6273,\"tipo_deuda\":\"O\",\"ahorro_total\":0,\"estatus_tasa\":\"No calculable\",\"fecha_inicio\":\"2023-12-29\",\"tasa_externa\":-1,\"monto_otorgado\":0,\"tipo_revolvencia\":\"Alta Revolvencia\",\"deuda_consolidable\":0,\"frecuencia_externa\":\"M\",\"ahorro_cuota_mensual\":0,\"numero_pagos_otorgado\":14,\"numero_pagos_restante\":-1},{\"cuota\":8553,\"saldo\":187608,\"avance\":71.9356,\"entidad\":\"BANCO\",\"registro\":6,\"tasa_kubo\":0.6273,\"tipo_deuda\":\"I\",\"ahorro_total\":-286056.4970224007,\"estatus_tasa\":\"Calculable\",\"fecha_inicio\":\"2023-02-10\",\"tasa_externa\":0.2469,\"monto_otorgado\":260800,\"tipo_revolvencia\":\"Alta Revolvencia\",\"deuda_consolidable\":0,\"frecuencia_externa\":\"M\",\"ahorro_cuota_mensual\":-14302.824851120036,\"numero_pagos_otorgado\":36,\"numero_pagos_restante\":28},{\"cuota\":1115,\"saldo\":28047,\"avance\":-1,\"entidad\":\"BANCO\",\"registro\":5,\"tasa_kubo\":0.6273,\"tipo_deuda\":\"R\",\"ahorro_total\":-21112.30365025795,\"estatus_tasa\":\"No calculable\",\"fecha_inicio\":\"2021-05-25\",\"tasa_externa\":-1,\"monto_otorgado\":28047,\"tipo_revolvencia\":\"Alta Revolvencia\",\"deuda_consolidable\":1,\"frecuencia_externa\":\"Z\",\"ahorro_cuota_mensual\":-1055.6151825128975,\"numero_pagos_otorgado\":14,\"numero_pagos_restante\":-1},{\"cuota\":3761,\"saldo\":43851,\"avance\":87.702,\"entidad\":\"KUBO FINANCIERO\",\"registro\":2,\"tasa_kubo\":0.6273,\"tipo_deuda\":\"I\",\"ahorro_total\":-12417.36522668727,\"estatus_tasa\":\"Calculable\",\"fecha_inicio\":\"2024-02-12\",\"tasa_externa\":0.6554,\"monto_otorgado\":50000,\"tipo_revolvencia\":\"Alta Revolvencia\",\"deuda_consolidable\":0,\"frecuencia_externa\":\"M\",\"ahorro_cuota_mensual\":-620.8682613343635,\"numero_pagos_otorgado\":24,\"numero_pagos_restante\":16},{\"cuota\":0,\"saldo\":0,\"avance\":-1,\"entidad\":\"CIA Q' OTORGA\",\"registro\":3,\"tasa_kubo\":0.6273,\"tipo_deuda\":\"R\",\"ahorro_total\":0,\"estatus_tasa\":\"No calculable\",\"fecha_inicio\":\"2024-08-15\",\"tasa_externa\":-1,\"monto_otorgado\":0,\"tipo_revolvencia\":\"Alta Revolvencia\",\"deuda_consolidable\":1,\"frecuencia_externa\":\"Z\",\"ahorro_cuota_mensual\":0,\"numero_pagos_otorgado\":14,\"numero_pagos_restante\":-1}],\"kubo_scores\":[\"C4\",\"C3\",\"C2\",\"C1\",\"B5\",\"B4\",\"B3\",\"B2\",\"B1\",\"A5\",\"A4\",\"A3\",\"A2\"],\"sub_loan_type\":\"CONSOLIDACION_FLEXIBLE\",\"tasa_asistida\":[0.6273,0.6255,0.616,0.6061,0.5587,0.5475,0.5351,0.5197,0.5064,0.4682,0.4486,0.4206,0.3783],\"comprobante_tasa\":1,\"comprobante_ingresos\":1}],\"cuota_externa_total\":13479,\"monto_maximo_cliente\":31950,\"capacidad_maxima_pago\":9131.25}";
        ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

        FlexibleConsolidations flexCons = new FlexibleConsolidations(
                consolidationOffer,
                discountWeightedRate,
                minimumRateOffer,
                globalMinAmount,
                globalMaxAmount,
                extendedPaymentTerm);
        flexCons.initOffer();

        flexCons.getConsolidationOffer().getBuroDebts().get(0)
                .setBalance(30000)
                .setExternalRate(0.696)
                // .setExternalRate(0.7)
                .setPayment(4500)
                .setSelected(true)
                .setUploadedDocuments(true);

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

        // System.out.println(" ");
        // System.out.println("====== SIMULACIONES ACTUALIZADAS ======");
        // flexCons.updateCatSimulation(52, 'K');
        // System.out.println(flexCons.getCatSimulation());
        // System.out.println(" ");

        // flexCons.updateCatSimulation(25, 'W');
        // System.out.println(flexCons.getCatSimulation());
        // System.out.println(" ");

        // flexCons.updateCatSimulation(12, 'S');
        // System.out.println(flexCons.getCatSimulation());
        // System.out.println(" ");

        // flexCons.updateCatSimulation(22, 'M');
        // System.out.println(flexCons.getCatSimulation());

        System.out.println(" ");
        System.out.println("====== OBJETO DE CONSOLIDACIONES FLEXIBLES ======");
        System.out.println(flexCons);

        // System.out.println(" ");
        // System.out.println("====== OBJETO PARA PDF V2 ======");
        // System.out.println(flexCons.toJSONStringPdfV2("José", "jose@mail.com"));
    }
}
