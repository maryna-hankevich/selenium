package com.epam.helloapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MailBoxGeneralPage extends YandexAbstractPage {

	public MailBoxGeneralPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (xpath = "//a[@title='Написать (w, c)']")
	WebElement newEmailButton;

	public boolean isLoginCompleted() {
		return newEmailButton.isDisplayed();
	}

	public void writeNewEmail() {
		// Find the "button" input element by link text
		newEmailButton.click();
	}
}
