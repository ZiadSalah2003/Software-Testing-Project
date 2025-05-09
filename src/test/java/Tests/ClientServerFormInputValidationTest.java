package Tests;

import Pages.ClientServerFormInputValidationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientServerFormInputValidationTest extends TestBase {

    ClientServerFormInputValidationPage formPage;

    @BeforeMethod
    public void setUp() {
        // Open the page with the form
        driver.navigate().to("https://testpages.eviltester.com/styled/validation/input-validation.html");
        formPage = new ClientServerFormInputValidationPage(driver);
    }

    @Test
    public void testValidFormSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load
        formPage.waitForPageToLoad(2); // Using the updated method that doesn't expect a confirmation element

        // After form submission, check the page source for success message
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form using two-step navigation
        formPage.navigateBackToForm();

        // Add a small delay before the next test case
        sleep(2000);
    }

    @Test
    public void testInValidFirstSubmission() {
        formPage.fillOutForm("", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load
        formPage.waitForPageToLoad(2);

        // After form submission, check the page source
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form page
        formPage.navigateBackToForm();

        // Add a small delay before the next test case
        sleep(2000);
    }


    @Test
    public void testInValidSurnameSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa", "21", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load
        formPage.waitForPageToLoad(2);

        // After form submission, check the page source
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form page
        formPage.navigateBackToForm();

        // Add a small delay before the next test case
        sleep(2000);
    }


    @Test
    public void testInValidAgeSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "90", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load
        formPage.waitForPageToLoad(2);

        // After form submission, check the page source
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form page
        formPage.navigateBackToForm();

        // Add a small delay before the next test case
        sleep(2000);
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
