package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class DemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By signUpLink = By.id("signin2");
    private By loginLink = By.id("login2");
    private By contactLink = By.xpath("//a[contains(text(), 'Contact')]");
    private By logoutLink = By.id("logout2");
    private By welcomeUserText = By.id("nameofuser");

    private By signUpModal = By.id("signInModal");
    private By signUpUsernameField = By.id("sign-username");
    private By signUpPasswordField = By.id("sign-password");
    private By signUpButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Sign up')]");
    private By closeSignUpModalButton = By.xpath("//div[@id='signInModal']//button[contains(text(),'Close')]");
    private By closeSignUpModalX = By.xpath("//div[@id='signInModal']//button[@class='close']");

    private By loginUsernameField = By.id("loginusername");
    private By loginPasswordField = By.id("loginpassword");
    private By loginButton = By.xpath("//button[contains(@onclick, 'logIn()')]");
    private By closeLoginModalButton = By.xpath("//div[@id='logInModal']//button[contains(text(),'Close')]");
    private By closeLoginModalX = By.xpath("//div[@id='logInModal']//button[@class='close']");

    private By contactEmailField = By.id("recipient-email");
    private By contactNameField = By.id("recipient-name");
    private By contactMessageField = By.id("message-text");
    private By sendMessageButton = By.xpath("//button[contains(@onclick, 'send()')]");
    private By closeContactModalButton = By.xpath("//div[@id='exampleModal']//button[contains(text(),'Close')]");
    private By closeContactModalX = By.xpath("//div[@id='exampleModal']//button[@class='close']");

    private By anyOpenModal = By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]");

    private By homeLink = By.xpath("//a[contains(text(), 'Home')]");
    private By phonesCategory = By.xpath("//a[contains(text(), 'Phones')]");
    private By laptopsCategory = By.xpath("//a[contains(text(), 'Laptops')]");
    private By monitorsCategory = By.xpath("//a[contains(text(), 'Monitors')]");
    
    private By cartLink = By.id("cartur");
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");
    private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    
    private By orderNameField = By.id("name");
    private By orderCountryField = By.id("country");
    private By orderCityField = By.id("city");
    private By orderCardField = By.id("card");
    private By orderYearField = By.id("year");
    private By orderMonthField = By.id("month");
    private By purchaseButton = By.xpath("//button[contains(text(), 'Purchase')]");
    private By orderSuccessMessage = By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]");
    private By orderConfirmButton = By.xpath("//button[contains(text(), 'OK')]");

    public DemoBlazePage(WebDriver driver) {
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

    public void navigateToDemoBlaze() {
        driver.get("https://www.demoblaze.com/index.html");
        sleep(500);
    }
    
    public void refreshPage() {
        driver.navigate().refresh();
        sleep(500);
    }
    
    public boolean isAnyModalOpen() {
        try {
            return driver.findElement(By.className("modal")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void closeAllModals() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('.modal').modal('hide');"
            );
            sleep(200);
        } catch (Exception e) {
            clickOutsideModals();
        }
    }
    
    public void clickOutsideModals() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "document.elementFromPoint(0, 0).click();"
            );
            sleep(200);
        } catch (Exception e) {
        }
    }
    
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void ensureCleanState() {
        try {
            closeAllModals();
            if (isLoggedIn()) {
                logout();
            }
            sleep(200);
        } catch (Exception e) {
        }
    }

    public void clickSignUp() {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript("$('#signInModal').modal('show');");
            sleep(200);
            
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('sign-username').value = '';" +
                "document.getElementById('sign-password').value = '';"
            );
        } catch (Exception e) {
            try {
                driver.findElement(signUpLink).click();
                sleep(200);
                clearInputFields(signUpUsernameField, signUpPasswordField);
            } catch (Exception ex) {
            }
        }
    }

    public void signUp(String username, String password) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('sign-username').value='" + username + "';" +
                "document.getElementById('sign-password').value='" + password + "';" +
                "$('#signInModal .btn-primary').click();"
            );
            
            sleep(200);
        } catch (Exception e) {
            try {
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsernameField));
                usernameField.clear();
                usernameField.sendKeys(username);
                
                WebElement passwordField = driver.findElement(signUpPasswordField);
                passwordField.clear();
                passwordField.sendKeys(password);
                
                WebElement submitButton = driver.findElement(signUpButton);
                submitButton.click();
                
                sleep(200);
            } catch (Exception ex) {
            }
        }
    }

    public String handleAlert() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            return "No alert present";
        }
    }

    public void closeSignUpModal() {
        try {
            if (isElementPresent(closeSignUpModalButton)) {
                driver.findElement(closeSignUpModalButton).click();
            } 
            else if (isElementPresent(closeSignUpModalX)) {
                driver.findElement(closeSignUpModalX).click();
            } 
            else {
                clickOutsideModals();
            }
            sleep(200);
        } catch (Exception e) {
            clickOutsideModals();
        }
    }

    public void clickLogin() {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "$('#logInModal').modal('show');" +
                "document.getElementById('loginusername').value = '';" +
                "document.getElementById('loginpassword').value = '';"
            );
            sleep(200);
        } catch (Exception e) {
            try {
                driver.findElement(loginLink).click();
                sleep(200);
                clearInputFields(loginUsernameField, loginPasswordField);
            } catch (Exception ex) {
            }
        }
    }

    public void login(String username, String password) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('loginusername').value='" + username + "';" +
                "document.getElementById('loginpassword').value='" + password + "';" +
                "$('#logInModal .btn-primary').click();"
            );
            
            sleep(800);
            
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText));
            } catch (Exception e) {
            }
        } catch (Exception e) {
            try {
                WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginUsernameField));
                usernameField.clear();
                usernameField.sendKeys(username);
                
                WebElement passwordField = driver.findElement(loginPasswordField);
                passwordField.clear();
                passwordField.sendKeys(password);
                
                WebElement loginBtn = driver.findElement(loginButton);
                loginBtn.click();
                
                sleep(800);
                
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText));
                } catch (Exception ex) {
                }
            } catch (Exception ex) {
            }
        }
    }

    public void closeLoginModal() {
        try {
            if (isElementPresent(closeLoginModalButton)) {
                driver.findElement(closeLoginModalButton).click();
            } 
            else if (isElementPresent(closeLoginModalX)) {
                driver.findElement(closeLoginModalX).click();
            } 
            else {
                clickOutsideModals();
            }
            sleep(200);
        } catch (Exception e) {
            clickOutsideModals();
        }
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUserText));
            String welcomeText = driver.findElement(welcomeUserText).getText();
            return welcomeText.contains("Welcome");
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        try {
            return driver.findElement(welcomeUserText).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickContact() {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('#exampleModal').modal('show');" +
                "document.getElementById('recipient-email').value = '';" +
                "document.getElementById('recipient-name').value = '';" +
                "document.getElementById('message-text').value = '';"
            );
            sleep(200);
        } catch (Exception e) {
            try {
                driver.findElement(contactLink).click();
                sleep(200);
                clearInputFields(contactEmailField, contactNameField, contactMessageField);
            } catch (Exception ex) {
            }
        }
    }

    public void submitContactForm(String email, String name, String message) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "document.getElementById('recipient-email').value='" + email + "';" +
                "document.getElementById('recipient-name').value='" + name + "';" +
                "document.getElementById('message-text').value='" + message + "';" +
                "$('#exampleModal .btn-primary').click();"
            );
            
            sleep(200);
        } catch (Exception e) {
            try {
                WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmailField));
                emailField.clear();
                emailField.sendKeys(email);
                
                WebElement nameField = driver.findElement(contactNameField);
                nameField.clear();
                nameField.sendKeys(name);
                
                WebElement messageField = driver.findElement(contactMessageField);
                messageField.clear();
                messageField.sendKeys(message);
                
                WebElement sendBtn = driver.findElement(sendMessageButton);
                sendBtn.click();
                
                sleep(200);
            } catch (Exception ex) {
            }
        }
    }

    public void closeContactModal() {
        try {
            if (isElementPresent(closeContactModalButton)) {
                driver.findElement(closeContactModalButton).click();
            } else if (isElementPresent(closeContactModalX)) {
                driver.findElement(closeContactModalX).click();
            } else {
                clickOutsideModals();
            }
            sleep(200);
        } catch (Exception e) {
            clickOutsideModals();
        }
    }

    public void logout() {
        try {
            ((JavascriptExecutor) driver).executeScript("document.getElementById('logout2').click();");
            sleep(500);
        } catch (Exception e) {
            try {
                driver.findElement(logoutLink).click();
                sleep(500);
            } catch (Exception ex) {
            }
        }
    }
    
    public void switchToNewTab() {
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            
            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    driver.switchTo().window(handle);
                    sleep(500);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    private void clearInputFields(By... fields) {
        try {
            for (By field : fields) {
                WebElement element = driver.findElement(field);
                element.clear();
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
            }
        } catch (Exception e) {
        }
    }

    public void signUpWithUsernameOnly() {
        ensureCleanState();
        
        clickSignUp();
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsernameField));
            usernameField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", usernameField);
            usernameField.sendKeys("qwe");
            
            WebElement passwordField = driver.findElement(signUpPasswordField);
            passwordField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", passwordField);
            
            WebElement submitButton = driver.findElement(signUpButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
            
            handleAlert();
            
            if (isAnyModalOpen()) {
                closeSignUpModal();
            }
            
        } catch (Exception e) {
            ensureCleanState();
        }
    }
    
    public void signUpWithPasswordOnly() {
        ensureCleanState();
        
        clickSignUp();
        
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpUsernameField));
            usernameField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", usernameField);
            
            WebElement passwordField = driver.findElement(signUpPasswordField);
            passwordField.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", passwordField);
            passwordField.sendKeys("qwe");
            
            WebElement submitButton = driver.findElement(signUpButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
            
            handleAlert();
            
            if (isAnyModalOpen()) {
                closeSignUpModal();
            }
            
        } catch (Exception e) {
            ensureCleanState();
        }
    }
    
    public void completeSignUp() {
        ensureCleanState();
        
        clickSignUp();
        
        signUp("qwe", "qwe");
        
        handleAlert();
        
        if (isAnyModalOpen()) {
            closeSignUpModal();
        }
    }
    
    public void completeLogin() {
        ensureCleanState();
        
        clickLogin();
        
        login("qwe", "qwe");
        
        sleep(500);
    }
    
    public void executeFullSignupAndLoginSequence() {
        navigateToDemoBlaze();
        
        signUpWithUsernameOnly();
        sleep(500);
        
        signUpWithPasswordOnly();
        sleep(500);
        
        completeSignUp();
        sleep(500);
        
        completeLogin();
        
        if (isLoggedIn()) {
        } else {
        }
    }

    public String testEmptyUsername(String password) {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('#signInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('sign-username').value = '';" +
                "  document.getElementById('sign-password').value = '" + password + "';" +
                "  $('#signInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300);
            
            String alertText = handleAlert();
            
            ((JavascriptExecutor) driver).executeScript("$('#signInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            clickSignUp();
            signUp("", password);
            String alertText = handleAlert();
            closeSignUpModal();
            return alertText;
        }
    }

    public String testEmptyPassword(String username) {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('#signInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('sign-username').value = '" + username + "';" +
                "  document.getElementById('sign-password').value = '';" +
                "  $('#signInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300);
            
            String alertText = handleAlert();
            
            ((JavascriptExecutor) driver).executeScript("$('#signInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            clickSignUp();
            signUp(username, "");
            String alertText = handleAlert();
            closeSignUpModal();
            return alertText;
        }
    }
    
    public String testLoginEmptyUsername(String password) {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('#logInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('loginusername').value = '';" +
                "  document.getElementById('loginpassword').value = '" + password + "';" +
                "  $('#logInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300);
            
            String alertText = handleAlert();
            
            ((JavascriptExecutor) driver).executeScript("$('#logInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            clickLogin();
            login("", password);
            String alertText = handleAlert();
            closeLoginModal();
            return alertText;
        }
    }
    
    public String testLoginEmptyPassword(String username) {
        ensureCleanState();
        
        try {
            ((JavascriptExecutor) driver).executeScript(
                "$('#logInModal').modal('show');" +
                "setTimeout(function() {" +
                "  document.getElementById('loginusername').value = '" + username + "';" +
                "  document.getElementById('loginpassword').value = '';" +
                "  $('#logInModal .btn-primary').click();" +
                "}, 200);"
            );
            
            sleep(300);
            
            String alertText = handleAlert();
            
            ((JavascriptExecutor) driver).executeScript("$('#logInModal').modal('hide');");
            sleep(200);
            
            return alertText;
        } catch (Exception e) {
            clickLogin();
            login(username, "");
            String alertText = handleAlert();
            closeLoginModal();
            return alertText;
        }
    }

    public boolean isLoginLinkHidden() {
        try {
            return !driver.findElement(loginLink).isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }
    
    public boolean isLogoutLinkVisible() {
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignUpModalOpen() {
        try {
            WebElement modal = driver.findElement(signUpModal);
            String displayStyle = modal.getAttribute("style");
            return displayStyle != null && displayStyle.contains("display: block") || 
                   modal.getAttribute("class") != null && modal.getAttribute("class").contains("show");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isLoginModalOpen() {
        try {
            WebElement modal = driver.findElement(By.id("logInModal"));
            String displayStyle = modal.getAttribute("style");
            return displayStyle != null && displayStyle.contains("display: block") || 
                   modal.getAttribute("class") != null && modal.getAttribute("class").contains("show");
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToHome() {
        try {
            driver.findElement(homeLink).click();
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public void navigateToPhones() {
        try {
            driver.findElement(phonesCategory).click();
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public void navigateToProductPage(String productName) {
        try {
            By productLink = By.xpath("//a[contains(text(), '" + productName + "')]");
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(productLink));
            driver.findElement(productLink).click();
            
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public void addToCart() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
            driver.findElement(addToCartButton).click();
            
            sleep(500);
            
            handleAlert();
            
        } catch (Exception e) {
        }
    }
    
    public void navigateToCart() {
        try {
            driver.findElement(cartLink).click();
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public void clickPlaceOrder() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
            driver.findElement(placeOrderButton).click();
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderNameField));
            
            if (name != null && !name.isEmpty()) {
                driver.findElement(orderNameField).sendKeys(name);
            }
            
            if (country != null && !country.isEmpty()) {
                driver.findElement(orderCountryField).sendKeys(country);
            }
            
            if (city != null && !city.isEmpty()) {
                driver.findElement(orderCityField).sendKeys(city);
            }
            
            if (card != null && !card.isEmpty()) {
                driver.findElement(orderCardField).sendKeys(card);
            }
            
            if (month != null && !month.isEmpty()) {
                driver.findElement(orderMonthField).sendKeys(month);
            }
            
            if (year != null && !year.isEmpty()) {
                driver.findElement(orderYearField).sendKeys(year);
            }
            
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public void clickPurchase() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
            driver.findElement(purchaseButton).click();
            
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public boolean isOrderSuccessful() {
        try {
            return driver.findElement(orderSuccessMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getOrderConfirmationText() {
        try {
            WebElement successElement = driver.findElement(orderSuccessMessage);
            return successElement.getText();
        } catch (Exception e) {
            return null;
        }
    }
    
    public void confirmPurchase() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(orderConfirmButton));
            driver.findElement(orderConfirmButton).click();
            sleep(500);
        } catch (Exception e) {
        }
    }
    
    public boolean completeSamsungGalaxyS6Purchase(String name, String country, String city, String card, String month, String year) {
        try {
            navigateToHome();
            
            navigateToPhones();
            
            navigateToProductPage("Samsung galaxy s6");
            
            addToCart();
            
            navigateToCart();
            
            clickPlaceOrder();
            
            fillOrderForm(name, country, city, card, month, year);
            
            clickPurchase();
            
            boolean success = isOrderSuccessful();
            
            if (success) {
                confirmPurchase();
            }
            
            return success;
        } catch (Exception e) {
            return false;
        }
    }
}