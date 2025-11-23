package dto;

public class User {

  private final String username;
  private final String email;
  private final String password;
  private final String confirmPassword;
  private final String birthdate;
  
  public User(String username, String email, String password, String birthdate) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.confirmPassword = password;
    this.birthdate = birthdate;
  }
  
  public String getUsername() {
    return username;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public String getConfirmPassword() {
    return confirmPassword;
  }
  
  public String getBirthdate() {
    return birthdate;
  }
}