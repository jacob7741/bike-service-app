package bike.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By userNameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginButton");

    public void setUserNameField(String userName) {
        set(userNameField, userName);
    }
    public void setPasswordField(String password) {
        set(passwordField, password);
    }
    public MechanicPage clickLoginButton() {
        click(loginButton);
        return new MechanicPage();
    }

    public MechanicPage login(String userName, String password) {
        setUserNameField(userName);
        setPasswordField(password);
        return clickLoginButton();
    }
}