import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utils.Utils;
import Utils.Screenshoter;

public class AddMailToSpamTest {

    private Steps steps;
    private String subject = Utils.getRandomString(10);
    private String bodyText = Utils.getRandomString(30);

    private final String ADRESSEE = "romanegorov1016@gmail.com";
    private final String SENDER_NAME = "Raman Yahoray";
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
    public void sendMailTest()
    {
        steps.fillMailFields(ADRESSEE, subject, bodyText);
        steps.sendMail();
        if(!steps.checkSentFolder(ADRESSEE, subject, bodyText))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.checkSentFolder(ADRESSEE, subject, bodyText));//check that the e-mail is present in "sent" folder
    }

    @Test(dependsOnMethods = { "sendMailTest" })
    public void addToSpamTest()
    {
        steps.checkSentFolder(ADRESSEE, subject, bodyText);
        steps.addMailToSpam();
        if(!steps.checkSpamFolder(SENDER_NAME, subject, bodyText))
            Screenshoter.takeScreenshot();
        Assert.assertTrue(steps.checkSpamFolder(SENDER_NAME, subject, bodyText));
        steps.logout();
    }

    @AfterClass
    public void tearDown()
    {
        steps = new Steps();
        steps.closeBrowser();
    }
}
