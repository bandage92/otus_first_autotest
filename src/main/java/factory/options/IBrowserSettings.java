package factory.options;

import org.openqa.selenium.MutableCapabilities;

public interface IBrowserSettings {
  
  MutableCapabilities getOptions(MutableCapabilities options);
}