package utils;

import static java.time.ZoneId.systemDefault;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import com.github.javafaker.Faker;

public class DataGenerator {
  
  private static final Faker FAKER_RU = new Faker(new Locale.Builder().setLanguage("ru").build());
  private static final Faker FAKER_EN = new Faker(new Locale.Builder().setLanguage("en").build());
  
  // Случайное имя пользователя
  public static String generateUsername() {
    return FAKER_RU.name().firstName() + " " + FAKER_RU.name().lastName();
  }
  
  // Случайный email
  public static String generateEmail() {
    return FAKER_EN.internet().emailAddress();
  }
  
  // Случайный пароль
  public static String generatePassword() {
    return FAKER_EN.internet().password(8, 16, true, true, true);
  }
  
  // Случайная дата рождения
  public static String generateBirthdate() {
    return FAKER_RU.date().birthday(18, 65)
        .toInstant()
        .atZone(systemDefault())
        .toLocalDate()
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }
}