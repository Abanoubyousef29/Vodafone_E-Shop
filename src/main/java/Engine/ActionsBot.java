package Engine;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class ActionsBot {

    private static Wait<WebDriver> wait;

    public static void click(WebDriver driver, By locator) {

        fluentWait(driver);
        ActionsBot.wait.until(d -> {
            driver.findElement(locator).click();
            return true;
        });
    }

    public static void clickOnItemInList(WebDriver driver, By locator, int Index) {

        fluentWait(driver);
        ActionsBot.wait.until(d -> {
            driver.findElements(locator).get(Index).click();
            return true;
        });
    }

    public static void clickOnChildItemInList(WebDriver driver, By locator, String text) {

        fluentWait(driver);
        ActionsBot.wait.until(d -> {
            driver.findElement(locator).findElement(By.xpath("//*[text()='"+text+"']")).click();
            return true;
        });
    }



    public static void sendKeysToElement(WebDriver driver, By locator, String text) {
        fluentWait(driver);
        ActionsBot.wait.until(d -> {
            driver.findElement(locator).sendKeys(text);
            return true;
        });
    }

    public static String getTextFromElementBy(WebDriver driver, By locator) {
        fluentWait(driver);
        return ActionsBot.wait.until(d -> driver.findElement(locator).getText());
    }

    public static int getSizeOfElementList(WebDriver driver, By locator) {
        fluentWait(driver);
        return ActionsBot.wait.until(d -> driver.findElements(locator).size());
    }

    public static boolean elementDisplayed(WebDriver driver, By locator) {
        fluentWait(driver);
        return ActionsBot.wait.until(d -> {
            List<WebElement> elements = driver.findElements(locator);
            return !elements.isEmpty() && elements.get(0).isDisplayed();
        });
    }

    public static boolean elementNotDisplayed(WebDriver driver, By locator) {
        fluentWait(driver);
        return ActionsBot.wait.until(d -> {
            List<WebElement> elements = driver.findElements(locator);
            return elements.isEmpty() || !elements.get(0).isDisplayed();
        });
    }

    private static void fluentWait(WebDriver driver) {
        ActionsBot.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NotFoundException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(AssertionError.class)
                .ignoring(StaleElementReferenceException.class);
    }

}
