package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String PRODUCT = ".product";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        goToPage(BASE_URL);
    }

    public void clickOnProduct() {
        open();
        driver.findElements(By.cssSelector("#box-most-popular " + PRODUCT)).get(0).click();
    }

}
