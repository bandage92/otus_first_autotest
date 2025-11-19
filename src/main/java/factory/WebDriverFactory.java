package factory;

import data.EBrowserTypeData;
import factory.settings.ChromeSettings;
import factory.settings.EdgeSettings;
import factory.settings.FirefoxSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
  
  public static WebDriver create(EBrowserTypeData browserType, MutableCapabilities options) {
    return createDriver(browserType, options);
  }
  
  public static WebDriver create(EBrowserTypeData browserType) {
    return create(browserType, null);
  }
  
  private static WebDriver createDriver(EBrowserTypeData browserType, MutableCapabilities options) {
    return switch (browserType) {
      case CHROME -> {
        WebDriverManager.chromedriver().setup();
        yield new ChromeDriver((org.openqa.selenium.chrome.ChromeOptions) new ChromeSettings().getOptions(options));
      }
      case FIREFOX -> {
        WebDriverManager.firefoxdriver().setup();
        yield new FirefoxDriver((org.openqa.selenium.firefox.FirefoxOptions) new FirefoxSettings().getOptions(options));
      }
      case EDGE -> {
        WebDriverManager.edgedriver().setup();
        yield new EdgeDriver((org.openqa.selenium.edge.EdgeOptions) new EdgeSettings().getOptions(options));
      }
    };
  }
}