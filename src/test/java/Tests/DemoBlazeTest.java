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
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000);
        
        homePage = new HomePage(driver);
        demoBlazePage = homePage.navigateToDemoBlaze();
        sleep(2000);
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
    public void testSignUpWithUsernameOnly() {
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah516");
        sleep(1000);
        
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        sleep(1000);
        
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeSignUpModal();
        sleep(1000);
    }
    
    @Test(priority = 2)
    public void testSignUpWithPasswordOnly() {
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        sleep(1000);
        
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah516");
        sleep(1000);
        
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeSignUpModal();
        sleep(1000);
    }
    
    @Test(priority = 3)
    public void testSignUpWithEmptyFields() {
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeSignUpModal();
        sleep(1000);
    }
    
    @Test(priority = 4)
    public void testSignUpWithCompleteInfo() {
        demoBlazePage.clickSignUp();
        sleep(1000);
        
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah516");
        sleep(1000);
        
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah516");
        sleep(1000);
        
        WebElement signUpButton = driver.findElement(By.xpath("//button[contains(@onclick, 'register()')]"));
        signUpButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeSignUpModal();
        sleep(1000);
    }
    
    @Test(priority = 5)
    public void testLoginWithUsernameOnly() {
        demoBlazePage.clickLogin();
        sleep(1000);

        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah516");
        sleep(1000);
        
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        sleep(1000);
        
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeLoginModal();
        sleep(1000);
    }
    
    @Test(priority = 6)
    public void testLoginWithPasswordOnly() {
        demoBlazePage.clickLogin();
        sleep(1000);
        
        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        sleep(1000);
        
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah516");
        sleep(1000);
        
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeLoginModal();
        sleep(1000);
    }
    
    @Test(priority = 7)
    public void testLoginWithEmptyFields() {
        demoBlazePage.clickLogin();
        sleep(1000);
        
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeLoginModal();
        sleep(1000);
    }
    
    @Test(priority = 8)
    public void testLoginWithCompleteInfo() {
        demoBlazePage.clickLogin();
        sleep(1000);

        WebElement usernameField = driver.findElement(By.id("loginusername"));
        usernameField.clear();
        usernameField.sendKeys("ziadsalah516");
        sleep(1000);

        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.clear();
        passwordField.sendKeys("ziadsalah516");
        sleep(1000);

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@onclick, 'logIn()')]"));
        loginButton.click();
        sleep(1000);
        sleep(1000);
        sleep(1000);

        demoBlazePage.handleAlert();
        sleep(1000);

        String result = driver.findElement(By.id("nameofuser")).getText();
        assert result.equals("Welcome ziadsalah516");
        sleep(1000);
    }
    
    @Test(priority = 9)
    public void testContactWithCompleteInfo() {
        demoBlazePage.clickContact();
        sleep(1000);
        
        demoBlazePage.submitContactForm(
            "test@gmail.com", 
            "test user", 
            "This is a test message"
        );
        sleep(1000);
        
        demoBlazePage.handleAlert();
        sleep(1000);
        
        demoBlazePage.closeContactModal();
        sleep(1000);
    }
    
    @Test(priority = 10)
    public void testSamsungGalaxyS6Purchase() {
        demoBlazePage.navigateToHome();
        sleep(1000);
        
        demoBlazePage.navigateToPhones();
        sleep(1000);
        
        demoBlazePage.navigateToProductPage("Samsung galaxy s6");
        sleep(1000);
        
        demoBlazePage.addToCart();
        sleep(1000);
        
        demoBlazePage.navigateToCart();
        sleep(1000);
        
        demoBlazePage.clickPlaceOrder();
        sleep(1000);
        
        String name = "Test User";
        String country = "United States";
        String city = "New York";
        String creditCard = "4111111111111111";
        String month = "05";
        String year = "2025";
        
        demoBlazePage.fillOrderForm(name, country, city, creditCard, month, year);
        sleep(1000);
        
        demoBlazePage.clickPurchase();
        sleep(1000);
        
        boolean isSuccess = demoBlazePage.isOrderSuccessful();
        String confirmationText = demoBlazePage.getOrderConfirmationText();
        
        demoBlazePage.confirmPurchase();
        sleep(1000);
    }
}