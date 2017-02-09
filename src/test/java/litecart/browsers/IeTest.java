package litecart.browsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IeTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){

        driver = new InternetExplorerDriver();
        Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
        System.out.println(capabilities);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest() {
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("loginToAdmin")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
