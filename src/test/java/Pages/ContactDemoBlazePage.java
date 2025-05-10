package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class ContactDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By contactLink = By.xpath("//a[contains(text(), 'Contact')]");

    private By contactModal = By.id("exampleModal");
    private By contactEmailField = By.id("recipient-email");
    private By contactNameField = By.id("recipient-name");
    private By contactMessageField = By.id("message-text");
    private By sendMessageButton = By.xpath("//button[contains(@onclick, 'send()')]");
    private By closeContactModalButton = By.xpath("//div[@id='exampleModal']//button[contains(text(),'Close')]");
    private By closeContactModalX = By.xpath("//div[@id='exampleModal']//button[@class='close']");

    public ContactDemoBlazePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(Math.min(milliseconds, 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickContact() {
        wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactModal));
    }

    public void submitContactForm(String email, String name, String message) {
        WebElement emailElement = wait.until(
                ExpectedConditions.elementToBeClickable(contactEmailField));
        emailElement.clear();
        emailElement.sendKeys(email);
        
        WebElement nameElement = wait.until(
                ExpectedConditions.elementToBeClickable(contactNameField));
        nameElement.clear();
        nameElement.sendKeys(name);
        
        WebElement messageElement = wait.until(
                ExpectedConditions.elementToBeClickable(contactMessageField));
        messageElement.clear();
        messageElement.sendKeys(message);
        
        WebElement sendButtonElement = wait.until(
                ExpectedConditions.elementToBeClickable(sendMessageButton));
        sendButtonElement.click();
    }

    public void closeContactModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeContactModalButton)).click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(closeContactModalX)).click();
            } catch (Exception ex) {
                driver.navigate().refresh();
            }
        }
    } 
    public String handleAlert() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (TimeoutException e) {
            return "No alert present";
        } catch (Exception e) {
            return "Alert handling error: " + e.getMessage();
        }
    }
    
    public void submitContactWithCompleteInfo(String email, String name, String message) {
        clickContact();
        
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(contactEmailField));
        emailField.clear();
        emailField.sendKeys(email);
        sleep(500);
        
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(contactNameField));
        nameField.clear();
        nameField.sendKeys(name);
        sleep(500);
        
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(contactMessageField));
        messageField.clear();
        messageField.sendKeys(message);
        sleep(500);
        
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));
        sendButton.click();
        sleep(500);
    }
}
