package Tests;

import Pages.HomePage;
import Pages.LoginDemoBlazePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginDemoBlaze extends TestBase {
    private HomePage homePage;
    private LoginDemoBlazePage loginDemoBlazePage;
    
    @BeforeClass
    public void setupClass() {
        // First navigate to TestPages website
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000); // Wait 1 second for initial load
        
        homePage = new HomePage(driver);
        
        // Now navigate to DemoBlaze website from TestPages
        System.out.println("Navigating from TestPages to DemoBlaze");
        homePage.navigateToDemoBlaze();
        sleep(2000); // Wait for DemoBlaze to load
        
        // Initialize LoginDemoBlazePage
        loginDemoBlazePage = new LoginDemoBlazePage(driver);
    }
    
    /**
     * Test Case 5: Login with username only
     * Opens login modal, enters only username "ziad", clicks login button, then closes the modal
     */    @Test(priority = 1)
    public void testLoginWithUsernameOnly() {
        System.out.println("=== Test Case 5: Login with username only ===");
        
        // Use the page object method for login with username only
        loginDemoBlazePage.loginWithUsernameOnly("ziadsalah");
        sleep(1000);
        
        // Handle alert
        loginDemoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        loginDemoBlazePage.closeLoginModal();
        sleep(1000);
        
        System.out.println("Test Case 1 completed");
    }
    
    /**
     * Test Case 6: Login with password only
     * Opens login modal, enters only password "ziad", clicks login button, then closes the modal
     */    @Test(priority = 2)
    public void testLoginWithPasswordOnly() {
        System.out.println("=== Test Case 6: Login with password only ===");
        
        // Use the page object method for login with password only
        loginDemoBlazePage.loginWithPasswordOnly("ziadsalah");
        sleep(1000);
        
        // Handle alert
        loginDemoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        loginDemoBlazePage.closeLoginModal();
        sleep(1000);
        
        System.out.println("Test Case 2 completed");
    }
    
    /**
     * Test Case 7: Login with empty fields
     * Opens login modal, leaves both fields empty, clicks login button, then closes the modal
     */    @Test(priority = 3)
    public void testLoginWithEmptyFields() {
        System.out.println("=== Test Case 7: Login with empty fields ===");
        
        // Use the page object method for login with empty fields
        loginDemoBlazePage.loginWithEmptyFields();
        sleep(1000);
        
        // Handle alert
        loginDemoBlazePage.handleAlert();
        sleep(1000);
        
        // Close modal
        loginDemoBlazePage.closeLoginModal();
        sleep(1000);
        
        System.out.println("Test Case 3 completed");
    }
    
    /**
     * Test Case 8: Login with complete information
     * Opens login modal, enters both username and password as "ziad", clicks login button, then closes the modal
     */    @Test(priority = 4)
    public void testLoginWithCompleteInfo() {
        System.out.println("=== Test Case 8: Login with complete information ===");

        // Use the page object method for login with complete information
        loginDemoBlazePage.loginWithCompleteInfo("ziadsalah", "ziadsalah");
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

        System.out.println("Test Case 4 completed");
    }
}
