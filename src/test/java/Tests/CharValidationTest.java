package Tests;

import Pages.CharValidationPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CharValidationTest extends TestBase {
    private HomePage homePage;
    private CharValidationPage charValidationPage;

    @BeforeClass
    public void setupTests() {
        // Navigate to the site once for all tests
        homePage = new HomePage(driver);
        System.out.println("Navigating to the Character Validation page");
        try {
            charValidationPage = homePage.openCharValidationPage();
            System.out.println("Successfully navigated to the Character Validation page");
        } catch (Exception e) {
            System.out.println("Failed to navigate to the Character Validation page: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test valid input in the character validation page
     * Valid characters include A-Z, a-z, 0-9, and *
     */
    @Test(priority = 1) // Ensure this runs first
    public void testValidInput() {
        // Enter valid input (7 characters from allowed set)
        String validInput = "Abc12*Z";
        System.out.println("Entering valid input: " + validInput);
        
        try {
            charValidationPage.enterText(validInput);
            System.out.println("Text entered successfully");
            
            // Click check button
            charValidationPage.clickCheckButton();
            System.out.println("Check button clicked");
            
            // Wait for 2 seconds
            sleep(2000);
            
            // Verify that the input is recognized as valid
            boolean isValid = charValidationPage.isInputValid();
            System.out.println("Is input valid? " + isValid);
            Assert.assertTrue(isValid, "Valid input was incorrectly marked as invalid");
            System.out.println("Valid input test successful: " + validInput);
        } catch (Exception e) {
            System.out.println("Exception during valid input test: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Exception in valid input test: " + e.getMessage());
        }
    }
    
    /**
     * Test invalid input in the character validation page
     * Input with characters outside of A-Z, a-z, 0-9, and * should be invalid
     */
    @Test(priority = 2) // Ensure this runs after the valid input test
    public void testInvalidInput() {
        // Wait between tests
        sleep(2000);
        
        // Enter invalid input (contains invalid character !)
        String invalidInput = "Abc!123";
        System.out.println("Entering invalid input: " + invalidInput);
        
        try {
            charValidationPage.enterText(invalidInput);
            System.out.println("Invalid text entered successfully");
            
            // Click check button
            charValidationPage.clickCheckButton();
            System.out.println("Check button clicked for invalid input");
            
            // Wait for 2 seconds
            sleep(2000);
            
            // Verify that the input is recognized as invalid
            boolean isInvalid = !charValidationPage.isInputValid();
            System.out.println("Is input invalid? " + isInvalid);
            Assert.assertTrue(isInvalid, "Invalid input was incorrectly marked as valid");
            System.out.println("Invalid input test successful: " + invalidInput);
        } catch (Exception e) {
            System.out.println("Exception during invalid input test: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Exception in invalid input test: " + e.getMessage());
        }
    }
    
    /**
     * @deprecated Use implicitWait method from TestBase instead
     * Kept for backward compatibility with existing code
     */
    @Override
    @Deprecated
    protected void sleep(int milliseconds) {
        // Use the implicitWait method from the parent class
        super.implicitWait(milliseconds);
    }
}