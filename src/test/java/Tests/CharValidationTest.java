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
    @Test(priority = 1)
    public void testValidInput() {
        String validInput = "Abc12*Z";        
        try {
            charValidationPage.enterText(validInput);
            charValidationPage.clickCheckButton();
            sleep(2000);
            boolean isValid = charValidationPage.isInputValid();
            Assert.assertTrue(isValid, "Valid input was incorrectly marked as invalid");
        } catch (Exception e) {
            System.out.println("Exception during valid input test: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Exception in valid input test: " + e.getMessage());
        }
    }
    @Test(priority = 2)
    public void testInvalidInput() {
        sleep(2000);
        String invalidInput = "Abc!123";
        try {
            charValidationPage.enterText(invalidInput);
            charValidationPage.clickCheckButton();
            sleep(2000);
            boolean isInvalid = !charValidationPage.isInputValid();
            Assert.assertTrue(isInvalid, "Invalid input was incorrectly marked as valid");
        } catch (Exception e) {
            System.out.println("Exception during invalid input test: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Exception in invalid input test: " + e.getMessage());
        }
    }
}