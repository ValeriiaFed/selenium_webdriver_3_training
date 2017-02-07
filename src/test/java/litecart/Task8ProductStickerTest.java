package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task8ProductStickerTest extends BaseTest {

    private static final String PRODUCT = ".product";
    private static final String STICKER = ".sticker";

    @Test
    public void productStickerTest() {
        goToPage(BASE_URL);

        for (WebElement product: driver.findElements(By.cssSelector(PRODUCT))){
            assertEquals(product.findElements(By.cssSelector(STICKER)).size(), 1);
        }
    }
}
