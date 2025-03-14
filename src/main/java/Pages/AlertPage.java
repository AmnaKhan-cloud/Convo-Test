package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AlertPage {

        WebDriver driver;

        // Locators using By
        private By jsAlertButton = By.cssSelector("button[onclick='jsAlert()']");
        private By jsConfirmButton = By.cssSelector("button[onclick='jsConfirm()']");
        private By jsPromptButton = By.cssSelector("button[onclick='jsPrompt()']");
        private By resultText = By.id("result");

        public AlertPage(WebDriver driver) {
            this.driver = driver;
        }

        // Click the JS Alert button
        public void clickJsAlertButton() {
            driver.findElement(jsAlertButton).click();
        }

        // Click the JS Confirm button
        public void clickJsConfirmButton() {
            driver.findElement(jsConfirmButton).click();
        }

        // Click the JS Prompt button
        public void clickJsPromptButton() {
            driver.findElement(jsPromptButton).click();
        }

        // Get the result text after interacting with the alert
        public String getResultText() {
            return driver.findElement(resultText).getText();
        }

        // Handle simple alert
        public void handleSimpleAlert() {
            Alert alert = driver.switchTo().alert();
            alert.accept(); // Click OK
        }

        // Handle confirmation alert
        public void handleConfirmationAlert(boolean accept) {
            Alert alert = driver.switchTo().alert();
            if (accept) {
                alert.accept(); // Click OK
            } else {
                alert.dismiss(); // Click Cancel
            }
        }

        // Handle prompt alert
        public void handlePromptAlert(String inputText, boolean accept) {
            Alert alert = driver.switchTo().alert();
            if (inputText != null) {
                alert.sendKeys(inputText); // Enter text
            }
            if (accept) {
                alert.accept(); // Click OK
            } else {
                alert.dismiss(); // Click Cancel
            }
        }
    }

