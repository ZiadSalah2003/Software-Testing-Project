package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.time.Duration;

public class TestBase {
    public static WebDriver driver;

    @BeforeTest
    public void openUrl()
    {
        // Setup WebDriver using WebDriverManager with optimized options
        WebDriverManager.chromedriver().setup();
        
        // Add Chrome options to speed up browser startup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Reduced pause before navigating to the page
        implicitWait(500);
        
        driver.get("https://testpages.eviltester.com/styled/index.html");
        
        // Reduced implicit wait time from 30 seconds to 5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        // Reduced additional wait after page load
        implicitWait(1000);
    }
    
    @AfterTest
    public void closeUrl()
    {
        // Reduced pause before closing the browser
        implicitWait(500);
        driver.quit();
    }
    
    /**
     * Sets implicit wait on the driver.
     * This replaces the previous sleep method with a more efficient implicit wait.
     * @param milliseconds Maximum time to wait in milliseconds
     */
    protected void implicitWait(int milliseconds) {
        // Set implicit wait with a reasonable maximum for consistency
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Math.min(milliseconds, 1000)));
    }
    
    /**
     * @deprecated Use implicitWait method instead
     * Kept for backward compatibility with existing code
     */
    @Deprecated
    protected void sleep(int milliseconds) {
        implicitWait(milliseconds);
    }
}
