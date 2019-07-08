package com.testCase.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testCase.core.PageBase;

public class HomePage extends PageBase {

	private final static Logger LOGGER = Logger.getLogger(HomePage.class);
	@FindBy(xpath = ("//*[contains(text(),'MIDDLEWARE COCKPIT')]"))
	private WebElement title;

	public boolean verifyHomePageTitle() throws InterruptedException {
		boolean flag = false;

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(title));
		LOGGER.info(title.isDisplayed());
		if (title.isDisplayed()) {
			flag = true;
			LOGGER.info("Successfully navigated to home page.");
		}
		return flag;
	}

	public MQDashBoardPage navigateToMQDashBoardPage() throws InterruptedException {
		WebElement infoLink = driver.findElement(By.cssSelector("a[href*='pageSubmenu']"));
		wait.until(ExpectedConditions.visibilityOf(infoLink));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", infoLink);

		WebElement sspMq = driver.findElement(By.cssSelector("a[href*='selfsubmenu4']"));
		wait.until(ExpectedConditions.visibilityOf(sspMq));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", sspMq);

		Thread.sleep(4000);

		driver.switchTo().frame("myFrame");

		LOGGER.info("Navigating to SSP Queue Creation Home Page");

		return new MQDashBoardPage();
	}

	public QueueCreationsPage navigateToQueueCreationPage() throws InterruptedException {
		WebElement sspLink = driver.findElement(By.cssSelector("a[href*='homeSubmenu']"));
		wait.until(ExpectedConditions.visibilityOf(sspLink));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", sspLink);

		WebElement sspMq = driver.findElement(By.cssSelector("a[href*='selfsubmenu']"));
		wait.until(ExpectedConditions.visibilityOf(sspMq));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", sspMq);

		WebElement queueCreations = driver.findElement(By.cssSelector("a[onclick*='ssp_queue_creation.html']"));
		wait.until(ExpectedConditions.visibilityOf(queueCreations));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", queueCreations);

		Thread.sleep(2000);

		driver.switchTo().frame("myFrame");
		
		LOGGER.info("Navigating to SSP Queue Creation Home Page");

		return new QueueCreationsPage();
	}

}
