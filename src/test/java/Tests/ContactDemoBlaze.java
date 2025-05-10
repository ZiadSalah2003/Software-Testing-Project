package Tests;

import Pages.ContactDemoBlazePage;
import Pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContactDemoBlaze extends TestBase {
    private HomePage homePage;
    private ContactDemoBlazePage contactDemoBlazePage;
    
    @BeforeClass
    public void setupClass() {
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000);
        
        homePage = new HomePage(driver);
        homePage.navigateToDemoBlaze();
        sleep(2000);
        contactDemoBlazePage = new ContactDemoBlazePage(driver);
    }

    @Test(priority = 1)
    public void testContactWithCompleteInfo() {
        contactDemoBlazePage.submitContactWithCompleteInfo(
            "test@gmail.com", 
            "test user", 
            "This is a test message for the contact form!"
        );
        String alertText = contactDemoBlazePage.handleAlert();
        contactDemoBlazePage.closeContactModal();
        sleep(1000);
    }
}
