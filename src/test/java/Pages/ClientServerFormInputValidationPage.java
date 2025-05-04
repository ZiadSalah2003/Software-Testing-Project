package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientServerFormInputValidationPage {

    private WebDriver driver;

    // Locators for the form fields
    private By firstNameField = By.name("firstname");
    private By surnameField = By.name("surname");
    private By ageField = By.name("age");
    private By countryField = By.name("country");
    private By notesField = By.name("notes");
    private By submitButton = By.xpath("/html/body/div/div[3]/form/input[4]");

    // Locator for success message or another indicator on the new page after submission
    private By confirmationMessage = By.id("confirmation"); // Adjust based on actual element

    public ClientServerFormInputValidationPage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Method to fill out the form fields with values and submit
    public void fillOutForm(String firstName, String surname, String age, String country, String notes) {
        waitForElementToBeVisible(firstNameField, 10);
        driver.findElement(firstNameField).sendKeys(firstName);
        waitForElementToBeVisible(surnameField, 10);
        driver.findElement(surnameField).sendKeys(surname);
        waitForElementToBeVisible(ageField, 10);
        driver.findElement(ageField).sendKeys(age);
        waitForElementToBeVisible(countryField, 10);
        driver.findElement(countryField).sendKeys(country);
        waitForElementToBeVisible(notesField, 10);
        driver.findElement(notesField).sendKeys(notes);
    }

    public void submitForm() {
        waitForElementToBeClickable(submitButton, 10);
        driver.findElement(submitButton).click();
    }

    // Method to wait for the page to load after the form submission (i.e., the redirection)
    public void waitForPageToLoad(int timeoutInSeconds) {
        // Wait for a specific element on the redirected page to ensure it's fully loaded
        waitForElementToBeVisible(confirmationMessage, timeoutInSeconds); // Adjust based on confirmation element
    }

    // Method to navigate back to the form page after submission
    public void navigateBackToForm() {
        driver.navigate().back(); // Go back to the form page after submission
    }
}
