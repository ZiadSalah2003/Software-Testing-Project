package Tests;

import Pages.HomePage;
import Pages.NoteTakerPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NoteTakerTest extends TestBase {
    HomePage homePage;
    NoteTakerPage noteTakerPage;
    @Test
    public void testAddAndDeleteNote() {
        homePage = new HomePage(driver);
        noteTakerPage = homePage.openNoteTakerPage();
        noteTakerPage.clearAllNotes();
        sleep(500);
        String noteTitle = "Test Note Title";
        String noteDetails = "This is a test note that will be deleted after 2 seconds.";
        noteTakerPage.addAndDeleteNote(noteTitle, noteDetails);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        sleep(500);
        boolean noteStillExists = noteTakerPage.noteExists(noteTitle);
        Assert.assertFalse(noteStillExists, "Note was not successfully deleted");
    }
}