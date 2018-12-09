package com.epam.helloapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailBoxNewEmailPage extends YandexAbstractPage {

	public MailBoxNewEmailPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath = "//div[@name='to']")
	WebElement toInput;

	@FindBy (name = "subj-03cf7d863bc585ba11298a39a90fc521b45fb77d")
	WebElement subjectInput;

	@FindBy (xpath = "//div[@role='textbox']")
	WebElement bodyInput;


	@FindBy (xpath = "//div[@title='Закрыть']")
	WebElement crossButton;

	@FindBy (xpath = "//button[@data-action='save']")
	WebElement saveButton;

	public boolean isNewEmailBoxDisplayed() {
		return toInput.isDisplayed();
	}

	public void saveEmailAsDraft(String toEmail, String subject, String body) {
		// Find the text input element by "id" attribute with a certain value
		// and type the user name there
		toInput.clear();
		toInput.sendKeys(toEmail);

		subjectInput.clear();
		subjectInput.sendKeys(subject);

		bodyInput.clear();
		bodyInput.sendKeys(body);

		crossButton.click();
		saveButton.click();
	}
}
