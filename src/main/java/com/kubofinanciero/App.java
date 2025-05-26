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
        String jsonOffer = "{\"Prospecto\": 4944710, \"monto_maximo_cliente\": 40470.0, \"cuota_externa_total\": 9194.0, \"capacidad_maxima_pago\": 8620.65, \"bursolnum\": 7888479, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 11059.071737778298, \"monto_max\": 40470.0, \"cuota_min\": 2313.4298633614962, \"monto_min\": 25000, \"plazo_max\": 14.0, \"plazo_min\": 4, \"tasa\": 0.3783, \"kubo_score\": \"A2\", \"comision\": 0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.3783], \"comisiones\": [0.065], \"kubo_scores\": [\"A2\"], \"deudas_buro\": [ {\"entidad\": \"FINANCIERA\", \"registro\":7, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2021-05-04\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":5, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2023-08-01\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":8, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2021-06-24\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":3547.0, \"cuota\":400.0, \"saldo\":3547.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2023-12-17\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":71.77057098627091, \"ahorro_total\":1004.7879938077928, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":2, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-12-17\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":7333.0, \"cuota\":0.0, \"saldo\":7333.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-07-18\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-311.9252475211941, \"ahorro_total\":-4366.953465296717, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":6, \"monto_otorgado\":30000.0, \"cuota\":5626.0, \"saldo\":18563.0, \"tasa_externa\":0.4232, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":6.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":61.8767, \"fecha_inicio\":\"2024-06-03\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":2849.884163966204, \"ahorro_total\":39898.378295526854, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":4, \"monto_otorgado\":26210.0, \"cuota\":3168.0, \"saldo\":26210.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3783, \"numero_pagos_otorgado\":14.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-06-17\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":742.6001312518074, \"ahorro_total\":10396.401837525304, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Baja Revolvencia\", \"deuda_consolidable\":1}]}]}";
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
                .setBalance(40000)
                .setExternalRate(0.42)
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
