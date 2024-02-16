package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 341134, \"monto_maximo_cliente\": 100000.0, \"cuota_externa_total\": 14932.0, \"capacidad_maxima_pago\": 126768.0, \"bursolnum\": 7259739, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 5788.866525024883, \"monto_max\": 100000.0, \"cuota_min\": 173.6361078775173, \"monto_min\": 5000, \"plazo_max\": 24.0, \"plazo_min\": 4, \"tasa\": 0.5486, \"comision\": 0.0417, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":100000.0, \"cuota\":6241.0, \"saldo\":79244.0, \"tasa_externa\":0.4284, \"tasa_kubo\":0.0055, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":17.0, \"frecuencia_externa\":\"M\", \"avance\":0.2076, \"fecha_inicio\":\"2023-08-03\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":452.13347497511677, \"ahorro_total\":10851.203399402802, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":1, \"monto_otorgado\":472.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0055, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2021-05-08\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":452.13347497511677, \"ahorro_total\":10851.203399402802, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":34200.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0055, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2023-03-22\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":452.13347497511677, \"ahorro_total\":10851.203399402802, \"estatus_tasa\":\"Falta Pago_periodico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":99000.0, \"cuota\":1240.0, \"saldo\":73859.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.0055, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.2539, \"fecha_inicio\":\"2013-02-01\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":452.13347497511677, \"ahorro_total\":10851.203399402802, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":5, \"monto_otorgado\":100000.0, \"cuota\":7451.0, \"saldo\":70117.0, \"tasa_externa\":0.6433, \"tasa_kubo\":0.0055, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":11.0, \"frecuencia_externa\":\"M\", \"avance\":0.2988, \"fecha_inicio\":\"2023-01-17\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":452.13347497511677, \"ahorro_total\":10851.203399402802, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

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
    System.out.println(flexCons.toJSONStringPdf("Juan"));
  }
}
