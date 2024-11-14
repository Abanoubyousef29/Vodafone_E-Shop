package Pages;
import Engine.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class P02_SearchResultPage {

    // 1- define webdriver
    // 2- define constructor and initialize webdriver
    // 3- define locators using By
    // 4- define action methods for each locator

    WebDriver driver ;

    public P02_SearchResultPage(WebDriver driver){
        this.driver=driver;
    }

    private final By CATEGORY_TABS_SECTION = By.xpath("//div[@class='category-tabs-section']");
    private final By PRODUCT_CARDS = By.xpath("//div[@class='product-cards']");


    public P02_SearchResultPage selectFromCategoryTabSection(String tap){
        ActionsBot.clickOnChildItemInList(driver,this.CATEGORY_TABS_SECTION,tap);
        return this;
    }

    public P02_SearchResultPage selectFromProductList(String productName){
        ActionsBot.clickOnChildItemInList(driver,this.PRODUCT_CARDS,productName);
        return this;
    }


}
