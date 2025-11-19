package factory.options;

public class ChromeOptions implements IBrowserOptions {
  
  @Override
  public IBrowserOptions applyBrowserOptions() {
    return new org.openqa.selenium.chrome.ChromeOptions();
  }
}