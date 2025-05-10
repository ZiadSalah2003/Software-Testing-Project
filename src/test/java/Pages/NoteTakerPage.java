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
    private By noteField = By.id("note");
    private By addButton = By.id("add");
    private By notes = By.cssSelector(".note");
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

    public void addAndDeleteNote(String noteTitle, String noteDetails) {
        WebElement titleInput = driver.findElement(noteTitleInput);
        titleInput.clear();
        titleInput.sendKeys(noteTitle);
        
        WebElement detailsInput = driver.findElement(noteDetailsInput);
        detailsInput.clear();
        detailsInput.sendKeys(noteDetails);
        
        driver.findElement(addNoteButton).click();
        sleep(500);
        
        sleep(2000);
        
        List<WebElement> deleteButtons = driver.findElements(deleteNoteButtons);
        if (!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
            
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (Exception e) {
            }
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearAllNotes() {
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
        driver.navigate().refresh();
    }
}
