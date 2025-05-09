package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import java.util.List;

public class CartDemoBlazePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation elements
    private By homeLink = By.xpath("//a[contains(text(), 'Home')]");
    private By phonesCategory = By.xpath("//a[contains(text(), 'Phones')]");
    private By laptopsCategory = By.xpath("//a[contains(text(), 'Laptops')]");
    private By monitorsCategory = By.xpath("//a[contains(text(), 'Monitors')]");
    
    // Cart elements
    private By cartLink = By.id("cartur");
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");
    private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    
    // Order form elements
    private By orderNameField = By.id("name");
    private By orderCountryField = By.id("country");
    private By orderCityField = By.id("city");
    private By orderCardField = By.id("card");
    private By orderYearField = By.id("year");
    private By orderMonthField = By.id("month");
    private By purchaseButton = By.xpath("//button[contains(text(), 'Purchase')]");
    private By orderSuccessMessage = By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]");
    private By orderConfirmButton = By.xpath("//button[contains(text(), 'OK')]");
    private By productTitles = By.xpath("//a[@class='hrefch']");

    public CartDemoBlazePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    /**
     * Helper method to sleep for the specified milliseconds
     * All sleep durations have been optimized for faster execution
     */
    private void sleep(int milliseconds) {
        try {
            // Use even smaller sleep times to speed up tests
            Thread.sleep(Math.min(milliseconds, 200));  // Limit sleep time to max 200ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigate to home page
     */
    public void navigateToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
        sleep(500);
    }

    /**
     * Navigate to Phones category
     */
    public void navigateToPhones() {
        wait.until(ExpectedConditions.elementToBeClickable(phonesCategory)).click();
        sleep(500);
    }

    /**
     * Navigate to Laptops category
     */
    public void navigateToLaptops() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopsCategory)).click();
        sleep(500);
    }

    /**
     * Navigate to Monitors category
     */
    public void navigateToMonitors() {
        wait.until(ExpectedConditions.elementToBeClickable(monitorsCategory)).click();
        sleep(500);
    }

    /**
     * Navigate to the product page by product name
     */
    public void navigateToProductPage(String productName) {
        try {
            List<WebElement> products = driver.findElements(productTitles);
            for (WebElement product : products) {
                if (product.getText().toLowerCase().contains(productName.toLowerCase())) {
                    product.click();
                    sleep(500);
                    return;
                }
            }
            System.out.println("Product " + productName + " not found");
        } catch (Exception e) {
            System.out.println("Error navigating to product page: " + e.getMessage());
        }
    }

    /**
     * Add product to cart
     */
    public void addToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
            sleep(500);
            // Handle alert
            handleAlert();
        } catch (Exception e) {
            System.out.println("Error adding product to cart: " + e.getMessage());
        }
    }

    /**
     * Navigate to cart
     */
    public void navigateToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error navigating to cart: " + e.getMessage());
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", 
                    driver.findElement(cartLink));
            } catch (Exception ex) {
                System.out.println("JavaScript click also failed: " + ex.getMessage());
            }
        }
    }

    /**
     * Click Place Order button
     */
    public void clickPlaceOrder() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking Place Order button: " + e.getMessage());
        }
    }

    /**
     * Fill in order form
     */
    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        try {
            // Fill name field
            WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(orderNameField));
            nameElement.clear();
            nameElement.sendKeys(name);
            
            // Fill country field
            WebElement countryElement = wait.until(ExpectedConditions.elementToBeClickable(orderCountryField));
            countryElement.clear();
            countryElement.sendKeys(country);
            
            // Fill city field
            WebElement cityElement = wait.until(ExpectedConditions.elementToBeClickable(orderCityField));
            cityElement.clear();
            cityElement.sendKeys(city);
            
            // Fill card field
            WebElement cardElement = wait.until(ExpectedConditions.elementToBeClickable(orderCardField));
            cardElement.clear();
            cardElement.sendKeys(card);
            
            // Fill month field
            WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(orderMonthField));
            monthElement.clear();
            monthElement.sendKeys(month);
            
            // Fill year field
            WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(orderYearField));
            yearElement.clear();
            yearElement.sendKeys(year);
        } catch (Exception e) {
            System.out.println("Error filling order form: " + e.getMessage());
        }
    }

    /**
     * Click Purchase button
     */
    public void clickPurchase() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error clicking Purchase button: " + e.getMessage());
        }
    }

    /**
     * Check if order is successful
     */
    public boolean isOrderSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get order confirmation text
     */
    public String getOrderConfirmationText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Confirm purchase
     */
    public void confirmPurchase() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(orderConfirmButton)).click();
            sleep(500);
        } catch (Exception e) {
            System.out.println("Error confirming purchase: " + e.getMessage());
        }
    }

    /**
     * Handle alert with faster response time - optimized version
     */    /**
     * Handle alert with faster response time - optimized version
     * Returns the alert text if present, or a message indicating no alert was found
     */
    public String handleAlert() {
        try {
            // Use a shorter wait time to check for alerts to avoid long delays
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = shortWait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (TimeoutException e) {
            // This is expected when no alert is present, so just return a message
            return "No alert present";
        } catch (Exception e) {
            System.out.println("Error handling alert: " + e.getMessage());
            return "Alert handling error: " + e.getMessage();
        }
    }
}
