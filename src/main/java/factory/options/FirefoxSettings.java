package factory.options;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings implements IBrowserSettings {
  
  public MutableCapabilities getOptions(MutableCapabilities options) {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    
    if (options != null) {
      firefoxOptions.merge(options);
    }
    return firefoxOptions;
  }
}