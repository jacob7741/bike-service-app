package bike.pages.pagesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginPageTest extends BasePageTest{

    @Test
    public void testLogin() {
        loginPage.setUserNameField("janesmith");
        loginPage.setPasswordField("password34");
        loginPage.clickLoginButton();
    }
}