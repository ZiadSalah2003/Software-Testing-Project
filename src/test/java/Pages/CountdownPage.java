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
    private By clearButton = By.id("clear-timer");
    private By countdownDisplay = By.id("countdown-display");
    private By timerSecondsInput = By.id("timer-seconds");

    public CountdownPage(WebDriver driver) {
        this.driver = driver;
    }

    // Helper method for sleeping
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to set timer seconds in the input field
    public void setTimerSeconds(String seconds) {
        System.out.println("Setting timer to " + seconds + " seconds");
        driver.findElement(timerSecondsInput).clear();
        driver.findElement(timerSecondsInput).sendKeys(seconds);
        sleep(500); // Short pause to ensure the input is processed
    }

    public void startCountdown() {
        System.out.println("Starting the countdown timer");
        driver.findElement(startButton).click();
        sleep(500); // Short pause after clicking
    }

    public void stopCountdown() {
        System.out.println("Stopping the countdown timer");
        driver.findElement(stopButton).click();
        sleep(500); // Short pause after clicking
    }

    public void resetCountdown() {
        System.out.println("Resetting the countdown timer");
        driver.findElement(resetButton).click();
        sleep(500); // Short pause after clicking
    }

    public void clearCountdown() {
        System.out.println("Clearing the countdown timer");
        driver.findElement(clearButton).click();
        sleep(500); // Short pause after clicking
    }

    public String getCountdownValue() {
        try {
            return driver.findElement(countdownDisplay).getText();
        } catch (Exception e) {
            System.out.println("Could not get countdown value: " + e.getMessage());
            return "0";
        }
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

    /**
     * Perform the full countdown test sequence:
     * 1. Clear the timer
     * 2. Set timer to 2000 seconds
     * 3. Reset the timer
     * 4. Start the timer
     * 5. Wait 4 seconds
     * 6. Stop the timer
     * 7. Wait 2 more seconds before closing
     */
    public void performTimerSequence() {
        try {
            // Clear the timer first
            clearCountdown();
            
            // Set the timer to 2000 seconds
            setTimerSeconds("2000");
            
            // Reset the timer before starting
            System.out.println("Resetting the timer before starting");
            resetCountdown();
            
            // Start the timer
            startCountdown();
            
            // Wait for 4 seconds
            System.out.println("Waiting for 4 seconds...");
            sleep(4000);
            
            // Stop the timer
            stopCountdown();
            
            // Wait 2 more seconds before closing
            System.out.println("Waiting for 2 more seconds before completing...");
            sleep(2000);
            
            // Log completion
            System.out.println("Timer sequence completed");
            
        } catch (Exception e) {
            System.out.println("Error during timer sequence: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
