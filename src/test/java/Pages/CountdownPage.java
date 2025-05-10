package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CountdownPage {
    private WebDriver driver;
    private By startButton = By.id("start-timer");
    private By stopButton = By.id("stop-timer");
    private By resetButton = By.id("reset-timer"); 
    private By clearButton = By.id("clear-timer");
    private By countdownDisplay = By.id("countdown-display");
    private By timerSecondsInput = By.id("timer-seconds");

    public CountdownPage(WebDriver driver) {
        this.driver = driver;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTimerSeconds(String seconds) {
        driver.findElement(timerSecondsInput).clear();
        driver.findElement(timerSecondsInput).sendKeys(seconds);
        sleep(500);
    }

    public void startCountdown() {
        driver.findElement(startButton).click();
        sleep(500);
    }

    public void stopCountdown() {
        driver.findElement(stopButton).click();
        sleep(500);
    }

    public void resetCountdown() {
        driver.findElement(resetButton).click();
        sleep(500);
    }

    public void clearCountdown() {
        driver.findElement(clearButton).click();
        sleep(500);
    }

    public String getCountdownValue() {
        try {
            return driver.findElement(countdownDisplay).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isCountdownRunning() {
        String initialValue = getCountdownValue();
        sleep(1000);
        String newValue = getCountdownValue();
        return !initialValue.equals(newValue);
    }

    public void performTimerSequence() {
        try {
            clearCountdown();
            setTimerSeconds("2000");
            resetCountdown();
            startCountdown();
            sleep(4000);
            stopCountdown();
            sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
