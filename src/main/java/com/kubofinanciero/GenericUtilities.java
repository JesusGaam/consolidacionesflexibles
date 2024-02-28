package com.kubofinanciero;

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
}
