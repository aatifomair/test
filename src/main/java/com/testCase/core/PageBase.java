package com.testCase.core;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	public static String winHandle;
	public static WebDriver driver;
	public static WebDriverWait wait;
	Properties prop = new Properties();
	public String baseUrl;
	private final static Logger LOGGER = Logger.getLogger(PageBase.class);
	
	public void acceptAlertPopup() throws InterruptedException {
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		Thread.sleep(5000);
		LOGGER.info("Clicking on Ok button in popup");
	}
	
	
}
