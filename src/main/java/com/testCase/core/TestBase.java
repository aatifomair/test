package com.testCase.core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

public class TestBase extends PageBase {

	private final static Logger LOGGER = Logger.getLogger(TestBase.class);

	public SoftAssert S_ASSERT = new SoftAssert();
	public Assertion H_ASSERT = new Assertion();

	@BeforeClass
	public void setUp() {
		try {
			localBrowserSelection(TestUtil.getPropertyFile().getProperty("iebrowser"));
			deleteBrowserCookies();
			driver.manage().window().maximize();
			if (System.getProperty("loginURL") != null && System.getProperty("loginURL") != "") {
				setURL(System.getProperty("loginURL"));
			} else {
				setURL(TestUtil.getPropertyFile().getProperty("loginURL"));
			}
			Thread.sleep(3000);
			winHandle = driver.getWindowHandle();
			wait = new WebDriverWait(driver, 30);
			Thread.sleep(3000);
			LOGGER.info("No action will be performed for 10 seconds...");

		} catch (Exception e) {
			LOGGER.info("Problem occurs while trying to open the Portal URL");
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {

		if (driver != null) {
			driver.close();
		}
	}

	public void localBrowserSelection(String browser) {

		if (System.getProperty("os.name").startsWith("Windows")) {
			LOGGER.info("Operating System version:" + System.getProperty("os.name"));
			if (browser.equals("firefox")) {
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				ieCapabilities.setCapability("ensureCleanSession", true);
				driver = new FirefoxDriver(ieCapabilities);
				LOGGER.debug("Firefox Browser has been opened");
			} else if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				ieCapabilities.setCapability("ensureCleanSession", true);
				driver = new ChromeDriver(ieCapabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				LOGGER.info("Chrome Explorer has been opened...");
			} else if (browser.equals("ie")) {
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				ieCapabilities.setCapability("ensureCleanSession", true);
				driver = new InternetExplorerDriver(ieCapabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				LOGGER.info("Internet Explorer has been opened...");
			}
		} else {
			FirefoxBinary binary = new FirefoxBinary(new File("/usr/lib64/firefox/firefox-bin"));
			FirefoxProfile profile = new FirefoxProfile();
			binary.setEnvironmentProperty("DISPLAY", System.getProperty("lmportal.xvfb.id", ":99"));
			driver = new FirefoxDriver(binary, profile);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public void setURL(String url) throws InterruptedException {

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void deleteBrowserCookies() throws InterruptedException {
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
		LOGGER.info("Delected all cookies...");
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenShot(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			LOGGER.info("Test failure for : " + result.getName() + " Taking screenshot.");
			TestUtil.captureScreenshot(driver, result.getName());
		}
		if (ITestResult.SUCCESS == result.getStatus()) {
			TestUtil.captureScreenshot(driver, result.getName());
		}

	}

}
