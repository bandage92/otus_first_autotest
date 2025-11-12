package factory.options;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IBrowserSettings {
  
  public MutableCapabilities getOptions(MutableCapabilities options) {
    ChromeOptions chromeOptions = new ChromeOptions();
    
    if (options != null) {
      chromeOptions.merge(options);
    }
    return chromeOptions;
  }
}