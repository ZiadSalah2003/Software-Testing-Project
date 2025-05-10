package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class SwagLabsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By hamburgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By loginForm = By.className("login-box");
    
    private By productSortButton = By.className("product_sort_container");
    private By addToCartTShirtRed = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By addToCartOnesie = By.id("add-to-cart-sauce-labs-onesie");
    private By shoppingCartLink = By.className("shopping_cart_link");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By removeTShirtButton = By.id("remove-sauce-labs-bolt-t-shirt");
    private By removeOnesieButton = By.cssSelector("[data-test='remove-sauce-labs-onesie']");
    private By checkoutButton = By.id("checkout");
    
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    
    private By finishButton = By.id("finish");
    
    private By backHomeButton = By.id("back-to-products");
    private By checkoutComplete = By.className("checkout_complete_container");

    public SwagLabsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void enterUsername(String username) {
        WebElement usernameElement = waitForElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = waitForElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLogin() {
        waitForElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        sleep(500);
        enterPassword(password);
        sleep(500);
        clickLogin();
        sleep(500);
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.urlContains("inventory"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginFormDisplayed() {
        try {
            return driver.findElement(loginForm).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        sleep(500);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        sleep(500);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void sortProductsBy(String sortValue) {
        try {
            Select sortDropdown = new Select(waitForElement(productSortButton));
            sortDropdown.selectByValue(sortValue);
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTShirtRedToCart() {
        try {
            waitForElement(addToCartTShirtRed).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOnesieToCart() {
        try {
            waitForElement(addToCartOnesie).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCartItemCount() {
        try {
            String badgeText = driver.findElement(shoppingCartBadge).getText();
            return Integer.parseInt(badgeText);
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickOnCart() {
        try {
            waitForElement(shoppingCartLink).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeTShirtFromCart() {
        try {
            waitForElement(removeTShirtButton).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeOnesieFromCart() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('[data-test=\"remove-sauce-labs-onesie\"]').click();"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickCheckout() {
        try {
            waitForElement(checkoutButton).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickCheckoutFast() {
        try {
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        try {
            WebElement firstNameElement = waitForElement(firstNameField);
            firstNameElement.clear();
            sleep(500);
            firstNameElement.sendKeys(firstName);
            sleep(500);
            
            WebElement lastNameElement = waitForElement(lastNameField);
            lastNameElement.clear();
            sleep(500);
            lastNameElement.sendKeys(lastName);
            sleep(500);
            
            WebElement postalCodeElement = waitForElement(postalCodeField);
            postalCodeElement.clear();
            sleep(500);
            postalCodeElement.sendKeys(postalCode);
            sleep(500);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickContinue() {
        try {
            waitForElement(continueButton).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickFinish() {
        try {
            waitForElement(finishButton).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isOrderCompletionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutComplete)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBackHome() {
        try {
            waitForElement(backHomeButton).click();
            sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void completeCheckout(String firstName, String lastName, String postalCode) {
        clickCheckout();
        fillCheckoutInfo(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
        if (isOrderCompletionDisplayed()) {
            clickBackHome();
        }
    }

    public void completeCheckoutFast(String firstName, String lastName, String postalCode) {
        removeOnesieFromCart();
        clickCheckoutFast();
        fillCheckoutInfo(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
        if (isOrderCompletionDisplayed()) {
            clickBackHome();
        }
    }
}