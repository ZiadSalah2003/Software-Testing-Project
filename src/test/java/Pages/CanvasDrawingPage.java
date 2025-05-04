package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CanvasDrawingPage {
    private WebDriver driver;

    // Canvas Drawing elements
    private By xInput = By.id("xnum");
    private By yInput = By.id("ynum");
    private By shapeSizeInput = By.id("shapesize");
    private By colorSelect = By.id("colourselect");
    private By shapeSelect = By.id("shapeselect");
    private By showButton = By.name("gobutton");
    private By clearButton = By.name("clearbutton"); // Add the clear button locator
    private By canvas = By.id("canvas"); // Updated from mycanvas to canvas

    public CanvasDrawingPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Set the X coordinate value
     * @param xValue The X coordinate value
     */
    public void setXCoordinate(String xValue) {
        WebElement xElement = driver.findElement(xInput);
        xElement.clear();
        xElement.sendKeys(xValue);
    }

    /**
     * Set the Y coordinate value
     * @param yValue The Y coordinate value
     */
    public void setYCoordinate(String yValue) {
        WebElement yElement = driver.findElement(yInput);
        yElement.clear();
        yElement.sendKeys(yValue);
    }

    /**
     * Set the shape size value
     * @param size The size of the shape
     */
    public void setShapeSize(String size) {
        WebElement sizeElement = driver.findElement(shapeSizeInput);
        sizeElement.clear();
        sizeElement.sendKeys(size);
    }

    /**
     * Select the color for the shape
     * @param color Color value (#000000, #FF0000, #00FF00, #0000FF, #C0C0C0)
     */
    public void selectColor(String color) {
        Select colorDropdown = new Select(driver.findElement(colorSelect));
        colorDropdown.selectByValue(color);
    }

    /**
     * Select color by visible text
     * @param colorName Color name (Black, Red, Green, Blue, Grey)
     */
    public void selectColorByName(String colorName) {
        Select colorDropdown = new Select(driver.findElement(colorSelect));
        colorDropdown.selectByVisibleText(colorName);
    }

    /**
     * Select the shape type
     * @param shapeType Shape value (1 for Circle, 0 for Square)
     */
    public void selectShape(String shapeType) {
        Select shapeDropdown = new Select(driver.findElement(shapeSelect));
        shapeDropdown.selectByValue(shapeType);
    }

    /**
     * Select shape by visible text
     * @param shapeName Shape name (Circle or Square)
     */
    public void selectShapeByName(String shapeName) {
        Select shapeDropdown = new Select(driver.findElement(shapeSelect));
        shapeDropdown.selectByVisibleText(shapeName);
    }

    /**
     * Click the Show button to draw the shape
     */
    public void clickShowButton() {
        driver.findElement(showButton).click();
    }

    /**
     * Click the Clear button to clear the canvas
     */
    public void clickClearButton() {
        driver.findElement(clearButton).click();
        sleep(1000); // Short pause after clearing
    }

    /**
     * Verify if the canvas is displayed
     * @return True if canvas is displayed
     */
    public boolean isCanvasDisplayed() {
        return driver.findElement(canvas).isDisplayed();
    }

    /**
     * Set all parameters and draw the shape
     * @param x X coordinate
     * @param y Y coordinate
     * @param size Shape size
     * @param colorValue Color value
     * @param shapeValue Shape value (1 for Circle, 0 for Square)
     */
    public void drawShape(String x, String y, String size, String colorValue, String shapeValue) {
        setXCoordinate(x);
        setYCoordinate(y);
        setShapeSize(size);
        selectColor(colorValue);
        selectShape(shapeValue);
        clickShowButton();
        sleep(1000); // Wait for drawing to complete
    }

    /**
     * Helper method for drawing a shape using color and shape names instead of values
     * @param x X coordinate
     * @param y Y coordinate
     * @param size Shape size
     * @param colorName Color name (Black, Red, Green, Blue, Grey)
     * @param shapeName Shape name (Circle or Square)
     */
    public void drawShapeByName(String x, String y, String size, String colorName, String shapeName) {
        setXCoordinate(x);
        setYCoordinate(y);
        setShapeSize(size);
        selectColorByName(colorName);
        selectShapeByName(shapeName);
        clickShowButton();
        sleep(1000); // Wait for drawing to complete
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