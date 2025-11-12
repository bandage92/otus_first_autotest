package utils;

public class GetBaseURL {
  
  private static final String BASE_URL =
      System.getProperty("test.base.url", "https://otus.home.kartushin.su");
  
  public static String getBaseUrl() {
    return BASE_URL;
  }
  
  public static String getFormPage() {
    return BASE_URL + "/form.html";
  }
}