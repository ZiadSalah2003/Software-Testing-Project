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
        // First navigate to TestPages website
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000); // Wait 1 second for initial load
        
        homePage = new HomePage(driver);
        
        // Now navigate to DemoBlaze website from TestPages
        System.out.println("Navigating from TestPages to DemoBlaze");
        homePage.navigateToDemoBlaze();
        sleep(2000); // Wait for DemoBlaze to load
        
        // Initialize ContactDemoBlazePage
        contactDemoBlazePage = new ContactDemoBlazePage(driver);
    }
    
    /**
     * Test Case 9: Contact with complete information
     * Opens contact modal, fills all fields, clicks send message button, then closes the modal
     */
    @Test(priority = 1)
    public void testContactWithCompleteInfo() {
        System.out.println("=== Test Case 9: Contact with complete information ===");
        
        // Use the Page Object to handle contact form submission
        contactDemoBlazePage.submitContactWithCompleteInfo(
            "test@gmail.com", 
            "test user", 
            "This is a test message for the contact form!"
        );
        
        // Handle alert
        String alertText = contactDemoBlazePage.handleAlert();
        System.out.println("Alert message: " + alertText);
        
        // Close contact modal
        contactDemoBlazePage.closeContactModal();
        sleep(1000);
        
        System.out.println("Test Case 9 completed");
    }
}
