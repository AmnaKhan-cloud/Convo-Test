package Config;

import Pages.LoginPage;
import Pages.TablePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest{

    protected static WebDriver driver;
    protected LoginPage loginPage;
    protected String baseUrl;
    protected String browserType;
    protected int implicitWait;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    TablePage tablePage;
    Properties properties = new Properties();
    public ConfigReader configReader;


    @BeforeTest
    public void setUp() throws InterruptedException {
        // Load properties from config file

        driver = BaseTest.getDriver();
        configReader = ConfigReader.getInstance();


        // Fetch the URL

        String browserType = configReader.browser();
        System.out.println("Selected Browser: " + browserType);
        // browserType = properties.getProperty("browser"); // Fetch the browser type
        // Initialize the WebDriver based on the browser type specified in the config
        if (browserType.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        String loginUrl = configReader.getLoginUrl();
    }

    public static WebDriver getDriver() {
        return driver;
    }


        // Initialize Page
    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

        @DataProvider(name="loginData")
       public Object[][] loginData() {
            String[] usernames = {
                    "tomsmith",             // valid username
                    "invalidUser",          // invalid username
                    " tomsmith",            // valid username with leading space
                    "tomsmith ",            // valid username with trailing space
                    " tomsmith ",           // valid username with leading and trailing space
                    " tom smith ",          // valid username with spaces in between
                    ""                      // empty username
            };


            String[] passwords = {
                    "SuperSecretPassword!", // valid password
                    "invalidPassword",      // invalid password
                    " SuperSecretPassword!",// valid password with leading space
                    "SuperSecretPassword! ",// valid password with trailing space
                    " SuperSecret Password! ", // valid password with spaces in between
                    ""};

                    Object[][] data = new Object[usernames.length * passwords.length][2]; // Array for combinations
            int index = 0;
            for (String username : usernames) {
                for (String password : passwords) {
                    data[index][0] = username;  // username
                    data[index][1] = password;  // password
                    index++;
                }
            }
            return data;
        }



}
