package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
public class ComprehensiveTest extends TestBase {
    
    private HomePage homePage;
    private void navigateToHomePage() {
        System.out.println("Navigating back to home page...");
        driver.navigate().to("https://testpages.eviltester.com/styled/index.html");
        sleep(2000);
        homePage = new HomePage(driver);
    }
    
    @Test(priority = 1)
    public void testCalculator() {
        homePage = new HomePage(driver);
        CalculatorPage calculatorPage = homePage.openCalculatorPage();
        

        calculatorPage.calculateWithFunction("5", "7", "plus");
        sleep(1000);
        

        calculatorPage.calculateWithFunction("10", "4", "minus");
        sleep(1000);
        

        calculatorPage.calculateWithFunction("6", "7", "times");
        sleep(1000);
        calculatorPage.calculateWithFunction("20", "5", "divide");
        sleep(1000);
        navigateToHomePage();
    }
    
    @Test(priority = 2)
    public void testButtonCalculator() {
        ButtonCalculatorPage buttonCalculatorPage = homePage.openButtonCalculatorPage();
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.performCalculation(25, "+", 17);
        sleep(1000);
        buttonCalculatorPage.clearDisplay();
        sleep(1000);
        buttonCalculatorPage.performCalculation(6, "*", 7);
        sleep(1000);
        buttonCalculatorPage.calculateTwoPlusFive();
        sleep(1000);
        navigateToHomePage();
    }
    
    @Test(priority = 3)
    public void testCountdown() {
        CountdownPage countdownPage = homePage.openCountdownPage();
        countdownPage.performTimerSequence();
        navigateToHomePage();
    }
    
    @Test(priority = 4)
    public void testClientServerForm() {
        driver.navigate().to("https://testpages.eviltester.com/styled/validation/input-validation.html");
        ClientServerFormInputValidationPage formPage = new ClientServerFormInputValidationPage(driver);
        formPage.fillOutForm("Ahmed", "Mustafa Abd Elaty", "21", "Egypt", "This is a test note.");
        formPage.submitForm();
        formPage.waitForPageToLoad(2);
        formPage.navigateBackToHomePage();
        homePage = new HomePage(driver);
    }
    
    @Test(priority = 5)
    public void testCharValidation() {
        CharValidationPage charValidationPage = homePage.openCharValidationPage();
        
        // Test valid input
        String validInput = "Abc12*Z";
        charValidationPage.enterText(validInput);
        charValidationPage.clickCheckButton();
        sleep(2000);
        String invalidInput = "Abc!123";
        charValidationPage.enterText(invalidInput);
        charValidationPage.clickCheckButton();
        sleep(2000);
        navigateToHomePage();
    }
    
    @Test(priority = 6)
    public void testNoteTaker() {
        NoteTakerPage noteTakerPage = homePage.openNoteTakerPage();
        noteTakerPage.clearAllNotes();
        sleep(500);
        String noteTitle = "Test Note Title";
        String noteDetails = "This is a test note that will be deleted after 2 seconds.";
        noteTakerPage.addAndDeleteNote(noteTitle, noteDetails);
        navigateToHomePage();
    }
    
    @Test(priority = 7)
    public void testCanvasDrawing() {
        CanvasDrawingPage canvasDrawingPage = homePage.openCanvasDrawingPage();
        canvasDrawingPage.setXCoordinate("20");
        canvasDrawingPage.setYCoordinate("30");
        canvasDrawingPage.setShapeSize("65");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100"); 
        canvasDrawingPage.setShapeSize("50");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("50");
        canvasDrawingPage.setShapeSize("75");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("200");
        canvasDrawingPage.setYCoordinate("200");
        canvasDrawingPage.setShapeSize("100");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("50");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("10");
        canvasDrawingPage.selectColorByName("Grey");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("10");
        canvasDrawingPage.setYCoordinate("10");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("290");
        canvasDrawingPage.setYCoordinate("10");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("10");
        canvasDrawingPage.setYCoordinate("290");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("290");
        canvasDrawingPage.setYCoordinate("290");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100");
        canvasDrawingPage.setShapeSize("1");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("200");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        sleep(2000);
        navigateToHomePage();
    }
    
    @Test(priority = 8)
    public void testCanvasScribble() {
        ScribblePage scribblePage = homePage.openCanvasScribblePage();
        scribblePage.completeCanvasTask();
        sleep(2000);
        navigateToHomePage();
    }
    
    @Test(priority = 9)
    public void testTriangleApplication() {
        driver.navigate().to("https://testpages.eviltester.com/styled/apps/triangle/triangle001.html");
        TheFamousTriangleApplicationPage trianglePage = new TheFamousTriangleApplicationPage(driver);
        

        trianglePage.testTriangle("5", "5", "5");
        String result = trianglePage.getTriangleType();
        sleep(1000);

        trianglePage.testTriangle("5", "5", "3");
        result = trianglePage.getTriangleType();
        sleep(1000);
        trianglePage.testTriangle("4", "5", "6");
        result = trianglePage.getTriangleType();
        sleep(1000);
    }
}