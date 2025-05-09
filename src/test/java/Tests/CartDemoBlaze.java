package Tests;

import Pages.CartDemoBlazePage;
import Pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartDemoBlaze extends TestBase {
    private HomePage homePage;
    private CartDemoBlazePage cartDemoBlazePage;
    
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
        
        // Initialize CartDemoBlazePage
        cartDemoBlazePage = new CartDemoBlazePage(driver);
    }
    
    /**
     * Test Case 10: Samsung Galaxy S6 Purchase Flow
     * 1. Navigate to home page
     * 2. Navigate to Phones category
     * 3. Select Samsung Galaxy S6
     * 4. Add to cart
     * 5. Navigate to cart
     * 6. Place order
     * 7. Fill in order details
     * 8. Purchase
     * 9. Verify success
     */
    @Test(priority = 1)
    public void testSamsungGalaxyS6Purchase() {
        System.out.println("=== Test Case 10: Samsung Galaxy S6 Purchase Flow ===");
        
        // Make sure we're on the home page
        cartDemoBlazePage.navigateToHome();
        sleep(1000);
        
        // Navigate to the Phones category
        cartDemoBlazePage.navigateToPhones();
        sleep(1000);
        
        // Navigate to Samsung Galaxy S6 product page
        cartDemoBlazePage.navigateToProductPage("Samsung galaxy s6");
        sleep(1000);
        
        // Add product to cart
        cartDemoBlazePage.addToCart();
        sleep(1000);
        
        // Go to cart
        cartDemoBlazePage.navigateToCart();
        sleep(1000);
        
        // Place order
        cartDemoBlazePage.clickPlaceOrder();
        sleep(1000);
        
        // Fill in order details
        String name = "Test User";
        String country = "United States";
        String city = "New York";
        String creditCard = "4111111111111111";
        String month = "05";
        String year = "2025";
        
        cartDemoBlazePage.fillOrderForm(name, country, city, creditCard, month, year);
        sleep(1000);
        
        // Purchase
        cartDemoBlazePage.clickPurchase();
        sleep(1000);
        
        // Verify success message
        boolean isSuccess = cartDemoBlazePage.isOrderSuccessful();
        String confirmationText = cartDemoBlazePage.getOrderConfirmationText();
        
        // Print result
        if (isSuccess) {
            System.out.println("Purchase successful! Confirmation: " + confirmationText);
        } else {
            System.out.println("Purchase failed!");
        }
        
        // Close the confirmation dialog
        cartDemoBlazePage.confirmPurchase();
        sleep(1000);
        
        System.out.println("Test Case 1 completed");
    }
}
