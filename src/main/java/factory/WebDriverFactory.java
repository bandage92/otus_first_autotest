package factory;

import exceptions.BrowserNotFoundException;
import factory.settings.ChromeSettings;
import factory.settings.EdgeSettings;
import factory.settings.FirefoxSettings;
import factory.settings.IBrowserSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
  
  private final String browser = System.getProperty("browser");
  private final String noOptions = System.getProperty("noOptions");
  
  public WebDriver create() {
    switch (browser) {
      
      // Google Chrome
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        
        if (!Boolean.parseBoolean(noOptions)) {
          IBrowserSettings<ChromeOptions> chromeOptions = new ChromeSettings();
          return new ChromeDriver((chromeOptions.getSettings()));
        }
        
        return new ChromeDriver();
      }
      
      // Firefox
      case "firefox": {
        WebDriverManager.firefoxdriver().setup();
        
        if (!Boolean.parseBoolean(noOptions)) {
          IBrowserSettings<FirefoxOptions> firefoxOptions = new FirefoxSettings();
          return new FirefoxDriver(firefoxOptions.getSettings());
        }
        
        return new FirefoxDriver();
      }
      
      // MS EDGE
      case "edge": {
        WebDriverManager.edgedriver().setup();
        
        if (!Boolean.parseBoolean(noOptions)) {
          IBrowserSettings<EdgeOptions> edgeOptions = new EdgeSettings();
          return new EdgeDriver(edgeOptions.getSettings());
        }
        
        return new EdgeDriver();
      }
    }
    
    throw new BrowserNotFoundException(browser);
  }
}