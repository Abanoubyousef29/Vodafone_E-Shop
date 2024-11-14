package Listeners;

import Recording.MyScreenRecorder;
import drivers.DriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {


    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        // Retrieve the enableRecording parameter value
        String enableRecordingParam = result.getTestContext().getCurrentXmlTest().getParameter("enableRecording");
        boolean enableRecording = enableRecordingParam == null || Boolean.parseBoolean(enableRecordingParam);

        if (retryCount < maxRetryCount) {
            retryCount++;

            // Close the WebDriver instance before retrying
            WebDriver driver = DriverHolder.getDriver();
            if (driver != null) {
                driver.quit();  // This closes the browser
                DriverHolder.setDriver(null);  // Ensure the driver instance is cleared
            }

            // Start recording at the beginning of the retry attempt if recording is enabled
            if ( (enableRecording ) && ( retryCount == maxRetryCount) ) {
                try {
                    MyScreenRecorder.startRecording(result.getMethod().getMethodName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // Return true to indicate the retry should happen
            return true;
        } else {
            // Stop recording after all retries are exhausted if recording is enabled
            if ( (enableRecording ) && ( retryCount == maxRetryCount) ) {
                try {
                    MyScreenRecorder.stopRecording();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            // Return false to indicate no more retries
            return false;
        }
    }
}