package com.epam.helloapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends YandexAbstractPage {
;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy (linkText = "Войти в почту")
	WebElement goToSignInButton;


	@FindBy (name = "login")
	WebElement loginInput;

	@FindBy (name = "passwd")
	WebElement passwordInput;
	
	@FindBy (xpath = "//button[@type='submit']")
	WebElement submitBtn;

	private String userName = "marina.hankevich";

	private String password =  "Password1";

	private String pageTitleMailBox = "Яндекс.Паспорт";
	
	private String pageTitle = "Яндекс";

	private String pageTitleSignIn = "";

	public MailBoxGeneralPage loginToMailbox() {
		loginInput.sendKeys(userName);
		passwordInput.sendKeys(password);
		submitBtn.click();
		return new MailBoxGeneralPage (driver);
	}
	
	public LoginPage openPage() {
//		driver.get("https://passport.yandex.by/");
		driver.get("https://yandex.com/");
		new WebDriverWait (driver, 10).until(ExpectedConditions.titleContains(pageTitle));
		goToSignInButton.click();
		new WebDriverWait (driver, 10).until(ExpectedConditions.titleContains(pageTitleSignIn));
		return this;
	}
	
}
