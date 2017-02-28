package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductDetailsPage;

import java.io.IOException;

public class Task13CartTest extends BaseTest {

    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;
    private CheckoutPage checkoutPage;

    @Test
    public void cartTest() throws IOException {
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        checkoutPage = new CheckoutPage(driver);

        int cartQuantity = 0;

        for (int i = 0; i < 3; i++){
            homePage.open();
            homePage.clickOnProduct();
            cartQuantity = productDetailsPage.addProductToCartAndWaitForQuantityToChange();
        }
        productDetailsPage.clickCheckoutLink();

        for (int i = 1; i <= cartQuantity; i++){
            checkoutPage.removeItemsOneByOneAndWaitForCartToChange();
        }
    }
}
