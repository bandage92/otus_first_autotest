package utils;

import data.EBrowserTypeData;

public class GetBrowserType {
  
  public static EBrowserTypeData getBrowserType() {
    String browserName = System.getProperty("browser", "chrome").toUpperCase().trim();
    try {
      return EBrowserTypeData.valueOf(browserName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Неподдерживаемый браузер: " + browserName);
    }
  }
}