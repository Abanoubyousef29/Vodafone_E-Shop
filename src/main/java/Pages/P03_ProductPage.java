package Pages;

import Engine.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class P03_ProductPage {


    // 1- define webdriver
    // 2- define constructor and initialize webdriver
    // 3- define locators using By
    // 4- define action methods for each locator

    WebDriver driver ;

    public P03_ProductPage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private WebDriverWait wait;

    // Locator for product color options
    private final By PRODUCT_COLOR = By.xpath("//div[@class='--color-option']");
     // Locator for out-of-stock label
    private final By OUT_OF_STOCK_LABEL = By.xpath("//div[@class='availability']//*[text()='Out Of Stock ']");
    // Locator for add-to-cart button
    private final By ADD_TO_CART_BUTTON = By.xpath("//button[@class='add-to-cart']");


    public boolean checkProductColors() {
        List<WebElement> colorOptions = driver.findElements(PRODUCT_COLOR);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < colorOptions.size(); i++) {
            colorOptions.get(i).findElement(By.xpath("./div")).click(); // Click on each color option
            WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='add-to-cart']"));

            if (addToCartButton.getAttribute("disabled") != null) {
                // The button is disabled
                ActionsBot.elementDisplayed(driver, OUT_OF_STOCK_LABEL);
                System.out.println("The 'Out Of Stock' button is enabled.");
            } else {
                // The button is enabled
                ActionsBot.elementNotDisplayed(driver, OUT_OF_STOCK_LABEL);
                System.out.println("The 'Add to Cart' button is enabled.");
            }
        }

        // If loop completes successfully for all colors, return true
        return true;
    }

}
