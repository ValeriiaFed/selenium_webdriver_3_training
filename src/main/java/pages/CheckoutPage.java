package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private static final By UPDATE_QUANTITY_BUTTON = By.cssSelector("button[name=update_cart_item]");
    private static final By ORDER_TOTAL = By.cssSelector("#order_confirmation-wrapper .footer td:nth-child(2) strong");
    private static final By PRODUCT_QUANTITY_INPUT = By.cssSelector("input[name=quantity]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void removeItemsOneByOneAndWaitForCartToChange() {
        for (int j = Integer.parseInt(driver.findElement(PRODUCT_QUANTITY_INPUT).getAttribute("value")); j > 0; j--){
            WebElement orderTotal = driver.findElement(ORDER_TOTAL);
            driver.findElement(PRODUCT_QUANTITY_INPUT).clear();
            driver.findElement(PRODUCT_QUANTITY_INPUT).sendKeys(String.valueOf(j - 1));
            driver.findElement(UPDATE_QUANTITY_BUTTON).click();
            wait.until(ExpectedConditions.stalenessOf(orderTotal));
        }
    }
}
