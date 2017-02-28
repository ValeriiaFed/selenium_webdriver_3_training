package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[name=add_cart_product]");
    private static final By CART_QUANTITY = By.cssSelector("#cart .quantity");
    private static final By CHECKOUT_LINK = By.cssSelector("#cart .link");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public int addProductToCartAndWaitForQuantityToChange() {
        int initialQuantity = Integer.parseInt(driver.findElement(CART_QUANTITY).getText());
        driver.findElement(ADD_TO_CART_BUTTON).click();
        wait.until(ExpectedConditions.textToBe(CART_QUANTITY, String.valueOf(initialQuantity + 1)));
        return initialQuantity + 1;
    }

    public void clickCheckoutLink() {
        driver.findElement(CHECKOUT_LINK).click();
    }
}
