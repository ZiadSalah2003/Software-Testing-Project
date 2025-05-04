package Tests;

import Pages.ClientServerFormPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClientServerFormTest extends TestBase {
    HomePage homePage;
    ClientServerFormPage formPage;

    @Test
    public void testFormSubmission() {
        // Navigate to the Form page
        homePage = new HomePage(driver);
        formPage = homePage.openClientServerFormPage();

        // Fill out the form
        formPage.setUsername("TestUser");
        formPage.setPassword("password123");
        formPage.setComments("This is a test comment for the form submission test.");
        formPage.selectCheckbox(0); // Select first checkbox
        formPage.selectRadio(1);    // Select second radio button

        // Submit the form
        formPage.submitForm();

        // Verify form was submitted successfully
        Assert.assertTrue(formPage.isFormSubmitted(), "Form was not submitted successfully");
        Assert.assertEquals(formPage.getResultUsername(), "TestUser", "Username was not correctly submitted");
    }

    @Test
    public void testRequiredFields() {
        // Navigate to the Form page
        homePage = new HomePage(driver);
        formPage = homePage.openClientServerFormPage();

        // Submit form without filling required fields
        formPage.submitForm();

        // Check if form validation works (this would depend on the actual implementation)
        // For this example, we'll assume the form doesn't submit without a username
        Assert.assertFalse(formPage.isFormSubmitted(), "Form submitted without required fields");
    }
}
