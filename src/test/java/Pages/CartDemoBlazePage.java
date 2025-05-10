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
    private By productTitles = By.xpath("//a[@class='hrefch']");

    public CartDemoBlazePage(WebDriver driver) {
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
    public void navigateToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
        sleep(500);
    }
    public void navigateToPhones() {
        wait.until(ExpectedConditions.elementToBeClickable(phonesCategory)).click();
        sleep(500);
    }
    public void navigateToLaptops() {
        wait.until(ExpectedConditions.elementToBeClickable(laptopsCategory)).click();
        sleep(500);
    }
    public void navigateToMonitors() {
        wait.until(ExpectedConditions.elementToBeClickable(monitorsCategory)).click();
        sleep(500);
    }
    public void navigateToProductPage(String productName) {
        List<WebElement> products = driver.findElements(productTitles);
        for (WebElement product : products) {
            if (product.getText().toLowerCase().contains(productName.toLowerCase())) {
                product.click();
                sleep(500);
                return;
            }
        }
    }
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        sleep(500);
        handleAlert();
    }
    public void navigateToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        sleep(500);
    }
    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
        sleep(500);
    }
    public void fillOrderForm(String name, String country, String city, String card, String month, String year) {
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(orderNameField));
        nameElement.clear();
        nameElement.sendKeys(name);
        WebElement countryElement = wait.until(ExpectedConditions.elementToBeClickable(orderCountryField));
        countryElement.clear();
        countryElement.sendKeys(country);
        WebElement cityElement = wait.until(ExpectedConditions.elementToBeClickable(orderCityField));
        cityElement.clear();
        cityElement.sendKeys(city);
        WebElement cardElement = wait.until(ExpectedConditions.elementToBeClickable(orderCardField));
        cardElement.clear();
        cardElement.sendKeys(card);
        WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(orderMonthField));
        monthElement.clear();
        monthElement.sendKeys(month);
        WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(orderYearField));
        yearElement.clear();
        yearElement.sendKeys(year);
    }
    public void clickPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();
        sleep(500);
    }
    public boolean isOrderSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getOrderConfirmationText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }
    public void confirmPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(orderConfirmButton)).click();
        sleep(500);

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
}
