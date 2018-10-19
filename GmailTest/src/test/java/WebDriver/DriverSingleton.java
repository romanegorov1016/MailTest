package WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private DriverSingleton(){}

    public static WebDriver getDriver()
    {
        if (null == driver){
            try{
                driver = new RemoteWebDriver(new URL("http://10.6.103.19:4445/wd/hub"), DesiredCapabilities.chrome());
            }
            catch (Exception e){}
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver()
    {
        driver.quit();
        driver = null;
    }
}
