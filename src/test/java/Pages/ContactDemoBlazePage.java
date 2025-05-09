package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class ContactDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation elements
    private By contactLink = By.xpath("//a[contains(text(), 'Contact')]");

    // Contact modal elements
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
     * Kept for backward compatibility with existing code
     */
    @Deprecated
    private void sleep(int milliseconds) {
        implicitWait(milliseconds);
    }

    /**
     * Fast contact modal opener - optimized
     */
    public void clickContact() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(contactModal));
        } catch (Exception e) {
            System.out.println("Error opening contact modal: " + e.getMessage());
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                    driver.findElement(contactLink));
            } catch (Exception ex) {
                System.out.println("JavaScript click also failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Submit contact form with optimized input handling - faster version
     */
    public void submitContactForm(String email, String name, String message) {
        try {
            // Clear and enter email
            WebElement emailElement = wait.until(
                    ExpectedConditions.elementToBeClickable(contactEmailField));
            emailElement.clear();
            emailElement.sendKeys(email);
            
            // Clear and enter name
            WebElement nameElement = wait.until(
                    ExpectedConditions.elementToBeClickable(contactNameField));
            nameElement.clear();
            nameElement.sendKeys(name);
            
            // Clear and enter message
            WebElement messageElement = wait.until(
                    ExpectedConditions.elementToBeClickable(contactMessageField));
            messageElement.clear();
            messageElement.sendKeys(message);
            
            // Click send message button
            WebElement sendButtonElement = wait.until(
                    ExpectedConditions.elementToBeClickable(sendMessageButton));
            sendButtonElement.click();
        } catch (Exception e) {
            System.out.println("Error during contact form submission: " + e.getMessage());
        }
    }

    /**
     * Close contact modal
     */
    public void closeContactModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeContactModalButton)).click();
        } catch (Exception e) {
            try {
                // Try the X button as an alternative
                wait.until(ExpectedConditions.elementToBeClickable(closeContactModalX)).click();
            } catch (Exception ex) {
                System.out.println("Error closing contact modal: " + ex.getMessage());
                // Try refreshing the page as a last resort
                driver.navigate().refresh();
            }
        }
    }    /**
     * Handle alert with faster response time - optimized version
     * Returns the alert text if present, or a message indicating no alert was found
     */
    public String handleAlert() {
        try {
            // Use a shorter wait time to check for alerts to avoid long delays
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (TimeoutException e) {
            // This is expected when no alert is present, so just return a message
            return "No alert present";
        } catch (Exception e) {
            System.out.println("Error handling alert: " + e.getMessage());
            return "Alert handling error: " + e.getMessage();
        }
    }
    
    /**
     * Submit contact form with complete information
     */
    public void submitContactWithCompleteInfo(String email, String name, String message) {
        clickContact();
        
        // Wait for email field to be clickable then fill it
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(contactEmailField));
        emailField.clear();
        emailField.sendKeys(email);
        sleep(500);
        
        // Fill name field
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(contactNameField));
        nameField.clear();
        nameField.sendKeys(name);
        sleep(500);
        
        // Fill message field
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(contactMessageField));
        messageField.clear();
        messageField.sendKeys(message);
        sleep(500);
        
        // Click send message button
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(sendMessageButton));
        sendButton.click();
        sleep(500);
    }
}
