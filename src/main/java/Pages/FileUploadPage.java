package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadPage {

    private WebDriver driver;

    // Locators using By
    private By fileInput = By.id("file-upload"); // Locator for the file input element
    private By uploadButton = By.id("file-submit"); // Locator for the upload button
    private By uploadedFiles = By.id("uploaded-files"); // Locator for the uploaded file name

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadFile(String filePath) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized!");
        }
        WebElement fileInputElement = driver.findElement(fileInput);
        fileInputElement.sendKeys(filePath);
    }
    // Method to upload a file using the Robot class
    public void uploadFileWithRobot(String filePath) throws AWTException {
        WebElement fileInputElement = driver.findElement(fileInput);
        fileInputElement.click(); // Click the file input element to trigger the file dialog

        // Use Robot class to handle the file dialog
        Robot robot = new Robot();

        // Copy the file path to the clipboard
        StringSelection stringSelection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        // Paste the file path and press Enter
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    // Method to click the upload button
    public void clickUploadButton() {
        driver.findElement(uploadButton).click();
    }

    // Method to get the uploaded file name
    public String getUploadedFileName() {
        return driver.findElement(uploadedFiles).getText();
    }
}