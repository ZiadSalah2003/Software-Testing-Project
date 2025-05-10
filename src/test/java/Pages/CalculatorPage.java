package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CalculatorPage {
    private WebDriver driver;
    private By number1Field = By.id("number1");
    private By number2Field = By.id("number2");
    private By functionDropdown = By.id("function");
    private By calculateButton = By.id("calculate");
    private By answerField = By.id("answer");

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNumber1(String number) {
        driver.findElement(number1Field).clear();
        driver.findElement(number1Field).sendKeys(number);
    }

    public void setNumber2(String number) {
        driver.findElement(number2Field).clear();
        driver.findElement(number2Field).sendKeys(number);
    }
    
    public void selectFunction(String function) {
        Select functionSelect = new Select(driver.findElement(functionDropdown));
        functionSelect.selectByValue(function);
    }

    public void clickCalculate() {
        driver.findElement(calculateButton).click();
    }

    public String getAnswer() {
        return driver.findElement(answerField).getText();
    }

    public void calculateSum(String num1, String num2) {
        setNumber1(num1);
        setNumber2(num2);
        selectFunction("plus");
        clickCalculate();
    }

    public void calculateWithFunction(String num1, String num2, String function) {
        setNumber1(num1);
        setNumber2(num2);
        selectFunction(function);
        clickCalculate();
    }
}
