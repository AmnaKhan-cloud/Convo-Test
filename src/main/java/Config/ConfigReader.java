package Config;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

	public class ConfigReader {

		private Properties properties;
		private static ConfigReader configReader;


		public ConfigReader() {
			properties = new Properties();
			try (InputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
				// Load the properties file
				properties.load(inputStream);
			} catch (IOException e) {
				// Log the error or handle it in your preferred way
				System.err.println("Error loading config.properties file: " + e.getMessage());
				e.printStackTrace();
			}
		}

		public static ConfigReader getInstance() {
			if (configReader == null) {
				configReader = new ConfigReader();
			}
			return configReader;
		}

		public String getTableUrl() throws InterruptedException {
			//String tableUrl = properties.getProperty("table_url");

			return getProperty("table_url");
		}

		public String browser() {
			return getProperty("browser");
		}

		// Method to get the Login URL from config.properties
		public String getLoginUrl() {
			return getProperty("login_url");
		}

		// A helper method to fetch properties in a safer way
		private String getProperty(String key) {
			String value = properties.getProperty(key);
			if (value == null || value.isEmpty()) {
				System.err.println("Warning: Property '" + key + "' is missing or empty in config.properties.");
			}
			return value;
		}

		public String getAlertUrl() {
			return getProperty("alert_url");

		}

		public String getFileUrl() {
			return getProperty("file_url");
		}
	}
