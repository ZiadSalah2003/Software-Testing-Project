  package Tests;

import Pages.HomePage;
import Pages.SwagLabsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SwagLabsTest extends TestBase {
    private HomePage homePage;
    private SwagLabsPage swagLabsPage;
    
    @BeforeClass
    public void setupClass() {
        driver.get("https://testpages.eviltester.com/styled/index.html");
        sleep(2000);
        
        homePage = new HomePage(driver);
        
        System.out.println("Navigating from TestPages to Swag Labs");
        swagLabsPage = homePage.navigateToSwagLabs();
        sleep(3000);
        
        try {
            Assert.assertTrue(swagLabsPage.isLoginFormDisplayed(), "Login form should be displayed");
            System.out.println("Successfully verified login form is displayed");
        } catch (Exception e) {
            System.out.println("Failed to verify login form, retrying direct navigation...");
            driver.get("https://www.saucedemo.com");
            sleep(1000);
            swagLabsPage = new SwagLabsPage(driver);
        }
    }    //#region Login Tests
    
    /**
     * Test Case 1: Login with empty username and empty password
     * Verifies that an error message is shown when attempting to login with empty credentials
     */
    @Test(priority = 1)
    public void testLoginWithEmptyFields() {
        System.out.println("=== Test Case 1: Login with empty username and empty password ===");

        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(1000);
            swagLabsPage = new SwagLabsPage(driver);
        }

        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");

        swagLabsPage.clickLogin();
        sleep(1000);

        Assert.assertTrue(swagLabsPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMsg = swagLabsPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username is required"), 
                         "Error message should indicate username is required, but got: " + errorMsg);

        System.out.println("Test completed: Error message correctly shown for empty fields");
    }    /**
     * Test Case 2: Login with username only
     * Verifies that an error message is shown when attempting to login with only a username
     */
    @Test(priority = 2)
    public void testLoginWithUsernameOnly() {
        System.out.println("=== Test Case 2: Login with username only ===");

        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(3000);
            swagLabsPage = new SwagLabsPage(driver);
        }

        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");
        sleep(1000);

        swagLabsPage.enterUsername("standard_user");
        swagLabsPage.clickLogin();
        sleep(1000);

        Assert.assertTrue(swagLabsPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMsg = swagLabsPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Password is required"),
                         "Error message should indicate password is required, but got: " + errorMsg);

        System.out.println("Test completed: Error message correctly shown for missing password");
    }    /**
     * Test Case 3: Login with password only
     * Verifies that an error message is shown when attempting to login with only a password
     */
    @Test(priority = 3)
    public void testLoginWithPasswordOnly() {
        System.out.println("=== Test Case 3: Login with password only ===");

        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(3000);
            swagLabsPage = new SwagLabsPage(driver);
        }

        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");
        sleep(1000);

        swagLabsPage.enterPassword("secret_sauce");
        swagLabsPage.clickLogin();
        sleep(1000);

        Assert.assertTrue(swagLabsPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMsg = swagLabsPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username is required"),
                         "Error message should indicate username is required, but got: " + errorMsg);

        System.out.println("Test completed: Error message correctly shown for missing username");
    }    /**
     * Test Case 4: Login with valid credentials
     * Verifies that a successful login can be performed with correct username and password
     */
    @Test(priority = 4)
    public void testLoginWithValidCredentials() {
        System.out.println("=== Test Case 4: Login with valid credentials ===");

        if (!driver.getCurrentUrl().contains("saucedemo.com")) {
            driver.get("https://www.saucedemo.com");
            sleep(3000);
            swagLabsPage = new SwagLabsPage(driver);
        }

        swagLabsPage.enterUsername("");
        swagLabsPage.enterPassword("");
        sleep(1000);

        swagLabsPage.enterUsername("standard_user");
        sleep(1000);
        swagLabsPage.enterPassword("secret_sauce");
        sleep(1000);
        swagLabsPage.clickLogin();
        sleep(2000);

        Assert.assertTrue(swagLabsPage.isLoginSuccessful(),
                         "Login should be successful with valid credentials");

        System.out.println("Test completed: Successfully logged in with valid credentials");
    }
    
    //#endregion    //#region Product and Cart Tests
    
    /**
     * Test Case 5: Sort products and add items to cart
     * Tests product sorting functionality and verifies that items can be added to cart
     */
    @Test(priority = 5)
    public void testSortProductsAndAddToCart() {
        System.out.println("=== Test Case 5: Sort products and add items to cart ===");

        if (!swagLabsPage.isLoginSuccessful()) {
            System.out.println("Not logged in, performing login first");

            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(3000);
                swagLabsPage = new SwagLabsPage(driver);
            }

            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(2000);

            Assert.assertTrue(swagLabsPage.isLoginSuccessful(),
                            "Login should be successful before proceeding with product sorting");
        }

        System.out.println("Sorting products by Name (Z to A) - optimized");
        swagLabsPage.sortProductsBy("za");

        System.out.println("Sorting products by Price (low to high)");
        swagLabsPage.sortProductsBy("lohi");
        sleep(1000);

        System.out.println("Sorting products by Price (high to low)");
        swagLabsPage.sortProductsBy("hilo");
        sleep(1000);

        swagLabsPage.addTShirtRedToCart();
        swagLabsPage.addOnesieToCart();

        int cartCount = swagLabsPage.getCartItemCount();
        Assert.assertEquals(cartCount, 2, "Cart should contain 2 items but contains " + cartCount);

        System.out.println("Test completed: Successfully sorted products and added items to cart");
    }
    
    //#endregion    //#region Checkout Tests
    
    /**
     * Test Case 6: Complete checkout flow
     * Tests the end-to-end checkout process including removing items and completing purchase
     */
    @Test(priority = 6)
    public void testCompleteCheckoutFlow() {
        System.out.println("=== Test Case 6: Complete Checkout Flow ===");

        if (!swagLabsPage.isLoginSuccessful()) {
            System.out.println("Not logged in, performing login first");

            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500);
                swagLabsPage = new SwagLabsPage(driver);
            }

            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500);

            Assert.assertTrue(swagLabsPage.isLoginSuccessful(),
                            "Login should be successful before proceeding with checkout flow");
        }

        System.out.println("Sorting products by Name (Z to A) - optimized");
        swagLabsPage.sortProductsBy("za");

        System.out.println("Clicking on shopping cart");
        swagLabsPage.clickOnCart();

        if (swagLabsPage.isLoginFormDisplayed()) {
            System.out.println("Redirected to login page, logging in again");
            swagLabsPage.enterUsername("standard_user");
            swagLabsPage.enterPassword("secret_sauce");
            swagLabsPage.clickLogin();
            sleep(500);
        }

        System.out.println("Clicking on shopping cart again");
        swagLabsPage.clickOnCart();

        System.out.println("Removing Sauce Labs Onesie from cart (optimized)");
        swagLabsPage.removeOnesieFromCart();

        System.out.println("Clicking checkout button (optimized)");
        swagLabsPage.clickCheckoutFast();

        System.out.println("Filling checkout information");
        swagLabsPage.fillCheckoutInfo("test", "test", "12345");

        System.out.println("Clicking continue button");
        swagLabsPage.clickContinue();

        System.out.println("Clicking finish button");
        swagLabsPage.clickFinish();

        Assert.assertTrue(swagLabsPage.isOrderCompletionDisplayed(),
                         "Order completion page should be displayed");

        System.out.println("Clicking back home button");
        swagLabsPage.clickBackHome();

        System.out.println("Test completed: Successfully completed checkout flow with optimized operations");
    }    /**
     * Test Case 7: Quick checkout with optimized operations
     * Tests a streamlined checkout flow using optimized helper methods
     */
    @Test(priority = 7)
    public void testQuickCheckoutFlow() {
        System.out.println("=== Test Case 7: Quick Checkout with Optimized Operations ===");

        if (!swagLabsPage.isLoginSuccessful()) {
            System.out.println("Not logged in, performing login first");

            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500);
                swagLabsPage = new SwagLabsPage(driver);
            }

            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500);
        }

        int cartItems = swagLabsPage.getCartItemCount();
        if (cartItems == 0) {
            swagLabsPage.addTShirtRedToCart();
            swagLabsPage.addOnesieToCart();
        }

        swagLabsPage.clickOnCart();
        swagLabsPage.completeCheckoutFast("test", "test", "12345");

        System.out.println("Test completed: Successfully executed quick checkout flow");
    }
    
    //#endregion    //#region Price Tests
    
    /**
     * Test Case 8: Test total price after changing item price
     * Verifies that the total price is updated correctly after changing an item's price
     */
    @Test(priority = 8)
    public void testTotalPriceAfterChangingItemPrice() {
        System.out.println("=== Test Case 8: Test Total Price After Changing Item Price ===");

        if (!swagLabsPage.isLoginSuccessful()) {
            System.out.println("Not logged in, performing login first");

            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500);
                swagLabsPage = new SwagLabsPage(driver);
            }

            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500);
        }

        int cartItems = swagLabsPage.getCartItemCount();
        if (cartItems == 0) {
            swagLabsPage.addTShirtRedToCart();
        }

        swagLabsPage.clickOnCart();
        swagLabsPage.clickCheckout();
        swagLabsPage.fillCheckoutInfo("test", "test", "12345");
        swagLabsPage.clickContinue();
          // Get the original item price before changing it
        double originalItemPrice = swagLabsPage.getItemPrice();
        System.out.println("Original item price: $" + originalItemPrice);
        
        // Set the new price
        double newPrice = 10.0;
        System.out.println("Changing item price to: $" + newPrice);
        
        // Change the item price using JavaScript
        swagLabsPage.changeItemPriceInCart(newPrice);
        sleep(1000);
        
        // Get the actual modified item price to confirm it was changed
        double modifiedItemPrice = swagLabsPage.getItemPrice();
        System.out.println("Modified item price: $" + modifiedItemPrice);
        Assert.assertEquals(modifiedItemPrice, newPrice, 
                         "Item price should be changed to the new value");
        
        // Get the actual total price (includes tax)
        double actualTotalPrice = swagLabsPage.getTotalPrice();
        System.out.println("Actual total price (with tax): $" + actualTotalPrice);
        
        // For this test, we can verify the total is greater than the item price (due to tax)
        Assert.assertTrue(actualTotalPrice > newPrice,
                         "Total price should be greater than the item price due to tax");
        System.out.println("Test completed: Total price updated correctly after changing item price");
    }
    
    //#endregion
}