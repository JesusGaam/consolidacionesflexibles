package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 915334, \"monto_maximo_cliente\": 396656.0, \"cuota_externa_total\": 25526.0, \"capacidad_maxima_pago\": 390950.0, \"bursolnum\": 7262001, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 17032.558390112892, \"monto_max\": 396656.0, \"cuota_min\": 208.00254263563033, \"monto_min\": 5000, \"plazo_max\": 54.0, \"plazo_min\": 4, \"tasa\": 0.3813, \"comision\": 0.0, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.3813, 0.3617, 0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0, 0.0428, 0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":7, \"monto_otorgado\":15300.0, \"cuota\":1116.0, \"saldo\":15358.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-0.0038, \"fecha_inicio\":\"2017-06-23\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":336000.0, \"cuota\":16556.0, \"saldo\":311616.0, \"tasa_externa\":0.5792, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":144.0, \"numero_pagos_restante\":59.0, \"frecuencia_externa\":\"S\", \"avance\":0.0726, \"fecha_inicio\":\"2023-02-20\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":22400.0, \"cuota\":1500, \"saldo\":22322.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0035, \"fecha_inicio\":\"2014-09-13\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":15300.0, \"cuota\":704, \"saldo\":14035.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0827, \"fecha_inicio\":\"2017-06-23\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":4, \"monto_otorgado\":50000.0, \"cuota\":3282.0, \"saldo\":49566.0, \"tasa_externa\":0.6886, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":36.0, \"numero_pagos_restante\":28.0, \"frecuencia_externa\":\"M\", \"avance\":0.0087, \"fecha_inicio\":\"2023-07-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"GUBERNAMENTALES\", \"registro\":5, \"monto_otorgado\":70670.0, \"cuota\":2356.0, \"saldo\":47524.0, \"tasa_externa\":0.0001, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":30.0, \"numero_pagos_restante\":20.0, \"frecuencia_externa\":\"M\", \"avance\":0.3275, \"fecha_inicio\":\"2023-05-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"GUBERNAMENTALES\", \"registro\":6, \"monto_otorgado\":66472.0, \"cuota\":2216.0, \"saldo\":20676.0, \"tasa_externa\":0.0001, \"tasa_kubo\":0.0038, \"numero_pagos_otorgado\":30.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.689, \"fecha_inicio\":\"2022-03-07\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":2843.441609887108, \"ahorro_total\":153545.8469339038, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}\n"
        + //
        "";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    // System.out.println(consolidationOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(2).setSelected(false);
    // flexCons.updateOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(2)
    // .setSelected(true)
    // .setExternalRate(0.32);
    // flexCons.updateOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(5)
    // .setSelected(true)
    // .setExternalRate(0.2)
    // .setFinancialEntity("Coppel")
    // .setAwardedPaymentTerms(30);
    // flexCons.updateOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(6)
    // .setExternalRate(0.5);
    // flexCons.updateOffer();

    // System.out.println(flexCons);
    System.out.println(flexCons.toJSONStringPdf("Juan", "juan@mail.com"));
  }
}
