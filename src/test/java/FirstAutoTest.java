import static factory.WebDriverFactory.create;
import static org.junit.jupiter.api.Assertions.*;
import static utils.GetBaseURL.getFormPage;
import static utils.GetBrowserType.getBrowserType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import page.FormPage;
import utils.ConfigReader;

public class FirstAutoTest {
  
  private static final Logger LOGGER = LogManager.getLogger(FirstAutoTest.class);
  private WebDriver driver;
  
  @BeforeEach
  public void startDriver() {
    var browserName = getBrowserType();
    driver = create(browserName);
    LOGGER.info("Драйвер запущен для браузера: {}", browserName);
    
    driver.get(getFormPage());
    LOGGER.info("Переход на страницу: {}", getFormPage());
  }
  
  @AfterEach
  public void driverStop() {
    if (driver != null) {
      driver.close();
      LOGGER.debug("Драйвер закрыт");
    }
  }
  
  @Test
  public void fillForm() {
    FormPage formPage = new FormPage(driver);
    
    formPage.inputTextIntoUsernameField(ConfigReader.getUsername())
            .inputTextIntoEmailField(ConfigReader.getEmail())
            .inputTextIntoPasswordField(ConfigReader.getPassword())
            .inputTextIntoConfirmPasswordField(ConfigReader.getPassword())
            .inputTextIntoBirthdateField(ConfigReader.getBirthdate())
            .selectLanguageLevel();
    LOGGER.info("Все поля формы успешно заполнены");
    
    assertEquals(formPage.getPasswordValue(), formPage.getConfirmPasswordValue(),
        "Пароль и подтверждение пароля должны совпадать");
    LOGGER.info("Assert пройден: пароль и подтверждение пароля совпадают");
    
    formPage.clickSubmitButton();
    
    String outputText = formPage.getOutputText();
    assertFalse(outputText.isEmpty(), "Блок содержит данные");
    
    assertAll("Проверка вывода данных",
        () -> assertTrue(outputText.contains("Имя пользователя: " + formPage.getUsernameValue())),
        () -> assertTrue(outputText.contains("Электронная почта: " + formPage.getEmailValue())),
        () -> assertTrue(outputText.contains("Дата рождения: " + formPage.getBirthdateValue())),
        () -> assertTrue(outputText.contains("Уровень языка: " + formPage.getLanguageLevelValue()))
    );
    LOGGER.info("Данные отображаются корректно");
  }
}