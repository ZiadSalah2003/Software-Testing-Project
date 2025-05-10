package Tests;

import Pages.ButtonCalculatorPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ButtonCalculatorTest extends TestBase {
    HomePage homePage;
    ButtonCalculatorPage buttonCalculatorPage;
    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        sleep(1000);
    }
    @Test
    public void testAddition() {
        System.out.println("Starting addition test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.performCalculation(25, "+", 17);
        sleep(1000);
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "42", "Button calculator addition failed");
        sleep(1000);
    }
    @Test
    public void testMultiplication() {
        System.out.println("Starting multiplication test");
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.performCalculation(6, "*", 7);
        sleep(1000);
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "42", "Button calculator multiplication failed");
        sleep(1000);
    }

    @Test
    public void testClearFunction() {
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);
        buttonCalculatorPage.enterNumber(123);
        sleep(1000);
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "", "Clear function did not reset display to 0");
        sleep(1000);
    }
    @Test
    public void testTwoPlusFiveExample() {
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        sleep(2000);
        buttonCalculatorPage.calculateTwoPlusFive();
        sleep(1000);
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "7", "Button calculator example 2 + 5 failed");
        sleep(1000);
    }
    @Test
    public void testSubtraction() {
        buttonCalculatorPage = homePage.openButtonCalculatorPage();
        System.out.println("Starting subtraction test");
        sleep(2000);
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.calculateTwentyFiveMinusSixteen();
        sleep(1000);
        String result = buttonCalculatorPage.getDisplayValue();
        Assert.assertEquals(result, "9", "Button calculator subtraction failed");
        sleep(1000);
    }
}
