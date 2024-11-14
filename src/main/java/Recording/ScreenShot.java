package Recording;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import static Util.Utility.timeRightNowInRightFormat;

public class ScreenShot {

    private static File getScreenshotFile(String screenshotName) {
        String timestamp = timeRightNowInRightFormat();
        String directory = System.getProperty("user.dir") + "/src/test/resources/Screenshots/";
        return new File(directory + screenshotName + timestamp + ".png");
    }

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), getScreenshotFile(screenshotName));
        } catch (WebDriverException | IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
