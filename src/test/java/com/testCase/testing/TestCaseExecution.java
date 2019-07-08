package com.testCase.testing;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.testCase.core.TestBase;
import com.testCase.core.TestUtil;
import com.testCase.pageObjects.HomePage;
import com.testCase.pageObjects.LoginPage;
import com.testCase.pageObjects.MQDashBoardPage;
import com.testCase.pageObjects.QueueCreationsPage;

public class TestCaseExecution extends TestBase {

	List<String> list;

	@Test(priority = 1, alwaysRun = true)
	public void LoginTest() throws InterruptedException, IOException {

		String userName = TestUtil.getUserName();
		String password = TestUtil.getPassword();

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		HomePage homePage = loginPage.navigateToHomePage(userName, password);
		homePage = PageFactory.initElements(driver, HomePage.class);
		S_ASSERT.assertTrue(homePage.verifyHomePageTitle(), "The page is not navigated to Portal home page.");

	}

	@Test(priority = 2)
	public void navigateToCreateQueueTest() throws InterruptedException, IOException {

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);

		QueueCreationsPage queueCreationsPage = homePage.navigateToQueueCreationPage();
		queueCreationsPage = PageFactory.initElements(driver, QueueCreationsPage.class);
		S_ASSERT.assertTrue(queueCreationsPage.verifyQueueCreationPageTitle(),
				"Unable to navigate to queue creation page.");
	}

	@Test(priority = 3)
	public void createQueueTest() throws InterruptedException, IOException {

		QueueCreationsPage queueCreationsPage = PageFactory.initElements(driver, QueueCreationsPage.class);
		S_ASSERT.assertTrue(queueCreationsPage.createQueue(), "Unable to Create Queue.");
	}

	@Test(priority = 4)
	public void navigateToDashboardTest() throws InterruptedException, IOException {

		QueueCreationsPage queueCreationsPage = PageFactory.initElements(driver, QueueCreationsPage.class);

		HomePage homePage = queueCreationsPage.navigateToHomePage();
		homePage = PageFactory.initElements(driver, HomePage.class);

		MQDashBoardPage mQDashBoardPage = homePage.navigateToMQDashBoardPage();
		mQDashBoardPage = PageFactory.initElements(driver, MQDashBoardPage.class);
		S_ASSERT.assertTrue(mQDashBoardPage.verifyMQDashBoardPageTitle(),
				"The page is not navigated to MQ Dashboard home page.");
	}

	@Test(priority = 5)
	public void MqDashBoardTest() throws InterruptedException, IOException {

		MQDashBoardPage mQDashBoardPage = PageFactory.initElements(driver, MQDashBoardPage.class);
		S_ASSERT.assertTrue(mQDashBoardPage.checkMQDashBoardPage(), "Unable to Check MQ DASHBOARD");
	}

}
