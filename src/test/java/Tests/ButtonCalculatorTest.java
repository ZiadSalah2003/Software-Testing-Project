package Tests;

import Pages.ButtonCalculatorPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ButtonCalculatorTest extends TestBase {
    HomePage homePage;
    ButtonCalculatorPage buttonCalculatorPage;
    
    // Helper method to add delays
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Setup method to run before each test
    @BeforeMethod
    public void setUp() {
        // Always start fresh with a new HomePage instance
        homePage = new HomePage(driver);
        sleep(1000);
    }

    @Test
    public void testAddition() {
        // Navigate to the Button Calculator page
        System.out.println("Starting addition test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);

        // Clear display first
        buttonCalculatorPage.clearDisplay();
        sleep(1000);

        // Perform calculation using buttons
        buttonCalculatorPage.performCalculation(25, "+", 17);
        sleep(1000);

        // Verify result
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "42", "Button calculator addition failed");
        sleep(1000);
    }

    @Test
    public void testMultiplication() {
        // Always navigate to the Button Calculator page fresh for each test
        System.out.println("Starting multiplication test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);

        // Clear display first
        buttonCalculatorPage.clearDisplay();
        sleep(1000);

        // Perform calculation using buttons
        buttonCalculatorPage.performCalculation(6, "*", 7);
        sleep(1000);

        // Verify result
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "42", "Button calculator multiplication failed");
        sleep(1000);
    }

    @Test
    public void testClearFunction() {
        // Always navigate to the Button Calculator page fresh for each test
        System.out.println("Starting clear function test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);

        // Enter a number
        buttonCalculatorPage.enterNumber(123);
        sleep(1000);

        // Clear the display
        buttonCalculatorPage.clearDisplay();
        sleep(1000);

        // Verify display is cleared
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "0", "Clear function did not reset display to 0");
        sleep(1000);
    }

    @Test
    public void testTwoPlusFiveExample() {
        // Always navigate to the Button Calculator page fresh for each test
        System.out.println("Starting two plus five example test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);

        // Use our example method to calculate 2 + 5
        buttonCalculatorPage.calculateTwoPlusFive();
        sleep(1000);

        // Verify result
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "7", "Button calculator example 2 + 5 failed");
        sleep(1000);
    }

    @Test
    public void testSubtraction() {
        // Navigate to the Button Calculator page
        System.out.println("Starting subtraction test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);

        // Clear display first
        buttonCalculatorPage.clearDisplay();
        sleep(1000);

        // Use the specific method for 25 - 16
        buttonCalculatorPage.calculateTwentyFiveMinusSixteen();
        sleep(1000);

        // Verify result
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "9", "Button calculator subtraction failed");
        sleep(1000);
    }
}
