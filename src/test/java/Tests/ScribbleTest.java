package Tests;

import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.ScribblePage;

public class ScribbleTest extends TestBase {
    
    @Test
    public void testCanvasScribble() {
        HomePage homePage = new HomePage(driver);
        ScribblePage scribblePage = homePage.openCanvasScribblePage();
        scribblePage.completeCanvasTask();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}