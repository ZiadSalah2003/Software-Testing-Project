package Tests;

import Pages.ClientServerFormInputValidationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientServerFormInputValidationTest extends TestBase {
    ClientServerFormInputValidationPage formPage;

    @BeforeMethod
    public void setUp() {
        driver.navigate().to("https://testpages.eviltester.com/styled/validation/input-validation.html");
        formPage = new ClientServerFormInputValidationPage(driver);
    }

    @Test
    public void testValidFormSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();
        formPage.waitForPageToLoad(2);
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");
        formPage.navigateBackToForm();
        sleep(2000);
    }

    @Test
    public void testInValidFirstSubmission() {
        formPage.fillOutForm("", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();
        formPage.waitForPageToLoad(2);
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");
        formPage.navigateBackToForm();
        sleep(2000);
    }

    @Test
    public void testInValidSurnameSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa", "21", "Egypt", "This is a test note.");
        formPage.submitForm();
        formPage.waitForPageToLoad(2);
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");
        formPage.navigateBackToForm();
        sleep(2000);
    }

    @Test
    public void testInValidAgeSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "90", "Egypt", "This is a test note.");
        formPage.submitForm();
        formPage.waitForPageToLoad(2);
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");
        formPage.navigateBackToForm();
        sleep(2000);
    }
}
