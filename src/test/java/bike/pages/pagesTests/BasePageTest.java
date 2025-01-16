package bike.pages.pagesTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import bike.pages.BasePage;
import bike.pages.LoginPage;

public class BasePageTest {

    protected WebDriver dWebDriver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    private String url = "http://localhost:8080"; 
    
    @BeforeEach
    public void setUp() {
        dWebDriver = new SafariDriver();
        dWebDriver.manage().window().fullscreen();
        dWebDriver.get(url);
        basePage = new BasePage();
        BasePage.setdWebDriver(dWebDriver);
        loginPage = new LoginPage();
    }

    @AfterEach
    public void tearDown() {
        dWebDriver.quit();
    }
}