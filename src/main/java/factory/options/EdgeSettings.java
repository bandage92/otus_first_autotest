package factory.options;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeSettings implements IBrowserSettings {
  
  public MutableCapabilities getOptions(MutableCapabilities options) {
    EdgeOptions edgeOptions = new EdgeOptions();
    
    if (options != null) {
      edgeOptions.merge(options);
    }
    return edgeOptions;
  }
}