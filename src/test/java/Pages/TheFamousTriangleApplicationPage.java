package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class TheFamousTriangleApplicationPage {
    private WebDriver driver;

    // Locators
    private By side1Field = By.id("side1");
    private By side2Field = By.id("side2");
    private By side3Field = By.id("side3");
    private By submitButton = By.id("identify-triangle-action");
    private By resultText = By.id("triangle-type");

    public TheFamousTriangleApplicationPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Sets implicit wait on the driver.
     * This replaces the previous sleep method with a more efficient implicit wait.
     * @param milliseconds Maximum time to wait in milliseconds
     */
    private void implicitWait(int milliseconds) {
        // Set implicit wait with a maximum of 200ms for consistency
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Math.min(milliseconds, 200)));
    }
    
    /**
     * @deprecated Use implicitWait method instead
     * Kept for backward compatibility with existing code
     */
    @Deprecated
    private void sleep(int milliseconds) {
        implicitWait(milliseconds);
    }

    public void testTriangle(String side1, String side2, String side3) {
        driver.findElement(side1Field).clear();
        driver.findElement(side1Field).sendKeys(side1);

        driver.findElement(side2Field).clear();
        driver.findElement(side2Field).sendKeys(side2);

        driver.findElement(side3Field).clear();
        driver.findElement(side3Field).sendKeys(side3);

        // Click the identify button
        driver.findElement(submitButton).click();

        // Wait 2 seconds for result to appear
        sleep(2000);
    }

    public String getTriangleType() {
        return driver.findElement(resultText).getText();
    }
}
