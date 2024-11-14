package Listeners;


import drivers.DriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static Recording.ScreenShot.captureScreenshot;


public class Listener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Ensure the RetryAnalyzer is set for each test case
        IRetryAnalyzer retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
        if (retryAnalyzer == null) {
            result.getMethod().setRetryAnalyzerClass(RetryAnalyzer.class);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverHolder.getDriver();
        // Logic to handle failure (e.g., take screenshots)
        captureScreenshot(driver, result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }


    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }


}
