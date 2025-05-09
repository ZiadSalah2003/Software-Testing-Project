package Tests;

import Pages.HomePage;
import Pages.SwagLabsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwagLabsTest extends TestBase {
    private HomePage homePage;
    private SwagLabsPage swagLabsPage;
    
    @BeforeClass
    public void setupClass() {
        // First navigate to TestPages website
        driver.get("https://testpages.eviltester.com/styled/index.html");
        sleep(2000); // Wait 2 seconds for initial load
        
        homePage = new HomePage(driver);
        
        // Now navigate to Swag Labs website from TestPages
        System.out.println("Navigating from TestPages to Swag Labs");
        swagLabsPage = homePage.navigateToSwagLabs();
        sleep(3000); // Wait 3 seconds for Swag Labs to load fully
        
        // Verify we're on the login page
        try {
            Assert.assertTrue(swagLabsPage.isLoginFormDisplayed(), "Login form should be displayed");
            System.out.println("Successfully verified login form is displayed");
        } catch (Exception e) {
            System.out.println("Failed to verify login form, retrying direct navigation...");
            // If verification fails, retry with direct navigation
            driver.get("https://www.saucedemo.com");
            sleep(1000);
            swagLabsPage = new SwagLabsPage(driver);
        }
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
     * Test Case 1: Login with empty username and empty password
     * Attempts to login without entering any credentials
     */
    @Test(priority = 1)
    public void testLoginWithEmptyFields() {
        System.out.println("=== Test Case 1: Login with empty username and empty password ===");
        
        // Ensure we're on the login page
        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(1000);
            swagLabsPage = new SwagLabsPage(driver);
        }
        
        // Clear fields to ensure they're empty
        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");
        
        // Click login button without entering any credentials
        swagLabsPage.clickLogin();
        sleep(1000); // Wait for error message to appear
        
        // Verify error message is displayed
        Assert.assertTrue(swagLabsPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMsg = swagLabsPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username is required"), 
                         "Error message should indicate username is required, but got: " + errorMsg);
        
        System.out.println("Test completed: Error message correctly shown for empty fields");
    }
    
    /**
     * Test Case 2: Login with username only
     * Enters only username "standard_user" without password
     */
    @Test(priority = 2)
    public void testLoginWithUsernameOnly() {
        System.out.println("=== Test Case 2: Login with username only ===");
        
        // Ensure we're on the login page
        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(3000);
            swagLabsPage = new SwagLabsPage(driver);
        }
        
        // Clear fields first
        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");
        sleep(1000); // Wait 1 second
        
        // Enter only username
        swagLabsPage.enterUsername("standard_user");
        swagLabsPage.clickLogin();
        sleep(1000); // Wait for error message to appear
        
        // Verify error message is displayed
        Assert.assertTrue(swagLabsPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMsg = swagLabsPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Password is required"), 
                         "Error message should indicate password is required, but got: " + errorMsg);
        
        System.out.println("Test completed: Error message correctly shown for missing password");
    }
    
    /**
     * Test Case 4: Login with valid credentials
     * Enters both username "standard_user" and password "secret_sauce"
     */
    @Test(priority = 4)
    public void testLoginWithValidCredentials() {
        System.out.println("=== Test Case 4: Login with valid credentials ===");
        
        // Ensure we're on the login page
        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(3000);
            swagLabsPage = new SwagLabsPage(driver);
        }
        
        // Clear fields first
        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");
        sleep(1000); // Wait 1 second
        
        // Enter valid credentials
        swagLabsPage.enterUsername("standard_user");
        sleep(1000); // Wait 1 second
        swagLabsPage.enterPassword("secret_sauce");
        sleep(1000); // Wait 1 second
        swagLabsPage.clickLogin();
        sleep(2000); // Wait longer for successful login to complete
        
        // Verify successful login
        Assert.assertTrue(swagLabsPage.isLoginSuccessful(), 
                         "Login should be successful with valid credentials");
        
        System.out.println("Test completed: Successfully logged in with valid credentials");
    }
    
    /**
     * Test Case 5: Sort products and add items to cart
     * 1. Login with valid credentials
     * 2. Sort products by Z to A (optimized)
     * 3. Sort products by price (low to high)
     * 4. Sort products by price (high to low)
     * 5. Add Test.allTheThings() T-Shirt (Red) to cart
     * 6. Add Sauce Labs Onesie to cart
     */
    @Test(priority = 5)
    public void testSortProductsAndAddToCart() {
        System.out.println("=== Test Case 5: Sort products and add items to cart ===");
        
        // First ensure we're logged in
        if (!swagLabsPage.isLoginSuccessful()) {
            // If not logged in, perform login
            System.out.println("Not logged in, performing login first");
            
            // Ensure we're on the login page
            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(3000);
                swagLabsPage = new SwagLabsPage(driver);
            }
            
            // Login with valid credentials
            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(2000);
            
            // Verify login was successful
            Assert.assertTrue(swagLabsPage.isLoginSuccessful(), 
                            "Login should be successful before proceeding with product sorting");
        }
        
        // Sort products by Name (Z to A) - optimized for speed
        System.out.println("Sorting products by Name (Z to A) - optimized");
        swagLabsPage.sortProductsBy("za");
        // No explicit wait after Z to A sorting to make it faster
        
        // Sort products by Price (low to high)
        System.out.println("Sorting products by Price (low to high)");
        swagLabsPage.sortProductsBy("lohi");
        sleep(1000); // Wait 1 second as requested
        
        // Sort products by Price (high to low)
        System.out.println("Sorting products by Price (high to low)");
        swagLabsPage.sortProductsBy("hilo");
        sleep(1000); // Wait 1 second as requested
        
        // Add Test.allTheThings() T-Shirt (Red) to cart
        swagLabsPage.addTShirtRedToCart();
        
        // Add Sauce Labs Onesie to cart
        swagLabsPage.addOnesieToCart();
        
        // Verify that 2 items were added to cart
        int cartCount = swagLabsPage.getCartItemCount();
        Assert.assertEquals(cartCount, 2, "Cart should contain 2 items but contains " + cartCount);
        
        System.out.println("Test completed: Successfully sorted products and added items to cart");
    }
    
    /**
     * Test Case 6: Complete Checkout Flow
     * 1. Login with valid credentials if not already logged in
     * 2. Sort products by Name (Z to A) - optimized for speed
     * 3. Click on shopping cart
     * 4. Login again if redirected
     * 5. Click on shopping cart again
     * 6. Remove an item
     * 7. Checkout
     * 8. Fill checkout info (first name, last name, postal code)
     * 9. Continue checkout
     * 10. Complete order
     * 11. Return to home page
     */
    @Test(priority = 6)
    public void testCompleteCheckoutFlow() {
        System.out.println("=== Test Case 6: Complete Checkout Flow ===");
        
        // Step 1: Ensure we're logged in with valid credentials
        if (!swagLabsPage.isLoginSuccessful()) {
            // If not logged in, perform login
            System.out.println("Not logged in, performing login first");
            
            // Ensure we're on the login page
            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500); // Wait 0.5 seconds
                swagLabsPage = new SwagLabsPage(driver);
            }
            
            // Login with valid credentials
            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500); // Wait 0.5 seconds
            
            // Verify login was successful
            Assert.assertTrue(swagLabsPage.isLoginSuccessful(), 
                            "Login should be successful before proceeding with checkout flow");
        }
        
        // Step 2: Sort products by Name (Z to A) - optimized for speed
        System.out.println("Sorting products by Name (Z to A) - optimized");
        swagLabsPage.sortProductsBy("za");
        // No additional wait after Z to A sorting to make it faster
        
        // Step 3: Click on shopping cart
        System.out.println("Clicking on shopping cart");
        swagLabsPage.clickOnCart();
        
        // Step 4: Check if redirected to login page and login again if needed
        if (swagLabsPage.isLoginFormDisplayed()) {
            System.out.println("Redirected to login page, logging in again");
            swagLabsPage.enterUsername("standard_user");
            swagLabsPage.enterPassword("secret_sauce");
            swagLabsPage.clickLogin();
            sleep(500); // Wait 0.5 seconds
        }
        
        // Step 5: Click on shopping cart again
        System.out.println("Clicking on shopping cart again");
        swagLabsPage.clickOnCart();
        
        // Step 6: Remove Sauce Labs Onesie from cart (using optimized method)
        System.out.println("Removing Sauce Labs Onesie from cart (optimized)");
        swagLabsPage.removeOnesieFromCart();
        
        // Step 7: Click checkout button (using optimized method)
        System.out.println("Clicking checkout button (optimized)");
        swagLabsPage.clickCheckoutFast();
        
        // Step 8: Fill checkout form
        System.out.println("Filling checkout information");
        swagLabsPage.fillCheckoutInfo("test", "test", "12345");
        
        // Step 9: Click continue
        System.out.println("Clicking continue button");
        swagLabsPage.clickContinue();
        
        // Step 10: Click finish
        System.out.println("Clicking finish button");
        swagLabsPage.clickFinish();
        
        // Verify order completion page is displayed
        Assert.assertTrue(swagLabsPage.isOrderCompletionDisplayed(), 
                         "Order completion page should be displayed");
        
        // Step 11: Click back home
        System.out.println("Clicking back home button");
        swagLabsPage.clickBackHome();
        
        System.out.println("Test completed: Successfully completed checkout flow with optimized operations");
    }
    
    /**
     * Test Case 7: Quick Checkout with Optimized Operations
     * This test case uses the optimized methods for a faster checkout flow
     */
    @Test(priority = 7)
    public void testQuickCheckoutFlow() {
        System.out.println("=== Test Case 7: Quick Checkout with Optimized Operations ===");
        
        // Ensure we're logged in with valid credentials
        if (!swagLabsPage.isLoginSuccessful()) {
            // If not logged in, perform login
            System.out.println("Not logged in, performing login first");
            
            // Ensure we're on the login page
            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500);
                swagLabsPage = new SwagLabsPage(driver);
            }
            
            // Login with valid credentials
            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500);
        }
        
        // Add items to cart if needed
        int cartItems = swagLabsPage.getCartItemCount();
        if (cartItems == 0) {
            // Add some products to cart
            swagLabsPage.addTShirtRedToCart();
            swagLabsPage.addOnesieToCart();
        }
        
        // Navigate to cart
        swagLabsPage.clickOnCart();
        
        // Execute the optimized checkout flow using the helper method
        swagLabsPage.completeCheckoutFast("test", "test", "12345");
        
        System.out.println("Test completed: Successfully executed quick checkout flow");
    }
}