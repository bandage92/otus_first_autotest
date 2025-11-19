package factory.options;

public class EdgeOptions implements IBrowserOptions {
  
  @Override
  public IBrowserOptions applyBrowserOptions() {
    return new org.openqa.selenium.edge.EdgeOptions();
  }
}