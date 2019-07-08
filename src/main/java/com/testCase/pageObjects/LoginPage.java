package com.testCase.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testCase.core.PageBase;

public class LoginPage extends PageBase {
	@FindBy(id = "login")
	public WebElement user;

	@FindBy(id = "password")
	public WebElement pass;

	@FindBy(id = "Submit")
	WebElement login;

	public HomePage navigateToHomePage(String userName, String password) throws InterruptedException {

		user.sendKeys(userName);
		pass.sendKeys(password);
		login.click();
		return new HomePage();

	}
}
