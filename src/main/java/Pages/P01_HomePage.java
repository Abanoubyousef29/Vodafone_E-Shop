package Pages;
import Engine.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;


public class P01_HomePage {

    // 1- define webdriver
    // 2- define constructor and initialize webdriver
    // 3- define locators using By
    // 4- define action methods for each locator

    WebDriver driver ;

    public P01_HomePage(WebDriver driver){
        this.driver=driver;
    }

    private final By SEARCH_BAR = By.id("searchInput");
    private final By SEARCH_LIST = By.xpath("//*[contains(@class, 'search-results') and contains(@class, 'desktop-search-results')]");


    public P01_HomePage searchForProduct(String item){
        ActionsBot.sendKeysToElement(driver , this.SEARCH_BAR,item);
        return this;
    }

    public P01_HomePage selectFromSearchList(String item) {


        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement selectedTab = wait.until(d -> {
            try {
                WebElement element = d.findElement(SEARCH_LIST).findElement(By.xpath("//*[text()='" + item + "']/parent::*"));
                return element.isDisplayed() ? element : null;
            } catch (StaleElementReferenceException e) {
                return null; // Retry if the element is stale
            }
        });

        if (selectedTab != null) {
            selectedTab.click();
        } else {
            throw new NoSuchElementException("The specified item was not found or did not become stable in time.");
        }


        return this;
    }



}
