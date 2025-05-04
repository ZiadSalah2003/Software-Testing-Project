package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class ScribblePage {
    private WebDriver driver;
    
    // Page elements
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
    
    /**
     * Draw on canvas using simple direct mouse actions
     */
    public void drawSimple() {
        WebElement canvasElement = driver.findElement(canvas);
        
        // Get actual canvas dimensions
        int canvasWidth = Integer.parseInt(canvasElement.getAttribute("width"));
        int canvasHeight = Integer.parseInt(canvasElement.getAttribute("height"));
        
        System.out.println("Canvas dimensions: " + canvasWidth + "x" + canvasHeight);
        
        Actions actions = new Actions(driver);
        
        // Draw T for "TASNEEM"
        // Horizontal line of T
        actions.moveToElement(canvasElement, 50, 50)
               .clickAndHold()
               .moveByOffset(50, 0)
               .release()
               .perform();
        
        // Vertical line of T
        actions.moveToElement(canvasElement, 75, 50)
               .clickAndHold()
               .moveByOffset(0, 100)
               .release()
               .perform();
        
        // Draw A
        int aStartX = 120;
        int aStartY = 150;
        
        // Left side of A
        actions.moveToElement(canvasElement, aStartX, aStartY)
               .clickAndHold()
               .moveByOffset(-20, -100)
               .release()
               .perform();
        
        // Right side of A
        actions.moveToElement(canvasElement, aStartX, aStartY - 100)
               .clickAndHold()
               .moveByOffset(20, 100)
               .release()
               .perform();
        
        // Middle line of A
        actions.moveToElement(canvasElement, aStartX - 10, aStartY - 50)
               .clickAndHold()
               .moveByOffset(20, 0)
               .release()
               .perform();
        
        // Draw S
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
        
        // Draw N
        int nStartX = 190;
        int nStartY = 150;
        
        // Left vertical of N
        actions.moveToElement(canvasElement, nStartX, nStartY)
               .clickAndHold()
               .moveByOffset(0, -100)
               .release()
               .perform();
        
        // Diagonal of N
        actions.moveToElement(canvasElement, nStartX, nStartY - 100)
               .clickAndHold()
               .moveByOffset(30, 100)
               .release()
               .perform();
        
        // Right vertical of N
        actions.moveToElement(canvasElement, nStartX + 30, nStartY)
               .clickAndHold()
               .moveByOffset(0, -100)
               .release()
               .perform();
        
        // Draw first E
        int e1StartX = 240;
        int e1StartY = 80;
        
        // Vertical line
        actions.moveToElement(canvasElement, e1StartX, e1StartY)
               .clickAndHold()
               .moveByOffset(0, 70)
               .release()
               .perform();
        
        // Three horizontal lines
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
        
        // Draw second E - the canvas is 300px wide so we need to be careful with space
        int e2StartX = 270;
        int e2StartY = 80;
        
        // Vertical line
        actions.moveToElement(canvasElement, e2StartX, e2StartY)
               .clickAndHold()
               .moveByOffset(0, 70)
               .release()
               .perform();
        
        // Three horizontal lines - smaller to fit in canvas
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
        
        // Lower case 'm' is too large to fit, so we'll skip it for this simple demo
        
        sleep(500);
    }
    
    /**
     * Click on the "Show Events" button
     */
    public void clickShowEventsButton() {
        // Direct click is more reliable for this simple button
        WebElement button = driver.findElement(showEventsButton);
        button.click();
        
        sleep(500);
    }
    
    /**
     * Check if the events are displayed
     */
    public boolean areEventsDisplayed() {
        WebElement events = driver.findElement(eventsDiv);
        return events.isDisplayed();
    }
    
    /**
     * Complete the canvas task: draw the pattern and click show events
     */
    public void completeCanvasTask() {
        // Use simple direct drawing for better visibility
        drawSimple();
        
        // Click the show events button
        clickShowEventsButton();
        
        // Verify events are displayed
        if (areEventsDisplayed()) {
            System.out.println("Events are displayed successfully");
        } else {
            System.out.println("Events were not displayed");
        }
    }
}