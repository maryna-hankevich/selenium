package yandex.test;


import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.epam.helloapp.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class YandexMailTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
//Path to Chromdriver.exe is specified in the Environment Variables
    public void start () {
        System.setProperty("webdriver.chrome.driver", "d:\\chromedriver.exe");
        driver = new ChromeDriver ();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait (driver, 10);
    }

    @Test
    public void YandexMailTest () {
        MailBoxGeneralPage mailBoxGeneralPage = new LoginPage(driver).openPage().loginToMailbox();
        boolean isLoginCompleted = mailBoxGeneralPage.isLoginCompleted();
        Assert.assertTrue(isLoginCompleted, "Login failed or mailbox general page has changed!");

        mailBoxGeneralPage.writeNewEmail();

        MailBoxNewEmailPage mailBoxNewEmailPage = new MailBoxNewEmailPage(driver);
        Assert.assertTrue(mailBoxNewEmailPage.isNewEmailBoxDisplayed(), "New email box is not displayed!");
        mailBoxNewEmailPage.saveEmailAsDraft("marina.hankevich@yandex.by","hello","body text");

        MailBoxDraftPage mailBoxDraftPage = new MailBoxDraftPage(driver);
        mailBoxDraftPage.openDraft();
        Assert.assertTrue(mailBoxDraftPage.isDraftExists(), "Can't find draft email");
        mailBoxDraftPage.sendDraft();

        MailBoxSentPage mailBoxSentPage = new MailBoxSentPage(driver);
        Assert.assertTrue(mailBoxSentPage.isSentEmailExists(), "Can't find sent email");
        mailBoxSentPage.openSentPage();

        mailBoxSentPage.logout();
 }


    @AfterClass
    private void stop() {
        // TODO Auto-generated method stub
        driver.quit();
        driver = null;

    }
}
