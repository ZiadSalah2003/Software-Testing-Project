package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import java.time.Duration;

public class PracticeAutomationFormPage {

    private WebDriver driver;
    private By nameInput = By.id("name-input");
    private By passwordInput = By.cssSelector("input[type='password']");
    private By waterCheckbox = By.id("drink1");
    private By wineCheckbox = By.id("drink4");
    private By colorRedRadio = By.id("color1");
    private By automationDropdown = By.id("automation");
    private By emailInput = By.id("email");
    private By submitButton = By.id("submit-btn");

    public PracticeAutomationFormPage(WebDriver driver) {
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
      private void safeClick(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        sleep(500);
        element.click();
    } 
    public void navigateToFormFields() {
        driver.get("https://practice-automation.com/form-fields/");
        sleep(2000);
    }

    public void fillForm(String name, String password, String email) {
        waitForElementToBeVisible(nameInput, 10);
        safeSetValue(nameInput, name);
        waitForElementToBeVisible(passwordInput, 10);
        safeSetValue(passwordInput, password);
        waitForElementToBeClickable(waterCheckbox, 10);
        if (!driver.findElement(waterCheckbox).isSelected()) {
            safeClick(waterCheckbox);
        }
        waitForElementToBeClickable(wineCheckbox, 10);
        if (!driver.findElement(wineCheckbox).isSelected()) {
            safeClick(wineCheckbox);
        }
        waitForElementToBeClickable(colorRedRadio, 10);
        safeClick(colorRedRadio);
        waitForElementToBeVisible(automationDropdown, 10);
        Select dropdown = new Select(driver.findElement(automationDropdown));
        dropdown.selectByValue("yes");
        waitForElementToBeVisible(emailInput, 10);
        safeSetValue(emailInput, email);
    }    public void submitForm() {
        WebElement button = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", button);
        sleep(1000);
        waitForElementToBeClickable(submitButton, 10);
        safeClick(submitButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        sleep(1000);
        alert.accept();
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
