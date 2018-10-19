package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends AbstractPage {

    private final String BASE_URL = "https://mail.ru/";
    WebDriverWait wait= new WebDriverWait(driver,30);

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "mailbox:login")
    private WebElement inputMail;

    @FindBy(id = "mailbox:password")
    private WebElement inputPassword;

    @FindBy(xpath = "//label/input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//i[@class='x-ph__menu__button__text x-ph__menu__button__text_auth']")
    private WebElement userMail;

    public void login(String username, String password)
    {
        inputMail.sendKeys(username);
        inputPassword.sendKeys(password);
        submitButton.click();
    }

    public String getLoggedInUserName()
    {
        wait.until(ExpectedConditions.visibilityOf(userMail));
        highlightElement(userMail);
        return userMail.getText();
    }
}
