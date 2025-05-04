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
        // Navigate to the Calculator page before each test
        homePage = new HomePage(driver);
        calculatorPage = homePage.openCalculatorPage();
    }

    @Test
    public void testAddition() {
        // Perform addition calculation
        calculatorPage.calculateWithFunction("5", "7", "plus");

        // Verify result
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "12", "Addition calculation failed");
    }
    
    @Test
    public void testSubtraction() {
        // Perform subtraction calculation
        calculatorPage.calculateWithFunction("10", "4", "minus");

        // Verify result
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "6", "Subtraction calculation failed");
    }
    
    @Test
    public void testMultiplication() {
        // Perform multiplication calculation
        calculatorPage.calculateWithFunction("6", "7", "times");

        // Verify result
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "42", "Multiplication calculation failed");
    }
    
    @Test
    public void testDivision() {
        // Perform division calculation
        calculatorPage.calculateWithFunction("20", "5", "divide");

        // Verify result
        String result = calculatorPage.getAnswer();
        Assert.assertEquals(result, "4", "Division calculation failed");
    }

    @Test
    public void testInvalidInput() {
        // Test with invalid input
        calculatorPage.calculateWithFunction("abc", "5", "plus");

        // Verify error handling
        String result = calculatorPage.getAnswer();
        Assert.assertTrue(result.contains("ERR") || result.isEmpty(), "Invalid input not handled properly");
    }
    
    @Test
    public void testDivisionByZero() {
        // Test division by zero
        calculatorPage.calculateWithFunction("10", "0", "divide");
        
        // Verify error handling
        String result = calculatorPage.getAnswer();
        Assert.assertTrue(result.contains("ERR") || result.contains("Infinity"), 
                "Division by zero not handled properly");
    }
}
