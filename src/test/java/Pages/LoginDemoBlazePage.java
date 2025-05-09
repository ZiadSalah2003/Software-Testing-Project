package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class LoginDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation elements
    private By loginLink = By.id("login2");
    private By logoutLink = By.id("logout2");
    private By welcomeUserText = By.id("nameofuser");

    // Login modal elements
    private By loginUsernameField = By.id("loginusername");
    private By loginPasswordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[contains(@onclick, 'logIn()')]");
    private By closeLoginModalButton = By.xpath("//div[@id='logInModal']//button[contains(text(),'Close')]");
    private By closeLoginModalX = By.xpath("//div[@id='logInModal']//button[@class='close']");

    public LoginDemoBlazePage(WebDriver driver) {
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
     * Fast login modal opener - optimized
     */
    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        } catch (Exception e) {
            System.out.println("Error opening login modal: " + e.getMessage());
            // Try JavaScript click as a fallback
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                    driver.findElement(loginLink));
            } catch (Exception ex) {
                System.out.println("JavaScript click also failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Login with optimized input handling - faster version
     */
    public void login(String username, String password) {
        try {
            // Clear and enter username
            WebElement usernameElement = wait.until(
                    ExpectedConditions.elementToBeClickable(loginUsernameField));
            usernameElement.clear();
            usernameElement.sendKeys(username);
            
            // Clear and enter password
            WebElement passwordElement = wait.until(
                    ExpectedConditions.elementToBeClickable(loginPasswordField));
            passwordElement.clear();
            passwordElement.sendKeys(password);
            
            // Click login button
            WebElement loginButtonElement = wait.until(
                    ExpectedConditions.elementToBeClickable(loginButton));
            loginButtonElement.click();
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    /**
     * Close login modal - explicitly click Close button first
     */
    public void closeLoginModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginModalButton)).click();
        } catch (Exception e) {
            try {
                // Try the X button as an alternative
                wait.until(ExpectedConditions.elementToBeClickable(closeLoginModalX)).click();
            } catch (Exception ex) {
                System.out.println("Error closing login modal: " + ex.getMessage());
                // Try refreshing the page as a last resort
                driver.navigate().refresh();
            }
        }
    }

    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get welcome message text
     */
    public String getWelcomeMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText)).getText();
        } catch (Exception e) {
            return "";
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
     * Optimized logout function
     */
    public void logout() {
        try {
            if (isLoggedIn()) {
                wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
                sleep(500); // Small wait for logout to complete
            }
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
        }
    }
    
    /**
     * Login with username only
     */
    public void loginWithUsernameOnly(String username) {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
    }
    
    /**
     * Login with password only
     */
    public void loginWithPasswordOnly(String password) {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
    }
    
    /**
     * Login with empty fields
     */
    public void loginWithEmptyFields() {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
    }
      /**
     * Login with complete information
     */
    public void loginWithCompleteInfo(String username, String password) {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
        
        // Handle alert if it appears (may not appear for successful login)
        handleAlert();
        sleep(500);
    }
}
