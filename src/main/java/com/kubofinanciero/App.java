package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 5272657, \"monto_maximo_cliente\": 174000.0, \"cuota_externa_total\": 19494.0, \"capacidad_maxima_pago\": 174801.0, \"bursolnum\": 7397434, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 9961.65206988942, \"monto_max\": 174000.0, \"cuota_min\": 285.5882600985808, \"monto_min\": 5000, \"plazo_max\": 59.0, \"plazo_min\": 4, \"tasa\": 0.7007, \"comision\": 0.0481, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":6, \"monto_otorgado\":80000.0, \"cuota\":4232.0, \"saldo\":79706.0, \"tasa_externa\":0.6271, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":144.0, \"numero_pagos_restante\":68.0, \"frecuencia_externa\":\"S\", \"avance\":0.0037, \"fecha_inicio\":\"2023-12-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1302.5587135374599, \"ahorro_total\":-76850.96409871013, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":9, \"monto_otorgado\":10000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2023-02-13\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":8, \"monto_otorgado\":28400.0, \"cuota\":1420.0, \"saldo\":28395.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0002, \"fecha_inicio\":\"2023-09-11\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-544.4224333862021, \"ahorro_total\":-32120.923569785926, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":12285.0, \"cuota\":614.0, \"saldo\":10963.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.1076, \"fecha_inicio\":\"2021-03-30\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-144.44208970638965, \"ahorro_total\":-8522.083292676989, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":18, \"monto_otorgado\":10000.0, \"cuota\":-1.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":0.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":1.0, \"fecha_inicio\":\"2023-02-13\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1.0, \"ahorro_total\":-59.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":80000.0, \"cuota\":4228.0, \"saldo\":80418.0, \"tasa_externa\":0.6265, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":144.0, \"numero_pagos_restante\":70.0, \"frecuencia_externa\":\"S\", \"avance\":-0.0052, \"fecha_inicio\":\"2024-01-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1306.5587135374599, \"ahorro_total\":-77086.96409871013, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":14000.0, \"cuota\":2098.0, \"saldo\":7708.0, \"tasa_externa\":1.7593, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":48.0, \"numero_pagos_restante\":9.0, \"frecuencia_externa\":\"S\", \"avance\":0.4494, \"fecha_inicio\":\"2023-01-06\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":1129.4522251309445, \"ahorro_total\":66637.68128272572, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":15000.0, \"cuota\":816.0, \"saldo\":14167.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":19.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":0.0555, \"fecha_inicio\":\"2023-09-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-221.72975878827378, \"ahorro_total\":-13082.055768508153, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":4, \"monto_otorgado\":10100.0, \"cuota\":4900.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2023-12-11\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":4900.0, \"ahorro_total\":289100.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":7, \"monto_otorgado\":97368.0, \"cuota\":7196.0, \"saldo\":97384.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.7007, \"numero_pagos_otorgado\":40.0, \"numero_pagos_restante\":36.0, \"frecuencia_externa\":\"M\", \"avance\":-0.0002, \"fecha_inicio\":\"2023-11-21\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":459.8885897535574, \"ahorro_total\":27133.426795459887, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(2)
    // .setExternalRate(0.30)
    // .setSelected(true);
    // flexCons.updateOffer();

    // CAMBIOS PARA PROSPECTO 2842882: OFERTA SIN AHORROS NI MONTO RESTANTE
    // flexCons.getConsolidationOffer().getBuroDebts().get(1)
    // .setExternalRate(0.80)
    // .setSelected(false);
    // FIN CAMBIOS PARA PROSPECTO 2842882: OFERTA SIN AHORROS NI MONTO RESTANTE

    // CAMBIOS PARA PROSPECTO 2842882: OFERTA SIN AHORROS NI MONTO RESTANTE
    // flexCons.getConsolidationOffer().getBuroDebts().get(0)
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

    // System.out.println(flexCons);
    // System.out.println(flexCons.toJSONStringErrorOffer());
    // System.out.println(flexCons.toJSONStringPdf("Juan", null));
  }
}
