package com.kubofinanciero.dto;

public class FrequencyDaysDto {
  public char frequency;
  public String frequencyName;
  public int days;
  public double periodsPerYear;
  private final int daysOfYear = 360;

  public FrequencyDaysDto(char frequency, String frequencyName, int days) {
    this.frequency = frequency;
    this.frequencyName = frequencyName;
    this.days = days;
    this.periodsPerYear = (double) days > 0 ? daysOfYear / days : 0;
  }

  public char getFrequency() {
    return frequency;
  }

  public String getFrequencyName() {
    return frequencyName;
  }

  public int getDays() {
    return days;
  }

  public double getPeriodsPerYear() {
    return periodsPerYear;
  }

  public int getPeriodsPerYearForCat() {
    return (int) Math.ceil(periodsPerYear);
  }

  public int getDaysOfYear() {
    return daysOfYear;
  }

  public void setFrequency(char frequency) {
    this.frequency = frequency;
  }

  public void setDays(int days) {
    this.days = days;
  }

  public void setPeriodsPerYear(double periodsPerYear) {
    this.periodsPerYear = periodsPerYear;
  }

  @Override
  public String toString() {
    return "{\"frequency\": \"" + frequency +
        "\", \"frequencyName\": \"" + frequencyName +
        "\", \"days\":" + days +
        ", \"periodsPerYear\":" + periodsPerYear +
        ", \"daysOfYear\":" + daysOfYear + "}";
  }

}