package pages;

import static org.apache.logging.log4j.LogManager.*;
import static utils.DateConverter.*;
import static org.assertj.core.api.Assertions.*;

import data.EBrowserTypeData;
import dto.User;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage extends AbsBasePage {
  
  private static final Logger LOGGER = getLogger(FormPage.class);
  
  public FormPage(WebDriver driver) {
    super(driver, "/form.html");
  }
  
  // Локаторы
  @FindBy(id = "username")
  private WebElement usernameField;
  
  @FindBy(id = "email")
  private WebElement emailField;
  
  @FindBy(id = "password")
  private WebElement passwordField;
  
  @FindBy(id = "confirm_password")
  private WebElement confirmPasswordField;
  
  @FindBy(id = "birthdate")
  private WebElement birthdateField;
  
  @FindBy(id = "language_level")
  private WebElement languageLevelField;
  
  @FindBy(css = "input[type='submit']")
  private WebElement submitButton;
  
  @FindBy(id = "output")
  private WebElement outputDiv;
  
  // Методы ввода данных в поля и click
  public FormPage inputTextIntoUsernameField(String text) {
    inputText(usernameField, text);
    return this;
  }
  
  public FormPage inputTextIntoEmailField(String text) {
    inputText(emailField, text);
    return this;
  }
  
  public FormPage inputTextIntoPasswordField(String text) {
    inputText(passwordField, text);
    return this;
  }
  
  public FormPage inputTextIntoConfirmPasswordField(String text) {
    inputText(confirmPasswordField, text);
    return this;
  }
  
  public FormPage inputTextIntoBirthdateField(String date) {
    String dateToInput;
    if (getBrowserType() == EBrowserTypeData.FIREFOX) {
      dateToInput = convertDateToOutputFormat(date);
    } else {
      dateToInput = date;
    }
    
    inputText(birthdateField, dateToInput);
    return this;
  }
  
  public FormPage selectRandomLanguageLevel() {
    selectRandomOption(languageLevelField);
    return this;
  }
  
  public FormPage clickSubmitButton() {
    click(submitButton);
    return this;
  }
  
  // Get методы для получения value полей после ввода данных
  public String getUsernameValue() {
    return getFieldValue(usernameField);
  }
  
  public String getEmailValue() {
    return getFieldValue(emailField);
  }
  
  public String getPasswordValue() {
    return getFieldValue(passwordField);
  }
  
  public String getConfirmPasswordValue() {
    return getFieldValue(confirmPasswordField);
  }
  
  public String getBirthdateValue() {
    return getFieldValue(birthdateField);
  }
  
  public String getLanguageLevelValue() {
    return getSelectedOptionValue(languageLevelField);
  }
  
  public String getOutputText() {
    return getFieldText(outputDiv);
  }
  
  // Заполнение и отправка формы
  public FormPage fillFormAndSubmit(String username, String email, String password, String confirmPassword, String birthdate) {
    return inputTextIntoUsernameField(username)
        .inputTextIntoEmailField(email)
        .inputTextIntoPasswordField(password)
        .inputTextIntoConfirmPasswordField(confirmPassword)
        .inputTextIntoBirthdateField(birthdate)
        .selectRandomLanguageLevel()
        .clickSubmitButton();
  }
  
  // Assert на проверку того, что все поля формы действительно заполнены
  public FormPage assertFormFieldsContainEnteredData(User user) {
    assertThat(getUsernameValue())
        .as("Поле 'Имя пользователя' должно содержать введенное значение")
        .contains(user.getUsername());
    
    assertThat(getEmailValue())
        .as("Поле 'Электронная почта' должно содержать введенное значение")
        .contains(user.getEmail());
    
    assertThat(getPasswordValue())
        .as("Поле 'Пароль' должно содержать введенное значение")
        .contains(user.getPassword());
    
    assertThat(getConfirmPasswordValue())
        .as("Поле 'Подтвердите пароль' должно содержать введенное значение")
        .contains(user.getPassword());
    
    assertThat(getBirthdateValue())
        .as("Поле 'Дата рождения' не должно быть пустым")
        .isNotEmpty();
    
    assertThat(getLanguageLevelValue())
        .as("Поле 'Уровень знания языка' должно быть выбрано")
        .isNotNull();
    
    LOGGER.info("Assert пройден: все поля формы содержат введенные данные");
    return this;
  }
  
  // Assert на совпадение пароля и его подтверждения
  public FormPage assertPasswordsMatch() {
    assertThat(getPasswordValue())
        .as("Пароль и подтверждение пароля должны совпадать")
        .isEqualTo(getConfirmPasswordValue());
    LOGGER.info("Assert пройден: пароль и подтверждение пароля совпадают");
    return this;
  }
  
  // Assert на проверку корректности вывода данных
  public void assertOutputDataIsCorrect() {
    String outputText = getOutputText();
    
    assertThat(outputText)
        .as("Блок вывода должен содержать данные")
        .isNotEmpty()
        .contains("Имя пользователя: " + getUsernameValue())
        .contains("Электронная почта: " + getEmailValue())
        .contains("Дата рождения: " + getBirthdateValue())
        .contains("Уровень языка: " + getLanguageLevelValue());
    
    LOGGER.info("Assert пройден: данные отображаются корректно");
  }
}