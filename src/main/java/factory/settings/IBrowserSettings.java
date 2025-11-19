package factory.options;

import org.openqa.selenium.MutableCapabilities;

public interface IBrowserOptions {
  
  MutableCapabilities applyBrowserOptions();
  
  default MutableCapabilities getOptions(MutableCapabilities customOptions) {
    MutableCapabilities browserOptions = applyBrowserOptions();
    
    if (customOptions != null) {
      browserOptions.merge(customOptions);
    }
    
    return browserOptions;
  }
}