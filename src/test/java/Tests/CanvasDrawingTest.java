package Tests;

import Pages.CanvasDrawingPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CanvasDrawingTest extends TestBase {
    private HomePage homePage;
    private CanvasDrawingPage canvasDrawingPage;
    private int shapeCounter = 0;
    
    @BeforeClass
    public void setupTests() {
        homePage = new HomePage(driver);
        canvasDrawingPage = homePage.openCanvasDrawingPage();
        canvasDrawingPage = homePage.openCanvasDrawingPage();
        Assert.assertTrue(canvasDrawingPage.isCanvasDisplayed(), "Canvas is not displayed");
    }

    private void checkAndClearCanvas() {
        shapeCounter++;
        if (shapeCounter % 5 == 0) {
            sleep(2000);
            canvasDrawingPage.clickClearButton();
            sleep(1000);
        }
    }

    @Test(priority = 1)
    public void testDefaultValueCircle() {
        canvasDrawingPage.setXCoordinate("20");
        canvasDrawingPage.setYCoordinate("30");
        canvasDrawingPage.setShapeSize("65");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 2)
    public void testRedSquare() {
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100"); 
        canvasDrawingPage.setShapeSize("50");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 3)
    public void testBlueCircle() {
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("50");
        canvasDrawingPage.setShapeSize("75");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 4)
    public void testGreenSquareMaxSize() {
        canvasDrawingPage.setXCoordinate("200");
        canvasDrawingPage.setYCoordinate("200");
        canvasDrawingPage.setShapeSize("100");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 5)
    public void testGreyCircleMinSize() {
        canvasDrawingPage.setXCoordinate("50");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("10");
        canvasDrawingPage.selectColorByName("Grey");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 6)
    public void testEdgeCoordinates() {
        canvasDrawingPage.setXCoordinate("10");
        canvasDrawingPage.setYCoordinate("10");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("290");
        canvasDrawingPage.setYCoordinate("10");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("10");
        canvasDrawingPage.setYCoordinate("290");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("290");
        canvasDrawingPage.setYCoordinate("290");
        canvasDrawingPage.setShapeSize("20");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 7)
    public void testSizeBoundary() {
        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("100");
        canvasDrawingPage.setShapeSize("1");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("150");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 8)
    public void testAllColors() {
        int xPosition = 50;
        int yPosition = 100;
        String size = "30";
        canvasDrawingPage.setXCoordinate("50");
        canvasDrawingPage.setYCoordinate("140");
        canvasDrawingPage.setShapeSize("30");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("100");
        canvasDrawingPage.setYCoordinate("120");
        canvasDrawingPage.setShapeSize("30");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("100");
        canvasDrawingPage.setShapeSize("100");
        canvasDrawingPage.selectColorByName("Green");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("200");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);

        canvasDrawingPage.setXCoordinate("250");
        canvasDrawingPage.setYCoordinate("50");
        canvasDrawingPage.setShapeSize(size);
        canvasDrawingPage.selectColorByName("Grey");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }

    @Test(priority = 9)
    public void testOverlappingShapes() {
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("100");
        canvasDrawingPage.selectColorByName("Blue");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("125");
        canvasDrawingPage.setYCoordinate("125");
        canvasDrawingPage.setShapeSize("50");
        canvasDrawingPage.selectColorByName("Red");
        canvasDrawingPage.selectShapeByName("Square");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
        canvasDrawingPage.setXCoordinate("150");
        canvasDrawingPage.setYCoordinate("150");
        canvasDrawingPage.setShapeSize("25");
        canvasDrawingPage.selectColorByName("Black");
        canvasDrawingPage.selectShapeByName("Circle");
        canvasDrawingPage.clickShowButton();
        checkAndClearCanvas();
        sleep(2000);
    }
}