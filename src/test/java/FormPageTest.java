import static org.apache.logging.log4j.LogManager.*;
import static utils.DataGenerator.*;

import dto.User;
import factory.WebDriverFactory;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.FormPage;

public class FormPageTest {
  
  private static final Logger LOGGER = getLogger(FormPageTest.class);
  private WebDriver driver;
  
  @BeforeEach
  public void startDriver() {
    driver = new WebDriverFactory().create();
    String browserName = System.getProperty("browser");
    LOGGER.info("Драйвер запущен для браузера: {}", browserName);
  }
  
  @AfterEach
  public void driverStop() {
    if (driver != null) {
      driver.close();
      LOGGER.debug("Драйвер закрыт");
    }
  }
  
  @Test
  @DisplayName("Заполнение формы на странице 'Form Page'")
  public void formPageTest() {
    String password = generatePassword();
    
    User user = new User(
        System.getProperty("username", generateUsername()),
        System.getProperty("email", generateEmail()),
        System.getProperty("password", password),
        System.getProperty("birthdate", generateBirthdate())
    );
    
    FormPage formPage = new FormPage(driver);
    
    formPage.getURL();
    formPage.fillFormAndSubmit(
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getConfirmPassword(),
            user.getBirthdate()
        )
        .assertFormFieldsContainEnteredData(user)
        .assertPasswordsMatch()
        .assertOutputDataIsCorrect();
  }
}