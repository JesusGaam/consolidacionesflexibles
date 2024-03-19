package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 4915388, \"monto_maximo_cliente\": 84538.0, \"cuota_externa_total\": 32718.0, \"capacidad_maxima_pago\": 111820.0, \"bursolnum\": 7354265, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 8357.500949471312, \"monto_max\": 84538.0, \"cuota_min\": 308.7596227438041, \"monto_min\": 5000, \"plazo_max\": 16.0, \"plazo_min\": 4, \"tasa\": 0.6193, \"comision\": 0.0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Autogestivo\", \"comprobante_tasa\": 0, \"comprobante_ingresos\": 0, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.6193, 0.5991, 0.5668, 0.5504, 0.5486, 0.5391, 0.5292, 0.4818, 0.4706, 0.4582, 0.4428, 0.4295, 0.3813, 0.3617, 0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0, 0.0481, 0.0481, 0.0481, 0.0476, 0.0471, 0.0466, 0.046, 0.0455, 0.0449, 0.0444, 0.0439, 0.0433, 0.0428, 0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"FINANCIERA\", \"registro\":28, \"monto_otorgado\":5000.0, \"cuota\":575.0, \"saldo\":574.0, \"tasa_externa\":0.6497, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":12.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.8852, \"fecha_inicio\":\"2022-02-25\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":80.69560733212796, \"ahorro_total\":1291.1297173140474, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":20000.0, \"cuota\":2415.0, \"saldo\":19286.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0357, \"fecha_inicio\":\"2019-07-24\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":508.3690966014842, \"ahorro_total\":8133.9055456237475, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":4, \"monto_otorgado\":12258.0, \"cuota\":1626.0, \"saldo\":10100.0, \"tasa_externa\":0.9725, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":12.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.176, \"fecha_inicio\":\"2023-08-31\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":414.1633509354449, \"ahorro_total\":6626.613614967118, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":5, \"monto_otorgado\":3150.0, \"cuota\":464.0, \"saldo\":463.0, \"tasa_externa\":0.7353, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":9.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.853, \"fecha_inicio\":\"2023-05-07\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":152.5882326192406, \"ahorro_total\":2441.41172190785, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":6, \"monto_otorgado\":2799.0, \"cuota\":607.0, \"saldo\":605.0, \"tasa_externa\":0.9835, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":6.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.7839, \"fecha_inicio\":\"2023-07-31\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":330.28840098452525, \"ahorro_total\":5284.614415752404, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":7, \"monto_otorgado\":2759.0, \"cuota\":597.0, \"saldo\":595.0, \"tasa_externa\":0.9746, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":6.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.7843, \"fecha_inicio\":\"2023-08-01\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":324.2428361258682, \"ahorro_total\":5187.885378013892, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":8, \"monto_otorgado\":40000.0, \"cuota\":2677.0, \"saldo\":38286.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.0429, \"fecha_inicio\":\"2023-11-06\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":-1107.9875955364296, \"ahorro_total\":-17727.801528582873, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"FINANCIERA\", \"registro\":29, \"monto_otorgado\":1000.0, \"cuota\":204.0, \"saldo\":204.0, \"tasa_externa\":0.7421, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":6.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.796, \"fecha_inicio\":\"2022-08-26\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":105.13912146642559, \"ahorro_total\":1682.2259434628095, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":370983.0, \"cuota\":11842.0, \"saldo\":163762.0, \"tasa_externa\":0.2308, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":48.0, \"numero_pagos_restante\":13.0, \"frecuencia_externa\":\"M\", \"avance\":0.5586, \"fecha_inicio\":\"2021-04-27\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-24833.70530102103, \"ahorro_total\":-397339.28481633647, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"CIA Q' OTORGA\", \"registro\":2, \"monto_otorgado\":185678.0, \"cuota\":0.0, \"saldo\":4498.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":0.9758, \"fecha_inicio\":\"2019-10-19\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":-444.67623164401766, \"ahorro_total\":-7114.819706304283, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":9, \"monto_otorgado\":84000.0, \"cuota\":6740.0, \"saldo\":83691.0, \"tasa_externa\":0.7392, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":20.0, \"frecuencia_externa\":\"M\", \"avance\":0.0037, \"fecha_inicio\":\"2023-11-09\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1564.31379682025, \"ahorro_total\":-25029.020749124, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":10, \"monto_otorgado\":63200.0, \"cuota\":4971.0, \"saldo\":46970.0, \"tasa_externa\":0.713, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":24.0, \"numero_pagos_restante\":12.0, \"frecuencia_externa\":\"M\", \"avance\":0.2568, \"fecha_inicio\":\"2023-03-27\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1277.0075233219022, \"ahorro_total\":-20432.120373150436, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"COMUNICACIONES\", \"registro\":11, \"monto_otorgado\":690.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.6193, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2018-03-28\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(6)
        .setExternalRate(0.70)
        .setSelected(false);

    flexCons.getConsolidationOffer().getBuroDebts().get(2)
        .setSelected(false);
    flexCons.updateOffer();

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
    System.out.println(flexCons.toJSONStringPdf("Juan", null));
  }
}
