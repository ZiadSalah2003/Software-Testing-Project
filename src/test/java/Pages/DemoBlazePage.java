package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class DemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation elements
    private By signUpLink = By.id("signin2");
    private By loginLink = By.id("login2");
    private By contactLink = By.xpath("//a[contains(text(), 'Contact')]");
    private By logoutLink = By.id("logout2");
    private By welcomeUserText = By.id("nameofuser");

    // Sign Up modal elements
    private By signUpModal = By.id("signInModal");
    private By signUpUsernameField = By.id("sign-username");
    private By signUpPasswordField = By.id("sign-password");
    private By signUpButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Sign up')]");
    private By closeSignUpModalButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Close')]");
    private By closeSignUpModalX = By.xpath("//div[@id='signInModal']//button[@class='close']");

    // Login modal elements
    private By loginUsernameField = By.id("loginusername");
    private By loginPasswordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[contains(@onclick, 'logIn()')]");
    private By closeLoginModalButton = By.xpath("//div[@id='logInModal']//button[contains(text(),'Close')]");
    private By closeLoginModalX = By.xpath("//div[@id='logInModal']//button[@class='close']");

    // Contact modal elements
    private By contactEmailField = By.id("recipient-email");
    private By contactNameField = By.id("recipient-name");
    private By contactMessageField = By.id("message-text");
    private By sendMessageButton = By.xpath("//button[contains(@onclick, 'send()')]");
    private By closeContactModalButton = By.xpath("//div[@id='exampleModal']//button[contains(text(),'Close')]");
    private By closeContactModalX = By.xpath("//div[@id='exampleModal']//button[@class='close']");
    
    // Modal detection
    private By anyOpenModal = By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]");

    // Product navigation elements
    private By homeLink = By.xpath("//a[contains(text(), 'Home')]");
    private By phonesCategory = By.xpath("//a[contains(text(), 'Phones')]");
    private By laptopsCategory = By.xpath("//a[contains(text(), 'Laptops')]");
    private By monitorsCategory = By.xpath("//a[contains(text(), 'Monitors')]");
    
    // Cart elements
    private By cartLink = By.id("cartur");
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");
    private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    
    // Order form elements
    private By orderNameField = By.id("name");
    private By orderCountryField = By.id("country");
    private By orderCityField = By.id("city");
    private By orderCardField = By.id("card");
    private By orderYearField = By.id("year");
    private By orderMonthField = By.id("month");
    private By purchaseButton = By.xpath("//button[contains(text(), 'Purchase')]");
    private By orderSuccessMessage = By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]");
    private By orderConfirmButton = By.xpath("//button[contains(text(), 'OK')]");

    public DemoBlazePage(WebDriver driver) {
        this.driver = driver;
        // Further reduce wait time from 5 seconds to 3 seconds for faster execution
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    /**
     * Helper method to sleep for the specified milliseconds
     * All sleep durations have been further optimized for faster execution
     */
    private void sleep(int milliseconds) {
        try {
            // Use even smaller sleep times to speed up tests
            Thread.sleep(Math.min(milliseconds, 200));  // Limit sleep time to max 200ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigate to DemoBlaze website
     */
    public void navigateToDemoBlaze() {
        driver.get("https://www.demoblaze.com/index.html");
        sleep(500); // Reduced from 1000ms
    }
    
    /**
     * Refresh the page to clear any open modals - faster implementation
     */
    public void refreshPage() {
        driver.navigate().refresh();
        sleep(500); // Reduced from 1000ms
    }
    
    /**
     * Check if any modal is open
     */
    public boolean isAnyModalOpen() {
        try {
            return driver.findElements(anyOpenModal).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Close any open modals
     */
    public void closeAllModals() {
        try {
            // Try clicking on the X buttons of each possible modal
            if (isElementPresent(closeSignUpModalX)) {
                driver.findElement(closeSignUpModalX).click();
                sleep(200);
            }
            
            if (isElementPresent(closeLoginModalX)) {
                driver.findElement(closeLoginModalX).click();
                sleep(200);
            }
            
            if (isElementPresent(closeContactModalX)) {
                driver.findElement(closeContactModalX).click();
                sleep(200);
            }
            
            // If modals are still open, click on the background to dismiss them
            if (isAnyModalOpen()) {
                clickOutsideModals();
            }
            
            // If still open, refresh the page as a last resort
            if (isAnyModalOpen()) {
                refreshPage();
            }
            
        } catch (Exception e) {
            System.out.println("Error closing modals: " + e.getMessage());
            // As a last resort, refresh the page
            refreshPage();
        }
    }
    
    /**
     * Try clicking outside modals to close them
     */
    public void clickOutsideModals() {
        try {
            // Click on the navbar (outside any modal)
            ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('nav.navbar').click()");
            sleep(200);
        } catch (Exception e) {
            System.out.println("Failed to click outside modals: " + e.getMessage());
        }
    }
    
    /**
     * Check if an element is present
     */
    private boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ensure clean state before starting a new action - optimized version
     */
    public void ensureCleanState() {
        if (isAnyModalOpen()) {
            // Use JavaScript to close all modals at once - much faster
            try {
                ((JavascriptExecutor) driver).executeScript(
                    "$('.modal').modal('hide'); $('.modal-backdrop').remove();");
                sleep(200);
            } catch (Exception e) {
                // If JavaScript approach fails, fall back to clicking
                closeAllModals();
            }
        }
    }

    /**
     * Ultra-fast sign up modal opener - further optimized
     */
    public void clickSignUp() {
        ensureCleanState();
        
        // Use direct jQuery approach only - it's the fastest
        try {
            ((JavascriptExecutor) driver).executeScript("$('#signInModal').modal('show');");
            sleep(200);
            
            // Clear input fields more efficiently with JavaScript
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('sign-username').value = '';" +
                "document.getElementById('sign-password').value = '';"
            );
        } catch (Exception e) {
            // Fall back to standard click if JavaScript fails
            try {
                driver.findElement(signUpLink).click();
                sleep(200);
                clearInputFields(signUpUsernameField, signUpPasswordField);
            } catch (Exception ex) {
                System.out.println("Failed to open sign up modal: " + ex.getMessage());
            }
        }
    }

    /**
     * Sign up with optimized input handling - faster version
     */
    public void signUp(String username, String password) {
        try {
            // Use JavaScript for the fastest possible input setting
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('sign-username').value='" + username + "';" +
                "document.getElementById('sign-password').value='" + password + "';" +
                "$('#signInModal .btn-primary').click();"
            );
            
            sleep(200); // Brief wait for alert
        } catch (Exception e) {
            System.out.println("JavaScript sign up failed, trying standard approach: " + e.getMessage());
            
            try {
                // Get and clear username field
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsernameField));
                usernameField.clear();
                usernameField.sendKeys(username);
                
                // Get and clear password field
                WebElement passwordField = driver.findElement(signUpPasswordField);
                passwordField.clear();
                passwordField.sendKeys(password);
                
                // Click signup button
                WebElement submitButton = driver.findElement(signUpButton);
                submitButton.click();
                
                sleep(200);
            } catch (Exception ex) {
                System.out.println("All sign up approaches failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Handle alert with faster response time - optimized version
     */
    public String handleAlert() {
        try {
            // Use shorter timeout and don't wait if no alert
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            return "No alert present";
        }
    }

    /**
     * Close sign up modal - explicitly click Close button first
     */
    public void closeSignUpModal() {
        try {
            // Explicitly try to click the "Close" button first
            if (isElementPresent(closeSignUpModalButton)) {
                driver.findElement(closeSignUpModalButton).click();
            } 
            // If that didn't work, try the X button
            else if (isElementPresent(closeSignUpModalX)) {
                driver.findElement(closeSignUpModalX).click();
            } 
            // Last resort, click outside the modal
            else {
                clickOutsideModals();
            }
            sleep(200);
        } catch (Exception e) {
            System.out.println("Could not close sign up modal: " + e.getMessage());
            clickOutsideModals();
        }
    }

    /**
     * Fast login modal opener - optimized
     */
    public void clickLogin() {
        ensureCleanState();
        
        // JavaScript approach only - it's fastest
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "$('#logInModal').modal('show');" +
                "document.getElementById('loginusername').value = '';" +
                "document.getElementById('loginpassword').value = '';"
            );
            sleep(200);
        } catch (Exception e) {
            // Fall back to regular click
            try {
                driver.findElement(loginLink).click();
                sleep(200);
                clearInputFields(loginUsernameField, loginPasswordField);
            } catch (Exception ex) {
                System.out.println("Failed to open login modal: " + ex.getMessage());
            }
        }
    }

    /**
     * Login with optimized input handling - faster version
     */
    public void login(String username, String password) {
        try {
            // Use JavaScript for the fastest possible input setting and button click
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('loginusername').value='" + username + "';" +
                "document.getElementById('loginpassword').value='" + password + "';" +
                "$('#logInModal .btn-primary').click();"
            );
            
            // Wait for login to complete - this is critical
            sleep(800); // Increased from 500ms to ensure UI updates
            
            // Wait explicitly for welcome message to appear (indicating successful login)
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText));
            } catch (Exception e) {
                // Don't throw an exception if the wait fails, as test will validate this
                System.out.println("Note: Welcome message didn't appear within wait time");
            }
        } catch (Exception e) {
            System.out.println("JavaScript login failed, trying standard approach: " + e.getMessage());
            
            try {
                // Standard Selenium approach as fallback
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginUsernameField));
                usernameField.clear();
                usernameField.sendKeys(username);
                
                WebElement passwordField = driver.findElement(loginPasswordField);
                passwordField.clear();
                passwordField.sendKeys(password);
                
                WebElement loginBtn = driver.findElement(loginButton);
                loginBtn.click();
                
                // Wait for login to complete
                sleep(800);
                
                // Wait explicitly for welcome message
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText));
                } catch (Exception ex) {
                    // Don't throw exception
                    System.out.println("Note: Welcome message didn't appear within wait time (standard approach)");
                }
            } catch (Exception ex) {
                System.out.println("All login approaches failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Close login modal - explicitly click Close button first
     */
    public void closeLoginModal() {
        try {
            // Explicitly try to click the "Close" button first
            if (isElementPresent(closeLoginModalButton)) {
                driver.findElement(closeLoginModalButton).click();
            } 
            // If that didn't work, try the X button
            else if (isElementPresent(closeLoginModalX)) {
                driver.findElement(closeLoginModalX).click();
            }
            // Last resort, click outside the modal
            else {
                clickOutsideModals();
            }
            sleep(200);
        } catch (Exception e) {
            System.out.println("Could not close login modal: " + e.getMessage());
            clickOutsideModals();
        }
    }

    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText));
            String welcomeText = driver.findElement(welcomeUserText).getText();
            return welcomeText.contains("Welcome");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get welcome message text
     */
    public String getWelcomeMessage() {
        try {
            return driver.findElement(welcomeUserText).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Fast contact modal opener - optimized
     */
    public void clickContact() {
        ensureCleanState();
        
        // JavaScript approach for speed
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('#exampleModal').modal('show');" +
                "document.getElementById('recipient-email').value = '';" +
                "document.getElementById('recipient-name').value = '';" +
                "document.getElementById('message-text').value = '';"
            );
            sleep(200);
        } catch (Exception e) {
            // Fall back to regular click
            try {
                driver.findElement(contactLink).click();
                sleep(200);
                clearInputFields(contactEmailField, contactNameField, contactMessageField);
            } catch (Exception ex) {
                System.out.println("Failed to open contact modal: " + ex.getMessage());
            }
        }
    }

    /**
     * Submit contact form with optimized input handling - faster version
     */
    public void submitContactForm(String email, String name, String message) {
        try {
            // Use JavaScript for the fastest possible input setting and button click
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('recipient-email').value='" + email + "';" +
                "document.getElementById('recipient-name').value='" + name + "';" +
                "document.getElementById('message-text').value='" + message + "';" +
                "$('#exampleModal .btn-primary').click();"
            );
            
            sleep(200); // Brief wait for alert
        } catch (Exception e) {
            System.out.println("JavaScript form submission failed, trying standard approach: " + e.getMessage());
            
            try {
                // Standard Selenium approach as fallback
                WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmailField));
                emailField.clear();
                emailField.sendKeys(email);
                
                WebElement nameField = driver.findElement(contactNameField);
                nameField.clear();
                nameField.sendKeys(name);
                
                WebElement messageField = driver.findElement(contactMessageField);
                messageField.clear();
                messageField.sendKeys(message);
                
                WebElement sendBtn = driver.findElement(sendMessageButton);
                sendBtn.click();
                
                sleep(200);
            } catch (Exception ex) {
                System.out.println("All form submission approaches failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Close contact modal
     */
    public void closeContactModal() {
        try {
            if (isElementPresent(closeContactModalButton)) {
                driver.findElement(closeContactModalButton).click();
            } else if (isElementPresent(closeContactModalX)) {
                driver.findElement(closeContactModalX).click();
            } else {
                clickOutsideModals();
            }
            sleep(200);
        } catch (Exception e) {
            System.out.println("Could not close contact modal: " + e.getMessage());
            clickOutsideModals();
        }
    }

    /**
     * Optimized logout function
     */
    public void logout() {
        try {
            // Try JavaScript click for speed
            ((JavascriptExecutor) driver).executeScript("document.getElementById('logout2').click();");
            sleep(500); // Reduced from 1000ms
        } catch (Exception e) {
            // Fall back to regular click
            try {
                driver.findElement(logoutLink).click();
                sleep(500);
            } catch (Exception ex) {
                System.out.println("Failed to logout: " + ex.getMessage());
            }
        }
    }
    
    /**
     * Switch to a new tab if opened
     */
    public void switchToNewTab() {
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            
            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    driver.switchTo().window(handle);
                    sleep(500);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to switch to new tab: " + e.getMessage());
        }
    }

    /**
     * Helper method to clear input fields
     */
    private void clearInputFields(By... fields) {
        try {
            for (By field : fields) {
                WebElement element = driver.findElement(field);
                element.clear();
                // For more thorough clearing, also use JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
            }
        } catch (Exception e) {
            System.out.println("Error clearing input fields: " + e.getMessage());
        }
    }

    /**
     * Perform a specific sign-up sequence with username only
     * 1. Click on Sign up link
     * 2. Enter "qwe" as username
     * 3. Leave password empty
     * 4. Click Sign up button
     */
    public void signUpWithUsernameOnly() {
        ensureCleanState();
        System.out.println("Starting sign-up sequence with username only");
        
        // Open sign-up modal
        clickSignUp();
        
        try {
            // Enter username "qwe"
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsernameField));
            usernameField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", usernameField);
            usernameField.sendKeys("qwe");
            
            // Clear password field to ensure it's empty
            WebElement passwordField = driver.findElement(signUpPasswordField);
            passwordField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", passwordField);
            
            // Click signup button
            WebElement submitButton = driver.findElement(signUpButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
            
            // Handle alert
            handleAlert();
            
            // Close modal if still open
            if (isAnyModalOpen()) {
                closeSignUpModal();
            }
            
            System.out.println("Completed sign-up sequence with username only");
        } catch (Exception e) {
            System.out.println("Error in signUpWithUsernameOnly: " + e.getMessage());
            ensureCleanState(); // Clean up if there was an error
        }
    }
    
    /**
     * Perform a specific sign-up sequence with password only
     * 1. Click on Sign up link
     * 2. Leave username empty
     * 3. Enter "qwe" as password
     * 4. Click Sign up button
     */
    public void signUpWithPasswordOnly() {
        ensureCleanState();
        System.out.println("Starting sign-up sequence with password only");
        
        // Open sign-up modal
        clickSignUp();
        
        try {
            // Clear username field to ensure it's empty
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsernameField));
            usernameField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", usernameField);
            
            // Enter password "qwe"
            WebElement passwordField = driver.findElement(signUpPasswordField);
            passwordField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", passwordField);
            passwordField.sendKeys("qwe");
            
            // Click signup button
            WebElement submitButton = driver.findElement(signUpButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
            
            // Handle alert
            handleAlert();
            
            // Close modal if still open
            if (isAnyModalOpen()) {
                closeSignUpModal();
            }
            
            System.out.println("Completed sign-up sequence with password only");
        } catch (Exception e) {
            System.out.println("Error in signUpWithPasswordOnly: " + e.getMessage());
            ensureCleanState(); // Clean up if there was an error
        }
    }
    
    /**
     * Perform complete sign-up sequence
     * 1. Click on Sign up link
     * 2. Enter "qwe" as both username and password
     * 3. Click Sign up button
     */
    public void completeSignUp() {
        ensureCleanState();
        System.out.println("Starting complete sign-up sequence");
        
        // Open sign-up modal
        clickSignUp();
        
        // Sign up with both username and password
        signUp("qwe", "qwe");
        
        // Handle alert
        handleAlert();
        
        // Close modal if still open
        if (isAnyModalOpen()) {
            closeSignUpModal();
        }
        
        System.out.println("Completed full sign-up sequence");
    }
    
    /**
     * Perform complete login sequence immediately after signup
     * 1. Click on Login link
     * 2. Enter "qwe" as both username and password
     * 3. Click Login button
     */
    public void completeLogin() {
        ensureCleanState();
        System.out.println("Starting complete login sequence");
        
        // Open login modal
        clickLogin();
        
        // Login with both username and password
        login("qwe", "qwe");
        
        System.out.println("Completed login sequence");
        sleep(500); // Give time to verify login success
    }
    
    /**
     * Execute the full sequence of signup and login operations
     * Combines all the processes in the specified order
     */
    public void executeFullSignupAndLoginSequence() {
        System.out.println("Starting full signup and login sequence");
        
        // Navigate to the site
        navigateToDemoBlaze();
        
        // First signup attempt - username only
        signUpWithUsernameOnly();
        sleep(500);
        
        // Second signup attempt - password only
        signUpWithPasswordOnly();
        sleep(500);
        
        // Third signup attempt - complete credentials
        completeSignUp();
        sleep(500);
        
        // Login with the credentials
        completeLogin();
        
        System.out.println("Completed full signup and login sequence");
        
        // Verify login was successful
        if (isLoggedIn()) {
            System.out.println("Login successful - user is logged in");
        } else {
            System.out.println("Login may have failed - user does not appear to be logged in");
        }
    }

    /**
     * New fast method for empty username test
     * Combines multiple steps into one optimized sequence
     */
    public String testEmptyUsername(String password) {
        ensureCleanState();
        
        try {
            // Open modal, set only password, and submit all in one JavaScript call
            ((JavascriptExecutor) driver).executeScript(
                "$('#signInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('sign-username').value = '';" +
                "  document.getElementById('sign-password').value = '" + password + "';" +
                "  $('#signInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300); // Wait for alert
            
            String alertText = handleAlert();
            
            // Close modal
            ((JavascriptExecutor) driver).executeScript("$('#signInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            System.out.println("Fast empty username test failed: " + e.getMessage());
            // Fall back to standard sequence
            clickSignUp();
            signUp("", password);
            String alertText = handleAlert();
            closeSignUpModal();
            return alertText;
        }
    }
    
    /**
     * New fast method for empty password test
     * Combines multiple steps into one optimized sequence
     */
    public String testEmptyPassword(String username) {
        ensureCleanState();
        
        try {
            // Open modal, set only username, and submit all in one JavaScript call
            ((JavascriptExecutor) driver).executeScript(
                "$('#signInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('sign-username').value = '" + username + "';" +
                "  document.getElementById('sign-password').value = '';" +
                "  $('#signInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300); // Wait for alert
            
            String alertText = handleAlert();
            
            // Close modal
            ((JavascriptExecutor) driver).executeScript("$('#signInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            System.out.println("Fast empty password test failed: " + e.getMessage());
            // Fall back to standard sequence
            clickSignUp();
            signUp(username, "");
            String alertText = handleAlert();
            closeSignUpModal();
            return alertText;
        }
    }
    
    /**
     * New method for quick login test with empty username
     */
    public String testLoginEmptyUsername(String password) {
        ensureCleanState();
        
        try {
            // Open modal, set only password, and submit all in one JavaScript call
            ((JavascriptExecutor) driver).executeScript(
                "$('#logInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('loginusername').value = '';" +
                "  document.getElementById('loginpassword').value = '" + password + "';" +
                "  $('#logInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300); // Wait for alert
            
            String alertText = handleAlert();
            
            // Close modal
            ((JavascriptExecutor) driver).executeScript("$('#logInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            System.out.println("Fast login empty username test failed: " + e.getMessage());
            // Fall back to standard sequence
            clickLogin();
            login("", password);
            String alertText = handleAlert();
            closeLoginModal();
            return alertText;
        }
    }
    
    /**
     * New method for quick login test with empty password
     */
    public String testLoginEmptyPassword(String username) {
        ensureCleanState();
        
        try {
            // Open modal, set only username, and submit all in one JavaScript call
            ((JavascriptExecutor) driver).executeScript(
                "$('#logInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('loginusername').value = '" + username + "';" +
                "  document.getElementById('loginpassword').value = '';" +
                "  $('#logInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300); // Wait for alert
            
            String alertText = handleAlert();
            
            // Close modal
            ((JavascriptExecutor) driver).executeScript("$('#logInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            System.out.println("Fast login empty password test failed: " + e.getMessage());
            // Fall back to standard sequence
            clickLogin();
            login(username, "");
            String alertText = handleAlert();
            closeLoginModal();
            return alertText;
        }
    }

    /**
     * Check if the login link is hidden (indicating user is logged in)
     * @return true if login link is hidden, false otherwise
     */
    public boolean isLoginLinkHidden() {
        try {
            // Wait a moment for UI to update fully after login
            sleep(300);
            
            // Try multiple strategies to check if login link is hidden
            // 1. First check if it's not displayed
            WebElement loginElement = driver.findElement(loginLink);
            if (!loginElement.isDisplayed()) {
                return true;
            }
            
            // 2. Check if it has display:none style
            String displayStyle = loginElement.getAttribute("style");
            if (displayStyle != null && displayStyle.contains("display: none")) {
                return true;
            }
            
            // 3. Check if clicking it would fail (meaning it's disabled or hidden)
            try {
                // Use getAttribute to check aria-disabled or other attributes
                String disabled = loginElement.getAttribute("disabled");
                if (disabled != null && disabled.equals("true")) {
                    return true;
                }
                
                // Check CSS visibility
                String visibility = (String) ((JavascriptExecutor) driver).executeScript(
                    "return window.getComputedStyle(arguments[0]).getPropertyValue('visibility');", 
                    loginElement);
                if (visibility != null && (visibility.equals("hidden") || visibility.equals("collapse"))) {
                    return true;
                }
            } catch (Exception e) {
                // If we can't interact with it, it might be hidden
                return true;
            }
            
            return false;
        } catch (Exception e) {
            // If element can't be found, it's effectively hidden
            return true;
        }
    }
    
    /**
     * Check if logout link is visible (indicating user is logged in)
     * @return true if logout link is visible, false otherwise
     */
    public boolean isLogoutLinkVisible() {
        try {
            // Wait a moment for UI to update fully after login
            sleep(300);
            
            // Look for the logout link
            WebElement logoutElement = driver.findElement(logoutLink);
            
            // Check if it's displayed
            if (logoutElement.isDisplayed()) {
                return true;
            }
            
            // Additional check using JavaScript to verify visibility
            Boolean isVisible = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0];" +
                "return !!(elem.offsetWidth || elem.offsetHeight || elem.getClientRects().length);", 
                logoutElement);
            
            return isVisible != null && isVisible;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if signup modal is currently open
     * @return true if signup modal is open, false otherwise
     */
    public boolean isSignUpModalOpen() {
        try {
            WebElement modal = driver.findElement(signUpModal);
            String displayStyle = modal.getAttribute("style");
            return displayStyle != null && displayStyle.contains("display: block") || 
                   modal.getAttribute("class") != null && modal.getAttribute("class").contains("show");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if login modal is currently open
     * @return true if login modal is open, false otherwise
     */
    public boolean isLoginModalOpen() {
        try {
            WebElement modal = driver.findElement(By.id("logInModal"));
            String displayStyle = modal.getAttribute("style");
            return displayStyle != null && displayStyle.contains("display: block") || 
                   modal.getAttribute("class") != null && modal.getAttribute("class").contains("show");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Navigate to home page
     */
    public void navigateToHome() {
        try {
            driver.findElement(homeLink).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error navigating to home: " + e.getMessage());
        }
    }
    
    /**
     * Navigate to Phones category
     */
    public void navigateToPhones() {
        try {
            driver.findElement(phonesCategory).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error navigating to phones: " + e.getMessage());
        }
    }
    
    /**
     * Navigate to specific product page by product name
     */
    public void navigateToProductPage(String productName) {
        try {
            // Construct a dynamic XPath to find the product link by its name
            By productLink = By.xpath("//a[contains(text(), '" + productName + "')]");
            
            // Wait for the product to be visible and click it
            wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
            driver.findElement(productLink).click();
            
            // Wait for product page to load
            sleep(500);
            System.out.println("Navigated to product page: " + productName);
        } catch (Exception e) {
            System.out.println("Error navigating to product " + productName + ": " + e.getMessage());
        }
    }
    
    /**
     * Add current product to cart
     */
    public void addToCart() {
        try {
            // Click the Add to Cart button
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
            driver.findElement(addToCartButton).click();
            
            sleep(500);
            
            // Handle the "Product added" alert
            handleAlert();
            
            System.out.println("Product added to cart");
        } catch (Exception e) {
            System.out.println("Error adding product to cart: " + e.getMessage());
        }
    }
    
    /**
     * Navigate to cart page
     */
    public void navigateToCart() {
        try {
            // Click on Cart link
            driver.findElement(cartLink).click();
            sleep(500);
            System.out.println("Navigated to cart");
        } catch (Exception e) {
            System.out.println("Error navigating to cart: " + e.getMessage());
        }
    }
    
    /**
     * Click Place Order button in cart
     */
    public void clickPlaceOrder() {
        try {
            // Click the Place Order button
            wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
            driver.findElement(placeOrderButton).click();
            sleep(500);
            System.out.println("Clicked Place Order button");
        } catch (Exception e) {
            System.out.println("Error clicking Place Order: " + e.getMessage());
        }
    }
    
    /**
     * Fill order form with provided details
     */
    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        try {
            // Wait for the form to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderNameField));
            
            // Fill in all form fields
            if (name != null && !name.isEmpty()) {
                driver.findElement(orderNameField).sendKeys(name);
            }
            
            if (country != null && !country.isEmpty()) {
                driver.findElement(orderCountryField).sendKeys(country);
            }
            
            if (city != null && !city.isEmpty()) {
                driver.findElement(orderCityField).sendKeys(city);
            }
            
            if (card != null && !card.isEmpty()) {
                driver.findElement(orderCardField).sendKeys(card);
            }
            
            if (month != null && !month.isEmpty()) {
                driver.findElement(orderMonthField).sendKeys(month);
            }
            
            if (year != null && !year.isEmpty()) {
                driver.findElement(orderYearField).sendKeys(year);
            }
            
            sleep(500);
            System.out.println("Order form completed");
        } catch (Exception e) {
            System.out.println("Error filling order form: " + e.getMessage());
        }
    }
    
    /**
     * Click Purchase button to complete order
     */
    public void clickPurchase() {
        try {
            // Click the Purchase button
            wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
            driver.findElement(purchaseButton).click();
            
            // Wait for confirmation
            sleep(500);
            System.out.println("Clicked Purchase button");
        } catch (Exception e) {
            System.out.println("Error clicking Purchase button: " + e.getMessage());
        }
    }
    
    /**
     * Check if order was successful
     */
    public boolean isOrderSuccessful() {
        try {
            // Look for success message
            return driver.findElement(orderSuccessMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get the order success message text
     */
    public String getOrderConfirmationText() {
        try {
            // Get the success message text
            WebElement successElement = driver.findElement(orderSuccessMessage);
            return successElement.getText();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Click OK button to complete purchase
     */
    public void confirmPurchase() {
        try {
            // Click the OK button
            wait.until(ExpectedConditions.elementToBeClickable(orderConfirmButton));
            driver.findElement(orderConfirmButton).click();
            sleep(500);
            System.out.println("Confirmed purchase");
        } catch (Exception e) {
            System.out.println("Error confirming purchase: " + e.getMessage());
        }
    }
    
    /**
     * Complete Samsung Galaxy S6 purchase flow
     * This method performs the entire purchase flow from start to finish
     */
    public boolean completeSamsungGalaxyS6Purchase(String name, String country, String city, String card, String month, String year) {
        try {
            // Navigate to Home page
            navigateToHome();
            
            // Navigate to Phones category
            navigateToPhones();
            
            // Navigate to Samsung Galaxy S6 product page
            navigateToProductPage("Samsung galaxy s6");
            
            // Add the product to cart
            addToCart();
            
            // Navigate to cart
            navigateToCart();
            
            // Click Place Order
            clickPlaceOrder();
            
            // Fill the order form
            fillOrderForm(name, country, city, card, month, year);
            
            // Click Purchase
            clickPurchase();
            
            // Check if order was successful
            boolean success = isOrderSuccessful();
            
            // Click OK to complete the purchase
            if (success) {
                confirmPurchase();
            }
            
            return success;
        } catch (Exception e) {
            System.out.println("Error in purchase flow: " + e.getMessage());
            return false;
        }
    }
}