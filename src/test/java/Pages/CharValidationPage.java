package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CharValidationPage {
    private WebDriver driver;
    private By charactersInput = By.name("characters");
    private By validationMessage = By.name("validation_message");
    private By checkButton = By.name("validate");

    public CharValidationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String text) {
        driver.findElement(charactersInput).clear();
        driver.findElement(charactersInput).sendKeys(text);
    }

    public void clickCheckButton() {
        driver.findElement(checkButton).click();
        sleep(500);
    }

    public String getValidationMessage() {
        String message = driver.findElement(validationMessage).getAttribute("value");
        return message;
    }

    public boolean isInputValid() {
        String message = getValidationMessage();
        boolean isValid = !message.toLowerCase().contains("invalid");
        return isValid;
    }
    
    public void validateInput(String text) {
        enterText(text);
        clickCheckButton();
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
