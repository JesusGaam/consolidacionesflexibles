package com.kubofinanciero.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kubofinanciero.dto.ConsolidationOfferDto;
import com.kubofinanciero.dto.DebtDto;

public class JsonAdapter {

  public static ConsolidationOfferDto jsonToConsolidationOffer(String jsonString) {

    JsonObject mainJson = new JsonParser().parse(jsonString).getAsJsonObject();
    JsonArray offerList = mainJson.get("Oferta Automatico").getAsJsonArray();
    JsonObject jsonOffer = null;

    for (JsonElement offer : offerList) {
      String loanType = offer.getAsJsonObject().get("loan_type").getAsString();
      if (loanType.equals("CON")) {
        jsonOffer = offer.getAsJsonObject();
        break;
      }
    }
    if (jsonOffer == null) {
      return null;
    }

    JsonArray jsonFrequencies = jsonOffer.get("frecuencia").getAsJsonArray();
    char[] frequencies = new char[jsonFrequencies.size()];
    for (int i = 0; i < frequencies.length; i++) {
      frequencies[i] = jsonFrequencies.get(i).getAsCharacter();
    }

    JsonArray jsonAssistedRates = jsonOffer.get("tasa_asistida").getAsJsonArray();
    double[] assistedRates = new double[jsonAssistedRates.size()];
    for (int i = 0; i < assistedRates.length; i++) {
      assistedRates[i] = jsonAssistedRates.get(i).getAsDouble();
    }

    JsonArray jsonCommissionsRate = jsonOffer.get("comisiones").getAsJsonArray();
    double[] commissionsRate = new double[jsonCommissionsRate.size()];
    for (int i = 0; i < commissionsRate.length; i++) {
      commissionsRate[i] = jsonCommissionsRate.get(i).getAsDouble();
    }

    JsonArray jsonKuboScores = jsonOffer.get("kubo_scores").getAsJsonArray();
    String[] kuboScores = new String[jsonKuboScores.size()];
    for (int i = 0; i < kuboScores.length; i++) {
      kuboScores[i] = jsonKuboScores.get(i).getAsString();
    }

    JsonArray jsonBuroDepts = jsonOffer.get("deudas_buro").getAsJsonArray();
    List<DebtDto> buroDepts = new ArrayList<DebtDto>();
    for (JsonElement deptJsonElement : jsonBuroDepts) {
      JsonObject dept = deptJsonElement.getAsJsonObject();
      buroDepts.add(new DebtDto(

          dept.get("registro").getAsLong(),
          dept.get("entidad").getAsString(),
          dept.get("monto_otorgado").getAsDouble(),
          dept.get("cuota").getAsDouble(),
          dept.get("saldo").getAsDouble(),
          dept.get("tasa_externa").getAsDouble(),
          dept.get("tasa_kubo").getAsDouble(),
          dept.get("numero_pagos_otorgado").getAsInt(),
          dept.get("numero_pagos_restante").getAsInt(),
          dept.get("frecuencia_externa").getAsCharacter(),
          dept.get("avance").getAsDouble(),
          dept.get("fecha_inicio").getAsString(),
          dept.get("tipo_deuda").getAsCharacter(),
          dept.get("ahorro_cuota_mensual").getAsDouble(),
          dept.get("ahorro_total").getAsDouble(),
          dept.get("estatus_tasa").getAsString(),
          dept.get("tipo_revolvencia").getAsString(),
          dept.get("deuda_consolidable").getAsInt() == 1,
          0,
          0,
          false));
    }

    ConsolidationOfferDto offer = new ConsolidationOfferDto(
        jsonOffer.get("loan_type").getAsString(),
        jsonOffer.get("sub_loan_type").getAsString(),
        jsonOffer.get("cuota_min").getAsDouble(),
        jsonOffer.get("cuota_max").getAsDouble(),
        jsonOffer.get("monto_min").getAsDouble(),
        jsonOffer.get("monto_max").getAsDouble(),
        jsonOffer.get("plazo_min").getAsInt(),
        jsonOffer.get("plazo_max").getAsInt(),
        jsonOffer.get("tasa").getAsDouble(),
        jsonOffer.get("comision").getAsDouble(),
        jsonOffer.get("kubo_score").getAsString(),
        jsonOffer.get("proba").getAsDouble(),
        jsonOffer.get("segmento").getAsInt(),
        frequencies,
        assistedRates,
        commissionsRate,
        kuboScores,
        buroDepts,
        mainJson.get("Prospecto").getAsLong(),
        mainJson.get("bursolnum").getAsLong(),
        mainJson.get("monto_maximo_cliente").getAsDouble());

    return offer;
  }
}
