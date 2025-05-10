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
        countdownPage.performTimerSequence();
    }
}
