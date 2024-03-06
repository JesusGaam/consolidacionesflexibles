package com.kubofinanciero.utils;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GenericUtilities {

  public static String toCurrencyFormat(double number) {
    NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
    return formatoImporte.format(number);
  }

  public static String toStringDate(Date date) {
    String months[] = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
        "octubre", "noviembre", "diciembre" };

    Calendar today = Calendar.getInstance();
    today.setTime(date);

    return String.format("%s de %s de %s",
        today.get(Calendar.DATE),
        months[today.get(Calendar.MONTH)],
        today.get(Calendar.YEAR));
  }

  public static double round(double number, int nDigits) {
    nDigits = nDigits > 0 ? (int) Math.pow(10, nDigits) : 1;
    return (double) Math.round(number * nDigits) / nDigits;
  };

  public static double round(double number) {
    return round(number, 2);
  };
}
