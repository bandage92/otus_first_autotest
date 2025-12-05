package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter {
  
  // Конвертер формата даты: "dd/MM/yyyy" в "yyyy-MM-dd"
  public static String convertDateToOutputFormat(String date) {
    try {
      LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Неверный формат даты для Firefox: " + date, e);
    }
  }
}