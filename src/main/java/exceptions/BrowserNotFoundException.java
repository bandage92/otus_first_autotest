package exceptions;

public class BrowserNotFoundException extends RuntimeException {

  public BrowserNotFoundException(String browser) {
    super(String.format("Неподдерживаемый браузер: %s",  browser));
  }
}