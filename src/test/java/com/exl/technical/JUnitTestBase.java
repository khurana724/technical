package com.exl.technical;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Capabilities;

/**
 * Base class for all the JUnit-based test classes
 */
public class JUnitTestBase {

	protected static URL gridHubUrl;
	protected static String baseUrl;
	protected static Capabilities capabilities;

	protected WebDriver driver;
	String driverPath = "/Trading/workspace/technical/src/test/resources/chromedriver.exe";

	@ClassRule
	public static ExternalResource webDriverProperties = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			SuiteConfiguration config = new SuiteConfiguration();
			baseUrl = config.getProperty("site.url");
			if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
				gridHubUrl = new URL(config.getProperty("grid.url"));
			}
			capabilities = config.getCapabilities();
		};
	};

	@Rule
	public ExternalResource webDriver = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			// driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
			System.setProperty("webdriver.chrome.driver", driverPath);
			// Create a Chrome driver. All test classes use this.
			driver = new ChromeDriver();
			// Maximize Window
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		};

		@Override
		protected void after() {
			driver.quit();
		};
	};
}
