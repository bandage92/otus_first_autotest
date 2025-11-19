package page;

import data.EBrowserTypeData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.GetBrowserType;
import waits.ElementWaits;

public class FormPage {
  
  private final WebDriver driver;
  private final EBrowserTypeData browserType;
  
  private static final By USERNAME_FIELD = By.id("username");
  private static final By EMAIL_FIELD = By.id("email");
  private static final By PASSWORD_FIELD = By.id("password");
  private static final By CONFIRM_PASSWORD_FIELD = By.id("confirm_password");
  private static final By BIRTHDATE_FIELD = By.id("birthdate");
  private static final By LANGUAGE_LEVEL_FIELD = By.id("language_level");
  private static final By SUBMIT_BUTTON = By.cssSelector("input[type='submit']");
  private static final By OUTPUT_DIV = By.id("output");
  
  public FormPage(WebDriver driver) {
    this.driver = driver;
    this.browserType = GetBrowserType.getBrowserType();
  }
  
  // Template methods
  private void inputText(By locator, String text) {
    WebElement element = ElementWaits.presenceOfElement(driver, locator);
    element.sendKeys(text);
  }
  
  private String getFieldValue(By locator) {
    WebElement element = ElementWaits.presenceOfElement(driver, locator);
    return element.getAttribute("value");
  }
  
  private String getFieldText(By locator) {
    WebElement element = ElementWaits.presenceOfElement(driver, locator);
    return element.getText();
  }
  
  private Select getLanguageSelect() {
    WebElement languageLevelField = ElementWaits.presenceOfElement(driver, LANGUAGE_LEVEL_FIELD);
    return new Select(languageLevelField);
  }
  
  // Page methods
  public FormPage inputTextIntoUsernameField(String text) {
    inputText(USERNAME_FIELD, text);
    return this;
  }
  
  public FormPage inputTextIntoEmailField(String text) {
    inputText(EMAIL_FIELD, text);
    return this;
  }
  
  public FormPage inputTextIntoPasswordField(String text) {
    inputText(PASSWORD_FIELD, text);
    return this;
  }
  
  public FormPage inputTextIntoConfirmPasswordField(String text) {
    inputText(CONFIRM_PASSWORD_FIELD, text);
    return this;
  }
  
  public FormPage inputTextIntoBirthdateField(String date) {
    WebElement birthdateField = ElementWaits.presenceOfElement(driver, BIRTHDATE_FIELD);
    
    String dateToInput;
    if (browserType == EBrowserTypeData.FIREFOX) {
      dateToInput = convertDateToOutputFormat(date);
    } else {
      dateToInput = date;
    }
    
    birthdateField.sendKeys(dateToInput);
    return this;
  }
  
  public void selectLanguageLevel() {
    Select languageSelect = getLanguageSelect();
    languageSelect.selectByIndex(3);
  }
  
  public void clickSubmitButton() {
    WebElement submitButton = ElementWaits.presenceOfElement(driver, SUBMIT_BUTTON);
    submitButton.click();
  }
  
  // Getters
  public String getUsernameValue() {
    return getFieldValue(USERNAME_FIELD);
  }
  
  public String getEmailValue() {
    return getFieldValue(EMAIL_FIELD);
  }
  
  public String getPasswordValue() {
    return getFieldValue(PASSWORD_FIELD);
  }
  
  public String getConfirmPasswordValue() {
    return getFieldValue(CONFIRM_PASSWORD_FIELD);
  }
  
  public String getBirthdateValue() {
    return getFieldValue(BIRTHDATE_FIELD);
  }
  
  public String getLanguageLevelValue() {
    Select languageSelect = getLanguageSelect();
    return languageSelect.getOptions().get(3).getAttribute("value");
  }
  
  public String getOutputText() {
    return getFieldText(OUTPUT_DIV);
  }
  
  // Convert for Firefox
  public static String convertDateToOutputFormat(String date) {
    if (date == null || date.trim().isEmpty()) {
      throw new IllegalArgumentException("Дата не может быть пустой");
    }
    
    String[] p = date.split("\\.");
    if (p.length != 3) throw new IllegalArgumentException("Неверный формат: " + date);
    
    return p[2] + "-" + p[1] + "-" + p[0];
  }
}