package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String jsonOffer = "{\"Prospecto\": 300943, \"monto_maximo_cliente\": 74100.0, \"cuota_externa_total\": 4039.0, \"capacidad_maxima_pago\": 10936.0, \"bursolnum\": 7242992, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 3723.22624375344, \"monto_max\": 91500.0, \"cuota_min\": 141.1247705288141, \"monto_min\": 5000, \"plazo_max\": 36.0, \"plazo_min\": 4, \"tasa\": 0.5668, \"comision\": 0.0481, \"proba\": 0, \"segmento\": 26, \"frecuencia\": [\"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.2306, 0.2], \"comisiones\": [0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":22905.0, \"cuota\":400.0, \"saldo\":11969.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.4459, \"fecha_inicio\":\"2021-02-26\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Desbordamiento numérico\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":19099.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":-1, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2019-06-24\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Falta cuota\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":3, \"monto_otorgado\":74100.0, \"cuota\":3019.0, \"saldo\":37442.0, \"tasa_externa\":0.6075, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":36, \"numero_pagos_restante\":17.0, \"frecuencia_externa\":\"M\", \"avance\":0.2512, \"fecha_inicio\":\"2022-07-14\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":91500.0, \"cuota\":4039.0, \"saldo\":50311.0, \"tasa_externa\":0.3351, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":36, \"numero_pagos_restante\":16.0, \"frecuencia_externa\":\"M\", \"avance\":0.4502, \"fecha_inicio\":\"2022-06-15\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":5, \"monto_otorgado\":74100.0, \"cuota\":3645.0, \"saldo\":52462.0, \"tasa_externa\":0.507, \"tasa_kubo\":0.5668, \"numero_pagos_otorgado\":46, \"numero_pagos_restante\":20.0, \"frecuencia_externa\":\"M\", \"avance\":0.292, \"fecha_inicio\":\"2021-12-20\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":315.77375624655997, \"ahorro_total\":11367.855224876159, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";
        ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);

        FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
        flexCons.initOffer();
        // flexCons.getConsolidationOffer().getBuroDebts().get(0).setSelected(true);
        // flexCons.getConsolidationOffer().getBuroDebts().get(2)
        // .setSelected(true)
        // .setExternalRate(0.32);

        // flexCons.getConsolidationOffer().getBuroDebts().get(5)
        // .setSelected(true)
        // .setExternalRate(0.2);
        // flexCons.getConsolidationOffer().getBuroDebts().get(6).setExternalRate(0.5);

        // flexCons.updateOffer();
        System.out.println(flexCons);
    }
}
