package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage

{
    protected WebDriver driver;

    public abstract void openPage();

    public AbstractPage(WebDriver driver)
    {
       this.driver = driver;
    }

    protected void highlightElement(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid red'", element);
    }
}