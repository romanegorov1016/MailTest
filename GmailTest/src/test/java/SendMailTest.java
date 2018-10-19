import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.Utils;
import Utils.Screenshoter;

public class SendMailTest {

    private Steps steps;
    private String subject = Utils.getRandomString(10);
    private String bodyText = Utils.getRandomString(30);

    private final String ADRESSEE = "romanegorov1016@gmail.com";
    private final String USER_EMAIL = "epamlabmailtest@mail.ru";
    private final String USERNAME = "epamlabmailtest";
    private final String PASSWORD = "epamlab2018";

    @BeforeClass
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void loginTest()
    {
        steps.login(USERNAME, PASSWORD);
        if(!steps.isLoggedIn(USER_EMAIL))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.isLoggedIn(USER_EMAIL));
    }

    @Test(dependsOnMethods = { "loginTest" })
    public void mailAddToDraftsTest()
    {
        steps.addMailToDrafts(ADRESSEE, subject, bodyText);
        if(!steps.checkDrafts(ADRESSEE, subject, bodyText))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.checkDrafts(ADRESSEE, subject, bodyText));//check that the e-mail is present in "drafts" folder
    }

    @Test(dependsOnMethods = { "mailAddToDraftsTest" })
    public void mailRemoveFromDraftsTest()
    {
        steps.sendMail();
        if(steps.checkDrafts(ADRESSEE, subject, bodyText))
            Screenshoter.takeScreenshot();
        Assert.assertFalse(steps.checkDrafts(ADRESSEE, subject, bodyText));//check that the e-mail is disappeared from drafts folder
    }

    @Test(dependsOnMethods = { "mailRemoveFromDraftsTest" })
    public void mailAddToSentFolderTest()
    {
        if(!steps.checkSentFolder(ADRESSEE, subject, bodyText))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.checkSentFolder(ADRESSEE, subject, bodyText));//check that the e-mail is present in "sent" folder
        steps.logout();
    }

    @AfterClass
    public void tearDown()
    {
        steps = new Steps();
        steps.closeBrowser();
    }
}


