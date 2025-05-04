package Tests;

import Pages.CanvasDrawingPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CanvasDrawingTest extends TestBase {
    
    private HomePage homePage;
    private CanvasDrawingPage canvasDrawingPage;
    private int shapeCounter = 0; // Add counter to track number of shapes drawn
    
    @BeforeClass
    public void setupTests() {
        // Navigate to the Canvas Drawing page once for all tests
        homePage = new HomePage(driver);
        System.out.println("Navigating to the Canvas Drawing page");
        try {
            canvasDrawingPage = homePage.openCanvasDrawingPage();
            System.out.println("Successfully navigated to the Canvas Drawing page");
        } catch (Exception e) {
            System.out.println("Failed to navigate to the Canvas Drawing page: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Verify canvas is displayed
        Assert.assertTrue(canvasDrawingPage.isCanvasDisplayed(), "Canvas is not displayed");
    }

    /**
     * Helper method to check and clear canvas if needed
     */
    private void checkAndClearCanvas() {
        shapeCounter++;
        System.out.println("Shapes drawn: " + shapeCounter);
        
        // Clear the canvas after every 5 shapes
        if (shapeCounter % 5 == 0) {
            System.out.println("Waiting 2 seconds before clearing canvas...");
            sleep(2000); // Wait for 2 seconds before clearing
            System.out.println("Clearing canvas after 5 shapes...");
            canvasDrawingPage.clickClearButton();
            sleep(1000); // Give some time to see the cleared canvas
        }
    }

    /**
     * Test drawing a black circle at default coordinates
     */
    @Test(priority = 1)
    public void testDefaultValueCircle() {
        System.out.println("Testing black circle with default values");
        
        // Set the values
        canvasDrawingPage.setXCoordinate("20");
        canvasDrawingPage.setYCoordinate("30");
        canvasDrawingPage.setShapeSize("65");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        
        // Click Show button to see the result
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        
        // Wait to see the result
        sleep(2000);
    }
    
    /**
     * Test drawing a red square with custom coordinates and size
     */
    @Test(priority = 2)
    public void testRedSquare() {
        System.out.println("Testing red square with custom coordinates");
        
        // Set the values
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100"); 
        canvasDrawingPage.setShapeSize("50");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        
        // Click Show button to see the result
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        
        // Wait to see the result
        sleep(2000);
    }
    
    /**
     * Test drawing a blue circle with different coordinates
     */
    @Test(priority = 3)
    public void testBlueCircle() {
        System.out.println("Testing blue circle");
        
        // Set the values
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("50");
        canvasDrawingPage.setShapeSize("75");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        
        // Click Show button to see the result
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        
        // Wait to see the result
        sleep(2000);
    }
    
    /**
     * Test drawing a green square with maximum size
     */
    @Test(priority = 4)
    public void testGreenSquareMaxSize() {
        System.out.println("Testing green square with maximum size");
        
        // Set the values
        canvasDrawingPage.setXCoordinate("200");
        canvasDrawingPage.setYCoordinate("200");
        canvasDrawingPage.setShapeSize("100");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Square");
        
        // Click Show button to see the result
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        
        // Wait to see the result
        sleep(2000);
    }
    
    /**
     * Test drawing a grey circle with minimum size
     */
    @Test(priority = 5)
    public void testGreyCircleMinSize() {
        System.out.println("Testing grey circle with minimum size");
        
        // Set the values
        canvasDrawingPage.setXCoordinate("50");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("10");
        canvasDrawingPage.selectColorByName("Grey");
        canvasDrawingPage.selectShapeByName("Circle");
        
        // Click Show button to see the result
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas(); // This should trigger a clear as it's the 5th shape
        
        // Wait to see the result
        sleep(2000);
    }
    
    /**
     * Test drawing shapes at the edge of the canvas
     */
    @Test(priority = 6)
    public void testEdgeCoordinates() {
        System.out.println("Testing shapes at canvas edges");
        
        // Top left - Black Square
        canvasDrawingPage.setXCoordinate("10");
        canvasDrawingPage.setYCoordinate("10");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Top right - Red Circle
        canvasDrawingPage.setXCoordinate("290");
        canvasDrawingPage.setYCoordinate("10");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Bottom left - Green Circle
        canvasDrawingPage.setXCoordinate("10");
        canvasDrawingPage.setYCoordinate("290");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Bottom right - Blue Square
        canvasDrawingPage.setXCoordinate("290");
        canvasDrawingPage.setYCoordinate("290");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }
    
    /**
     * Test with boundary values for size parameter
     */
    @Test(priority = 7)
    public void testSizeBoundary() {
        System.out.println("Testing with boundary size values");
        
        // Draw a circle with minimum viable size 1
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100");
        canvasDrawingPage.setShapeSize("1");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas(); // This should be the 10th shape
        sleep(2000);
        
        // Draw a square with very large size 200
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("200");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }
    
    /**
     * Test all color options with one shape type
     */
    @Test(priority = 8)
    public void testAllColors() {
        System.out.println("Testing all available colors with circle shape");
        
        // Position circles in a row
        int xPosition = 50;
        int yPosition = 100;
        String size = "30";
        
        // Draw a black circle
        canvasDrawingPage.setXCoordinate(String.valueOf(xPosition));
        canvasDrawingPage.setYCoordinate(String.valueOf(yPosition));
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Draw a red circle
        xPosition += 50;
        canvasDrawingPage.setXCoordinate(String.valueOf(xPosition));
        canvasDrawingPage.setYCoordinate(String.valueOf(yPosition));
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Draw a green circle
        xPosition += 50;
        canvasDrawingPage.setXCoordinate(String.valueOf(xPosition));
        canvasDrawingPage.setYCoordinate(String.valueOf(yPosition));
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas(); // This should be the 15th shape
        sleep(2000);
        
        // Draw a blue circle
        xPosition += 50;
        canvasDrawingPage.setXCoordinate(String.valueOf(xPosition));
        canvasDrawingPage.setYCoordinate(String.valueOf(yPosition));
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Draw a grey circle
        xPosition += 50;
        canvasDrawingPage.setXCoordinate(String.valueOf(xPosition));
        canvasDrawingPage.setYCoordinate(String.valueOf(yPosition));
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Grey");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }
    
    /**
     * Test drawing multiple shapes overlapping
     */
    @Test(priority = 9)
    public void testOverlappingShapes() {
        System.out.println("Testing overlapping shapes");
        
        // Draw a large blue circle
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("100");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        
        // Draw a medium red square overlapping the circle
        canvasDrawingPage.setXCoordinate("125");
        canvasDrawingPage.setYCoordinate("125");
        canvasDrawingPage.setShapeSize("50");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas(); // This should be the 20th shape
        sleep(2000);
        
        // Draw a small black circle in the center of both
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("25");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }
    
    /**
     * Helper method to sleep for the specified milliseconds
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}