package com.kubofinanciero.dto;

public class FrequencyDaysDto {
  public char frequency;
  public int days;
  public double periodsPerYear;
  private final int daysOfYear = 360;

  public FrequencyDaysDto(char frequency, int days) {
    this.frequency = frequency;
    this.days = days;
    this.periodsPerYear = (double) daysOfYear / days;
  }

  public char getFrequency() {
    return frequency;
  }

  public int getDays() {
    return days;
  }

  public double getPeriodsPerYear() {
    return periodsPerYear;
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
        "\" , \"days\":" + days +
        ", \"periodsPerYear\":" + periodsPerYear +
        ", \"daysOfYear\":" + daysOfYear + "}";
  }

}