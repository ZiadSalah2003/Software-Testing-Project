package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    // Main page elements
    private By clientServerFormLink = By.linkText("HTML Form Example");
    private By calculatorLink = By.linkText("Calculator");
    private By buttonCalculatorLink = By.id("buttoncalculator");
    private By countdownLink = By.linkText("JavaScript Countdown Test Page");
    private By countdownLinkById = By.id("countdowntest"); // Added new countdown link by ID
    private By searchLink = By.linkText("Search");
    private By charValidationLink = By.linkText("7 Char Val Validation");
    private By noteTakerLink = By.linkText("Simple Note Taker");
    private By learnMoreLink = By.linkText("Learn More");
    private By morePracticeSitesLink = By.linkText("More Practice Sites");
    private By canvasScribbleLink = By.id("scribbletest");
    private By canvasDrawingLink = By.id("canvastest");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ClientServerFormInputValidationPage openClientServerFormPage() {
        driver.findElement(clientServerFormLink).click();
        sleep(1000);
        return new ClientServerFormInputValidationPage(driver);
    }

    public CalculatorPage openCalculatorPage() {
        driver.findElement(calculatorLink).click();
        sleep(1000);
        return new CalculatorPage(driver);
    }

    public ButtonCalculatorPage openButtonCalculatorPage() {
        // Print available links on page for debugging
        System.out.println("Looking for Button Calculator link...");
        try {
            driver.findElement(buttonCalculatorLink).click();
            sleep(1000);
            return new ButtonCalculatorPage(driver);
        } catch (Exception e) {
            // Try alternative selectors
            System.out.println("Failed with first selector, trying alternatives...");
            try {
                driver.findElement(By.partialLinkText("JS")).click();
            } catch (Exception e2) {
                try {
                    driver.findElement(By.xpath("//a[contains(text(), 'Calculator') and contains(text(), 'JS')]")).click();
                } catch (Exception e3) {
                    System.out.println("All attempts to find calculator link failed");
                    e3.printStackTrace();
                }
            }
            sleep(1000);
            return new ButtonCalculatorPage(driver);
        }
    }

    public CountdownPage openCountdownPage() {
        // Try the ID selector first, then fallback to link text if needed
        try {
            driver.findElement(countdownLinkById).click();
        } catch (Exception e) {
            System.out.println("Could not find countdown link by ID, trying link text");
            driver.findElement(countdownLink).click();
        }
        sleep(1000);
        return new CountdownPage(driver);
    }

    public SearchPage openSearchPage() {
        driver.findElement(searchLink).click();
        sleep(1000);
        return new SearchPage(driver);
    }

    public CharValidationPage openCharValidationPage() {
        System.out.println("Attempting to click on 7 Char Val Validation link");
        try {
            // Try the primary selector
            driver.findElement(charValidationLink).click();
        } catch (Exception e) {
            System.out.println("Could not find link by text, trying alternative selectors");
            try {
                // Try finding by partial link text
                driver.findElement(By.partialLinkText("7 Char")).click();
            } catch (Exception e2) {
                try {
                    // Try finding by href containing the URL
                    driver.findElement(By.cssSelector("a[href*='7charval']")).click();
                } catch (Exception e3) {
                    System.out.println("All direct attempts failed. Listing all links on page:");
                    // List all links on the page for debugging
                    List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                    for (WebElement link : allLinks) {
                        System.out.println("Link text: '" + link.getText() + "', href: '" + 
                                          link.getAttribute("href") + "', id: '" + 
                                          link.getAttribute("id") + "'");
                    }
                    // Last resort - try to find by ID (old approach)
                    driver.findElement(By.id("7charval")).click();
                }
            }
        }
        sleep(2000); // Increased sleep time to make sure page loads
        return new CharValidationPage(driver);
    }

    public NoteTakerPage openNoteTakerPage() {
        driver.findElement(noteTakerLink).click();
        sleep(1000);
        return new NoteTakerPage(driver);
    }

    public void clickLearnMore() {
        driver.findElement(learnMoreLink).click();
        sleep(1000);
    }

    public void clickMorePracticeSites() {
        driver.findElement(morePracticeSitesLink).click();
        sleep(1000);
    }

    public ScribblePage openCanvasScribblePage() {
        driver.findElement(canvasScribbleLink).click();
        sleep(1000);
        return new ScribblePage(driver);
    }
    
    public CanvasDrawingPage openCanvasDrawingPage() {
        driver.findElement(canvasDrawingLink).click();
        sleep(1000);
        return new CanvasDrawingPage(driver);
    }
}
