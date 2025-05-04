package Tests;

import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.ScribblePage;

public class ScribbleTest extends TestBase {
    
    @Test
    public void testCanvasScribble() {
        // Initialize homepage and navigate to Canvas Scribble page
        HomePage homePage = new HomePage(driver);
        ScribblePage scribblePage = homePage.openCanvasScribblePage();
        
        // Complete the canvas drawing task
        scribblePage.completeCanvasTask();
        
        // Add a brief pause to see the result
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}