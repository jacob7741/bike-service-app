 package bike.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    
    public static WebDriver dWebDriver;

    public static void setdWebDriver(WebDriver dWebDriver) {
        BasePage.dWebDriver = dWebDriver;
    }

    protected WebElement find(By locator) {
        return dWebDriver.findElement(locator);
    }

    protected void set(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }
    
    protected void click(By locator) {
        find(locator).click();
    }
}