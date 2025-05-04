package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    // Main page elements
    private By clientServerFormLink = By.linkText("HTML Form Example");
    // Updated calculator link to use linkText instead of id
    private By calculatorLink = By.linkText("Calculator");
    // Updated Button Calculator link to use the correct id
    private By buttonCalculatorLink = By.id("buttoncalculator");
    private By countdownLink = By.linkText("JavaScript Countdown Test Page");
    private By searchLink = By.linkText("Search");
    private By charValidationLink = By.linkText("7 Char Val Validation");
    private By noteTakerLink = By.linkText("Simple Note Taker");
    private By learnMoreLink = By.linkText("Learn More");
    private By morePracticeSitesLink = By.linkText("More Practice Sites");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ClientServerFormInputValidationPage openClientServerFormPage() {
        driver.findElement(clientServerFormLink).click();
        sleep(1000);
        return new ClientServerFormInputValidationPage(driver);
    }

    public CalculatorPage openCalculatorPage() {
        driver.findElement(calculatorLink).click();
        sleep(1000);
        return new CalculatorPage(driver);
    }

    public ButtonCalculatorPage openButtonCalculatorPage() {
        // Print available links on page for debugging
        System.out.println("Looking for Button Calculator link...");
        try {
            driver.findElement(buttonCalculatorLink).click();
            sleep(1000);
            return new ButtonCalculatorPage(driver);
        } catch (Exception e) {
            // Try alternative selectors
            System.out.println("Failed with first selector, trying alternatives...");
            try {
                driver.findElement(By.partialLinkText("JS")).click();
            } catch (Exception e2) {
                try {
                    driver.findElement(By.xpath("//a[contains(text(), 'Calculator') and contains(text(), 'JS')]")).click();
                } catch (Exception e3) {
                    System.out.println("All attempts to find calculator link failed");
                    e3.printStackTrace();
                }
            }
            sleep(1000);
            return new ButtonCalculatorPage(driver);
        }
    }

    public CountdownPage openCountdownPage() {
        driver.findElement(countdownLink).click();
        sleep(1000);
        return new CountdownPage(driver);
    }

    public SearchPage openSearchPage() {
        driver.findElement(searchLink).click();
        sleep(1000);
        return new SearchPage(driver);
    }

    public CharValidationPage openCharValidationPage() {
        driver.findElement(charValidationLink).click();
        sleep(1000);
        return new CharValidationPage(driver);
    }

    public NoteTakerPage openNoteTakerPage() {
        driver.findElement(noteTakerLink).click();
        sleep(1000);
        return new NoteTakerPage(driver);
    }

    public void clickLearnMore() {
        driver.findElement(learnMoreLink).click();
        sleep(1000);
    }

    public void clickMorePracticeSites() {
        driver.findElement(morePracticeSitesLink).click();
        sleep(1000);
    }
}
