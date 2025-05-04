package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CountdownPage {
    private WebDriver driver;

    // Countdown elements
    private By startButton = By.id("start-timer");
    private By stopButton = By.id("stop-timer");
    private By resetButton = By.id("reset-timer");
    private By countdownDisplay = By.id("countdown-display");
    private By durationInput = By.id("duration");

    public CountdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDuration(String seconds) {
        driver.findElement(durationInput).clear();
        driver.findElement(durationInput).sendKeys(seconds);
    }

    public void startCountdown() {
        driver.findElement(startButton).click();
    }

    public void stopCountdown() {
        driver.findElement(stopButton).click();
    }

    public void resetCountdown() {
        driver.findElement(resetButton).click();
    }

    public String getCountdownValue() {
        return driver.findElement(countdownDisplay).getText();
    }

    public void waitForCountdownToReach(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(countdownDisplay, value));
    }

    public boolean isCountdownRunning() {
        String initialValue = getCountdownValue();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String newValue = getCountdownValue();
        return !initialValue.equals(newValue);
    }
}
