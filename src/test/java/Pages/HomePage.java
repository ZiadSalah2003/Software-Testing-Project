package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import java.util.Set;

public class HomePage {
    private WebDriver driver;

    private By clientServerFormLink = By.linkText("HTML Form Example");
    private By calculatorLink = By.linkText("Calculator");
    private By buttonCalculatorLink = By.id("buttoncalculator");
    private By countdownLink = By.linkText("JavaScript Countdown Test Page");
    private By countdownLinkById = By.id("countdowntest");
    private By searchLink = By.linkText("Search");
    private By charValidationLink = By.linkText("7 Char Val Validation");
    private By noteTakerLink = By.linkText("Simple Note Taker");
    private By learnMoreLink = By.linkText("Learn More");
    private By morePracticeSitesLink = By.linkText("More Practice Sites");
    private By canvasScribbleLink = By.id("scribbletest");
    private By canvasDrawingLink = By.id("canvastest");
    private By relatedSitesLink = By.linkText("Related Sites");
    private By productStoreLink = By.linkText("Product Store");
    private By swagLabsLink = By.xpath("//a[contains(@href,'saucedemo.com')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(Math.min(milliseconds, 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ClientServerFormInputValidationPage openClientServerFormPage() {
        if (driver.getCurrentUrl().contains("HTML Form Example")) {
            return new ClientServerFormInputValidationPage(driver);
        }
        else {
            driver.findElement(clientServerFormLink).click();
            sleep(200);
            return new ClientServerFormInputValidationPage(driver);
        }
    }

    public CalculatorPage openCalculatorPage() {
        if (driver.getCurrentUrl().contains("calculator")) {
            return new CalculatorPage(driver);
        }
        else {
            driver.findElement(calculatorLink).click();
            sleep(200);
            return new CalculatorPage(driver);
        }
    }

    public ButtonCalculatorPage openButtonCalculatorPage() {
        if (driver.getCurrentUrl().contains("calculator.html")) {
            return new ButtonCalculatorPage(driver);
        }
        else {
            driver.findElement(buttonCalculatorLink).click();
            sleep(200);
            return new ButtonCalculatorPage(driver);
        }
    }

    public CountdownPage openCountdownPage() {
        if (driver.getCurrentUrl().contains("JavaScript Countdown Test Page")) {
            return new CountdownPage(driver);
        }
        else {
            try {
                driver.findElement(countdownLinkById).click();
            } catch (Exception e) {
                driver.findElement(countdownLink).click();
            }
            sleep(200);
            return new CountdownPage(driver);
        }
    }

    public SearchPage openSearchPage() {
        if (driver.getCurrentUrl().contains("Search")) {
            return new SearchPage(driver);
        }
        else {
            driver.findElement(searchLink).click();
            sleep(200);
            return new SearchPage(driver);
        }
    }

    public CharValidationPage openCharValidationPage() {
        if (driver.getCurrentUrl().contains("7 Char Val Validation")) {
            return new CharValidationPage(driver);
        }
        else {
            try {
                driver.findElement(charValidationLink).click();
            } catch (Exception e) {
                try {
                    driver.findElement(By.partialLinkText("7 Char")).click();
                } catch (Exception e2) {
                    try {
                        driver.findElement(By.cssSelector("a[href*='7charval']")).click();
                    } catch (Exception e3) {
                        driver.findElement(By.id("7charval")).click();
                    }
                }
            }
            sleep(500);
            return new CharValidationPage(driver);
        }
    }

    public NoteTakerPage openNoteTakerPage() {
        if (driver.getCurrentUrl().contains("Simple Note Taker")) {
            return new NoteTakerPage(driver);
        }
        else {
            driver.findElement(noteTakerLink).click();
            sleep(200);
            return new NoteTakerPage(driver);
        }
    }

    public void clickLearnMore() {
        driver.findElement(learnMoreLink).click();
        sleep(200);
    }

    public void clickMorePracticeSites() {
        driver.findElement(morePracticeSitesLink).click();
        sleep(200);
    }

    public ScribblePage openCanvasScribblePage() {
        if (driver.getCurrentUrl().contains("scribbletest")) {
            return new ScribblePage(driver);
        }
        else {
            driver.findElement(canvasScribbleLink).click();
            sleep(200);
            return new ScribblePage(driver);
        }
    }
    
    public CanvasDrawingPage openCanvasDrawingPage() {
        if (driver.getCurrentUrl().contains("canvastest")) {
            return new CanvasDrawingPage(driver);
        }
        else {
            driver.findElement(canvasDrawingLink).click();
            sleep(200);
            return new CanvasDrawingPage(driver);
        }
    }

    public DemoBlazePage navigateToDemoBlaze() {
        try {
            driver.get("https://www.demoblaze.com/");
            sleep(1000);
            return new DemoBlazePage(driver);
        } catch (Exception e) {
            String originalWindow = driver.getWindowHandle();
            driver.findElement(relatedSitesLink).click();
            sleep(1000);
            
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
            sleep(500);
            
            driver.findElement(productStoreLink).click();
            sleep(1000);
            
            Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            
            return new DemoBlazePage(driver);
        }
    }

    public SwagLabsPage navigateToSwagLabs() {
        try {
            WebElement relatedSitesElement = driver.findElement(relatedSitesLink);
            relatedSitesElement.click();
            sleep(1000);
            
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
            sleep(1000);
            
            driver.findElement(swagLabsLink).click();
            sleep(1000);
            
            Set<String> windows = driver.getWindowHandles();
            String originalWindow = driver.getWindowHandle();
            for (String window : windows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
            
            sleep(1000);
            return new SwagLabsPage(driver);
            
        } catch (Exception e) {
            driver.get("https://www.saucedemo.com");
            sleep(1000);
            return new SwagLabsPage(driver);
        }
    }
}
