package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor; // Added import for JavascriptExecutor
import java.util.List;

public class NoteTakerPage {
    private WebDriver driver;

    // Note Taker elements
    private By noteField = By.id("note");
    private By addButton = By.id("add");
    private By notes = By.cssSelector(".note");

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

    public void clearAllNotes() {
        // This would depend on the actual implementation of the page
        // If there's a clear all button, we would click it here
        // For now, we'll assume we need to clear local storage
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
        driver.navigate().refresh();
    }
}
