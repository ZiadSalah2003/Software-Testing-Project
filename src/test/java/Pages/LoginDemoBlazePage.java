package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class LoginDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginLink = By.id("login2");
    private By logoutLink = By.id("logout2");
    private By welcomeUserText = By.id("nameofuser");

    private By loginUsernameField = By.id("loginusername");
    private By loginPasswordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[contains(@onclick, 'logIn()')]");
    private By closeLoginModalButton = By.xpath("//div[@id='logInModal']//button[contains(text(),'Close')]");
    private By closeLoginModalX = By.xpath("//div[@id='logInModal']//button[@class='close']");

    public LoginDemoBlazePage(WebDriver driver) {
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

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                    driver.findElement(loginLink));
            } catch (Exception ex) {
            }
        }
    }

    public void login(String username, String password) {
        try {
            WebElement usernameElement = wait.until(
                    ExpectedConditions.elementToBeClickable(loginUsernameField));
            usernameElement.clear();
            usernameElement.sendKeys(username);
            
            WebElement passwordElement = wait.until(
                    ExpectedConditions.elementToBeClickable(loginPasswordField));
            passwordElement.clear();
            passwordElement.sendKeys(password);
            
            WebElement loginButtonElement = wait.until(
                    ExpectedConditions.elementToBeClickable(loginButton));
            loginButtonElement.click();
        } catch (Exception e) {
        }
    }

    public void closeLoginModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginModalButton)).click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(closeLoginModalX)).click();
            } catch (Exception ex) {
                driver.navigate().refresh();
            }
        }
    }

    public boolean isLoggedIn() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText)).getText();
        } catch (Exception e) {
            return "";
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

    public void logout() {
        try {
            if (isLoggedIn()) {
                wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
                sleep(500);
            }
        } catch (Exception e) {
        }
    }

    public void loginWithUsernameOnly(String username) {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
    }

    public void loginWithPasswordOnly(String password) {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
    }

    public void loginWithEmptyFields() {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
    }

    public void loginWithCompleteInfo(String username, String password) {
        clickLogin();
        WebElement usernameField = driver.findElement(loginUsernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        sleep(500);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        sleep(500);
        
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
        sleep(500);
        
        handleAlert();
        sleep(500);
    }
}
