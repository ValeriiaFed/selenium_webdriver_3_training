package litecart;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class Task12AddNewProductTest extends BaseTest {

    private static final By ADD_NEW_PRODUCT_BUTTON = By.xpath("//a[@class='button' and text()=' Add New Product']");
    private static final By STATUS_ENABLED_RADIO_BUTTON = By.cssSelector("input[name='status'][value='1']");
    private static final By NAME_INPUT = By.cssSelector("input[name='name[en]']");
    private static final By CODE_INPUT = By.cssSelector("input[name=code]");
    private static final By UNISEX_PRODUCT_GROUP_CHECKBOX = By.cssSelector("input[name='product_groups[]'][value='1-3']");
    private static final By QUANTITY_INPUT = By.cssSelector("input[name=quantity]");
    private static final By IMAGE_UPLOAD = By.cssSelector("input[name='new_images[]']");
    private static final By DATE_VALID_FROM_INPUT = By.cssSelector("input[name=date_valid_from]");
    private static final By DATE_VALID_TO_INPUT = By.cssSelector("input[name=date_valid_to]");
    private static final By INFORMATION_TAB = By.cssSelector("a[href='#tab-information']");
    private static final By PRICES_TAB = By.cssSelector("a[href='#tab-prices']");
    private static final By MANUFACTURER_SELECT = By.cssSelector("select[name=manufacturer_id]");
    private static final By KEYWORDS_INPUT = By.cssSelector("input[name=keywords]");
    private static final By SHORT_DESCRIPTION_INPUT = By.cssSelector("input[name='short_description[en]']");
    private static final By DESCRIPTION_INPUT = By.cssSelector("div[class='trumbowyg-editor']");
    private static final By HEAD_TITLE_INPUT = By.cssSelector("input[name='head_title[en]']");
    private static final By META_DESCRIPTION_INPUT = By.cssSelector("input[name='meta_description[en]']");
    private static final By PURCHASE_PRICE_INPUT = By.cssSelector("input[name=purchase_price]");
    private static final By PURCHASE_CURRENCY_SELECT = By.cssSelector("select[name=purchase_price_currency_code]");
    private static final By PRICE_USD_INPUT = By.cssSelector("input[name='prices[USD]']");
    private static final By PRICE_EURO_INPUT = By.cssSelector("input[name='prices[EUR]']");
    private static final By SAVE_BUTTON = By.cssSelector("button[name=save]");
    private static final String PRODUCT_ROW = "//table[@class='dataTable']//tr[@class='row' and contains(., '%s')]";
    private static final String MENU_ITEM = "//li[@id='app-']//span[@class='name' and text()='%s']";


    @Test
    public void addNewProductTest() throws IOException {
        String productId = RandomStringUtils.randomNumeric(8);
        String productName = "Product " + productId;
        loginToAdmin();

        driver.findElement(By.xpath(String.format(MENU_ITEM, "Catalog"))).click();
        driver.findElement(ADD_NEW_PRODUCT_BUTTON).click();
        driver.findElement(STATUS_ENABLED_RADIO_BUTTON).click();
        driver.findElement(NAME_INPUT).sendKeys(productName);
        driver.findElement(CODE_INPUT).sendKeys(productId);
        driver.findElement(UNISEX_PRODUCT_GROUP_CHECKBOX).click();
        driver.findElement(QUANTITY_INPUT).clear();
        driver.findElement(QUANTITY_INPUT).sendKeys("100");

        File file = new File(Paths.get("./src/main/resources/testImage.jpg").toAbsolutePath().toString());
        driver.findElement(IMAGE_UPLOAD).sendKeys(file.getCanonicalPath());
        driver.findElement(DATE_VALID_FROM_INPUT).sendKeys("02/13/2017");
        driver.findElement(DATE_VALID_TO_INPUT).sendKeys("02/13/2027");

        // information tab:
        driver.findElement(INFORMATION_TAB).click();
        new Select(driver.findElement(MANUFACTURER_SELECT)).selectByVisibleText("ACME Corp.");
        driver.findElement(KEYWORDS_INPUT).sendKeys("Test keywords");
        driver.findElement(SHORT_DESCRIPTION_INPUT).sendKeys("Test short description");
        driver.findElement(DESCRIPTION_INPUT).sendKeys("Test description");
        driver.findElement(HEAD_TITLE_INPUT).sendKeys("Test head title");
        driver.findElement(META_DESCRIPTION_INPUT).sendKeys("Test meta description");

        // prices tab:
        driver.findElement(PRICES_TAB).click();
        driver.findElement(PURCHASE_PRICE_INPUT).clear();
        driver.findElement(PURCHASE_PRICE_INPUT).sendKeys("50");
        new Select(driver.findElement(PURCHASE_CURRENCY_SELECT)).selectByVisibleText("US Dollars");
        driver.findElement(PRICE_USD_INPUT).sendKeys("50");
        driver.findElement(PRICE_EURO_INPUT).sendKeys("47");
        driver.findElement(SAVE_BUTTON).click();
        assertTrue(isElementDisplayed(By.xpath(String.format(PRODUCT_ROW, productName))));
    }

}
