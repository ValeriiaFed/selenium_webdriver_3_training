package litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class BaseTest {

    public static final String BASE_URL_ADMIN = "http://localhost:8080/litecart/admin/";
    public static final String BASE_URL = "http://localhost:8080/litecart/";
    public static final String MOZILLA_FIREFOX_NIGHTLY_54 = "C:\\Program Files\\Nightly\\firefox.exe";

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp(){
       // driver = new ChromeDriver();

//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
//        driver = new InternetExplorerDriver(caps);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, true);
        driver = new FirefoxDriver(
                new FirefoxBinary(new File(MOZILLA_FIREFOX_NIGHTLY_54)),
                new FirefoxProfile(), caps);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    public boolean isElementDisplayed(By by){
        return driver.findElements(by).size() > 0;
    }

    public void login() {
        goToPage(BASE_URL_ADMIN);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));
    }

    public void goToPage(String pageUrl) {
        driver.get(pageUrl);
    }
}
