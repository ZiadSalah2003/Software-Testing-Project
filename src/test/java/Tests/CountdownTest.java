package Tests;

import Pages.CountdownPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountdownTest extends TestBase {
    HomePage homePage;
    CountdownPage countdownPage;

    /**
     * Test case for the specific timer sequence:
     * 1. Clear the timer
     * 2. Set timer to 2000 seconds
     * 3. Start the timer
     * 4. Wait 4 seconds
     * 5. Stop the timer
     * 6. Wait 2 seconds and close
     */
    @Test
    public void testTimerSequence() {
        // Navigate to the Countdown page
        homePage = new HomePage(driver);
        countdownPage = homePage.openCountdownPage();
        
        System.out.println("Starting timer sequence test");
        
        // Execute the timer sequence
        countdownPage.performTimerSequence();
        
        System.out.println("Timer sequence test completed successfully");
    }
}
