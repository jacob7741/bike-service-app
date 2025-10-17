package bike.pages;

import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;

public class MechanicPage extends BasePage{

    private By editButtonField = By.id("editButton");
    private By doneButtonField = By.id("doneButton");
    public BooleanSupplier isLoggedIn;

    
    public void clickEditButton() {
        dWebDriver.findElement(editButtonField).click();
    }

    public void clickDoneButton() {
        dWebDriver.findElement(doneButtonField).click();
    }
}