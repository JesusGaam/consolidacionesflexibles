package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 957527, \"monto_maximo_cliente\": 360945.0, \"cuota_externa_total\": 35131.0, \"capacidad_maxima_pago\": 367456.0, \"bursolnum\": 7262364, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 21953.446212106446, \"monto_max\": 360945.0, \"cuota_min\": 299.79381766541826, \"monto_min\": 5000, \"plazo_max\": 54.0, \"plazo_min\": 4, \"tasa\": 0.5991, \"comision\": 0.0, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5991, 0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0, 0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":1, \"monto_otorgado\":10800.0, \"cuota\":1800, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.006, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2012-07-16\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":739.5537878935538, \"ahorro_total\":39935.904546251906, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":186600.0, \"cuota\":11788.0, \"saldo\":90098.0, \"tasa_externa\":0.7483, \"tasa_kubo\":0.006, \"numero_pagos_otorgado\":120.0, \"numero_pagos_restante\":17.0, \"frecuencia_externa\":\"S\", \"avance\":0.5172, \"fecha_inicio\":\"2020-08-18\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":739.5537878935538, \"ahorro_total\":39935.904546251906, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":10000.0, \"cuota\":1110.0, \"saldo\":11978.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.006, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-0.1978, \"fecha_inicio\":\"2013-10-01\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":739.5537878935538, \"ahorro_total\":39935.904546251906, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":126300.0, \"cuota\":7995.0, \"saldo\":126264.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.006, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0003, \"fecha_inicio\":\"2019-01-10\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":739.5537878935538, \"ahorro_total\":39935.904546251906, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":4, \"monto_otorgado\":224000.0, \"cuota\":14238.0, \"saldo\":214947.0, \"tasa_externa\":0.6876, \"tasa_kubo\":0.006, \"numero_pagos_otorgado\":40.0, \"numero_pagos_restante\":29.0, \"frecuencia_externa\":\"M\", \"avance\":0.0404, \"fecha_inicio\":\"2023-04-12\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":739.5537878935538, \"ahorro_total\":39935.904546251906, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}\n"
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
