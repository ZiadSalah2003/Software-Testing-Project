package Tests;

import Pages.CountdownPage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CountdownTest extends TestBase {
    HomePage homePage;
    CountdownPage countdownPage;
    @Test
    public void testTimerSequence() {
        homePage = new HomePage(driver);
        countdownPage = homePage.openCountdownPage();
        
        System.out.println("Starting timer sequence test");
        countdownPage.performTimerSequence();
        
        System.out.println("Timer sequence test completed successfully");
    }
}
