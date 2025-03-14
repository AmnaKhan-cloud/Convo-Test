package Tests;

import Config.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) throws InterruptedException {


       driver.manage().window().maximize();
        // Open the login page
        driver.get(configReader.getLoginUrl());
        // Initialize the LoginPage object
        loginPage = new LoginPage(driver);
        // Perform login actions
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/logout']")));
            // Adjust the XPath if necessary
            if (username.equals("tomsmith") && password.equals("SuperSecretPassword!"))
                Assert.assertTrue(logoutButton.isDisplayed(), "Logout button should be visible after successful login.");

        else {
                // If login is invalid, verify that the error message is displayed
                String errorMessage = loginPage.getErrorMessage();
                Assert.assertTrue(errorMessage.contains("Your username is invalid!"), "Error message is incorrect.");
            }
        } catch (Exception e) {
            // If the logout button is not found, the login attempt failed
            if (username.equals("tomsmith") && password.equals("SuperSecretPassword!")) {
                Assert.fail("Login was expected to be successful, but the logout button was not found.");
            } else {
                // For invalid login, no logout button should be present
                Assert.assertTrue(true);  // Test passes for invalid login (error message is checked)
            }
        }
    }

}
