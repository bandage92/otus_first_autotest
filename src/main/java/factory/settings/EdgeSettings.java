package factory.settings;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeSettings implements IBrowserSettings<EdgeOptions> {
  
  @Override
  public EdgeOptions getSettings() {
    return new EdgeOptions();
  }
}