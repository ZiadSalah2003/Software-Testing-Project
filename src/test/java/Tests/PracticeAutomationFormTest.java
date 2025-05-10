package Tests;

import Pages.PracticeAutomationFormPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PracticeAutomationFormTest extends TestBase {

    PracticeAutomationFormPage formPage;

    @BeforeMethod
    public void setUp() {
        formPage = new PracticeAutomationFormPage(driver);
    }

    @Test
    public void testPracticeAutomationForm() {
        formPage.navigateToFormFields();
        formPage.fillForm("Ziad", "Ziad", "ziadsalah2003@gmail.com");
        formPage.submitForm();
        sleep(2000);
    }
}
