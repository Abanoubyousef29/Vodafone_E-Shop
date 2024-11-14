import drivers.DriverFactory;
import drivers.DriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import Listeners.Listener;

import java.time.Duration;
import java.util.NoSuchElementException;

import static Util.Utility.clearDirectory;

@Listeners(Listener.class)
public class TestBase {

    WebDriver driver;
    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/src/test/resources/Screenshots/"; // Update this path
    private static final String RECORDSHOT_DIR = System.getProperty("user.dir") + "/recordings/"; // Update this path

    private static boolean isCleared = false;

    @BeforeSuite
    public void clearScreenshotDirectory(){
        if (!isCleared) {
            clearDirectory(SCREENSHOT_DIR);
            clearDirectory(RECORDSHOT_DIR);
            isCleared = true;
        }
    }

    @Parameters("browserName")
    @BeforeMethod
    public void openDriver(@Optional("defaultBrowser") String browserName) throws Exception {
        // Initialize WebDriver if it's not already set
        driver = DriverHolder.getDriver();
        if (driver == null) {
            driver = DriverFactory.getNewInstance(browserName);
            DriverHolder.setDriver(driver);
        }
    }

    @BeforeMethod(dependsOnMethods = "openDriver")
    public void openHomePage() throws Exception {
        if (driver != null) {
            driver.get("https://eshop.vodafone.com.eg/en/");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Wait for the cookie banner to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("onetrust-banner-sdk")));

        // Close popup if it appears
        closePopupIfPresent();

        // Accept cookies by clicking the "Accept All Cookies" button
        WebElement rejectCookiesButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        if (rejectCookiesButton.isDisplayed()) {
            rejectCookiesButton.click();
        }

        // Close popup if it appears
        closePopupIfPresent();
    }

    @AfterMethod
    public void clearSession() throws Exception {
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
                ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
                ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
            } catch (org.openqa.selenium.NoSuchSessionException e) {
                // Log the exception or handle it
                System.out.println("Session already closed. Skipping session cleanup.");
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            try {
                driver.quit();  // Close the browser
            } catch (org.openqa.selenium.NoSuchSessionException e) {
                // Log the exception or handle it
                System.out.println("Session already closed. Skipping driver quit.");
            } finally {
                DriverHolder.removeDriver();  // Clear the driver instance in any case
            }
        }
    }

    public void closePopupIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By closeButtonLocator = By.cssSelector(".close-modal-desktop, .close-modal");

        try {
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(closeButtonLocator));
            closeButton.click();
            System.out.println("Popup closed successfully.");
        } catch (NoSuchElementException e) {
            System.out.println("Popup not present.");
        } catch (Exception e) {
            System.out.println("Error while closing popup: " + e.getMessage());
        }
    }
}
