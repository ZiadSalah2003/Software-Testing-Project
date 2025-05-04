package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientServerFormPage {
    private WebDriver driver;

    // Form elements
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By commentsField = By.name("comments");
    private By fileUpload = By.name("filename");
    private By checkboxItems = By.name("checkboxes[]");
    private By radioItems = By.name("radioval");
    private By multipleSelect = By.name("multipleselect[]");
    private By dropdownSelect = By.name("dropdown");
    private By submitButton = By.cssSelector("input[type='submit']");

    // Result elements
    private By resultSection = By.id("_valueusername");

    public ClientServerFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void setComments(String comments) {
        driver.findElement(commentsField).sendKeys(comments);
    }

    public void selectCheckbox(int index) {
        WebElement[] checkboxes = driver.findElements(checkboxItems).toArray(new WebElement[0]);
        if (index < checkboxes.length) {
            checkboxes[index].click();
        }
    }

    public void selectRadio(int index) {
        WebElement[] radios = driver.findElements(radioItems).toArray(new WebElement[0]);
        if (index < radios.length) {
            radios[index].click();
        }
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public String getResultUsername() {
        return driver.findElement(resultSection).getText();
    }

    public boolean isFormSubmitted() {
        return driver.findElements(resultSection).size() > 0;
    }
}
