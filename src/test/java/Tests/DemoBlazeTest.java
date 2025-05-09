package Tests;

import Pages.DemoBlazePage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoBlazeTest extends TestBase {
    private HomePage homePage;
    private DemoBlazePage demoBlazePage;
    
    @BeforeClass
    public void setupClass() {
        // First navigate to TestPages website
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000); // Wait 1 second for initial load
        
        homePage = new HomePage(driver);
        
        // Now navigate to DemoBlaze website from TestPages
        System.out.println("Navigating from TestPages to DemoBlaze");
        demoBlazePage = homePage.navigateToDemoBlaze();
        sleep(2000); // Wait for DemoBlaze to load
    }
    
    /**
     * @deprecated This method is kept for backwards compatibility but uses implicitWait internally
     * Overrides the base class method to ensure all waits in this test file use implicit wait
     */
    @Override
    @Deprecated
    protected void sleep(int milliseconds) {
        // Use the implicitWait method from the parent class
        super.implicitWait(milliseconds);
    }
    
    /**
     * Test Case 1: Sign up with username only
     * Opens sign-up modal, enters only username "ziad", clicks sign-up button, then closes the modal
     */
    @Test(priority = 1)
    public void testSignUpWithUsernameOnly() {
        System.out.println("=== Test Case 1: Sign up with username only ===");
        
        // Open sign-up modal
        demoBlazePage.clickSignUp();
        sleep(1000); // Wait 1 second between operations
        
        // Find username field and enter value
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah");
        sleep(1000);
        
        // Leave password field empty
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        sleep(1000);
        
        // Click the sign-up button
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 1 completed");
    }
    
    /**
     * Test Case 2: Sign up with password only
     * Opens sign-up modal, enters only password "ziad", clicks sign-up button, then closes the modal
     */
    @Test(priority = 2)
    public void testSignUpWithPasswordOnly() {
        System.out.println("=== Test Case 2: Sign up with password only ===");
        
        // Open sign-up modal
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        // Leave username field empty
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        sleep(1000);
        
        // Find password field and enter value
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah");
        sleep(1000);
        
        // Click the sign-up button
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 2 completed");
    }
    
    /**
     * Test Case 3: Sign up with empty fields
     * Opens sign-up modal, leaves both fields empty, clicks sign-up button, then closes the modal
     */
    @Test(priority = 3)
    public void testSignUpWithEmptyFields() {
        System.out.println("=== Test Case 3: Sign up with empty fields ===");
        
        // Open sign-up modal
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        // Leave username field empty
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        sleep(1000);
        
        // Leave password field empty
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        sleep(1000);
        
        // Click the sign-up button
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 3 completed");
    }
    
    /**
     * Test Case 4: Sign up with complete information
     * Opens sign-up modal, enters both username and password as "ziad", clicks sign-up button, then closes the modal
     */
    @Test(priority = 4)
    public void testSignUpWithCompleteInfo() {
        System.out.println("=== Test Case 4: Sign up with complete information ===");
        
        // Open sign-up modal
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        // Find username field and enter value
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah");
        sleep(1000);
        
        // Find password field and enter value
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah");
        sleep(1000);
        
        // Click the sign-up button
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        driver.switchTo().alert().accept();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 4 completed");
    }
    
    /**
     * Test Case 5: Login with username only
     * Opens login modal, enters only username "ziad", clicks login button, then closes the modal
     */
    @Test(priority = 5)
    public void testLoginWithUsernameOnly() {
        System.out.println("=== Test Case 5: Login with username only ===");
        
        // Open login modal
        demoBlazePage.clickLogin();
        sleep(1000); // Wait 1 second between operations
        
        // Find username field and enter value
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah");
        sleep(1000);
        
        // Leave password field empty
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        sleep(1000);
        
        // Click the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeLoginModal();
        sleep(1000);
        
        System.out.println("Test Case 5 completed");
    }
    
    /**
     * Test Case 6: Login with password only
     * Opens login modal, enters only password "ziad", clicks login button, then closes the modal
     */
    @Test(priority = 6)
    public void testLoginWithPasswordOnly() {
        System.out.println("=== Test Case 6: Login with password only ===");
        
        // Open login modal
        demoBlazePage.clickLogin();
        sleep(1000);
        
        // Leave username field empty
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        sleep(1000);
        
        // Find password field and enter value
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah");
        sleep(1000);
        
        // Click the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeLoginModal();
        sleep(1000);
        
        System.out.println("Test Case 6 completed");
    }
    
    /**
     * Test Case 7: Login with empty fields
     * Opens login modal, leaves both fields empty, clicks login button, then closes the modal
     */
    @Test(priority = 7)
    public void testLoginWithEmptyFields() {
        System.out.println("=== Test Case 7: Login with empty fields ===");
        
        // Open login modal
        demoBlazePage.clickLogin();
        sleep(1000);
        
        // Leave username field empty
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        sleep(1000);
        
        // Leave password field empty
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        sleep(1000);
        
        // Click the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        demoBlazePage.closeLoginModal();
        sleep(1000);
        
        System.out.println("Test Case 7 completed");
    }
    
    /**
     * Test Case 8: Login with complete information
     * Opens login modal, enters both username and password as "ziad", clicks login button, then closes the modal
     */
    @Test(priority = 8)
    public void testLoginWithCompleteInfo() {
        System.out.println("=== Test Case 8: Login with complete information ===");

        // Open login modal
        demoBlazePage.clickLogin();
        sleep(1000);

        // Find username field and enter value
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah");
        sleep(1000);

        // Find password field and enter value
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah");
        sleep(1000);

        // Click the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);

        // Handle the alert by clicking OK
        driver.switchTo().alert().accept();
        sleep(1000);

        // Check if login was successful
        try {
            // Look for the welcome message element
            WebElement welcomeMessage = driver.findElement(By.id("nameofuser"));
            System.out.println("Login successful, welcome message: " + welcomeMessage.getText());

            // Logout after successful login to reset state
            WebElement logoutLink = driver.findElement(By.id("logout2"));
            logoutLink.click();
            sleep(1000);
        } catch (Exception e) {
            System.out.println("Login might have failed, welcome message not found: " + e.getMessage());
        }

        System.out.println("Test Case 8 completed");
    }
    /**
     * Test Case 9: Contact with complete information
     * Opens contact modal, fills all fields, clicks send message button, then closes the modal
     */
    @Test(priority = 9)
    public void testContactWithCompleteInfo() {
        System.out.println("=== Test Case 9: Contact with complete information ===");
        
        // Open contact modal
        WebElement contactLink = driver.findElement(By.xpath("//a[contains(text(), 'Contact')]"));
        contactLink.click();
        
        // Create WebDriverWait instance with timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for the contact modal to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));
        sleep(1000);
        
        // Wait for email field to be clickable then fill it
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("recipient-email")));
        emailField.clear();
        emailField.sendKeys("test@gmail.com");
        sleep(1000);
        
        // Fill name field - wait for it to be clickable first
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("recipient-name")));
        nameField.clear();
        nameField.sendKeys("test user");
        sleep(1000);
        
        // Fill message field - wait for it to be clickable first
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("message-text")));
        messageField.clear();
        messageField.sendKeys("This is a test message for the contact form!");
        sleep(1000);
        
        // Click the send message button
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@onclick, 'send()')]")));
        sendButton.click();
        sleep(1000);
        
        // Handle alert
        demoBlazePage.handleAlert();
        sleep(1000);
        
        // Close contact modal
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='exampleModal']//button[contains(text(),'Close')]")));
        closeButton.click();
        sleep(1000);
        
        System.out.println("Test Case 9 completed");
    }
    
    /**
     * Test Case 10: Samsung Galaxy S6 Purchase Flow
     * 1. Navigate to home page
     * 2. Navigate to Phones category
     * 3. Select Samsung Galaxy S6
     * 4. Add to cart
     * 5. Navigate to cart
     * 6. Place order
     * 7. Fill in order details
     * 8. Purchase
     * 9. Verify success
     */
    @Test(priority = 10)
    public void testSamsungGalaxyS6Purchase() {
        System.out.println("=== Test Case 10: Samsung Galaxy S6 Purchase Flow ===");
        
        // Make sure we're on the home page
        demoBlazePage.navigateToHome();
        sleep(1000);
        
        // Navigate to the Phones category
        demoBlazePage.navigateToPhones();
        sleep(1000);
        
        // Navigate to Samsung Galaxy S6 product page
        demoBlazePage.navigateToProductPage("Samsung galaxy s6");
        sleep(1000);
        
        // Add product to cart
        demoBlazePage.addToCart();
        sleep(1000);
        
        // Go to cart
        demoBlazePage.navigateToCart();
        sleep(1000);
        
        // Place order
        demoBlazePage.clickPlaceOrder();
        sleep(1000);
        
        // Fill in order details
        String name = "Test User";
        String country = "United States";
        String city = "New York";
        String creditCard = "4111111111111111";
        String month = "05";
        String year = "2025";
        
        demoBlazePage.fillOrderForm(name, country, city, creditCard, month, year);
        sleep(1000);
        
        // Purchase
        demoBlazePage.clickPurchase();
        sleep(1000);
        
        // Verify success message
        boolean isSuccess = demoBlazePage.isOrderSuccessful();
        String confirmationText = demoBlazePage.getOrderConfirmationText();
        
        // Print result
        if (isSuccess) {
            System.out.println("Purchase successful! Confirmation: " + confirmationText);
        } else {
            System.out.println("Purchase failed!");
        }
        
        // Close the confirmation dialog
        demoBlazePage.confirmPurchase();
        sleep(1000);
        
        System.out.println("Test Case 10 completed");
    }
}