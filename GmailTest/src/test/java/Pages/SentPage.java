package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;


public class SentPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/sent/";
    WebDriverWait wait= new WebDriverWait(driver,30);

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public SentPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//a[@id='PH_logoutLink']")
    WebElement logoutButton;

    @FindBy(xpath = "//a[@href=\"/messages/trash/\"]")
    WebElement trashButton;

    public boolean checkSentFolder(String addresseeText, String subjectText, String bodyText)
    {
        Collection<WebElement> drafts = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!drafts.isEmpty()){
            for (WebElement el : drafts) {
                if ((el.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subjectText+bodyText))
                        && (el.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(addresseeText))) {
                    highlightElement(el);
                    el.click();
                    return true;
                }
            }
        }
        return false;
    }

    public void addToSpam()
    {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        new Actions(driver).sendKeys(Keys.chord("j")).build().perform();
    }

    public void logout()
    {
        highlightElement(logoutButton);
        logoutButton.click();
    }

    public void dragMailToTrash(String addresseeText, String subjectText, String bodyText)
    {
        Collection<WebElement> drafts = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!drafts.isEmpty()){
            for (WebElement el : drafts) {
                if ((el.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subjectText+bodyText))
                        && (el.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(addresseeText))) {
                    highlightElement(el);
                    new Actions(driver).dragAndDrop(el, trashButton).build().perform();
                    break;
                }
            }
        }
    }
}
