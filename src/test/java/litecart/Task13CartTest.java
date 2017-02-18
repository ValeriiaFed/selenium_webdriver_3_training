package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class Task13CartTest extends BaseTest {

    private static final By UPDATE_QUANTITY_BUTTON = By.cssSelector("button[name=update_cart_item]");
    private static final By ORDER_TOTAL = By.cssSelector("#order_confirmation-wrapper .footer td:nth-child(2) strong");
    private static final By PRODUCT_QUANTITY_INPUT = By.cssSelector("input[name=quantity]");

    private static final String PRODUCT = ".product";
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[name=add_cart_product]");
    private static final By CART_QUANTITY = By.cssSelector("#cart .quantity");
    private static final By CHECKOUT_LINK = By.cssSelector("#cart .link");


    @Test
    public void cartTest() throws IOException {
        addProductToCartAndWaitForQuantityToChange();
        addProductToCartAndWaitForQuantityToChange();
        int cartQuantity = addProductToCartAndWaitForQuantityToChange();
        driver.findElement(CHECKOUT_LINK).click();
        for (int i = 1; i <= cartQuantity; i++){
            removeItemsOneByOneAndWaitForCartToChange();
        }
    }

    int addProductToCartAndWaitForQuantityToChange() {
        goToPage(BASE_URL);
        driver.findElements(By.cssSelector("#box-most-popular " + PRODUCT)).get(0).click();
        int initialQuantity = Integer.parseInt(driver.findElement(CART_QUANTITY).getText());
        driver.findElement(ADD_TO_CART_BUTTON).click();
        wait.until(ExpectedConditions.textToBe(CART_QUANTITY, String.valueOf(initialQuantity + 1)));
        return initialQuantity + 1;
    }

    void removeItemsOneByOneAndWaitForCartToChange() {
        for (int j = Integer.parseInt(driver.findElement(PRODUCT_QUANTITY_INPUT).getAttribute("value")); j > 0; j--){
            WebElement orderTotal = driver.findElement(ORDER_TOTAL);
            driver.findElement(PRODUCT_QUANTITY_INPUT).clear();
            driver.findElement(PRODUCT_QUANTITY_INPUT).sendKeys(String.valueOf(j - 1));
            driver.findElement(UPDATE_QUANTITY_BUTTON).click();
            wait.until(ExpectedConditions.stalenessOf(orderTotal));
        }
    }

}
