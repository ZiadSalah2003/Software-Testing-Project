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

    /**
     * Test adding a note and then deleting it
     * This test navigates to the Simple Note Taker page, adds a note with title and details,
     * waits for 2 seconds, and then deletes the note
     */
    @Test
    public void testAddAndDeleteNote() {
        // Log test start
        System.out.println("Starting test to add and delete a note");

        // Navigate to the Note Taker page
        homePage = new HomePage(driver);
        try {
            // Navigate to the Simple Note Taker page
            System.out.println("Navigating to Simple Note Taker page");
            noteTakerPage = homePage.openNoteTakerPage();
            System.out.println("Successfully navigated to the Simple Note Taker page");
            
            // Clear any existing notes for clean slate testing
            System.out.println("Clearing any existing notes");
            noteTakerPage.clearAllNotes();
            sleep(500); // Reduced wait time
        } catch (Exception e) {
            System.out.println("Failed to navigate to the Simple Note Taker page: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Could not navigate to Simple Note Taker page: " + e.getMessage());
        }

        try {
            // Add a note with title and details, then delete it
            String noteTitle = "Test Note Title";
            String noteDetails = "This is a test note that will be deleted after 2 seconds.";
            
            System.out.println("Adding note with title: '" + noteTitle + "' and details: '" + noteDetails + "'");
            
            // Call the method that handles all the adding, waiting, and deletion steps
            noteTakerPage.addAndDeleteNote(noteTitle, noteDetails);
            System.out.println("Add and delete operation completed");
            
            // Wait for any remaining alerts and accept them
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
                System.out.println("Caught and accepted remaining alert");
            } catch (Exception e) {
                // No alert present, which is fine
                System.out.println("No remaining alerts detected");
            }
            
            // Verify that no notes with this title exist
            sleep(500); // Brief wait to allow UI update
            boolean noteStillExists = noteTakerPage.noteExists(noteTitle);
            Assert.assertFalse(noteStillExists, "Note was not successfully deleted");
            System.out.println("Test passed: Note was successfully deleted");
            
        } catch (Exception e) {
            System.out.println("Exception during note taker test: " + e.getMessage());
            e.printStackTrace();
            
            // Try to handle any unhandled alerts
            try {
                Alert alert = driver.switchTo().alert();
                System.out.println("Unhandled alert found: " + alert.getText());
                alert.accept();
                System.out.println("Alert accepted during exception handling");
            } catch (NoAlertPresentException noAlertEx) {
                // No alert present
            }
            
            Assert.fail("Exception in note taker test: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to sleep for the specified milliseconds
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}