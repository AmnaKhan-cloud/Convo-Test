package Pages;

import Util.ElementUtil;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

 public class TablePage {

        private WebDriver driver;

        // Locators for the table (assuming the table has an id 'table1')
        By table = By.id("table1");

     public TablePage(WebDriver driver) {
         this.driver = driver;
     }



        // Method to get all rows from the table (excluding the header)
        public List<WebElement> getRows() {
            WebElement tableElement = driver.findElement(table);
            return tableElement.findElements(By.tagName("tr"));
        }

        // Method to get all cells from a specific row
        public List<WebElement> getCellsFromRow(WebElement row) {
            return row.findElements(By.tagName("td"));
        }

        // Method to get cell data (e.g., extract the text from the cell)
        public String getCellData(WebElement cell) {
            return cell.getText();
        }

        // Optional: Method to check if a specific data is in the table (e.g., check if a name exists)
        public boolean isDataPresent(String data) {
            List<WebElement> rows = getRows();
            for (WebElement row : rows) {
                List<WebElement> cells = getCellsFromRow(row);
                for (WebElement cell : cells) {
                    if (cell.getText().contains(data)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }



