package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CharValidationPage {
    private WebDriver driver;

    // 7 Char Validation elements
    private By textField = By.id("validation-message");
    private By validationMessage = By.id("validation-message");

    public CharValidationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String text) {
        driver.findElement(textField).clear();
        driver.findElement(textField).sendKeys(text);
    }

    public String getValidationMessage() {
        return driver.findElement(validationMessage).getText();
    }

    public boolean isInputValid() {
        String message = getValidationMessage();
        return !message.contains("Invalid");
    }
}
