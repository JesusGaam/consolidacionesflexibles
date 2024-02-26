package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 673131, \"monto_maximo_cliente\": 500000.0, \"cuota_externa_total\": 49972.0, \"capacidad_maxima_pago\": 939723.0, \"bursolnum\": 7262204, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 31287.480213814375, \"monto_max\": 500000.0, \"cuota_min\": 308.7596227438041, \"monto_min\": 5000, \"plazo_max\": 54.0, \"plazo_min\": 4, \"tasa\": 0.6193, \"comision\": 0.0, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.6193, 0.5991, 0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0, 0.0481, 0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":85000.0, \"cuota\":4250.0, \"saldo\":68898.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.1894, \"fecha_inicio\":\"2021-11-08\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":5, \"monto_otorgado\":2000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2005-12-12\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"CIA Q' OTORGA\", \"registro\":3, \"monto_otorgado\":443416.0, \"cuota\":0.0, \"saldo\":307831.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":0.3058, \"fecha_inicio\":\"2004-07-01\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"CIA Q' OTORGA\", \"registro\":1, \"monto_otorgado\":47000.0, \"cuota\":650, \"saldo\":31010.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.3402, \"fecha_inicio\":\"2007-01-13\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":8, \"monto_otorgado\":116890.0, \"cuota\":5735.0, \"saldo\":104580.0, \"tasa_externa\":0.5561, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":60.0, \"numero_pagos_restante\":37.0, \"frecuencia_externa\":\"M\", \"avance\":0.1053, \"fecha_inicio\":\"2022-03-23\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":7, \"monto_otorgado\":122650.0, \"cuota\":6132.0, \"saldo\":112776.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0805, \"fecha_inicio\":\"2006-01-26\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":6, \"monto_otorgado\":36900.0, \"cuota\":1845.0, \"saldo\":14242.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.614, \"fecha_inicio\":\"1997-10-08\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":613600.0, \"cuota\":36184.0, \"saldo\":512510.0, \"tasa_externa\":0.6928, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":120.0, \"numero_pagos_restante\":41.0, \"frecuencia_externa\":\"S\", \"avance\":0.1647, \"fecha_inicio\":\"2022-08-15\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":9, \"monto_otorgado\":36900.0, \"cuota\":1845.0, \"saldo\":14529.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0062, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.6063, \"fecha_inicio\":\"1997-10-08\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":18968.519786185625, \"ahorro_total\":1024300.0684540238, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}]}]}\n"
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
    // System.out.println(flexCons.toJSONStringPdf("Juan"));
  }
}
