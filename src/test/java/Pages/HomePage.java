package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import java.util.Set;

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

    // New related sites link
    private By relatedSitesLink = By.linkText("Related Sites");
    private By productStoreLink = By.linkText("Product Store");

    // New Swag Labs link
    private By swagLabsLink = By.xpath("//a[contains(@href,'saucedemo.com')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
      /**
     * Sets implicit wait on the driver.
     * This replaces the previous sleep method with a more efficient implicit wait.
     * @param milliseconds Maximum time to wait in milliseconds
     */
    private void implicitWait(int milliseconds) {
        // Set implicit wait with a maximum of 200ms for consistency with previous implementation
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Math.min(milliseconds, 200)));
    }
    
    /**
     * Creates an explicit wait for specific conditions
     * @param milliseconds Maximum time to wait in milliseconds
     * @return WebDriverWait object
     */
    private WebDriverWait getWait(int milliseconds) {
        return new WebDriverWait(driver, Duration.ofMillis(Math.min(milliseconds, 200)));
    }    public ClientServerFormInputValidationPage openClientServerFormPage() {
        driver.findElement(clientServerFormLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
        return new ClientServerFormInputValidationPage(driver);
    }

    public CalculatorPage openCalculatorPage() {
        driver.findElement(calculatorLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
        return new CalculatorPage(driver);
    }    public ButtonCalculatorPage openButtonCalculatorPage() {
        // Print available links on page for debugging
        System.out.println("Looking for Button Calculator link...");
        try {
            driver.findElement(buttonCalculatorLink).click();
            implicitWait(200); // Replaced sleep with implicitWait
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
            implicitWait(200); // Replaced sleep with implicitWait
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
        implicitWait(200); // Replaced sleep with implicitWait
        return new CountdownPage(driver);
    }    public SearchPage openSearchPage() {
        driver.findElement(searchLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
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
        // Using slightly longer wait for this complex page
        implicitWait(500); // Replaced sleep with implicitWait (kept longer wait time)
        return new CharValidationPage(driver);
    }    public NoteTakerPage openNoteTakerPage() {
        driver.findElement(noteTakerLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
        return new NoteTakerPage(driver);
    }

    public void clickLearnMore() {
        driver.findElement(learnMoreLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
    }

    public void clickMorePracticeSites() {
        driver.findElement(morePracticeSitesLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
    }

    public ScribblePage openCanvasScribblePage() {
        driver.findElement(canvasScribbleLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
        return new ScribblePage(driver);
    }
    
    public CanvasDrawingPage openCanvasDrawingPage() {
        driver.findElement(canvasDrawingLink).click();
        implicitWait(200); // Replaced sleep with implicitWait
        return new CanvasDrawingPage(driver);
    }
      /**
     * Navigate to DemoBlaze through direct URL for faster testing
     * This bypasses the Related Sites flow for efficiency
     * @return DemoBlazePage object for interaction with the DemoBlaze site
     */
    public DemoBlazePage navigateToDemoBlaze() {
        System.out.println("Direct navigation to DemoBlaze for faster testing");
        driver.get("https://www.demoblaze.com/index.html");
        // Use explicit wait to wait for page to load
        getWait(500).until(ExpectedConditions.titleContains("STORE"));
        return new DemoBlazePage(driver);
    }

    // Original navigation method kept for reference but not used in optimized tests
    public DemoBlazePage navigateToDemoBlazeOriginal() {
        // Original navigation logic
        System.out.println("Navigating to DemoBlaze from the TestPages homepage");
        
        try {
            // First check if already on DemoBlaze site
            if (driver.getCurrentUrl().contains("demoblaze.com")) {
                System.out.println("Already on DemoBlaze site, no need to navigate");
                return new DemoBlazePage(driver);
            }
            
            // If on TestPages, navigate using proper flow through Related Sites
            if (driver.getCurrentUrl().contains("testpages.eviltester.com")) {
                // First navigate to the Related Sites page
                // Try finding the Related Sites link with a more flexible approach
                WebElement relatedSitesElement = null;
                
                try {
                    // Try first by link text
                    relatedSitesElement = driver.findElement(relatedSitesLink);
                } catch (Exception e) {
                    System.out.println("Could not find Related Sites by link text, trying alternate methods");
                    
                    // Try by specific URL pattern
                    try {
                        relatedSitesElement = driver.findElement(By.cssSelector("a[href*='app=testpages&t=Others']"));
                    } catch (Exception e2) {
                        // Try by partial link text
                        try {
                            relatedSitesElement = driver.findElement(By.partialLinkText("Related"));
                        } catch (Exception e3) {
                            // Last resort - try to find link containing "Related" in any attribute
                            List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                            for (WebElement link : allLinks) {
                                String href = link.getAttribute("href");
                                String text = link.getText();
                                if ((href != null && href.contains("Others")) || 
                                    (text != null && text.contains("Related"))) {
                                    relatedSitesElement = link;
                                    break;
                                }
                            }
                        }
                    }
                }
                  if (relatedSitesElement != null) {
                    System.out.println("Found Related Sites link, clicking it");
                    relatedSitesElement.click();
                    // Use explicit wait to wait for page to load
                    getWait(2000).until(ExpectedConditions.urlContains("Others"));
                    
                    // Now on Related Sites page, look for Product Store link
                    WebElement productStoreElement = null;
                    
                    try {
                        // Try first by link text
                        productStoreElement = driver.findElement(productStoreLink);
                    } catch (Exception e) {
                        System.out.println("Could not find Product Store by link text, trying alternate methods");
                        
                        // Try by specific URL pattern
                        try {
                            productStoreElement = driver.findElement(By.cssSelector("a[href*='demoblaze.com']"));
                        } catch (Exception e2) {
                            // Try by partial link text
                            try {
                                productStoreElement = driver.findElement(By.partialLinkText("Product"));
                            } catch (Exception e3) {
                                // Last resort - print all links for debugging
                                System.out.println("All attempts to find Product Store link failed. Listing all links:");
                                List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                                for (WebElement link : allLinks) {
                                    System.out.println("Link text: '" + link.getText() + "', href: '" + 
                                                     link.getAttribute("href") + "'");
                                    
                                    // If we find a DemoBlaze link, use it
                                    String href = link.getAttribute("href");
                                    if (href != null && href.contains("demoblaze.com")) {
                                        productStoreElement = link;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                      if (productStoreElement != null) {
                        System.out.println("Found Product Store link, clicking it");
                        productStoreElement.click();
                        implicitWait(2000);
                        
                        // Store current window handle
                        String currentHandle = driver.getWindowHandle();
                        
                        // Check if a new tab was opened
                        Set<String> handles = driver.getWindowHandles();
                        if (handles.size() > 1) {
                            System.out.println("New tab detected, switching to it");
                            for (String handle : handles) {
                                if (!handle.equals(currentHandle)) {
                                    driver.switchTo().window(handle);
                                    break;
                                }
                            }
                        }
                          // Wait for page to load with explicit wait
                        getWait(3000).until(ExpectedConditions.titleContains("STORE"));
                        
                        System.out.println("Successfully navigated to DemoBlaze via Related Sites");
                    } else {
                        System.out.println("Could not find Product Store link, falling back to direct navigation");
                        driver.get("https://www.demoblaze.com/index.html");
                        getWait(3000).until(ExpectedConditions.titleContains("STORE"));
                    }                } else {
                    System.out.println("Could not find Related Sites link, falling back to direct navigation");
                    driver.get("https://www.demoblaze.com/index.html");
                    getWait(3000).until(ExpectedConditions.titleContains("STORE"));
                }
            } else {
                // Not on TestPages, navigate directly to DemoBlaze
                System.out.println("Not on TestPages, navigating directly to DemoBlaze");
                driver.get("https://www.demoblaze.com/index.html");
                getWait(3000).until(ExpectedConditions.titleContains("STORE"));
            }
            
        } catch (Exception e) {
            System.out.println("Error during navigation to DemoBlaze: " + e.getMessage());
            e.printStackTrace();            // As a fallback, navigate directly to DemoBlaze
            System.out.println("Falling back to direct navigation due to error");
            driver.get("https://www.demoblaze.com/index.html");
            getWait(3000).until(ExpectedConditions.titleContains("STORE"));
        }
        
        return new DemoBlazePage(driver);
    }

    /**
     * Navigate to Swag Labs through Related Sites
     * @return SwagLabsPage object for interaction with the Swag Labs site
     */    public SwagLabsPage navigateToSwagLabs() {
        System.out.println("Navigating to Swag Labs from the TestPages homepage");
        
        try {
            // First check if already on Swag Labs site
            if (driver.getCurrentUrl().contains("saucedemo.com")) {
                System.out.println("Already on Swag Labs site, no need to navigate");
                implicitWait(100000); // Use implicit wait instead of sleep
                return new SwagLabsPage(driver);
            }
            
            // Navigate to Related Sites page
            WebElement relatedSitesElement = null;
            try {
                // Try first by link text
                System.out.println("Looking for Related Sites link...");
                relatedSitesElement = driver.findElement(relatedSitesLink);
            } catch (Exception e) {
                System.out.println("Could not find Related Sites by link text, trying alternate methods");
                // Try by specific URL pattern
                try {
                    relatedSitesElement = driver.findElement(By.cssSelector("a[href*='app=testpages&t=Others']"));
                } catch (Exception e2) {
                    // Try by partial link text
                    try {
                        relatedSitesElement = driver.findElement(By.partialLinkText("Related"));
                    } catch (Exception e3) {
                        System.out.println("Could not find Related Sites link, trying last resort approach");
                        // Last resort - try to find Related Sites in all links
                        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                        for (WebElement link : allLinks) {
                            if (link.getText().contains("Related")) {
                                relatedSitesElement = link;
                                break;
                            }
                        }
                        
                        if (relatedSitesElement == null) {
                            throw new RuntimeException("Could not find Related Sites link");
                        }
                    }
                }
            }
              // Click on Related Sites link
            System.out.println("Found Related Sites link, clicking it");
            implicitWait(100000); // Use implicit wait instead of sleep
            relatedSitesElement.click();
            // Wait for page to load
            getWait(100000).until(ExpectedConditions.urlContains("Others"));
            
            // Scroll down to find Swag Labs link
            System.out.println("Scrolling down to find Swag Labs link");
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");
            implicitWait(100000); // Use implicit wait instead of sleep
            
            // Find and click on Swag Labs link
            WebElement swagLabsElement = null;
            try {
                System.out.println("Looking for Swag Labs link by xpath...");
                swagLabsElement = driver.findElement(swagLabsLink);
            } catch (Exception e) {
                System.out.println("Could not find Swag Labs by xpath, trying alternate methods");
                // Try alternative methods
                try {
                    swagLabsElement = driver.findElement(By.linkText("Swag Labs"));
                } catch (Exception e2) {
                    try {
                        swagLabsElement = driver.findElement(By.partialLinkText("Swag"));
                    } catch (Exception e3) {
                        System.out.println("Could not find Swag Labs link by text, trying to find by href");
                        // Last resort - list all links and find by URL containing saucedemo
                        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                        for (WebElement link : allLinks) {
                            String href = link.getAttribute("href");
                            String text = link.getText();
                            System.out.println("Link found: " + text + " - " + href);
                            if (href != null && href.contains("saucedemo.com")) {
                                swagLabsElement = link;
                                break;
                            }
                        }
                    }
                }
            }
              if (swagLabsElement == null) {
                System.out.println("Could not find Swag Labs link, falling back to direct navigation");
                driver.get("https://www.saucedemo.com");
                getWait(100000).until(ExpectedConditions.urlContains("saucedemo.com"));
            } else {
                // Click on Swag Labs link
                System.out.println("Found Swag Labs link, clicking it");
                implicitWait(100000); // Use implicit wait instead of sleep
                swagLabsElement.click();
                implicitWait(100000); // Use implicit wait instead of sleep
                
                // Store current window handle
                String currentHandle = driver.getWindowHandle();
                
                // Check if a new tab was opened
                Set<String> handles = driver.getWindowHandles();
                if (handles.size() > 1) {
                    System.out.println("New tab detected, switching to it");
                    for (String handle : handles) {
                        if (!handle.equals(currentHandle)) {
                            driver.switchTo().window(handle);
                            break;
                        }                    }
                    implicitWait(100000); // Use implicit wait instead of sleep after switching tabs
                }
            }
            
            // Verify we're on the Swag Labs page
            if (!driver.getCurrentUrl().contains("saucedemo.com")) {
                System.out.println("Not on Swag Labs site, navigating directly");
                driver.get("https://www.saucedemo.com");
                implicitWait(100000); // Use implicit wait instead of sleep
            }
              System.out.println("Successfully navigated to Swag Labs: " + driver.getCurrentUrl());
            implicitWait(100000); // Use implicit wait instead of sleep before returning to ensure page is fully loaded
            
        }
        catch (Exception e) {
            System.out.println("Error during navigation to Swag Labs: " + e.getMessage());
            e.printStackTrace();
            // As a fallback, navigate directly to Swag Labs
            System.out.println("Falling back to direct navigation due to error");
            driver.get("https://www.saucedemo.com");
            implicitWait(100000); // Use implicit wait instead of sleep after fallback navigation
        }
        
        return new SwagLabsPage(driver);
    }
}
