package factory;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
  
  private final String browserName = System.getProperty("browserName", "chrome");
  private final String browserVersion = System.getProperty("browserVersion", "128.0");
  private final String remoteDriverURL = System.getProperty("remote.url", "");
  private final String noOptions = System.getProperty("noOptions");
  
  public WebDriver create() throws MalformedURLException {
    if (!remoteDriverURL.isEmpty()) {
      DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
      desiredCapabilities.setCapability("browserVersion", browserVersion);
      desiredCapabilities.setCapability("browserName", browserName);
      desiredCapabilities.setCapability("selenoid:options", new HashMap<String, Object>() {{
          put("enableVNC", true);
          }});
      return new RemoteWebDriver(URI.create(remoteDriverURL).toURL(), desiredCapabilities);
    }
    
    switch (browserName) {
      
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
    
    throw new BrowserNotFoundException(browserName);
  }
}