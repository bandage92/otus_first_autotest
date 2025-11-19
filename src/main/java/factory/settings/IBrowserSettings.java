package factory.settings;

import org.openqa.selenium.MutableCapabilities;

public interface IBrowserSettings {
  
  MutableCapabilities applyBrowserOptions();
  
  default MutableCapabilities getOptions(MutableCapabilities customOptions) {
    MutableCapabilities browserOptions = applyBrowserOptions();
    
    return (customOptions != null)
        ? browserOptions.merge(customOptions)
        : browserOptions;
  }
}