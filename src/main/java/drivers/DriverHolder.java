package drivers;

import org.openqa.selenium.WebDriver;

public class DriverHolder {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverHolder.driver.set(driver);
    }

    public static void removeDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit(); // Properly close the WebDriver
            driver.remove(); // Remove the WebDriver instance from ThreadLocal
        }
    }
}
