package utils;

public class DateConverter {
  
  // Конвертер формата даты: "dd.MM.yyyy" в "yyyy-MM-dd"
  public static String convertDateToOutputFormat(String date) {
    if (date == null || date.trim().isEmpty()) {
      throw new IllegalArgumentException("Дата не может быть пустой");
    }
    
    String[] p = date.split("\\.");
    if (p.length != 3) throw new IllegalArgumentException("Неверный формат: " + date);
    
    return p[2] + "-" + p[1] + "-" + p[0];
  }
}