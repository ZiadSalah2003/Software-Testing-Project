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

        // Wait for the redirection page to load and confirm that it has fully loaded
        formPage.waitForPageToLoad(2); // Adjust the timeout as needed

        // After form submission, confirm if the confirmation page is visible
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form to test another case
        formPage.navigateBackToForm();

        // Add a small delay before the next test case
        sleep(2000);
    }

    @Test
    public void testInValidFirstSubmission() {
        formPage.fillOutForm("", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load and confirm that it has fully loaded
        formPage.waitForPageToLoad(2); // Adjust the timeout as needed

        // After form submission, confirm if the confirmation page is visible
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form to test another case


        // Add a small delay before the next test case
        sleep(2000);
    }


    @Test
    public void testInValidSurnameSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa", "21", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load and confirm that it has fully loaded
        formPage.waitForPageToLoad(2); // Adjust the timeout as needed

        // After form submission, confirm if the confirmation page is visible
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form to test another case


        // Add a small delay before the next test case
        sleep(2000);
    }


    @Test
    public void testInValidAgeSubmission() {
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "90", "Egypt", "This is a test note.");
        formPage.submitForm();

        // Wait for the redirection page to load and confirm that it has fully loaded
        formPage.waitForPageToLoad(2); // Adjust the timeout as needed

        // After form submission, confirm if the confirmation page is visible
        String confirmationMessage = driver.getPageSource();
        Assert.assertTrue(confirmationMessage.contains("Form submitted successfully"), "Expected form submission success message.");

        // Navigate back to the form to test another case


        // Add a small delay before the next test case
        sleep(2000);
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
