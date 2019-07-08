package com.testCase.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtil {

	private final static Logger LOGGER = Logger.getLogger(TestUtil.class);

	public static String getOsVersion() throws IOException {
		return System.getProperty("os.name");
	}

	public static Properties getPropertyFile() throws IOException {
		Properties prop = new Properties();
		String propertyFileLocation = (System.getProperty("user.dir") + "/src/test/resources/testcase1.properties");

		if (!getOsVersion().startsWith("Windows")) {
			propertyFileLocation = (System.getProperty("user.dir") + "/src/test/resources/testcase1.properties");
		}

		FileInputStream input = new FileInputStream(propertyFileLocation);
		prop.load(input);
		return prop;

	}

	public static String getUserName() throws IOException {
		return getPropertyFile().getProperty("userName");
	}

	public static String getPassword() throws IOException {
		return getPropertyFile().getProperty("password");
	}

	public static String getPortalTitle() throws IOException {
		return getPropertyFile().getProperty("portalTitle");
	}

	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			/*
			 * File screenShot = new File(System.getProperty("user.dir") +
			 * "\\Screenshots\\"+screenshotName+".png");
			 * if(!getOsVersion().startsWith("Windows")){ screenShot = new
			 * File(System.getProperty("user.dir") + "/Screenshots/"+screenshotName+".png");
			 * }
			 */
			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
			LOGGER.info("Screenshot has been captured");
		} catch (Exception e) {
			LOGGER.info("Exception while taking screenshot" + e.getMessage());
		}
	}
}
