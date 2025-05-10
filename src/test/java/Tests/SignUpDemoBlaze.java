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
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000);
        homePage = new HomePage(driver);
        homePage.navigateToDemoBlaze();
        sleep(2000);
        signUpDemoBlazePage = new SignUpDemoBlazePage(driver);
    }

    @Test(priority = 1)
    public void testSignUpWithUsernameOnly() {
        signUpDemoBlazePage.signUpWithUsernameOnly("ziadsalah");
        sleep(1000);
        String alertText = signUpDemoBlazePage.handleAlert();
        sleep(1000);
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
    }

    @Test(priority = 2)
    public void testSignUpWithPasswordOnly() {
        signUpDemoBlazePage.signUpWithPasswordOnly("ziadsalah516");
        sleep(1000);
        String alertText = signUpDemoBlazePage.handleAlert();
        sleep(1000);
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
    }

    @Test(priority = 3)
    public void testSignUpWithEmptyFields() {
        signUpDemoBlazePage.signUpWithEmptyFields();
        sleep(1000);
        String alertText = signUpDemoBlazePage.handleAlert();
        sleep(1000);
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
    }

    @Test(priority = 4)
    public void testSignUpWithCompleteInfo() {
        signUpDemoBlazePage.signUpWithCompleteInfo("ziadsalah", "ziadsalah516");
        sleep(1000);
        String alertText = signUpDemoBlazePage.handleAlert();
        sleep(1000);
        signUpDemoBlazePage.closeSignUpModal();
        sleep(1000);
    }
}
