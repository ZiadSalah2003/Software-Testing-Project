package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class SignUpDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation elements
    private By signUpLink = By.id("signin2");

    // Sign Up modal elements
    private By signUpModal = By.id("signInModal");
    private By signUpUsernameField = By.id("sign-username");
    private By signUpPasswordField = By.id("sign-password");
    private By signUpButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Sign up')]");
    private By closeSignUpModalButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Close')]");
    private By closeSignUpModalX = By.xpath("//div[@id='signInModal']//button[@class='close']");

    public SignUpDemoBlazePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    /**
     * Helper method to sleep for the specified milliseconds
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
     * Ultra-fast sign up modal opener - optimized
     */
    public void clickSignUp() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
        } catch (Exception e) {
            System.out.println("Error opening sign up modal: " + e.getMessage());
            // Try JavaScript click as a fallback
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                    driver.findElement(signUpLink));
            } catch (Exception ex) {
                System.out.println("JavaScript click also failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Sign up with optimized input handling - faster version
     */
    public void signUp(String username, String password) {
        try {
            // Clear and enter username
            WebElement usernameElement = wait.until(
                    ExpectedConditions.elementToBeClickable(signUpUsernameField));
            usernameElement.clear();
            usernameElement.sendKeys(username);
            
            // Clear and enter password
            WebElement passwordElement = wait.until(
                    ExpectedConditions.elementToBeClickable(signUpPasswordField));
            passwordElement.clear();
            passwordElement.sendKeys(password);
            
            // Click sign up button
            WebElement signUpButtonElement = wait.until(
                    ExpectedConditions.elementToBeClickable(signUpButton));
            signUpButtonElement.click();
        } catch (Exception e) {
            System.out.println("Error during sign up: " + e.getMessage());
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
     * Close sign up modal - explicitly click Close button first
     */
    public void closeSignUpModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeSignUpModalButton)).click();
        } catch (Exception e) {
            try {
                // Try the X button as an alternative
                wait.until(ExpectedConditions.elementToBeClickable(closeSignUpModalX)).click();
            } catch (Exception ex) {
                System.out.println("Error closing sign up modal: " + ex.getMessage());
                // Try refreshing the page as a last resort
                driver.navigate().refresh();
            }
        }
    }

    /**
     * Perform a specific sign-up sequence with username only
     */
    public void signUpWithUsernameOnly(String username) {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
    
    /**
     * Perform a specific sign-up sequence with password only
     */
    public void signUpWithPasswordOnly(String password) {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
    
    /**
     * Perform a sign-up with empty fields
     */
    public void signUpWithEmptyFields() {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
    
    /**
     * Perform sign-up with complete information
     */
    public void signUpWithCompleteInfo(String username, String password) {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
}
