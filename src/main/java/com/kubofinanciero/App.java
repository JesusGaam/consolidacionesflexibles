package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 4539704, \"monto_maximo_cliente\": 419195.0, \"cuota_externa_total\": 21247.0, \"capacidad_maxima_pago\": 416988.0, \"bursolnum\": 7355231, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 18386.536241682767, \"monto_max\": 419195.0, \"cuota_min\": 173.6361078775173, \"monto_min\": 5000, \"plazo_max\": 37.0, \"plazo_min\": 4, \"tasa\": 0.5292, \"comision\": 0.0417, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"KUBO FINANCIERO\", \"registro\":5, \"monto_otorgado\":10000.0, \"cuota\":1419.0, \"saldo\":10205.0, \"tasa_externa\":0.6318, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":9.0, \"numero_pagos_restante\":7.0, \"frecuencia_externa\":\"M\", \"avance\":-0.0205, \"fecha_inicio\":\"2024-01-17\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":811.5364193138869, \"ahorro_total\":30026.847514613815, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"SERVICIOS\", \"registro\":7, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"B\", \"avance\":-1.0, \"fecha_inicio\":\"2017-12-19\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"SERVICIOS\", \"registro\":4, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":-1.0, \"fecha_inicio\":\"2022-08-05\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":6, \"monto_otorgado\":60000.0, \"cuota\":3932.0, \"saldo\":60079.0, \"tasa_externa\":0.7549, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":96.0, \"numero_pagos_restante\":46.0, \"frecuencia_externa\":\"S\", \"avance\":-0.0013, \"fecha_inicio\":\"2024-01-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":287.21851588332083, \"ahorro_total\":10627.08508768287, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":200000.0, \"cuota\":12452.0, \"saldo\":191745.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":5.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":0.0413, \"fecha_inicio\":\"2023-11-06\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":302.7283862777367, \"ahorro_total\":11200.950292276259, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":140988.0, \"cuota\":2938.0, \"saldo\":69517.0, \"tasa_externa\":0.0001, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":96.0, \"numero_pagos_restante\":44.0, \"frecuencia_externa\":\"S\", \"avance\":0.5069, \"fecha_inicio\":\"2023-11-18\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-5626.507531377372, \"ahorro_total\":-208180.77866096277, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":18207.0, \"cuota\":506.0, \"saldo\":9354.0, \"tasa_externa\":0.0003, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":72.0, \"numero_pagos_restante\":27.0, \"frecuencia_externa\":\"S\", \"avance\":0.4862, \"fecha_inicio\":\"2023-06-26\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-600.0089413552062, \"ahorro_total\":-22200.33083014263, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}]}]}\n"
        + //
        "";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(2)
    // .setExternalRate(0.30)
    // .setSelected(true);
    // flexCons.updateOffer();

    // CAMBIOS PARA PROSPECTO 2842882: OFERTA SIN AHORROS NI MONTO RESTANTE
    // flexCons.getConsolidationOffer().getBuroDebts().get(2)
    // .setExternalRate(0.30)
    // .setSelected(true);
    // flexCons.updateOffer();
    // FIN CAMBIOS PARA PROSPECTO 2842882: OFERTA SIN AHORROS NI MONTO RESTANTE

    // CAMBIOS PARA PROSPECTO 1051311
    // flexCons.getConsolidationOffer().getBuroDebts().get(1)
    // .setExternalRate(0.60)
    // .setSelected(true);

    // flexCons.getConsolidationOffer().getBuroDebts().get(5)
    // .setExternalRate(0.50)
    // .setSelected(true);
    // flexCons.updateOffer();
    // FIN CAMBIOS PARA PROSPECTO 1051311

    // flexCons.getConsolidationOffer().getBuroDebts().get(7)
    // .setSelected(false);
    // flexCons.updateOffer();

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

    System.out.println(flexCons);
    // System.out.println(flexCons.toJSONStringErrorOffer());
    // System.out.println(flexCons.toJSONStringPdf("Juan",
    // "jesus.garcia@gmail.com"));
  }
}
