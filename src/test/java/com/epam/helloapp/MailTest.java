package com.epam.helloapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailTest {

    private static final String SIGN_IN_INPUT = "//button[@type='submit']";
    private static final String INPUT_LOGIN = "//input[@name='login']";
    private static final String INPUT_PASSWORD = "//input[@name='passwd']";
    private static final String INPUT_TO = "//div[@name='to']";
    private static final String INPUT_SUBJECT = "//input[@name='subj-03cf7d863bc585ba11298a39a90fc521b45fb77d']";
    private static final String INPUT_BODY = "//div[@role='textbox']";
    private static final String CROSS_BUTTON = "//div[@title='Закрыть']";
    private static final String SAVE_BUTTON = "//button[@data-action='save']";
    private static final String GO_TO_SIGN_IN_TEXT = "Войти в почту";
    private static final String PASSWORD = "Password1";
    private static final String LOGIN = "marina.hankevich";
    private static final String START_URL = "https://yandex.com/";
    private static final String NEW_MAIL_LINK = "//a[@title='Написать (w, c)']";
    private static final String DRAFT_LINK = "//a[@data-fid='6']";
    private static final String SENT_LINK = "//a[@data-fid='4']";
    private static final String LOGOUT_LINK = "//div[@class='mail-User-Name']";
    private static final String LOGOUT2_LINK = "//a[@value='Выйти из сервисов Яндекса']";
    private static final String DRAFT_ITEM = "//span[@title='body text']";
    private static final String DRAFT_SEND = "//button[@type='submit']";

    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        // initialize WebDriver for Chrome. Please mind webdriver, chromedriver
        // version and chrome browser versions.
        // works for webdriver v3.14, chromedriver v2.44, chrome browser
        // v70


        //Draft version for training goals, please avoid local paths in properties, setup repo environmental paths
        //or internal eclipse paths
        System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeClass(dependsOnMethods = "startBrowser", description = "Add implicite wait and maximize window")
    public void addImplicitly() {
        // setting standard timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // navigating to test url
        driver.get(START_URL);
        // Maximize browser window
        driver.manage().window().maximize();
    }

    @Test(description = "Login to Yandex Mail account")
    public void loginToYandex() {
        // Login via user-defined method
        doLogin(LOGIN, PASSWORD);
        doSaveMailAsDraft("marina.hankevich@yandex.by","hello","body text");
        doOpenDraftsAndSend();
    }


    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        // Custom implementation for is ElementPresent
        return !driver.findElements(by).isEmpty();
    }

    private void doLogin(String login, String password) {

        // Find the "button" input element by link text
        driver.findElement(By.linkText(GO_TO_SIGN_IN_TEXT)).click();

        // Find the text input element by "id" attribute with a certain value
        // and type the user name there
        WebElement loginInput = driver.findElement(By.xpath(INPUT_LOGIN));
        loginInput.clear();
        loginInput.sendKeys(login);

        // Find the text input element by xpath expression and type the password
        // there
        WebElement passwordInput = driver.findElement(By.xpath(INPUT_PASSWORD));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        // Now submit the form. WebDriver will find the form for us from the
        // element
        driver.findElement(By.xpath(SIGN_IN_INPUT)).click();
    }

    private void doSaveMailAsDraft(String toEmail, String subject, String body) {

        // Find the "button" input element by link text
        driver.findElement(By.xpath(NEW_MAIL_LINK)).click();

        // Find the text input element by "id" attribute with a certain value
        // and type the user name there
        WebElement toInput = driver.findElement(By.xpath(INPUT_TO));
        toInput.clear();
        toInput.sendKeys(toEmail);

        WebElement subjectInput = driver.findElement(By.xpath(INPUT_SUBJECT));
        subjectInput.clear();
        subjectInput.sendKeys(subject);

        WebElement bodyInput = driver.findElement(By.xpath(INPUT_BODY));
        bodyInput.clear();
        bodyInput.sendKeys(body);

        driver.findElement(By.xpath(CROSS_BUTTON)).click();
        driver.findElement(By.xpath(SAVE_BUTTON)).click();

    }

    private void doOpenDraftsAndSend() {

        // Find the "button" input element by link text
        driver.findElement(By.xpath(DRAFT_LINK)).click();
        driver.findElement(By.xpath(DRAFT_ITEM)).click();
        driver.findElement(By.xpath(DRAFT_SEND)).click();
        driver.findElement(By.xpath(DRAFT_LINK)).click();

        Assert.assertTrue(isElementPresent(By.xpath(DRAFT_ITEM)), "Looks like draft present");
        driver.findElement(By.xpath(SENT_LINK)).click();
        Assert.assertTrue(isElementPresent(By.xpath(DRAFT_ITEM)), "Looks like sent element not present");
        driver.findElement(By.xpath(LOGOUT_LINK)).click();
        driver.findElement(By.xpath(LOGOUT2_LINK)).click();

    }


}