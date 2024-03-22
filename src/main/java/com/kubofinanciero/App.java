package com.kubofinanciero;

import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.utils.JsonAdapter;

public class App {
  public static void main(String[] args) {
    String jsonOffer = "{\"Prospecto\": 39500, \"monto_maximo_cliente\": 71900.0, \"cuota_externa_total\": 27925.0, \"capacidad_maxima_pago\": 81060.0, \"bursolnum\": 7317209, \"Oferta Automatico\": [{\"loan_type\": \"CON\", \"sub_loan_type\": \"CONSOLIDACION_FLEXIBLE\" , \"cuota_max\": 2965.3458995878814, \"monto_max\": 71900.0, \"cuota_min\": 189.49186879271792, \"monto_min\": 5000, \"plazo_max\": 48.0, \"plazo_min\": 4, \"tasa\": 0.3337, \"comision\": 0.0, \"proba\": 0, \"segmento\": 26, \"tipo_flujo\": \"Autogestivo\", \"comprobante_tasa\": 0, \"comprobante_ingresos\": 0, \"frecuencia\": [\"M\", \"S\", \"K\", \"W\"] , \"tasa_asistida\": [0.3337, 0.2914, 0.2622, 0.2306, 0.2], \"comisiones\": [0, 0.0417, 0.03845, 0.03845, 0.03845], \"deudas_buro\": [ {\"entidad\": \"FINANCIERA\", \"registro\":13, \"monto_otorgado\":137585.0, \"cuota\":4665.0, \"saldo\":82503.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":36.0, \"numero_pagos_restante\":20.0, \"frecuencia_externa\":\"M\", \"avance\":0.4003, \"fecha_inicio\":\"2022-11-29\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-1850.9934737731373, \"ahorro_total\":-66635.76505583295, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":10, \"monto_otorgado\":100000.0, \"cuota\":4632.0, \"saldo\":94959.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":36.0, \"numero_pagos_restante\":29.0, \"frecuencia_externa\":\"M\", \"avance\":0.0504, \"fecha_inicio\":\"2023-09-13\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-103.97664990597605, \"ahorro_total\":-3743.1593966151377, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":9, \"monto_otorgado\":65000.0, \"cuota\":3101.0, \"saldo\":65669.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":36.0, \"numero_pagos_restante\":32.0, \"frecuencia_externa\":\"M\", \"avance\":-0.0103, \"fecha_inicio\":\"2023-12-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":22.615177561115615, \"ahorro_total\":814.1463922001622, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":16, \"monto_otorgado\":71900.0, \"cuota\":4588.0, \"saldo\":72761.0, \"tasa_externa\":0.7567, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":120.0, \"numero_pagos_restante\":57.0, \"frecuencia_externa\":\"S\", \"avance\":-0.012, \"fecha_inicio\":\"2024-01-04\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":1863.1069267607159, \"ahorro_total\":111786.41560564295, \"estatus_tasa\":\"Calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":15, \"monto_otorgado\":4500.0, \"cuota\":29.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":900.0, \"numero_pagos_restante\":767.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2013-03-08\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":29.0, \"ahorro_total\":26100.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":14, \"monto_otorgado\":20000.0, \"cuota\":1000.0, \"saldo\":2718.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.8641, \"fecha_inicio\":\"2019-08-11\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":3717.999999999993, \"ahorro_total\":-3717.999999999993, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":8, \"monto_otorgado\":500.0, \"cuota\":6336.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2019-08-03\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":6336.0, \"ahorro_total\":-6336.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"KUBO FINANCIERO\", \"registro\":12, \"monto_otorgado\":100000.0, \"cuota\":3589.0, \"saldo\":82549.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":48.0, \"numero_pagos_restante\":31.0, \"frecuencia_externa\":\"M\", \"avance\":0.1745, \"fecha_inicio\":\"2022-11-17\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-535.2641162557456, \"ahorro_total\":-25692.67758027579, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":6, \"monto_otorgado\":102000.0, \"cuota\":344.0, \"saldo\":2161.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.9788, \"fecha_inicio\":\"2019-12-06\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":2504.9999999999945, \"ahorro_total\":-2504.9999999999945, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":5, \"monto_otorgado\":30000.0, \"cuota\":1435.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2023-08-09\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":1435.0, \"ahorro_total\":-1435.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}, {\"entidad\": \"BANCO\", \"registro\":4, \"monto_otorgado\":37800.0, \"cuota\":3000.0, \"saldo\":6242.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.8349, \"fecha_inicio\":\"2021-03-09\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":9241.999999999985, \"ahorro_total\":-9241.999999999985, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Light Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":3, \"monto_otorgado\":45800.0, \"cuota\":2290.0, \"saldo\":5848.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.8723, \"fecha_inicio\":\"2023-08-25\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":8137.999999999985, \"ahorro_total\":-8137.999999999985, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":2, \"monto_otorgado\":20000.0, \"cuota\":96.0, \"saldo\":127.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":0.9936, \"fecha_inicio\":\"2022-11-01\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":222.99999999999966, \"ahorro_total\":-222.99999999999966, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"Transactor\", \"deuda_consolidable\":1}, {\"entidad\": \"BANCO\", \"registro\":1, \"monto_otorgado\":117200.0, \"cuota\":6776.0, \"saldo\":111852.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":19.0, \"numero_pagos_restante\":6.0, \"frecuencia_externa\":\"S\", \"avance\":0.0456, \"fecha_inicio\":\"2023-09-15\", \"tipo_deuda\":\"I\", \"ahorro_cuota_mensual\":-7122.1843719153785, \"ahorro_total\":-71221.84371915378, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":1}, {\"entidad\": \"TIENDA COMERCIAL\", \"registro\":7, \"monto_otorgado\":2000.0, \"cuota\":0.0, \"saldo\":0.0, \"tasa_externa\":-1.0, \"tasa_kubo\":0.3337, \"numero_pagos_otorgado\":-1.0, \"numero_pagos_restante\":-1.0, \"frecuencia_externa\":\"Z\", \"avance\":1.0, \"fecha_inicio\":\"2022-12-10\", \"tipo_deuda\":\"R\", \"ahorro_cuota_mensual\":0.0, \"ahorro_total\":-0.0, \"estatus_tasa\":\"No calculable\", \"tipo_revolvencia\":\"High Revolver\", \"deuda_consolidable\":0}]}]}";

    ConsolidationOfferDto consolidationOffer = JsonAdapter.jsonToConsolidationOffer(jsonOffer);
    FlexibleConsolidations flexCons = new FlexibleConsolidations(consolidationOffer);
    flexCons.initOffer();

    // flexCons.getConsolidationOffer().getBuroDebts().get(2)
    // .setExternalRate(0.30)
    // .setSelected(true);
    // flexCons.updateOffer();

    // CAMBIOS PARA PROSPECTO 2842882: OFERTA SIN AHORROS NI MONTO RESTANTE
    flexCons.getConsolidationOffer().getBuroDebts().get(0)
        .setExternalRate(0.80)
        .setSelected(true);
    flexCons.updateOffer();

    flexCons.getConsolidationOffer().getBuroDebts().get(0)
        .setAmountAwarded(0);
    flexCons.updateOffer();
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
