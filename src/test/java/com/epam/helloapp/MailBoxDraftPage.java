package com.epam.helloapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MailBoxDraftPage extends YandexAbstractPage {

	public MailBoxDraftPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath = "//*[text()='marina.hankevich']")
	WebElement draftEmail;

	@FindBy (xpath = "//a[@href='#draft']")
	WebElement draftLink;

	@FindBy (css = "span.mail-MessageSnippet-FromText")
	WebElement draftItem;

	@FindBy (css = "button.nb-button._nb-action-button")
	WebElement draftSendButton;



	public boolean isDraftExists() {
		return draftEmail.isDisplayed();
	}

	public void openDraft() {
		draftLink.click();
	}

	public void sendDraft() {
		draftItem.click();
		new WebDriverWait (driver, 10).until(ExpectedConditions.visibilityOf(draftSendButton));
		draftSendButton.click();
	}

}
