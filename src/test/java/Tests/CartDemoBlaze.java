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
        driver.get("https://testpages.eviltester.com/styled/page?app=testpages&t=Others");
        sleep(1000);
        
        homePage = new HomePage(driver);
        homePage.navigateToDemoBlaze();
        sleep(2000);
        cartDemoBlazePage = new CartDemoBlazePage(driver);
    }
    @Test(priority = 1)
    public void testSamsungGalaxyS6Purchase() {

        cartDemoBlazePage.navigateToHome();
        sleep(1000);
        cartDemoBlazePage.navigateToPhones();
        sleep(1000);
        cartDemoBlazePage.navigateToProductPage("Samsung galaxy s6");
        sleep(1000);
        cartDemoBlazePage.addToCart();
        sleep(1000);
        cartDemoBlazePage.navigateToCart();
        sleep(1000);
        cartDemoBlazePage.clickPlaceOrder();
        sleep(1000);
        String name = "Test User";
        String country = "United States";
        String city = "New York";
        String creditCard = "4111111111111111";
        String month = "05";
        String year = "2025";
        cartDemoBlazePage.fillOrderForm(name, country, city, creditCard, month, year);
        sleep(1000);
        cartDemoBlazePage.clickPurchase();
        sleep(1000);
        boolean isSuccess = cartDemoBlazePage.isOrderSuccessful();
        String confirmationText = cartDemoBlazePage.getOrderConfirmationText();
        cartDemoBlazePage.confirmPurchase();
        sleep(1000);
        System.out.println("Test Case 1 completed");
    }
}
