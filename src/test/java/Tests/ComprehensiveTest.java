package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * This class runs all the tests in sequence, navigating between pages.
 * After each test, it returns to the home page except for the final test.
 */
public class ComprehensiveTest extends TestBase {
    
    private HomePage homePage;
    
    /**
     * Helper method to navigate back to home page
     */
    private void navigateToHomePage() {
        System.out.println("Navigating back to home page...");
        driver.navigate().to("https://testpages.eviltester.com/styled/index.html");
        sleep(2000); // Wait for the page to load
        homePage = new HomePage(driver);
        System.out.println("Back on home page");
    }
    
    /**
     * @deprecated Use implicitWait method from TestBase instead
     * Kept for backward compatibility with existing code
     */
    @Override
    @Deprecated
    protected void sleep(int milliseconds) {
        // Use the implicitWait method from the parent class
        super.implicitWait(milliseconds);
    }
    
    @Test(priority = 1)
    public void testCalculator() {
        System.out.println("=== Starting Calculator Test ===");
        homePage = new HomePage(driver);
        CalculatorPage calculatorPage = homePage.openCalculatorPage();
        
        // Test addition
        calculatorPage.calculateWithFunction("5", "7", "plus");
        System.out.println("Addition test result: " + calculatorPage.getAnswer());
        sleep(100000);
        
        // Test subtraction
        calculatorPage.calculateWithFunction("10", "4", "minus");
        System.out.println("Subtraction test result: " + calculatorPage.getAnswer());
        sleep(100000);
        
        // Test multiplication
        calculatorPage.calculateWithFunction("6", "7", "times");
        System.out.println("Multiplication test result: " + calculatorPage.getAnswer());
        sleep(100000);
        
        // Test division
        calculatorPage.calculateWithFunction("20", "5", "divide");
        System.out.println("Division test result: " + calculatorPage.getAnswer());
        sleep(100000);
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 2)
    public void testButtonCalculator() {
        System.out.println("=== Starting Button Calculator Test ===");
        ButtonCalculatorPage buttonCalculatorPage = homePage.openButtonCalculatorPage();
        
        // Test addition
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.performCalculation(25, "+", 17);
        sleep(1000);
        System.out.println("Button calculator addition test result: " + buttonCalculatorPage.getDisplayValue());
        
        // Test multiplication
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.performCalculation(6, "*", 7);
        sleep(1000);
        System.out.println("Button calculator multiplication test result: " + buttonCalculatorPage.getDisplayValue());
        
        // Test example method
        buttonCalculatorPage.calculateTwoPlusFive();
        sleep(1000);
        System.out.println("Button calculator 2+5 test result: " + buttonCalculatorPage.getDisplayValue());
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 3)
    public void testCountdown() {
        System.out.println("=== Starting Countdown Test ===");
        CountdownPage countdownPage = homePage.openCountdownPage();
        
        // Perform timer sequence
        countdownPage.performTimerSequence();
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 4)
    public void testClientServerForm() {
        System.out.println("=== Starting Client Server Form Test ===");
        driver.navigate().to("https://testpages.eviltester.com/styled/validation/input-validation.html");
        ClientServerFormInputValidationPage formPage = new ClientServerFormInputValidationPage(driver);
        
        // Test valid form submission
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();
        formPage.waitForPageToLoad(2);
        System.out.println("Form submission completed");
        
        // Use the new method for two-step navigation back to home page
        formPage.navigateBackToHomePage();
        homePage = new HomePage(driver);
    }
    
    @Test(priority = 5)
    public void testCharValidation() {
        System.out.println("=== Starting Character Validation Test ===");
        CharValidationPage charValidationPage = homePage.openCharValidationPage();
        
        // Test valid input
        String validInput = "Abc12*Z";
        charValidationPage.enterText(validInput);
        charValidationPage.clickCheckButton();
        sleep(2000);
        System.out.println("Valid input test result: " + charValidationPage.isInputValid());
        
        // Test invalid input
        String invalidInput = "Abc!123";
        charValidationPage.enterText(invalidInput);
        charValidationPage.clickCheckButton();
        sleep(2000);
        System.out.println("Invalid input test result: " + !charValidationPage.isInputValid());
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 6)
    public void testNoteTaker() {
        System.out.println("=== Starting Note Taker Test ===");
        NoteTakerPage noteTakerPage = homePage.openNoteTakerPage();
        
        // Clear any existing notes
        noteTakerPage.clearAllNotes();
        sleep(500);
        
        // Add and delete a note
        String noteTitle = "Test Note Title";
        String noteDetails = "This is a test note that will be deleted after 2 seconds.";
        noteTakerPage.addAndDeleteNote(noteTitle, noteDetails);
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 7)
    public void testCanvasDrawing() {
        System.out.println("=== Starting Canvas Drawing Test ===");
        CanvasDrawingPage canvasDrawingPage = homePage.openCanvasDrawingPage();
        
        // Test drawing a red square
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100");
        canvasDrawingPage.setShapeSize("50");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        
        // Test drawing a blue circle
        canvasDrawingPage.setXCoordinate("200");
        canvasDrawingPage.setYCoordinate("100");
        canvasDrawingPage.setShapeSize("40");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 8)
    public void testCanvasScribble() {
        System.out.println("=== Starting Canvas Scribble Test ===");
        ScribblePage scribblePage = homePage.openCanvasScribblePage();
        
        // Complete canvas drawing task
        scribblePage.completeCanvasTask();
        sleep(2000);
        
        // Navigate back to home page
        navigateToHomePage();
    }
    
    @Test(priority = 9)
    public void testTriangleApplication() {
        System.out.println("=== Starting Triangle Application Test ===");
        driver.navigate().to("https://testpages.eviltester.com/styled/apps/triangle/triangle001.html");
        TheFamousTriangleApplicationPage trianglePage = new TheFamousTriangleApplicationPage(driver);
        
        // Test equilateral triangle
        trianglePage.testTriangle("5", "5", "5");
        String result = trianglePage.getTriangleType();
        System.out.println("Equilateral triangle test result: " + result);
        sleep(1000);
        
        // Test isosceles triangle
        trianglePage.testTriangle("5", "5", "3");
        result = trianglePage.getTriangleType();
        System.out.println("Isosceles triangle test result: " + result);
        sleep(1000);
        
        // Test scalene triangle
        trianglePage.testTriangle("4", "5", "6");
        result = trianglePage.getTriangleType();
        System.out.println("Scalene triangle test result: " + result);
        sleep(1000);
        
        // For the final test, we don't navigate back to the homepage
        System.out.println("=== All tests completed successfully ===");
    }
}