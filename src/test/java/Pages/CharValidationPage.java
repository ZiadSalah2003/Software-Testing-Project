package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

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
        implicitWait(500);
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
    
    /**
     * Sets implicit wait on the driver.
     * This replaces the previous sleep method with a more efficient implicit wait.
     * @param milliseconds Maximum time to wait in milliseconds
     */
    private void implicitWait(int milliseconds) {
        // Set implicit wait with a maximum of 200ms for consistency with previous implementation
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Math.min(milliseconds, 200)));
    }
    
    /**
     * @deprecated Use implicitWait method instead
     */
    @Deprecated
    private void sleep(int milliseconds) {
        implicitWait(milliseconds);
    }
}
