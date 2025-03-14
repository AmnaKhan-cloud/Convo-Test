package Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener{
    WebDriver driver;


        @Override
        public void onTestFailure(ITestResult result) {
            // Capture screenshot on test failure
            captureScreenshot(result);
        }

        public void captureScreenshot(ITestResult result) {
            // Create a timestamped screenshot file name
            String screenshotName = result.getMethod().getMethodName() + "_failure_" + System.currentTimeMillis() + ".png";

            try {
                // Take the screenshot and save it to a file
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destinationFile = new File("screenshots/" + screenshotName);
                FileUtils.copyFile(screenshot, destinationFile);

                System.out.println("Screenshot saved: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }

        // Other ITestListener methods can be implemented as needed
        @Override
        public void onTestStart(ITestResult result) {}

        @Override
        public void onTestSuccess(ITestResult result) {}

        @Override
        public void onTestSkipped(ITestResult result) {}

        @Override
        public void onStart(ITestContext context) {}

        @Override
        public void onFinish(ITestContext context) {}
    }


