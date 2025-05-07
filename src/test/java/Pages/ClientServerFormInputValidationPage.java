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

    // Locators for the form fields
    private By firstNameField = By.name("firstname");
    private By surnameField = By.name("surname");
    private By ageField = By.name("age");
    private By countryField = By.name("country");
    private By notesField = By.name("notes");
    // Changed to use name attribute instead of XPath for more reliable selection
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

    // Method to safely set value for an input field
    private void safeSetValue(By locator, String value) {
        try {
            WebElement element = driver.findElement(locator);
            // First try conventional way
            try {
                element.clear();
                sleep(200);
                element.sendKeys(value);
            } catch (Exception e) {
                // If that fails, try with JavaScript
                System.out.println("Using JavaScript to set element value for " + locator);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", element, value);
            }
            System.out.println("Set value for " + locator + " to: " + value);
        } catch (Exception e) {
            System.out.println("Failed to set value for " + locator + ": " + e.getMessage());
        }
    }

    // Method to fill out the form fields with values
    public void fillOutForm(String firstName, String surname, String age, String country, String notes) {
        System.out.println("Filling out form...");
        
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
        
        System.out.println("Form fields filled successfully");
    }

    // Method to submit the form using different strategies
    public void submitForm() {
        System.out.println("Submitting form...");
        try {
            waitForElementToBeClickable(submitButton, 10);
            WebElement button = driver.findElement(submitButton);
            
            try {
                // First try conventional click
                button.click();
                System.out.println("Form submitted with conventional click");
            } catch (Exception e) {
                System.out.println("Conventional click failed, trying JavaScript click");
                // If that fails, try with JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", button);
                System.out.println("Form submitted with JavaScript click");
            }
            
            // Wait a moment after submission
            sleep(1000);
            
        } catch (Exception e) {
            System.out.println("Failed to submit form: " + e.getMessage());
        }
    }

    // Method to wait for the page to load after the form submission
    public void waitForPageToLoad(int timeoutInSeconds) {
        System.out.println("Waiting for page to load...");
        try {
            // Get the current URL before waiting
            String initialUrl = driver.getCurrentUrl();
            System.out.println("Initial URL: " + initialUrl);
            
            // Wait for the specified time
            sleep(timeoutInSeconds * 1000);
            
            // Check if URL has changed (indicating a successful navigation)
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL after waiting: " + currentUrl);
            System.out.println("Current page title: " + driver.getTitle());
            
            if (!initialUrl.equals(currentUrl)) {
                System.out.println("Page navigation detected");
            }
        } catch (Exception e) {
            System.out.println("Error while waiting for page to load: " + e.getMessage());
        }
    }

    // Method to navigate back to home page after form submission (two-step navigation)
    public void navigateBackToHomePage() {
        // First go back to the form page
        System.out.println("Navigating back to form page...");
        driver.navigate().back();
        sleep(1000);
        
        // Then go back to the home page
        System.out.println("Navigating back to home page...");
        driver.navigate().back();
        sleep(1000);
    }

    // Method to navigate back to the form page after submission
    public void navigateBackToForm() {
        System.out.println("Navigating back to form page...");
        driver.navigate().back();
        sleep(500); // Short pause to ensure navigation completes
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
