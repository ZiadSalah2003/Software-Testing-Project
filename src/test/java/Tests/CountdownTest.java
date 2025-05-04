package Tests;

import Pages.CountdownPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountdownTest extends TestBase {
    HomePage homePage;
    CountdownPage countdownPage;

    @Test
    public void testCountdownStart() {
        // Navigate to the Countdown page
        homePage = new HomePage(driver);
        countdownPage = homePage.openCountdownPage();

        // Set duration and start countdown
        countdownPage.setDuration("10");
        countdownPage.startCountdown();

        // Verify countdown is running
        Assert.assertTrue(countdownPage.isCountdownRunning(), "Countdown did not start");
    }

    @Test
    public void testCountdownStop() {
        // Navigate to the Countdown page if not already there
        if (countdownPage == null) {
            homePage = new HomePage(driver);
            countdownPage = homePage.openCountdownPage();
        }

        // Set duration and start countdown
        countdownPage.setDuration("10");
        countdownPage.startCountdown();

        // Wait briefly to ensure countdown starts
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the countdown
        countdownPage.stopCountdown();

        // Verify countdown is stopped
        Assert.assertFalse(countdownPage.isCountdownRunning(), "Countdown did not stop");
    }

    @Test
    public void testCountdownReset() {
        // Navigate to the Countdown page if not already there
        if (countdownPage == null) {
            homePage = new HomePage(driver);
            countdownPage = homePage.openCountdownPage();
        }

        // Set duration, start and then reset
        countdownPage.setDuration("10");
        countdownPage.startCountdown();
        countdownPage.resetCountdown();

        // Verify countdown was reset
        String countdownValue = countdownPage.getCountdownValue();
        Assert.assertEquals(countdownValue, "10", "Countdown was not reset correctly");
    }
}
