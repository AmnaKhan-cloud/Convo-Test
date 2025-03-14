package Tests;

import Config.BaseTest;
import Pages.TablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.List;
import org.openqa.selenium.WebElement;
import Config.ConfigReader;

public class TableTest extends BaseTest {

    private WebDriver driver;
    private ConfigReader configReader;
    private TablePage tablePage;


    @BeforeTest
        public void testTable() throws InterruptedException {

        driver = BaseTest.getDriver();
        configReader = ConfigReader.getInstance();
        driver.manage().window().maximize();
        // Open the login page
        driver.get(configReader.getTableUrl());

        tablePage = new TablePage(driver);

            // Initialize the TablePage object

        }

        @Test(priority = 1)
        public void extractTableData() {
            // Extract rows from the table
            List<WebElement> rows = tablePage.getRows();
            Assert.assertTrue(rows.size() > 1, "The table has no data rows.");
            // Loop through rows (skipping the header row) and extract each cell's text
            for (int i = 1; i < rows.size(); i++) { // Skip the header row by starting from index 1
                WebElement row = rows.get(i);
                List<WebElement> cells = tablePage.getCellsFromRow(row);

                // Print cell data (you can store it or perform more actions if necessary)
                for (WebElement cell : cells) {
                    System.out.print(tablePage.getCellData(cell) + "\t"); // Print each cell's data separated by a tab
                }
                System.out.println(); // Move to the next row
            }
        }

        @Test(priority = 2)
        public void verifyDataPresence() {
            // Check if a specific data (e.g., "Jason") is present in the table
            String searchData = "Jason";
            boolean isFound = tablePage.isDataPresent(searchData);
            Assert.assertTrue(isFound, "The Jason value in  '" + searchData + "' is not found in the table.");
        }

    }

