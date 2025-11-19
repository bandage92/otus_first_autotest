package factory.settings;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IBrowserSettings {
  
  @Override
  public MutableCapabilities applyBrowserOptions() {
    return new ChromeOptions();
  }
}