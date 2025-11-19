package factory;

import data.EBrowserTypeData;
import factory.settings.ChromeSettings;
import factory.settings.EdgeSettings;
import factory.settings.FirefoxSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        yield new ChromeDriver((ChromeOptions) new ChromeSettings().getOptions(options));
      }
      case FIREFOX -> {
        WebDriverManager.firefoxdriver().setup();
        yield new FirefoxDriver((FirefoxOptions) new FirefoxSettings().getOptions(options));
      }
      case EDGE -> {
        WebDriverManager.edgedriver().setup();
        yield new EdgeDriver((EdgeOptions) new EdgeSettings().getOptions(options));
      }
    };
  }
}