package factory.options;

public class FirefoxOptions implements IBrowserOptions {
  
  @Override
  public IBrowserOptions applyBrowserOptions() {
    return new ChromeOptions();
  }
}