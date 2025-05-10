package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonCalculatorPage {
    private WebDriver driver;
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

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        sleep(1000);
    }

    public void enterNumber(int number) {
        String numStr = String.valueOf(number);
        for (int i = 0; i < numStr.length(); i++) {
            clickButton(String.valueOf(numStr.charAt(i)));
        }
    }

    public void performCalculation(int num1, String operator, int num2) {
        enterNumber(num1);
        sleep(1000);
        clickButton(operator);
        sleep(1000);
        enterNumber(num2);
        sleep(1000);
        clickButton("=");
        sleep(1000);
    }

    public void clearDisplay() {
        clickButton("AC");
        sleep(1000);
    }

    public String getDisplayValue() {
        sleep(1000);
        return driver.findElement(displayField).getAttribute("value");
    }
    public void calculateTwoPlusFive() {
        clearDisplay();
        sleep(1000);
        clickButton("2");
        sleep(1000);
        clickButton("+");
        sleep(1000);
        clickButton("5");
        sleep(1000);
        clickButton("=");
        sleep(1000);
    }
    public void calculateTwentyFiveMinusSixteen() {
        clearDisplay();
        sleep(1000);
        clickButton("2");
        sleep(500);
        clickButton("5");
        sleep(1000);
        clickButton("-");
        sleep(1000);
        clickButton("1");
        sleep(500);
        clickButton("6");
        sleep(1000);
        clickButton("=");
        sleep(1000);
    }
}
