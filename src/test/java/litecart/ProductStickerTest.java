package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductStickerTest extends BaseTest {

    private static final String BASE_PAGE = "http://localhost:8080/litecart/";
    private static final String PRODUCT = ".product";
    private static final String STICKER = ".sticker";

    @Test
    public void productStickerTest() {
        driver.get(BASE_PAGE);

        for (WebElement product: driver.findElements(By.cssSelector(PRODUCT))){
            assertEquals(product.findElements(By.cssSelector(STICKER)).size(), 1);
        }
    }
}
