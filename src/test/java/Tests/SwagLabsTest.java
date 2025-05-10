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
        driver.get("https://testpages.eviltester.com/styled/index.html");
        sleep(2000);
        
        homePage = new HomePage(driver);
        swagLabsPage = homePage.navigateToSwagLabs();
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
        swagLabsPage.enterPassword("secret_sauce");
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
}