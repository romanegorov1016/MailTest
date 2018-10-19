package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.Collection;


public class TrashPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/trash/";

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public TrashPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean checkTrashFolder(String addresseeText, String subjectText, String bodyText)
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
}
