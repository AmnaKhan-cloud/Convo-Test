package Tests;

import Config.BaseTest;
import Config.ConfigReader;
import Pages.AlertPage;
import Pages.TablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AlertTest extends BaseTest
{

    WebDriver driver;
    AlertPage alertPage;
    ConfigReader configReader;

    @BeforeTest
    public void testAlert() throws InterruptedException {
        // Initialize the AlertPage object
      driver = BaseTest.getDriver();
        configReader = new ConfigReader();
        driver.manage().window().maximize(); // Maximize the browser window

// Open the alert page using the URL from config.properties
        driver.get(configReader.getAlertUrl());;

       alertPage = new AlertPage(driver);
    }



        @Test
        public void testSimpleAlert() {
            // Click the JS Alert button
            alertPage.clickJsAlertButton();

            // Handle the simple alert
            alertPage.handleSimpleAlert();

            // Verify the result text
            String resultText = alertPage.getResultText();
            Assert.assertEquals(resultText, "You successfully clicked an alert", "Simple alert test failed.");
        }

        @Test
        public void testConfirmationAlertAccept() {
            // Click the JS Confirm button
            alertPage.clickJsConfirmButton();

            // Handle the confirmation alert (accept)
            alertPage.handleConfirmationAlert(true);

            // Verify the result text
            String resultText = alertPage.getResultText();
            Assert.assertEquals(resultText, "You clicked: Ok", "Confirmation alert (accept) test failed.");
        }

        @Test
        public void testConfirmationAlertDismiss() {
            // Click the JS Confirm button
            alertPage.clickJsConfirmButton();

            // Handle the confirmation alert (dismiss)
            alertPage.handleConfirmationAlert(false);

            // Verify the result text
            String resultText = alertPage.getResultText();
            Assert.assertEquals(resultText, "You clicked: Cancel", "Confirmation alert (dismiss) test failed.");
        }

        @Test
        public void testPromptAlertAccept() {
            // Click the JS Prompt button
            alertPage.clickJsPromptButton();

            // Handle the prompt alert (enter text and accept)
            alertPage.handlePromptAlert("Hello, Convo!", true);

            // Verify the result text
            String resultText = alertPage.getResultText();
            Assert.assertEquals(resultText, "You entered: Convo, World!", "Prompt alert (accept) test failed.");
        }

        @Test
        public void testPromptAlertDismiss() {
            // Click the JS Prompt button
            alertPage.clickJsPromptButton();

            // Handle the prompt alert (dismiss without entering text)
            alertPage.handlePromptAlert(null, false);

            // Verify the result text
            String resultText = alertPage.getResultText();
            Assert.assertEquals(resultText, "You entered: null", "Prompt alert (dismiss) test failed.");
        }
    }
