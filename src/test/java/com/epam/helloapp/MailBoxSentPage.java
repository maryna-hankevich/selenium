package com.epam.helloapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MailBoxSentPage extends YandexAbstractPage {

	public MailBoxSentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath = "//*[text()='marina.hankevich']")
	WebElement sentEmail;

	@FindBy (xpath = "//a[@href='#sent']")
	WebElement sentLink;

	public boolean isSentEmailExists() {
		return sentEmail.isDisplayed();
	}


	public void openSentPage() {
		sentLink.click();
	}

	public void logout() {
		driver.get("https://passport.yandex.by/passport?mode=embeddedauth&action=logout&uid=778705500&yu=3150848491544351516&retpath=https%3A%2F%2Fyandex.by");
	}
}
