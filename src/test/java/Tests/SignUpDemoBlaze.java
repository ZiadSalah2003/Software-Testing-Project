package Tests;

import Pages.HomePage;
import Pages.SignUpDemoBlazePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignUpDemoBlaze extends TestBase {
    private HomePage homePage;
    private SignUpDemoBlazePage signUpDemoBlazePage;
    
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
        
        // Initialize SignUpDemoBlazePage
        signUpDemoBlazePage = new SignUpDemoBlazePage(driver);
    }
      /**
     * Test Case 1: Sign up with username only
     * Opens sign-up modal, enters only username "ziadsalah", clicks sign-up button, then closes the modal
     */
    @Test(priority = 1)
    public void testSignUpWithUsernameOnly() {
        System.out.println("=== Test Case 1: Sign up with username only ===");
        
        // Use page object method to perform signup with username only
        signUpDemoBlazePage.signUpWithUsernameOnly("ziadsalah");
        sleep(1000);
        
        // Handle alert
        String alertText = signUpDemoBlazePage.handleAlert();
        System.out.println("Alert message: " + alertText);
        sleep(1000);
        
        // Close modal
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 1 completed");
    }
      /**
     * Test Case 2: Sign up with password only
     * Opens sign-up modal, enters only password "ziadsalah", clicks sign-up button, then closes the modal
     */
    @Test(priority = 2)
    public void testSignUpWithPasswordOnly() {
        System.out.println("=== Test Case 2: Sign up with password only ===");
        
        // Use page object method to perform signup with password only
        signUpDemoBlazePage.signUpWithPasswordOnly("ziadsalah");
        sleep(1000);
        
        // Handle alert
        String alertText = signUpDemoBlazePage.handleAlert();
        System.out.println("Alert message: " + alertText);
        sleep(1000);
        
        // Close modal
        signUpDemoBlazePage.closeSignUpModal();
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
        
        // Use page object method to perform signup with empty fields
        signUpDemoBlazePage.signUpWithEmptyFields();
        sleep(1000);
        
        // Handle alert
        String alertText = signUpDemoBlazePage.handleAlert();
        System.out.println("Alert message: " + alertText);
        sleep(1000);
        
        // Close modal
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 3 completed");
    }
      /**
     * Test Case 4: Sign up with complete information
     * Opens sign-up modal, enters both username and password as "ziadsalah", clicks sign-up button, then closes the modal
     */
    @Test(priority = 4)
    public void testSignUpWithCompleteInfo() {
        System.out.println("=== Test Case 4: Sign up with complete information ===");
        
        // Use page object method to perform signup with complete information
        signUpDemoBlazePage.signUpWithCompleteInfo("ziadsalah", "ziadsalah");
        sleep(1000);
        
        // Try to handle alert if present
        try {
            String alertText = signUpDemoBlazePage.handleAlert();
            System.out.println("Alert message: " + alertText);
        } catch (Exception e) {
            System.out.println("No alert was present or other issue occurred: " + e.getMessage());
        }
        sleep(1000);
        
        // Close modal
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
        
        System.out.println("Test Case 4 completed");
    }
}
