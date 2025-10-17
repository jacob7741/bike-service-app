package bike.pages.pagesTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bike.pages.MechanicPage;

public class MechanicPageTest extends BasePageTest{

    @Test
    public void MechanicPageTestLogin() {

        MechanicPage mechanicPage = loginPage.login("janesmith", "password34");
        loginPage.clickLoginButton();
    }
}