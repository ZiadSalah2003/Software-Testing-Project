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
        charValidationPage = homePage.openCharValidationPage();
    }
    @Test(priority = 1)
    public void testValidInput() {
        String validInput = "Abc12*Z";        
        charValidationPage.enterText(validInput);
        charValidationPage.clickCheckButton();
        sleep(2000);
        boolean isValid = charValidationPage.isInputValid();
        Assert.assertTrue(isValid, "Valid input was incorrectly marked as invalid");
    }
    @Test(priority = 2)
    public void testInvalidInput() {
        sleep(2000);
        String invalidInput = "Abc!123";
        charValidationPage.enterText(invalidInput);
        charValidationPage.clickCheckButton();
        sleep(2000);
        boolean isInvalid = !charValidationPage.isInputValid();
        Assert.assertTrue(isInvalid, "Invalid input was incorrectly marked as valid");
    }
}