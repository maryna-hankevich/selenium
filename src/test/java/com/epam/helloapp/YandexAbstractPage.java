package com.epam.helloapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class YandexAbstractPage {

	protected WebDriver driver;

	public YandexAbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
//	public WebDriver getDriver() {
//		return this.driver;
//	}
//
//	public boolean isElementPresent(By locator) {
//		return driver.findElements(locator).size() > 0;
//	}

}
