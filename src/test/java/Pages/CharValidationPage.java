package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CharValidationPage {
    private WebDriver driver;

    // 7 Char Validation elements
    private By charactersInput = By.name("characters");
    private By validationMessage = By.name("validation_message");
    private By checkButton = By.name("validate");

    public CharValidationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String text) {
        System.out.println("Clearing and entering text: " + text);
        driver.findElement(charactersInput).clear();
        driver.findElement(charactersInput).sendKeys(text);
    }

    public void clickCheckButton() {
        System.out.println("Clicking the Check Input button");
        driver.findElement(checkButton).click();
        // Small wait to allow validation to complete
        sleep(500);
    }

    public String getValidationMessage() {
        String message = driver.findElement(validationMessage).getAttribute("value");
        System.out.println("Validation message: " + message);
        return message;
    }

    public boolean isInputValid() {
        String message = getValidationMessage();
        boolean isValid = !message.toLowerCase().contains("invalid");
        System.out.println("Is input valid based on message? " + isValid);
        return isValid;
    }
    
    // Helper method that combines entering text and checking
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
