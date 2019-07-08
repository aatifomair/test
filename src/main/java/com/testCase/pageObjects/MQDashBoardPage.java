package com.testCase.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testCase.core.PageBase;

public class MQDashBoardPage extends PageBase {

	//
	private final static Logger LOGGER = Logger.getLogger(HomePage.class);
	@FindBy(xpath = ("//*[contains(text(),'Queue Manager')]"))
	private WebElement title;

	@FindBy(xpath = ("//*[contains(text(),'WebSphere MQ queue created')]"))
	private WebElement response;

	public boolean verifyMQDashBoardPageTitle() throws InterruptedException {
		boolean flag = false;

		wait.until(ExpectedConditions.visibilityOf(title));
		LOGGER.info(title.isDisplayed());
		if (title.isDisplayed()) {
			flag = true;
			LOGGER.info("Successfully navigated to MQ Dashboard page.");
		}
		return flag;
	}

	public boolean checkMQDashBoardPage() throws InterruptedException {

		boolean flag = false;
		WebElement detail = driver.findElement(By.cssSelector("a[href='#?queue_manager_name=PMYSUPGUAT']"));
		wait.until(ExpectedConditions.visibilityOf(detail));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", detail);
		Thread.sleep(5000);

		return true;
	}

	public HomePage navigateToHomePage() throws InterruptedException {

		driver.switchTo().parentFrame();

		return new HomePage();
	}

}
