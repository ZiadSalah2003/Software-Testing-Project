package Tests;

import Pages.CalculatorPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculatorTest extends TestBase {
    HomePage homePage;
    CalculatorPage calculatorPage;
    @BeforeMethod
    public void navigateToCalculator() {
        homePage = new HomePage(driver);
        calculatorPage = homePage.openCalculatorPage();
    }
    @Test
    public void testAddition() {
        calculatorPage.calculateWithFunction("5", "7", "plus");
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "12", "Addition calculation failed");
    }
    
    @Test
    public void testSubtraction() {
        calculatorPage.calculateWithFunction("10", "4", "minus");
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "6", "Subtraction calculation failed");
    }
    @Test
    public void testMultiplication() {
        calculatorPage.calculateWithFunction("6", "7", "times");
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "42", "Multiplication calculation failed");
    }
    @Test
    public void testDivision() {
        calculatorPage.calculateWithFunction("20", "5", "divide");
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "4", "Division calculation failed");
    }
    @Test
    public void testInvalidInput() {
        calculatorPage.calculateWithFunction("abc", "5", "plus");
        String result = calculatorPage.getAnswer();
        Assert.assertTrue(result.contains("ERR") || result.isEmpty(), "Invalid input not handled properly");
    }
    @Test
    public void testDivisionByZero() {
        calculatorPage.calculateWithFunction("10", "0", "divide");
        String result = calculatorPage.getAnswer();
        Assert.assertTrue(result.contains("ERR") || result.contains("Infinity"), "Division by zero not handled properly");
    }
}
