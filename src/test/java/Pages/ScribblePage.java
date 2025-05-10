package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class ScribblePage {
    private WebDriver driver;
    private By canvas = By.id("canvas");
    private By showEventsButton = By.id("show-events-button");
    private By eventsDiv = By.id("drawevents");
    private By eventsList = By.id("draweventslist");
    
    public ScribblePage(WebDriver driver) {
        this.driver = driver;
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void drawSimple() {
        WebElement canvasElement = driver.findElement(canvas);

        int canvasWidth = Integer.parseInt(canvasElement.getAttribute("width"));
        int canvasHeight = Integer.parseInt(canvasElement.getAttribute("height"));
        
        Actions actions = new Actions(driver);
        
        int aStartX = 120;
        int aStartY = 150;
        
        actions.moveToElement(canvasElement, 50, 50)
               .clickAndHold()
               .moveByOffset(50, 0)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, 75, 50)
               .clickAndHold()
               .moveByOffset(0, 100)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, aStartX, aStartY)
               .clickAndHold()
               .moveByOffset(-20, -100)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, aStartX, aStartY - 100)
               .clickAndHold()
               .moveByOffset(20, 100)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, aStartX - 10, aStartY - 50)
               .clickAndHold()
               .moveByOffset(20, 0)
               .release()
               .perform();
        
        int sStartX = 160;
        int sStartY = 80;
        
        actions.moveToElement(canvasElement, sStartX, sStartY)
               .clickAndHold()
               .moveByOffset(-20, 0)
               .moveByOffset(0, 40)
               .moveByOffset(20, 0)
               .moveByOffset(0, 40)
               .moveByOffset(-20, 0)
               .release()
               .perform();
        
        int nStartX = 190;
        int nStartY = 150;
        
        actions.moveToElement(canvasElement, nStartX, nStartY)
               .clickAndHold()
               .moveByOffset(0, -100)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, nStartX, nStartY - 100)
               .clickAndHold()
               .moveByOffset(30, 100)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, nStartX + 30, nStartY)
               .clickAndHold()
               .moveByOffset(0, -100)
               .release()
               .perform();
        
        int e1StartX = 240;
        int e1StartY = 80;
        
        actions.moveToElement(canvasElement, e1StartX, e1StartY)
               .clickAndHold()
               .moveByOffset(0, 70)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, e1StartX, e1StartY)
               .clickAndHold()
               .moveByOffset(30, 0)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, e1StartX, e1StartY + 35)
               .clickAndHold()
               .moveByOffset(20, 0)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, e1StartX, e1StartY + 70)
               .clickAndHold()
               .moveByOffset(30, 0)
               .release()
               .perform();
        
        int e2StartX = 270;
        int e2StartY = 80;
        
        actions.moveToElement(canvasElement, e2StartX, e2StartY)
               .clickAndHold()
               .moveByOffset(0, 70)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, e2StartX, e2StartY)
               .clickAndHold()
               .moveByOffset(25, 0)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, e2StartX, e2StartY + 35)
               .clickAndHold()
               .moveByOffset(15, 0)
               .release()
               .perform();
        
        actions.moveToElement(canvasElement, e2StartX, e2StartY + 70)
               .clickAndHold()
               .moveByOffset(25, 0)
               .release()
               .perform();
        
        sleep(500);
    }
    
    public void clickShowEventsButton() {
        WebElement button = driver.findElement(showEventsButton);
        button.click();
        sleep(500);
    }
    
    public boolean areEventsDisplayed() {
        WebElement events = driver.findElement(eventsDiv);
        return events.isDisplayed();
    }
    
    public void completeCanvasTask() {
        drawSimple();
        clickShowEventsButton();
        areEventsDisplayed();
    }
}