package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IBrowserSettings<ChromeOptions> {
  
  @Override
  public ChromeOptions getSettings() {
    return new ChromeOptions();
  }
}