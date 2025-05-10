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
    public void openUrl() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        sleep(500);
        
        driver.get("https://testpages.eviltester.com/styled/index.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        sleep(1000);
    }

    @AfterTest
    public void closeUrl() {
        sleep(500);
        driver.quit();
    }
    
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(Math.min(milliseconds, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
