package litecart.browsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class FirefoxTest {

    public static final String MOZILLA_FIREFOX_45 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    public static final String MOZILLA_FIREFOX_DEV_53 = "C:\\Program Files (x86)\\Firefox Developer Edition\\firefox.exe";
    public static final String MOZILLA_FIREFOX_NIGHTLY_54 = "C:\\Program Files\\Nightly\\firefox.exe";
    public static final String MOZILLA_FIREFOX_51 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";


    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        DesiredCapabilities caps = new DesiredCapabilities();

        // старая схема
//        caps.setCapability(FirefoxDriver.MARIONETTE, false);
//        driver = new FirefoxDriver(caps);

        // новая схема
        caps.setCapability(FirefoxDriver.MARIONETTE, true);
        driver = new FirefoxDriver(
                new FirefoxBinary(new File(MOZILLA_FIREFOX_NIGHTLY_54)),
                new FirefoxProfile(), caps);
        Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
        System.out.println(capabilities);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest() {
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
