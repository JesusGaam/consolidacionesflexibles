package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 738919, \"monto_maximo_cliente\": 171813.0, \"cuota_externa_total\": 14510.0, \"capacidad_maxima_pago\": 187124.0, \"bursolnum\": 7261892, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 8067.485326803379, \"monto_max\": 171813.0, \"cuota_min\": 227.39637225020516, \"monto_min\": 5000, \"plazo_max\": 53.0, \"plazo_min\": 4, \"tasa\": 0.7007, \"comision\": 0.0439, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.4295, 0.3813, 0.3617, 0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0.0439, 0.0433, 0.0428, 0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":160000.0, \"cuota\":8036.0, \"saldo\":104861.0, \"tasa_externa\":0.5355, \"tasa_kubo\":0.007, \"numero_pagos_otorgado\":96.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":0.3446, \"fecha_inicio\":\"2016-06-16\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":603.5146731966206, \"ahorro_total\":31986.27767942089, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":12700.0, \"cuota\":635.0, \"saldo\":7137.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.007, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.438, \"fecha_inicio\":\"2022-06-28\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":603.5146731966206, \"ahorro_total\":31986.27767942089, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":1, \"monto_otorgado\":1700.0, \"cuota\":1287.0, \"saldo\":1287.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.007, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":0.2429, \"fecha_inicio\":\"2022-11-20\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":603.5146731966206, \"ahorro_total\":31986.27767942089, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":3, \"monto_otorgado\":65000.0, \"cuota\":4787.0, \"saldo\":64934.0, \"tasa_externa\":0.8073, \"tasa_kubo\":0.007, \"numero_pagos_otorgado\":36.0, \"numero_pagos_restante\":29.0, \"frecuencia_externa\":\"M\", \"avance\":0.001, \"fecha_inicio\":\"2023-08-14\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":603.5146731966206, \"ahorro_total\":31986.27767942089, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}\n"
        + //
        "";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    // System.out.println(consolidationOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(7).setExternalRate(0.3);
    flexCons.updateOffer();

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

    System.out.println(flexCons);
    System.out.println(flexCons.toJSONStringPdf("Juan", "juan@mail.com"));
  }
}
