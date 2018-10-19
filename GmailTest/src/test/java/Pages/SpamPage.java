package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

public class SpamPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/spam/";
    WebDriverWait wait= new WebDriverWait(driver,30);

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public SpamPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean checkSpamFolder(String addresseeText, String subjectText, String bodyText)
    {
        Collection<WebElement> drafts = driver.findElements(By.xpath("//div[@class='b-datalist__item__info']"));
        if (!drafts.isEmpty()){
            for (WebElement el : drafts) {
                System.out.println(el.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText());
                System.out.println(el.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText());
                if ((el.findElement(By.xpath("//div[@class='b-datalist__item__subj']")).getText().equalsIgnoreCase(subjectText+bodyText))
                        && (el.findElement(By.xpath("//div[@class='b-datalist__item__addr']")).getText().equalsIgnoreCase(addresseeText))) {
                    highlightElement(el);
                    return true;
                }
            }
        }
        return false;
    }
}
