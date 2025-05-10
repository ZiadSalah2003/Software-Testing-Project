package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class SignUpDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signUpLink = By.id("signin2");
    private By signUpModal = By.id("signInModal");
    private By signUpUsernameField = By.id("sign-username");
    private By signUpPasswordField = By.id("sign-password");
    private By signUpButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Sign up')]");
    private By closeSignUpModalButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Close')]");
    private By closeSignUpModalX = By.xpath("//div[@id='signInModal']//button[@class='close']");

    public SignUpDemoBlazePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(Math.min(milliseconds, 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickSignUp() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                    driver.findElement(signUpLink));
            } catch (Exception ex) {
            }
        }
    }

    public void signUp(String username, String password) {
        try {
            WebElement usernameElement = wait.until(
                    ExpectedConditions.elementToBeClickable(signUpUsernameField));
            usernameElement.clear();
            usernameElement.sendKeys(username);
            
            WebElement passwordElement = wait.until(
                    ExpectedConditions.elementToBeClickable(signUpPasswordField));
            passwordElement.clear();
            passwordElement.sendKeys(password);
            
            WebElement signUpButtonElement = wait.until(
                    ExpectedConditions.elementToBeClickable(signUpButton));
            signUpButtonElement.click();
        } catch (Exception e) {
        }
    }

    public String handleAlert() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (TimeoutException e) {
            return "No alert present";
        } catch (Exception e) {
            return "Alert handling error: " + e.getMessage();
        }
    }

    public void closeSignUpModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeSignUpModalButton)).click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(closeSignUpModalX)).click();
            } catch (Exception ex) {
                driver.navigate().refresh();
            }
        }
    }

    public void signUpWithUsernameOnly(String username) {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
    
    public void signUpWithPasswordOnly(String password) {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
    
    public void signUpWithEmptyFields() {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
    
    public void signUpWithCompleteInfo(String username, String password) {
        clickSignUp();
        WebElement usernameField = driver.findElement(signUpUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(signUpPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement signUpButtonElement = driver.findElement(signUpButton);
        signUpButtonElement.click();
        sleep(500);
    }
}
