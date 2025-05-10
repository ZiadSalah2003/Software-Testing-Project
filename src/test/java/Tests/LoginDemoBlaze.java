package Tests;

import Pages.HomePage;
import Pages.LoginDemoBlazePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginDemoBlaze extends TestBase {
    private HomePage homePage;
    private LoginDemoBlazePage loginDemoBlazePage;
    
    @BeforeClass
    public void setupClass() {
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000);
        homePage = new HomePage(driver);
        homePage.navigateToDemoBlaze();
        sleep(2000);
        loginDemoBlazePage = new LoginDemoBlazePage(driver);
    }

    @Test(priority = 1)
    public void testLoginWithUsernameOnly() {
        loginDemoBlazePage.loginWithUsernameOnly("ziadsalah");
        sleep(1000);
        loginDemoBlazePage.handleAlert();
        sleep(1000);
        loginDemoBlazePage.closeLoginModal();
        sleep(1000);
    }

    @Test(priority = 2)
    public void testLoginWithPasswordOnly() {
        loginDemoBlazePage.loginWithPasswordOnly("ziadsalah");
        sleep(1000);
        loginDemoBlazePage.handleAlert();
        sleep(1000);
        loginDemoBlazePage.closeLoginModal();
        sleep(1000);
    }

    @Test(priority = 3)
    public void testLoginWithEmptyFields() {
        loginDemoBlazePage.loginWithEmptyFields();
        sleep(1000);
        loginDemoBlazePage.handleAlert();
        sleep(1000);
        loginDemoBlazePage.closeLoginModal();
        sleep(1000);
    }

    @Test(priority = 4)
    public void testLoginWithCompleteInfo() {
        loginDemoBlazePage.loginWithCompleteInfo("ziadsalah", "ziadsalah");
        sleep(1000);

        try {
            WebElement welcomeMessage = driver.findElement(By.id("nameofuser"));
            WebElement logoutLink = driver.findElement(By.id("logout2"));
            logoutLink.click();
            sleep(1000);
        } catch (Exception e) {
        }
    }
}
