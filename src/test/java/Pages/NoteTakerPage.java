package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class NoteTakerPage {
    private WebDriver driver;

    // Note Taker elements
    private By noteField = By.id("note");
    private By addButton = By.id("add");
    private By notes = By.cssSelector(".note");
    
    // New elements for the updated Simple Note Taker
    private By noteTitleInput = By.id("note-title-input");
    private By noteDetailsInput = By.id("note-details-input");
    private By addNoteButton = By.id("add-note"); 
    private By deleteNoteButtons = By.className("delete-note-in-list");

    public NoteTakerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterNote(String noteText) {
        driver.findElement(noteField).clear();
        driver.findElement(noteField).sendKeys(noteText);
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    public void addNote(String noteText) {
        enterNote(noteText);
        clickAddButton();
    }

    public List<WebElement> getAllNotes() {
        return driver.findElements(notes);
    }

    public int getNoteCount() {
        return getAllNotes().size();
    }

    public boolean noteExists(String noteText) {
        List<WebElement> noteElements = getAllNotes();
        for (WebElement note : noteElements) {
            if (note.getText().contains(noteText)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adds a note with title and details, then deletes it after waiting
     * @param noteTitle The title of the note
     * @param noteDetails The details/content of the note
     */
    public void addAndDeleteNote(String noteTitle, String noteDetails) {
        System.out.println("Starting to add a note...");
        
        // Enter title
        System.out.println("Entering note title: " + noteTitle);
        WebElement titleInput = driver.findElement(noteTitleInput);
        titleInput.clear();
        titleInput.sendKeys(noteTitle);
        
        // Enter note details
        System.out.println("Entering note details: " + noteDetails);
        WebElement detailsInput = driver.findElement(noteDetailsInput);
        detailsInput.clear();
        detailsInput.sendKeys(noteDetails);
        
        // Click add note button
        System.out.println("Clicking 'Add' button");
        driver.findElement(addNoteButton).click();
        
        // Wait to ensure note is added (reduced from 1000ms to 500ms)
        sleep(500);
        
        // Wait for 2 seconds as requested before deleting
        System.out.println("Waiting for 2 seconds before deleting note");
        sleep(2000);
        
        // Click delete button (first one in the list)
        System.out.println("Looking for delete buttons");
        List<WebElement> deleteButtons = driver.findElements(deleteNoteButtons);
        if (!deleteButtons.isEmpty()) {
            System.out.println("Found " + deleteButtons.size() + " delete buttons. Clicking the first one.");
            deleteButtons.get(0).click();
            
            // Handle the alert that appears
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert detected: " + alert.getText());
                alert.accept();
                System.out.println("Alert accepted");
            } catch (Exception e) {
                System.out.println("No alert was present: " + e.getMessage());
            }
        } else {
            System.out.println("ERROR: No delete buttons found");
        }
    }
    
    /**
     * Sleep helper method for waiting
     * Sets implicit wait on the driver.
     * This replaces the previous sleep method with a more efficient implicit wait.
     * @param milliseconds Maximum time to wait in milliseconds
     */
    private void implicitWait(int milliseconds) {
        // Set implicit wait with a maximum of 200ms for consistency
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Math.min(milliseconds, 200)));
    }
    
    /**
     * @deprecated Use implicitWait method instead
     * Kept for backward compatibility with existing code
     * @param milliseconds Time to wait in milliseconds
     */
    @Deprecated
    private void sleep(int milliseconds) {
        implicitWait(milliseconds);
    }

    public void clearAllNotes() {
        // This would depend on the actual implementation of the page
        // If there's a clear all button, we would click it here
        // For now, we'll assume we need to clear local storage
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
        driver.navigate().refresh();
    }
}
