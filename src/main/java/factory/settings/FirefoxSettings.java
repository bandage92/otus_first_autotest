package factory.settings;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings implements IBrowserSettings<FirefoxOptions> {
  
  @Override
  public FirefoxOptions getSettings() {
    return new FirefoxOptions();
  }
}