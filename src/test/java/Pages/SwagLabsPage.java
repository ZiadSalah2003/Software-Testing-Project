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

    //#region Page Elements
    
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
    
    //#endregion

    public SwagLabsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //#region Helper Methods
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement waitForElement(By locator) {
        sleep(500);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("Element not found or not clickable: " + locator);
            throw e;
        }
    }
    
    //#endregion

    //#region Login Methods
    
    public void enterUsername(String username) {
        if (username == null) {
            return;
        }
        WebElement element = waitForElement(usernameField);
        element.clear();
        sleep(500);
        element.sendKeys(username);
        sleep(500);
    }

    public void enterPassword(String password) {
        if (password == null) {
            return;
        }
        WebElement element = waitForElement(passwordField);
        element.clear();
        sleep(500);
        element.sendKeys(password);
        sleep(500);
    }

    public void clickLogin() {
        waitForElement(loginButton).click();
        sleep(500);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        sleep(500);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(productsHeader)).isDisplayed();
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

    public boolean isLoginFormDisplayed() {
        sleep(500);
        try {
            return waitForElement(usernameField).isDisplayed() &&
                    waitForElement(passwordField).isDisplayed() &&
                    waitForElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    //#endregion

    //#region Product Methods
    
    public void sortProductsBy(String sortValue) {
        try {
            System.out.println("Sorting products by: " + sortValue);
            WebElement sortDropdown = waitForElement(productSortContainer);
            Select select = new Select(sortDropdown);

            if ("za".equals(sortValue)) {
                System.out.println("Using optimized sorting for Z to A");
                select.selectByValue(sortValue);
            } else {
                select.selectByValue(sortValue);
                sleep(500);
            }

            System.out.println("Products sorted by: " + sortValue);
        } catch (Exception e) {
            System.out.println("Error sorting products: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addTShirtRedToCart() {
        try {
            System.out.println("Adding T-shirt (Red) to cart");
            waitForElement(addToCartTShirtRed).click();
            sleep(500);
            System.out.println("T-shirt (Red) added to cart");
        } catch (Exception e) {
            System.out.println("Error adding T-shirt (Red) to cart: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addOnesieToCart() {
        try {
            System.out.println("Adding Sauce Labs Onesie to cart");
            waitForElement(addToCartOnesie).click();
            sleep(500);
            System.out.println("Sauce Labs Onesie added to cart");
        } catch (Exception e) {
            System.out.println("Error adding Sauce Labs Onesie to cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //#endregion

    //#region Cart Methods
    
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
            System.out.println("Clicking on shopping cart");
            waitForElement(shoppingCartLink).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking on cart: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeTShirtFromCart() {
        try {
            System.out.println("Removing T-shirt from cart");
            waitForElement(removeTShirtButton).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error removing T-shirt from cart: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeOnesieFromCart() {
        try {
            System.out.println("Removing Sauce Labs Onesie from cart (optimized)");
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelector('[data-test=\"remove-sauce-labs-onesie\"]').click();"
            );
            System.out.println("Sauce Labs Onesie removed successfully");
        } catch (Exception e) {
            System.out.println("Error removing Onesie from cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //#endregion

    //#region Checkout Methods
    
    public void clickCheckout() {
        try {
            System.out.println("Clicking checkout button");
            waitForElement(checkoutButton).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking checkout button: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickCheckoutFast() {
        try {
            System.out.println("Clicking checkout button (optimized)");
            ((JavascriptExecutor) driver).executeScript(
                    "document.getElementById('checkout').click();"
            );
        } catch (Exception e) {
            System.out.println("Error clicking checkout button fast: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        try {
            System.out.println("Filling checkout information");
            waitForElement(firstNameField).sendKeys(firstName);
            sleep(500);
            waitForElement(lastNameField).sendKeys(lastName);
            sleep(500);
            waitForElement(postalCodeField).sendKeys(postalCode);
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error filling checkout information: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickContinue() {
        try {
            System.out.println("Clicking continue button");
            waitForElement(continueButton).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking continue button: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickFinish() {
        try {
            System.out.println("Clicking finish button");
            waitForElement(finishButton).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking finish button: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isOrderCompletionDisplayed() {
        try {
            return waitForElement(checkoutComplete).isDisplayed();
        } catch (Exception e) {
            System.out.println("Error checking order completion page: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void clickBackHome() {
        try {
            System.out.println("Clicking back home button");
            waitForElement(backHomeButton).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking back home button: " + e.getMessage());
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
    
    //#endregion

    //#region Price Manipulation Methods
      /**
     * Changes the price of an item in the cart using JavaScript
     * 
     * @param newPrice The new price to set for the item
     */
    public void changeItemPriceInCart(double newPrice) {
        try {
            WebElement itemElement = driver.findElement(By.className("inventory_item_price"));
            String priceText = itemElement.getText().replace("$", "");
            double currentPrice = Double.parseDouble(priceText);
            if (currentPrice != newPrice) {
                System.out.println("Changing item price from $" + currentPrice + " to $" + newPrice);
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].innerText = '$" + newPrice + "';", itemElement
                );
                System.out.println("Price changed successfully");
                
                // Force the page to recalculate totals by triggering a subtle DOM change
                ((JavascriptExecutor) driver).executeScript(
                    "document.querySelector('.summary_subtotal_label').style.opacity = '0.99';"
                );
                sleep(500);
                ((JavascriptExecutor) driver).executeScript(
                    "document.querySelector('.summary_subtotal_label').style.opacity = '1';"
                );
            } else {
                System.out.println("Price is already set to $" + newPrice);
            }
        } catch (Exception e) {
            System.out.println("Error changing item price: " + e.getMessage());
            e.printStackTrace();
        }
    }    /**
     * Gets the price of the item in the cart
     * 
     * @return The current item price as a double
     */
    public double getItemPrice() {
        try {
            WebElement itemElement = driver.findElement(By.className("inventory_item_price"));
            String priceText = itemElement.getText().replace("$", "");
            return Double.parseDouble(priceText);
        } catch (Exception e) {
            System.out.println("Error getting item price: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }    /**
     * Gets the total price including tax
     * 
     * @return The total price including tax as a double
     */
    public double getTotalPrice() {
        try {
            WebElement totalPriceElement = waitForElement(By.className("summary_subtotal_label"));
            String totalPriceText = totalPriceElement.getText().replace("Item total: $", "");
            
            WebElement taxElement = waitForElement(By.className("summary_tax_label"));
            String taxText = taxElement.getText().replace("Tax: $", "");
            
            double subtotal = Double.parseDouble(totalPriceText);
            double tax = Double.parseDouble(taxText);
            
            System.out.println("Subtotal: $" + subtotal);
            System.out.println("Tax: $" + tax);
            
            return subtotal + tax;
        } catch (Exception e) {
            System.out.println("Error getting total price: " + e.getMessage());
            e.printStackTrace();
            return 0.0;
        }
    }
    
    //#endregion
}