package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 399145, \"monto_maximo_cliente\": 30000.0, \"cuota_externa_total\": 1565.0, \"capacidad_maxima_pago\": 1300.0, \"bursolnum\": 7242634, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 1510.584118770852, \"monto_max\": 19243.0, \"cuota_min\": 159.13577930390898, \"monto_min\": 5000, \"plazo_max\": 16.0, \"plazo_min\": 4, \"tasa\": 0.5292, \"comision\": 0.0466, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"HIPOTECAGOBIERNO\", \"registro\":7, \"monto_otorgado\":465334.0, \"cuota\":4596.0, \"saldo\":467815.0, \"tasa_externa\":0.1162, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":360, \"numero_pagos_restante\":298.0, \"frecuencia_externa\":\"M\", \"avance\":-0.0053, \"fecha_inicio\":\"2018-12-12\", \"tipo_deuda\":\"M\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":10, \"monto_otorgado\":4575.0, \"cuota\":437.0, \"saldo\":682.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.9805, \"fecha_inicio\":\"2021-10-01\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"MICROFINANCIERA\", \"registro\":14, \"monto_otorgado\":19243.0, \"cuota\":1565.0, \"saldo\":5513.0, \"tasa_externa\":0.3991, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":16, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.7135, \"fecha_inicio\":\"2022-04-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":8, \"monto_otorgado\":700.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":1.0, \"fecha_inicio\":\"2016-08-20\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":6, \"monto_otorgado\":30000.0, \"cuota\":1584.0, \"saldo\":9734.0, \"tasa_externa\":0.4852, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":36, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.6755, \"fecha_inicio\":\"2021-07-01\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"SERVICIOS\", \"registro\":9, \"monto_otorgado\":0.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"B\", \"avance\":-1.0, \"fecha_inicio\":\"2019-01-24\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":35593.0, \"cuota\":0.0, \"saldo\":17223.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.5694, \"fecha_inicio\":\"2017-10-12\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"CIA Q' OTORGA\", \"registro\":1, \"monto_otorgado\":28558.0, \"cuota\":0.0, \"saldo\":3142.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.9317, \"fecha_inicio\":\"2013-09-13\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"FINANCIERA\", \"registro\":5, \"monto_otorgado\":8168.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2022-01-03\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"COMUNICACIONES\", \"registro\":3, \"monto_otorgado\":899.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2010-06-25\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"SERVICIOS\", \"registro\":2, \"monto_otorgado\":720.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5292, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2021-01-23\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":54.415881229148, \"ahorro_total\":870.654099666368, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}\n";
    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();
    flexCons.getConsolidationOffer().getBuroDebts().get(0).setSelected(true);
    flexCons.updateOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(2)
        .setSelected(true)
        .setExternalRate(0.32);
    flexCons.updateOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(5)
        .setSelected(true)
        .setExternalRate(0.2)
        .setFinancialEntity("Coppel")
        .setAwardedPaymentTerms(30);
    flexCons.updateOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setExternalRate(0.5);
    flexCons.updateOffer();

    flexCons.setOfferRate(0.20);
    flexCons.updateOffer();

    System.out.println(flexCons);
  }
}
