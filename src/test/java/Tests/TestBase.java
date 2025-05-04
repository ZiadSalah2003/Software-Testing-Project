package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import java.time.Duration;

public class TestBase {
    public static WebDriver driver;

    @BeforeTest
    public void openUrl()
    {
        // Setup WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Add a small pause before navigating to the page
        sleep(2000);
        
        driver.get("https://testpages.eviltester.com/styled/index.html");
        
        // Increased implicit wait time to 30 seconds and using Duration instead of TimeUnit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
        // Add additional wait after page load
        sleep(3000);
    }
    
    @AfterTest
    public void closeUrl()
    {
        // Add a pause before closing the browser
        sleep(2000);
        driver.quit();
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
