package com.testCase.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.testCase.core.PageBase;

public class QueueCreationsPage extends PageBase {

	private final static Logger LOGGER = Logger.getLogger(HomePage.class);
	@FindBy(xpath = ("//*[contains(text(),'Queue Creation')]"))
	private WebElement title;

	@FindBy(xpath = ("//*[contains(text(),'WebSphere MQ queue created')]"))
	private WebElement response;

	public boolean verifyQueueCreationPageTitle() throws InterruptedException {
		boolean flag = false;

		wait.until(ExpectedConditions.visibilityOf(title));
		LOGGER.info(title.isDisplayed());
		if (title.isDisplayed()) {
			flag = true;
			LOGGER.info("Successfully navigated to Queue Creation page.");
		}
		return flag;
	}

	public boolean createQueue() throws InterruptedException {

		boolean flag = false;
		WebElement queueManagerName = driver.findElement(By.cssSelector("select[id='queue_manager_name']"));

		Select queueManager = new Select(queueManagerName);
		wait.until(ExpectedConditions.visibilityOf(queueManagerName));
		queueManager.selectByVisibleText("PMYSUPGUAT");
		Thread.sleep(2000);

		WebElement queueName = driver.findElement(By.cssSelector("input[id='queue_name']"));
		wait.until(ExpectedConditions.visibilityOf(queueName));
		queueName.sendKeys("Test Queue 1");
		Thread.sleep(2000);

		WebElement submit = driver.findElement(By.cssSelector("input[id='submit']"));
		wait.until(ExpectedConditions.visibilityOf(submit));
		// submit.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
		Thread.sleep(5000);

		LOGGER.info("Creating Queue");
		wait.until(ExpectedConditions.visibilityOf(response));

		if (response.isDisplayed()) {
			flag = true;
			LOGGER.info("Queue created Successfully.");
		}
		// driver.switchTo().frame(frame);

		return flag;
	}

	public HomePage navigateToHomePage() throws InterruptedException {

		driver.switchTo().parentFrame();

		return new HomePage();
	}

}
