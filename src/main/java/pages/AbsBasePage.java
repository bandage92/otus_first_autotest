package pages;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.openqa.selenium.support.PageFactory.*;
import static waits.ElementWaits.*;

import java.util.ArrayList;
import java.util.List;
import data.EBrowserTypeData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class AbsBasePage {

  protected WebDriver driver;
  protected EBrowserTypeData browserType;
  private final String baseURL = System.getProperty("base.url");
  private final String path;
  
  public AbsBasePage(WebDriver driver, String path) {
    this.driver = driver;
    this.path = path;
    
    initElements(driver, this);
    
    this.browserType = initializeBrowserType();
  }
  
  // Метод для инициализации браузера
  private EBrowserTypeData initializeBrowserType() {
    try {
      String browser = System.getProperty("browser", "chrome");
      return EBrowserTypeData.valueOf(browser.toUpperCase());
    } catch (Exception e) {
      return EBrowserTypeData.CHROME;
    }
  }
  
  // Get метод для перехода на страницу
  public void getURL() {
    driver.get(baseURL + path);
  }
  
  // Get метод для получения типа браузера
  public EBrowserTypeData getBrowserType() {
    return browserType;
  }
  
  // Общий Get метод для получения атрибута 'value' в поле
  protected String getFieldValue(WebElement element) {
    return waitForElementToBeVisible(driver, element).getAttribute("value");
  }
  
  // Общий Get метод для получения текста из поля
  protected String getFieldText(WebElement element) {
    return waitForElementToBeVisible(driver, element).getText();
  }
  
  // Общий Input метод
  protected void inputText(WebElement element, String text) {
    waitForElementToBeVisible(driver, element).sendKeys(text);
  }
  
  // Общий Click метод
  protected void click(WebElement element) {
    waitForElementToBeVisible(driver, element).click();
  }
  
  // Общий Select метод
  protected Select getSelect(WebElement element) {
    return new Select(waitForElementToBeVisible(driver, element));
  }
  
  // Select метод по индексу
  protected void selectByIndex(WebElement element, int index) {
    getSelect(element).selectByIndex(index);
  }
  
  // Select метод для получения атрибута 'value'
  protected String getSelectedOptionValue(WebElement element) {
    return getSelect(element).getFirstSelectedOption().getAttribute("value");
  }
  
  // Select метод для выбора случайной 'enabled' опции
  protected void selectRandomOption(WebElement element) {
    Select select = getSelect(element);
    List<WebElement> allOptions = select.getOptions();
    
    List<Integer> enabledIndexes = new ArrayList<>();
    for (int i = 0; i < allOptions.size(); i++) {
      if (allOptions.get(i).getAttribute("disabled") == null) {
        enabledIndexes.add(i);
      }
    }
    
    if (enabledIndexes.isEmpty()) {
      throw new IllegalStateException("Нет активных опций для выбора");
    }
    
    int randomIndex = current().nextInt(0, enabledIndexes.size());
    int optionIndex = enabledIndexes.get(randomIndex);
    
    selectByIndex(element, optionIndex);
  }
}