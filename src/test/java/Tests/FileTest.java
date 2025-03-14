package Tests;

import Config.BaseTest;
import Config.ConfigReader;
import Pages.AlertPage;
import Pages.FileUploadPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;

public class FileTest extends BaseTest {

    FileUploadPage fileUploadPage;
    private ConfigReader configReader;
    WebDriver driver;


    @BeforeTest
    public void testFileTest() throws InterruptedException {
        // Initialize the FileUploadPage object


        driver = BaseTest.getDriver();
        configReader = ConfigReader.getInstance();
        driver.manage().window().maximize(); // Maximize the browser window

       driver.get(configReader.getFileUrl());


        // Initialize the FileUploadPage object
        fileUploadPage =new FileUploadPage(driver);

        // Navigate to the File Upload page

        }


    @Test
    public void testFileUploadWithRobot() throws Exception {

        // Provide the absolute path of the file to upload
        String filePath = "/src/main/resources/all.txt";


        // Upload the file using the Robot class
        fileUploadPage.uploadFileWithRobot(filePath);

        //Click the upload button
        fileUploadPage.clickUploadButton();

        // Verify the uploaded file name
        String uploadedFileName = fileUploadPage.getUploadedFileName();
        Assert.assertEquals(uploadedFileName, "file.txt", "File upload test failed.");
    }
}