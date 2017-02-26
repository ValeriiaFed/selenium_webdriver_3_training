package litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class Task17BrowserLogsTest extends BaseTest {

    public static final String FIRST_CATEGORY_URL = "http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    public static final String PRODUCT_LIST = "//*[@class='row']//td/img/..//a";


    @Test
    public void browserLogsTest() {
        loginToAdmin();
        goToPage(FIRST_CATEGORY_URL);
        List<WebElement> products = driver.findElements(By.xpath(PRODUCT_LIST));
        for (int i = 0; i < products.size(); i++){
            driver.findElements(By.xpath(PRODUCT_LIST)).get(i).click();
            Assert.assertTrue(driver.manage().logs().get("browser").getAll().size() == 0);
            goToPage(FIRST_CATEGORY_URL);
        }
    }
}
