package factory.settings;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeSettings implements IBrowserSettings {
  
  @Override
  public MutableCapabilities applyBrowserOptions() {
    return new EdgeOptions();
  }
}