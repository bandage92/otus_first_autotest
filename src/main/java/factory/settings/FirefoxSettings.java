package factory.settings;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings implements IBrowserSettings {
  
  @Override
  public MutableCapabilities applyBrowserOptions() {
    return new FirefoxOptions();
  }
}