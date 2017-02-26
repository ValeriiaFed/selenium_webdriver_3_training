package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertFalse;

public class Task14CountriesNewWindowTest extends BaseTest {

    private static final String COUNTRIES_PAGE_URL = BASE_URL_ADMIN + "?app=countries&doc=countries";
    private static final By COUNTRY_ROW = By.cssSelector(".dataTable .row");
    private static final By EDIT_BUTTON = By.cssSelector("a[title=Edit]");
    private static final By EXTERNAL_LINK_LIST = By.xpath("//i[contains(@class,'fa-external-link')]/..");


    @Test
    public void countriesNewWindowTest() {
        loginToAdmin();
        goToPage(COUNTRIES_PAGE_URL);
        driver.findElements(COUNTRY_ROW).get(0).findElement(EDIT_BUTTON).click();
        String currentWindow = driver.getWindowHandle();
        String currentTitle = driver.getTitle();

        for (int i = 0; i < driver.findElements(EXTERNAL_LINK_LIST).size(); i++){
            driver.findElements(EXTERNAL_LINK_LIST).get(i).click();
            switchToNewWindow(currentWindow);
            assertFalse("Opened url is incorrect", driver.getTitle().equals(currentTitle));
            driver.close();
            driver.switchTo().window(currentWindow);
        }
    }

    private String switchToNewWindow(String currentWindow) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String window: driver.getWindowHandles()){
            if (! window.equals(currentWindow)){
                driver.switchTo().window(window);
                return window;
            }
        }
        throw new RuntimeException("New Window is not opened");
    }
}
