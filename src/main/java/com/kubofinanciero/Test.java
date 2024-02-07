package com.kubofinanciero;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.dto.DebtDto;
import com.kubofinanciero.utils.JsonAdapter;

public class Test {
    private static String offer = "{\"Prospecto\":27744,\"Oferta Automatico\":[{\"loan_type\":\"CON\",\"sub_loan_type\":\"CONSOLIDACION_FLEXIBLE\",\"credito_id\":101363171,\"cuota_max\":605.9702501316251,\"monto_max\":5000,\"cuota_min\":283.1081927352023,\"monto_min\":5000,\"plazo_max\":12,\"plazo_min\":4,\"tasa\":0.6509,\"comision\":0.0481,\"proba\":0,\"segmento\":26,\"frecuencia\":[\"S\",\"K\",\"W\"],\"tasa_asistida\":[[0.6509,0.6193,0.5991,0.5668,0.5504,0.5486,0.5391,0.5292,0.4818,0.4706,0.4582,0.4428,0.4295,0.3813,0.3617,0.3337,0.2914,0.2622,0.2306,0.2,0.165]],\"comisiones\":[[0,0.0481,0.0481,0.0481,0.0481,0.0476,0.0471,0.0466,0.046,0.0455,0.0449,0.0444,0.0439,0.0433,0.0428,0.0423,0.0417,0.03845,0.03845,0.03845,0.0352]],\"deudas_buro\":[{\"entidad\":\"FINANCIERA\",\"monto_otorgado\":2628,\"cuota\":0,\"saldo\":0,\"tasa_externa\":-1,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":1,\"fecha_inicio\":\"2023-12-05\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":66.02974986837489,\"ahorro_total\":792.3569984204987,\"estatus_tasa\":\"Falta cuota\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"BANCO\",\"monto_otorgado\":7284,\"cuota\":659,\"saldo\":6109,\"tasa_externa\":-1,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":0,\"numero_pagos_restante\":6,\"frecuencia_externa\":\"M\",\"avance\":0.0602,\"fecha_inicio\":\"2020-11-21\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":66.02974986837489,\"ahorro_total\":792.3569984204987,\"estatus_tasa\":\"Falta n\\\\u00FAmero de pagos\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"KUBO FINANCIERO\",\"monto_otorgado\":20000,\"cuota\":1479,\"saldo\":13852,\"tasa_externa\":0.7519,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":18,\"numero_pagos_restante\":11,\"frecuencia_externa\":\"M\",\"avance\":0.1233,\"fecha_inicio\":\"2023-07-20\",\"tipo_deuda\":\"I\",\"ahorro_cuota_mensual\":66.02974986837489,\"ahorro_total\":792.3569984204987,\"estatus_tasa\":\"Calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":0},{\"entidad\":\"FINANCIERA\",\"monto_otorgado\":5000,\"cuota\":672,\"saldo\":2274,\"tasa_externa\":1.0034,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":12,\"numero_pagos_restante\":6,\"frecuencia_externa\":\"M\",\"avance\":0.5452,\"fecha_inicio\":\"2023-03-30\",\"tipo_deuda\":\"I\",\"ahorro_cuota_mensual\":66.02974986837489,\"ahorro_total\":792.3569984204987,\"estatus_tasa\":\"Calculable\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"BANCO\",\"monto_otorgado\":230000,\"cuota\":6248,\"saldo\":217496,\"tasa_externa\":-1,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":19,\"numero_pagos_restante\":6,\"frecuencia_externa\":\"S\",\"avance\":0.0544,\"fecha_inicio\":\"2023-07-04\",\"tipo_deuda\":\"I\",\"ahorro_cuota_mensual\":363.94673129811144,\"ahorro_total\":13102.082326732012,\"estatus_tasa\":\"Tasa negativa\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":1},{\"entidad\":\"SERVICIOS\",\"monto_otorgado\":0,\"cuota\":0,\"saldo\":0,\"tasa_externa\":-1,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":-1,\"fecha_inicio\":\"2021-02-26\",\"tipo_deuda\":\"O\",\"ahorro_cuota_mensual\":363.94673129811144,\"ahorro_total\":13102.082326732012,\"estatus_tasa\":\"Falta cuota\",\"tipo_revolvencia\":\"High Revolver\",\"deuda_consolidable\":0},{\"entidad\":\"BANCO\",\"monto_otorgado\":3558,\"cuota\":0,\"saldo\":1060,\"tasa_externa\":-1,\"tasa_kubo\":0.6509,\"numero_pagos_otorgado\":-1,\"numero_pagos_restante\":-1,\"frecuencia_externa\":\"Z\",\"avance\":0.9584,\"fecha_inicio\":\"2023-04-27\",\"tipo_deuda\":\"R\",\"ahorro_cuota_mensual\":699.7107546210427,\"ahorro_total\":33586.11622181005,\"estatus_tasa\":\"Falta cuota\",\"tipo_revolvencia\":\"Transactor\",\"deuda_consolidable\":1}],\"monto_maximo_cliente\":20000,\"cuota_externa_total\":672,\"capacidad_maxima_pago\":24651,\"bursolnum\":7153239}]}";

    // public static ConsolidationOfferDto jsonToConsolidationOffer(String
    // jsonString) {

    // JsonObject mainJson = new JsonParser().parse(jsonString).getAsJsonObject();
    // JsonArray offerList = mainJson.get("Oferta Automatico").getAsJsonArray();
    // JsonObject jsonOffer = null;

    // for (JsonElement offer : offerList) {
    // String loanType = offer.getAsJsonObject().get("loan_type").getAsString();
    // if (loanType.equals("CON")) {
    // jsonOffer = offer.getAsJsonObject();
    // break;
    // }
    // }
    // if (jsonOffer == null) {
    // return null;
    // }

    // JsonArray jsonFrequencies = jsonOffer.get("frecuencia").getAsJsonArray();
    // char[] frequencies = new char[jsonFrequencies.size()];
    // for (int i = 0; i < frequencies.length; i++) {
    // frequencies[i] = jsonFrequencies.get(i).getAsCharacter();
    // }

    // JsonArray jsonAssistedRates =
    // jsonOffer.get("tasa_asistida").getAsJsonArray().get(0).getAsJsonArray();
    // double[] assistedRates = new double[jsonAssistedRates.size()];
    // for (int i = 0; i < assistedRates.length; i++) {
    // assistedRates[i] = jsonAssistedRates.get(i).getAsDouble();
    // }

    // JsonArray jsonCommissionsRate =
    // jsonOffer.get("comisiones").getAsJsonArray().get(0).getAsJsonArray();
    // double[] commissionsRate = new double[jsonCommissionsRate.size()];
    // for (int i = 0; i < commissionsRate.length; i++) {
    // commissionsRate[i] = jsonCommissionsRate.get(i).getAsDouble();
    // }

    // JsonArray jsonBuroDepts = jsonOffer.get("deudas_buro").getAsJsonArray();
    // List<DebtDto> buroDepts = new ArrayList<DebtDto>();
    // for (JsonElement deptJsonElement : jsonBuroDepts) {
    // JsonObject dept = deptJsonElement.getAsJsonObject();
    // buroDepts.add(new DebtDto(
    // 0,
    // dept.get("entidad").getAsString(),
    // dept.get("monto_otorgado").getAsDouble(),
    // dept.get("cuota").getAsDouble(),
    // dept.get("saldo").getAsDouble(),
    // dept.get("tasa_externa").getAsDouble(),
    // dept.get("tasa_kubo").getAsDouble(),
    // dept.get("numero_pagos_otorgado").getAsInt(),
    // dept.get("numero_pagos_restante").getAsInt(),
    // dept.get("frecuencia_externa").getAsCharacter(),
    // dept.get("avance").getAsDouble(),
    // dept.get("fecha_inicio").getAsString(),
    // dept.get("tipo_deuda").getAsCharacter(),
    // dept.get("ahorro_cuota_mensual").getAsDouble(),
    // dept.get("ahorro_total").getAsDouble(),
    // dept.get("estatus_tasa").getAsString(),
    // dept.get("tipo_revolvencia").getAsString(),
    // dept.get("deuda_consolidable").getAsInt() == 1,
    // 0,
    // 0,
    // false));
    // }

    // ConsolidationOfferDto offer = new ConsolidationOfferDto(
    // jsonOffer.get("loan_type").getAsString(),
    // jsonOffer.get("sub_loan_type").getAsString(),
    // jsonOffer.get("credito_id").getAsLong(),
    // jsonOffer.get("cuota_min").getAsDouble(),
    // jsonOffer.get("cuota_max").getAsDouble(),
    // jsonOffer.get("monto_min").getAsDouble(),
    // jsonOffer.get("monto_max").getAsDouble(),
    // jsonOffer.get("plazo_min").getAsInt(),
    // jsonOffer.get("plazo_max").getAsInt(),
    // jsonOffer.get("tasa").getAsDouble(),
    // jsonOffer.get("comision").getAsDouble(),
    // jsonOffer.get("proba").getAsDouble(),
    // jsonOffer.get("segmento").getAsInt(),
    // frequencies,
    // assistedRates,
    // commissionsRate,
    // buroDepts,
    // mainJson.get("Prospecto").getAsLong(),
    // jsonOffer.get("bursolnum").getAsLong(),
    // jsonOffer.get("monto_maximo_cliente").getAsDouble());
    // System.out.println(offer);

    // return offer;
    // }

    public static void main(String[] args) {

        JsonAdapter.jsonToConsolidationOffer(offer);
    }
}
