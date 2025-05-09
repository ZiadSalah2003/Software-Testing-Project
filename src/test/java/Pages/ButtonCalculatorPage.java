package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class ButtonCalculatorPage {
    private WebDriver driver;

    // Button Calculator elements
    private By displayField = By.id("calculated-display");
    private By button0 = By.id("button00");
    private By button1 = By.id("button01");
    private By button2 = By.id("button02");
    private By button3 = By.id("button03");
    private By button4 = By.id("button04");
    private By button5 = By.id("button05");
    private By button6 = By.id("button06");
    private By button7 = By.id("button07");
    private By button8 = By.id("button08");
    private By button9 = By.id("button09");
    private By buttonPlus = By.id("buttonplus");
    private By buttonMinus = By.id("buttonminus");
    private By buttonMultiply = By.id("buttonmultiply");
    private By buttonDivide = By.id("buttondivide");
    private By buttonEquals = By.id("buttonequals");
    private By buttonallClear = By.id("buttonallclear");
    private By buttonClear = By.id("buttonclearentry");

    public ButtonCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Sets implicit wait on the driver.
     * This replaces the previous sleep method with a more efficient implicit wait.
     * @param milliseconds Maximum time to wait in milliseconds
     */
    private void implicitWait(int milliseconds) {
        // Set implicit wait with a maximum of 200ms for consistency with previous implementation
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Math.min(milliseconds, 200)));
    }

    /**
     * @deprecated Use implicitWait method instead
     */
    @Deprecated
    private void sleep(int milliseconds) {
        implicitWait(milliseconds);
    }

    public void clickButton(String button) {
        switch (button) {
            case "0": driver.findElement(button0).click(); break;
            case "1": driver.findElement(button1).click(); break;
            case "2": driver.findElement(button2).click(); break;
            case "3": driver.findElement(button3).click(); break;
            case "4": driver.findElement(button4).click(); break;
            case "5": driver.findElement(button5).click(); break;
            case "6": driver.findElement(button6).click(); break;
            case "7": driver.findElement(button7).click(); break;
            case "8": driver.findElement(button8).click(); break;
            case "9": driver.findElement(button9).click(); break;
            case "+": driver.findElement(buttonPlus).click(); break;
            case "-": driver.findElement(buttonMinus).click(); break;
            case "*": driver.findElement(buttonMultiply).click(); break;
            case "/": driver.findElement(buttonDivide).click(); break;
            case "=": driver.findElement(buttonEquals).click(); break;
            case "CE": driver.findElement(buttonClear).click(); break;
            case "AC": driver.findElement(buttonallClear).click(); break;
        }
        // Add delay after each button click
        implicitWait(1000);
    }

    public void enterNumber(int number) {
        String numStr = String.valueOf(number);
        for (int i = 0; i < numStr.length(); i++) {
            clickButton(String.valueOf(numStr.charAt(i)));
        }
    }

    public void performCalculation(int num1, String operator, int num2) {
        enterNumber(num1);
        implicitWait(1000);
        clickButton(operator);
        implicitWait(1000);
        enterNumber(num2);
        implicitWait(1000);
        clickButton("=");
        implicitWait(1000);
    }

    public void clearDisplay() {
        clickButton("AC");
        implicitWait(1000);
    }

    public String getDisplayValue() {
        implicitWait(1000);
        return driver.findElement(displayField).getText();
    }
    
    /**
     * Example method for calculating 2 + 5 = 7
     * This is a specific example of using the calculator
     */
    public void calculateTwoPlusFive() {
        // Clear the display first
        clearDisplay();
        implicitWait(1000);
        
        // Click button 2
        clickButton("2");
        implicitWait(1000);
        
        // Click the plus button
        clickButton("+");
        implicitWait(1000);
        
        // Click button 5
        clickButton("5");
        implicitWait(1000);
        
        // Click equals to get the result
        clickButton("=");
        implicitWait(1000);
        
        // The result should be "7" in the display
    }
    
    /**
     * Calculates 25 - 16 = 9
     * This method implements the specific test case requested
     */
    public void calculateTwentyFiveMinusSixteen() {
        // Clear the display first
        clearDisplay();
        implicitWait(1000);
        
        // Enter number 25
        clickButton("2");
        implicitWait(500);
        clickButton("5");
        implicitWait(1000);
        
        // Click the minus button
        clickButton("-");
        implicitWait(1000);
        
        // Enter number 16
        clickButton("1");
        implicitWait(500);
        clickButton("6");
        implicitWait(1000);
        
        // Click equals to get the result
        clickButton("=");
        implicitWait(1000);
        
        // The result should be "9" in the display
    }
}
