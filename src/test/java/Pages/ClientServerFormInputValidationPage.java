package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientServerFormInputValidationPage {

    private WebDriver driver;
    private By firstNameField = By.name("firstname");
    private By surnameField = By.name("surname");
    private By ageField = By.name("age");
    private By countryField = By.name("country");
    private By notesField = By.name("notes");
    private By submitButton = By.cssSelector("input[type='submit']");

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
    private void safeSetValue(By locator, String value) {
        try {
            WebElement element = driver.findElement(locator);
            try {
                element.clear();
                sleep(200);
                element.sendKeys(value);
            } catch (Exception e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", element, value);
            }
        } catch (Exception e) {
            System.out.println("Failed to set value for " + locator + ": " + e.getMessage());
        }
    }
    public void fillOutForm(String firstName, String surname, String age, String country, String notes) {
        
        waitForElementToBeVisible(firstNameField, 10);
        safeSetValue(firstNameField, firstName);
        
        waitForElementToBeVisible(surnameField, 10);
        safeSetValue(surnameField, surname);
        
        waitForElementToBeVisible(ageField, 10);
        safeSetValue(ageField, age);
        
        waitForElementToBeVisible(countryField, 10);
        safeSetValue(countryField, country);
        
        waitForElementToBeVisible(notesField, 10);
        safeSetValue(notesField, notes);
    }
    public void submitForm() {
            waitForElementToBeClickable(submitButton, 10);
            WebElement button = driver.findElement(submitButton);
            button.click();
            sleep(1000);
    }
    public void waitForPageToLoad(int timeoutInSeconds) {
        String initialUrl = driver.getCurrentUrl();
        sleep(timeoutInSeconds * 1000);
        String currentUrl = driver.getCurrentUrl();
    }
    public void navigateBackToHomePage() {
        driver.navigate().back();
        sleep(1000);
        driver.navigate().back();
        sleep(1000);
    }
    public void navigateBackToForm() {
        driver.navigate().back();
        sleep(500);
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
