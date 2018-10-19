package Steps;

import Pages.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import WebDriver.DriverSingleton;

import java.io.File;
import java.io.IOException;


public class Steps {

    private WebDriver driver;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public void initBrowser()
    {
        driver = DriverSingleton.getDriver();
    }

    public void login(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn(String username)
    {
        LoginPage loginPage = new LoginPage(driver);
        String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
        return actualUsername.equals(username);
    }

    public void addMailToDrafts(String addresseeText, String subjectText, String bodyText)
    {
        MessagesPage messagesPage=new MessagesPage(driver);
        messagesPage.draftsAdd(addresseeText,subjectText, bodyText);
    }

    public boolean checkDrafts(String addressee, String subject, String body)
    {
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        return draftsPage.checkDrafts(addressee, subject, body);
    }

    public void sendMail()
    {
        MessagesPage messagesPage=new MessagesPage(driver);
        messagesPage.sendMail();
    }

    public boolean checkSentFolder(String addressee, String subject, String body)
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.openPage();
        return sentPage.checkSentFolder(addressee, subject, body);
    }

    public void logout()
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.logout();
    }

    public void closeBrowser()
    {
        DriverSingleton.closeDriver();
    }

    public void fillMailFields(String addresseeText, String subjectText, String bodyText)
    {
        MessagesPage messagesPage=new MessagesPage(driver);
        messagesPage.fillMailFields(addresseeText,subjectText, bodyText);
    }
    public void addMailToSpam()
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.addToSpam();
    }

    public boolean checkSpamFolder(String addressee, String subject, String body)
    {
        SpamPage spamPage= new SpamPage(driver);
        spamPage.openPage();
        return spamPage.checkSpamFolder(addressee, subject, body);
    }

    public void dragMailToTrash(String addressee, String subject, String body)
    {
        SentPage sentPage= new SentPage(driver);
        sentPage.openPage();
        sentPage.dragMailToTrash(addressee, subject, body);
    }

    public boolean checkTrashFolder(String addressee, String subject, String body)
    {
        TrashPage trashPage= new TrashPage(driver);
        trashPage.openPage();
        return trashPage.checkTrashFolder(addressee, subject, body);
    }
}
