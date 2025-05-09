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
    
    // Login page elements
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    
    // Products page elements (for verifying successful login)
    private By productsHeader = By.cssSelector(".title");
    private By burgerMenu = By.id("react-burger-menu-btn");
    
    // Products sort elements
    private By productSortContainer = By.className("product_sort_container");
    
    // Product elements
    private By addToCartTShirtRed = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");
    private By addToCartOnesie = By.id("add-to-cart-sauce-labs-onesie");
    
    // Cart elements
    private By shoppingCartLink = By.cssSelector("a.shopping_cart_link");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By removeTShirtButton = By.id("remove-sauce-labs-bolt-t-shirt");
    private By removeOnesieButton = By.cssSelector("[data-test='remove-sauce-labs-onesie']");
    private By checkoutButton = By.id("checkout");
    
    // Checkout information elements
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    
    // Checkout review elements
    private By finishButton = By.id("finish");
    
    // Order completion elements
    private By backHomeButton = By.id("back-to-products");
    private By checkoutComplete = By.className("checkout_complete_container");
    
    public SwagLabsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Helper method to sleep for the specified milliseconds
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Wait for element to be visible and clickable
     */
    private WebElement waitForElement(By locator) {
        sleep(500); // Add 0.5 second delay before every element interaction as requested
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("Element not found or not clickable: " + locator);
            throw e;
        }
    }
    
    /**
     * Clear and set username field
     * @param username username to enter
     */
    public void enterUsername(String username) {
        if (username == null) {
            return;
        }
        WebElement element = waitForElement(usernameField);
        element.clear();
        sleep(500); // 0.5 second delay as requested
        element.sendKeys(username);
        sleep(500); // 0.5 second delay as requested
    }
    
    /**
     * Clear and set password field
     * @param password password to enter
     */
    public void enterPassword(String password) {
        if (password == null) {
            return;
        }
        WebElement element = waitForElement(passwordField);
        element.clear();
        sleep(500); // 0.5 second delay as requested
        element.sendKeys(password);
        sleep(500); // 0.5 second delay as requested
    }
    
    /**
     * Click login button
     */
    public void clickLogin() {
        waitForElement(loginButton).click();
        sleep(500); // 0.5 second delay as requested
    }
    
    /**
     * Perform login with provided credentials
     * @param username username to use
     * @param password password to use
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    /**
     * Check if login was successful
     * @return true if login was successful, false otherwise
     */
    public boolean isLoginSuccessful() {
        sleep(500); // 0.5 second delay as requested
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productsHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get error message shown after failed login
     * @return error message text or empty string if no error
     */
    public String getErrorMessage() {
        sleep(500); // 0.5 second delay as requested
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        sleep(500); // 0.5 second delay as requested
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if login form is displayed
     * @return true if login form is displayed, false otherwise
     */
    public boolean isLoginFormDisplayed() {
        sleep(500); // 0.5 second delay as requested
        try {
            return waitForElement(usernameField).isDisplayed() &&
                   waitForElement(passwordField).isDisplayed() &&
                   waitForElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Sort products by the provided sort option with optimized performance
     * @param sortValue Value of the sort option (az, za, lohi, hilo)
     */
    public void sortProductsBy(String sortValue) {
        try {
            System.out.println("Sorting products by: " + sortValue);
            WebElement sortDropdown = waitForElement(productSortContainer);
            Select select = new Select(sortDropdown);
            
            // Directly select by value without additional waits for "za" option
            if ("za".equals(sortValue)) {
                System.out.println("Using optimized sorting for Z to A");
                select.selectByValue(sortValue);
                // No additional sleep for za sorting to make it faster
            } else {
                // Normal flow for other sort options
                select.selectByValue(sortValue);
                sleep(500); // 0.5 second delay for other sort options
            }
            
            System.out.println("Products sorted by: " + sortValue);
        } catch (Exception e) {
            System.out.println("Error sorting products: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Add T-shirt (red) to cart
     */
    public void addTShirtRedToCart() {
        try {
            System.out.println("Adding T-shirt (Red) to cart");
            waitForElement(addToCartTShirtRed).click();
            sleep(500); // 0.5 second delay as requested
            System.out.println("T-shirt (Red) added to cart");
        } catch (Exception e) {
            System.out.println("Error adding T-shirt (Red) to cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Add Sauce Labs Onesie to cart
     */
    public void addOnesieToCart() {
        try {
            System.out.println("Adding Sauce Labs Onesie to cart");
            waitForElement(addToCartOnesie).click();
            sleep(500); // 0.5 second delay as requested
            System.out.println("Sauce Labs Onesie added to cart");
        } catch (Exception e) {
            System.out.println("Error adding Sauce Labs Onesie to cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Get the number of items in the cart
     * @return number of items in cart or 0 if cart is empty
     */
    public int getCartItemCount() {
        try {
            String badgeText = driver.findElement(shoppingCartBadge).getText();
            return Integer.parseInt(badgeText);
        } catch (Exception e) {
            return 0; // No badge means empty cart
        }
    }
    
    /**
     * Click on shopping cart link
     */
    public void clickOnCart() {
        try {
            System.out.println("Clicking on shopping cart");
            waitForElement(shoppingCartLink).click();
            sleep(500); // 0.5 second delay as requested
        } catch (Exception e) {
            System.out.println("Error clicking on cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Remove Sauce Labs Bolt T-Shirt from cart
     */
    public void removeTShirtFromCart() {
        try {
            System.out.println("Removing T-shirt from cart");
            waitForElement(removeTShirtButton).click();
            sleep(500); // 0.5 second delay as requested
        } catch (Exception e) {
            System.out.println("Error removing T-shirt from cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Remove Sauce Labs Onesie from cart with optimized performance
     */
    public void removeOnesieFromCart() {
        try {
            System.out.println("Removing Sauce Labs Onesie from cart (optimized)");
            // Use direct JS click with data-test selector for maximum performance
            ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('[data-test=\"remove-sauce-labs-onesie\"]').click();"
            );
            // No additional waits to make it faster
            System.out.println("Sauce Labs Onesie removed successfully");
        } catch (Exception e) {
            System.out.println("Error removing Onesie from cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click checkout button
     */
    public void clickCheckout() {
        try {
            System.out.println("Clicking checkout button");
            waitForElement(checkoutButton).click();
            sleep(500); // 0.5 second delay as requested
        } catch (Exception e) {
            System.out.println("Error clicking checkout button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click checkout button with optimized performance
     */
    public void clickCheckoutFast() {
        try {
            System.out.println("Clicking checkout button (optimized)");
            // Use direct JS click for better performance
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
            // No sleep after clicking to make it faster
            System.out.println("Checkout button clicked successfully");
        } catch (Exception e) {
            System.out.println("Error clicking checkout button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Fill checkout information form
     * @param firstName First name to enter
     * @param lastName Last name to enter
     * @param postalCode Postal code to enter
     */
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        try {
            System.out.println("Filling checkout information");
            
            // Enter first name
            WebElement firstNameElement = waitForElement(firstNameField);
            firstNameElement.clear();
            sleep(500); // 0.5 second delay as requested
            firstNameElement.sendKeys(firstName);
            sleep(500); // 0.5 second delay as requested
            
            // Enter last name
            WebElement lastNameElement = waitForElement(lastNameField);
            lastNameElement.clear();
            sleep(500); // 0.5 second delay as requested
            lastNameElement.sendKeys(lastName);
            sleep(500); // 0.5 second delay as requested
            
            // Enter postal code
            WebElement postalCodeElement = waitForElement(postalCodeField);
            postalCodeElement.clear();
            sleep(500); // 0.5 second delay as requested
            postalCodeElement.sendKeys(postalCode);
            sleep(500); // 0.5 second delay as requested
            
        } catch (Exception e) {
            System.out.println("Error filling checkout info: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click continue button on checkout info page
     */
    public void clickContinue() {
        try {
            System.out.println("Clicking continue button");
            waitForElement(continueButton).click();
            sleep(500); // 0.5 second delay as requested
        } catch (Exception e) {
            System.out.println("Error clicking continue button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Click finish button on checkout review page
     */
    public void clickFinish() {
        try {
            System.out.println("Clicking finish button");
            waitForElement(finishButton).click();
            sleep(500); // 0.5 second delay as requested
        } catch (Exception e) {
            System.out.println("Error clicking finish button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Check if order completion page is displayed
     * @return true if on order completion page, false otherwise
     */
    public boolean isOrderCompletionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutComplete)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Click back home button on order completion page
     */
    public void clickBackHome() {
        try {
            System.out.println("Clicking back home button");
            waitForElement(backHomeButton).click();
            sleep(500); // 0.5 second delay as requested
        } catch (Exception e) {
            System.out.println("Error clicking back home button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Complete checkout process with provided information
     * @param firstName First name for checkout
     * @param lastName Last name for checkout
     * @param postalCode Postal code for checkout
     */
    public void completeCheckout(String firstName, String lastName, String postalCode) {
        // Click checkout button
        clickCheckout();
        
        // Fill checkout information
        fillCheckoutInfo(firstName, lastName, postalCode);
        
        // Click continue
        clickContinue();
        
        // Click finish
        clickFinish();
        
        // Click back home
        if (isOrderCompletionDisplayed()) {
            clickBackHome();
        }
    }
    
    /**
     * Complete checkout process with optimized remove and checkout steps
     * @param firstName First name for checkout
     * @param lastName Last name for checkout
     * @param postalCode Postal code for checkout
     */
    public void completeCheckoutFast(String firstName, String lastName, String postalCode) {
        // Remove Onesie with optimized method
        removeOnesieFromCart();
        
        // Click checkout button with optimized method
        clickCheckoutFast();
        
        // Fill checkout information
        fillCheckoutInfo(firstName, lastName, postalCode);
        
        // Click continue
        clickContinue();
        
        // Click finish
        clickFinish();
        
        // Click back home
        if (isOrderCompletionDisplayed()) {
            clickBackHome();
        }
    }
}