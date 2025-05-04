package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    // Search elements
    private By searchField = By.name("searchterm");
    private By searchButton = By.name("submitbutton");
    private By searchResults = By.className("resultlist");
    private By resultItems = By.cssSelector(".resultlist li");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchTerm(String searchTerm) {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(searchTerm);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public void performSearch(String searchTerm) {
        enterSearchTerm(searchTerm);
        clickSearch();
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(resultItems);
    }

    public int getResultCount() {
        return getSearchResults().size();
    }

    public boolean hasResults() {
        return getResultCount() > 0;
    }

    public boolean resultContains(String text) {
        List<WebElement> results = getSearchResults();
        for (WebElement result : results) {
            if (result.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }
}
