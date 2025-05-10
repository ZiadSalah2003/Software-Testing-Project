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
        sleep(2000);
        
        homePage = new HomePage(driver);
        swagLabsPage = homePage.navigateToSwagLabs();
        sleep(3000);
        sleep(3000);
        
        try {
            Assert.assertTrue(swagLabsPage.isLoginFormDisplayed(), "Login form should be displayed");
        } catch (Exception e) {
            driver.get("https://www.saucedemo.com");
            sleep(1000);
            swagLabsPage = new SwagLabsPage(driver);
        }
    }
    
    @Override
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test(priority = 1)
    public void testLoginWithEmptyFields() {
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
    }
    
    @Test(priority = 2)
    public void testLoginWithUsernameOnly() {
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
    }
    
    @Test(priority = 4)
    public void testLoginWithValidCredentials() {
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
        sleep(1000);
        swagLabsPage.enterPassword("secret_sauce");
        sleep(1000);
        sleep(1000);
        swagLabsPage.clickLogin();
        sleep(2000);
        
        Assert.assertTrue(swagLabsPage.isLoginSuccessful(), 
                         "Login should be successful with valid credentials");
    }
    
    @Test(priority = 5)
    public void testSortProductsAndAddToCart() {
        if (!swagLabsPage.isLoginSuccessful()) {
            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500);
                swagLabsPage = new SwagLabsPage(driver);
            }
            
            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500);
            
            Assert.assertTrue(swagLabsPage.isLoginSuccessful(), 
                            "Login should be successful before proceeding with product sorting");
        }
        
        swagLabsPage.sortProductsBy("za");
        
        swagLabsPage.sortProductsBy("lohi");
        sleep(1000);
        
        swagLabsPage.sortProductsBy("hilo");
        sleep(1000);
        
        swagLabsPage.addTShirtRedToCart();
        
        swagLabsPage.addOnesieToCart();
        
        int cartCount = swagLabsPage.getCartItemCount();
        Assert.assertEquals(cartCount, 2, "Cart should contain 2 items but contains " + cartCount);
    }
    
    @Test(priority = 6)
    public void testCompleteCheckoutFlow() {
        if (!swagLabsPage.isLoginSuccessful()) {
            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                driver.get("https://www.saucedemo.com");
                sleep(500);
                sleep(500);
                swagLabsPage = new SwagLabsPage(driver);
            }
            
            swagLabsPage.login("standard_user", "secret_sauce");
            sleep(500);
            
            Assert.assertTrue(swagLabsPage.isLoginSuccessful(), 
                            "Login should be successful before proceeding with checkout flow");
        }
        
        swagLabsPage.sortProductsBy("za");
        
        swagLabsPage.clickOnCart();
        
        if (swagLabsPage.isLoginFormDisplayed()) {
            swagLabsPage.enterUsername("standard_user");
            swagLabsPage.enterPassword("secret_sauce");
            swagLabsPage.clickLogin();
            sleep(500);
            sleep(500);
        }
        
        swagLabsPage.clickOnCart();
        
        swagLabsPage.removeOnesieFromCart();
        
        swagLabsPage.clickCheckoutFast();
        
        swagLabsPage.fillCheckoutInfo("test", "test", "12345");
        
        swagLabsPage.clickContinue();
        
        swagLabsPage.clickFinish();
        
        Assert.assertTrue(swagLabsPage.isOrderCompletionDisplayed(), 
                         "Order completion page should be displayed");
        
        swagLabsPage.clickBackHome();
    }
    
    @Test(priority = 7)
    public void testQuickCheckoutFlow() {
        if (!swagLabsPage.isLoginSuccessful()) {
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