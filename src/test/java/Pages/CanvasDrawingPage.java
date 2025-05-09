package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CanvasDrawingPage {
    private WebDriver driver;
    private By xInput = By.id("xnum");
    private By yInput = By.id("ynum");
    private By shapeSizeInput = By.id("shapesize");
    private By colorSelect = By.id("colourselect");
    private By shapeSelect = By.id("shapeselect");
    private By showButton = By.name("gobutton");
    private By clearButton = By.name("clearbutton");
    private By canvas = By.id("canvas");

    public CanvasDrawingPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setXCoordinate(String xValue) {
        WebElement xElement = driver.findElement(xInput);
        xElement.clear();
        xElement.sendKeys(xValue);
    }
    public void setYCoordinate(String yValue) {
        WebElement yElement = driver.findElement(yInput);
        yElement.clear();
        yElement.sendKeys(yValue);
    }
    public void setShapeSize(String size) {
        WebElement sizeElement = driver.findElement(shapeSizeInput);
        sizeElement.clear();
        sizeElement.sendKeys(size);
    }
    public void selectColor(String color) {
        Select colorDropdown = new Select(driver.findElement(colorSelect));
        colorDropdown.selectByValue(color);
    }
    public void selectColorByName(String colorName) {
        Select colorDropdown = new Select(driver.findElement(colorSelect));
        colorDropdown.selectByVisibleText(colorName);
    }
    public void selectShape(String shapeType) {
        Select shapeDropdown = new Select(driver.findElement(shapeSelect));
        shapeDropdown.selectByValue(shapeType);
    }
    public void selectShapeByName(String shapeName) {
        Select shapeDropdown = new Select(driver.findElement(shapeSelect));
        shapeDropdown.selectByVisibleText(shapeName);
    }
    public void clickShowButton() {
        driver.findElement(showButton).click();
    }
    public void clickClearButton() {
        driver.findElement(clearButton).click();
        sleep(1000);
    }
    public boolean isCanvasDisplayed() {
        return driver.findElement(canvas).isDisplayed();
    }
    public void drawShape(String x, String y, String size, String colorValue, String shapeValue) {
        setXCoordinate(x);
        setYCoordinate(y);
        setShapeSize(size);
        selectColor(colorValue);
        selectShape(shapeValue);
        clickShowButton();
        sleep(1000);
    }
    public void drawShapeByName(String x, String y, String size, String colorName, String shapeName) {
        setXCoordinate(x);
        setYCoordinate(y);
        setShapeSize(size);
        selectColorByName(colorName);
        selectShapeByName(shapeName);
        clickShowButton();
        sleep(1000);
    }
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}