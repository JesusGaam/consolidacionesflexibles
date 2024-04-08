package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 24059, \"monto_maximo_cliente\": 962287.0, \"cuota_externa_total\": 41450.0, \"capacidad_maxima_pago\": 524380.0, \"bursolnum\": 6149272, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 19056.680029461608, \"monto_max\": 962287.0, \"cuota_min\": 189.49186879271792, \"monto_min\": 5000, \"plazo_max\": 59.0, \"plazo_min\": 4, \"tasa\": 0.6265, \"kubo_score\": \"D3\", \"comision\": 0.0423, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Tradicional\", \"comprobante_tasa\": 1, \"comprobante_ingresos\": 1, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0.0423, 0.0417, 0.03845, 0.03845, 0.03845], \"kubo_scores\": ['A3', 'A2', 'K3', 'K2', 'K1'], \"deudas_buro\": [ {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":1, \"monto_otorgado\":90000.0, \"cuota\":6131.0, \"saldo\":36957.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.5894, \"fecha_inicio\":\"2007-09-19\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":43087.999999999905, \"ahorro_total\":-43087.999999999905, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"CIA Q' OTORGA\", \"registro\":2, \"monto_otorgado\":259758.0, \"cuota\":0.0, \"saldo\":316127.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"M\", \"avance\":-0.217, \"fecha_inicio\":\"2021-03-19\", \"tipo_deuda\":\"O\", \"ahorro_cuota_mensual\":316126.9999999992, \"ahorro_total\":-316126.9999999992, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"CIA Q' OTORGA\", \"registro\":3, \"monto_otorgado\":25000.0, \"cuota\":13291.0, \"saldo\":6666.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.7334, \"fecha_inicio\":\"2022-04-23\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":19956.99999999998, \"ahorro_total\":-19956.99999999998, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":500000.0, \"cuota\":20063.0, \"saldo\":410362.0, \"tasa_externa\":0.3741, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":48.0, \"numero_pagos_restante\":22.0, \"frecuencia_externa\":\"M\", \"avance\":0.1793, \"fecha_inicio\":\"2022-02-15\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-558.320581278731, \"ahorro_total\":-26799.387901379087, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":188100.0, \"cuota\":116.0, \"saldo\":83383.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.5567, \"fecha_inicio\":\"2020-02-11\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":83498.9999999998, \"ahorro_total\":-83498.9999999998, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":6, \"monto_otorgado\":418000.0, \"cuota\":1.0, \"saldo\":11913.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.9715, \"fecha_inicio\":\"2023-03-09\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":11913.999999999969, \"ahorro_total\":-11913.999999999969, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":9, \"monto_otorgado\":21000.0, \"cuota\":1050.0, \"saldo\":7241.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.6552, \"fecha_inicio\":\"2013-12-23\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":8290.999999999982, \"ahorro_total\":-8290.999999999982, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":7, \"monto_otorgado\":200000.0, \"cuota\":15001.0, \"saldo\":31618.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":25.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"M\", \"avance\":0.8419, \"fecha_inicio\":\"2021-04-20\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":3224.5802279166437, \"ahorro_total\":80614.50569791609, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":8, \"monto_otorgado\":380000.0, \"cuota\":39530.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":900.0, \"numero_pagos_restante\":802.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2016-02-12\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":39530.0, \"ahorro_total\":35577000.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(0)
    // .setAmountAwarded(10000)
    // .setExternalRate(0.1)
    // .setSelected(true);

    // flexCons.getConsolidationOffer().getBuroDebts().get(3)
    // .setAmountAwarded(20000)
    // .setExternalRate(0.4)
    // .setSelected(true);

    // flexCons.getConsolidationOffer().getBuroDebts().get(5)
    // .setBalance(100000)
    // .setExternalRate(0.8)
    // .setSelected(true);

    // flexCons.updateOffer();

    System.out.println(flexCons);
    // System.out.println(flexCons.toJSONStringErrorOffer());
    // System.out.println(flexCons.toJSONString());
    // System.out.println(flexCons.toJSONStringPdf("Juan", null));

    System.out.println("WeightedRate: " + flexCons.getWeightedRate());
  }
}
