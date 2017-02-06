package litecart;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class LeftMenuNavigationTest extends BaseTest {

    private static final String MENU_ITEM = "//span[text()= '%s']";
    private static final String SUB_MENU_ITEMS_LIST = ".selected .docs li";
    private static final String PAGE_TITLE = "h1";
    private static final String MENU_NAMES_LIST = "name";

    @Test
    public void leftMenuNavigationTest() {
        login();
        List<String> menuNamesList = getMenuNamesList();

        for (String menuName: menuNamesList){
            clickOnMenuItem(menuName);
            titleIsDisplayedVerify();

            if (isMenuHasSubMenuItems()){
                subMenuItemsTitlesVerify(menuName);
            }
        }
    }

    private void clickOnMenuItem(String menuName) {
        driver.findElement(By.xpath(String.format(MENU_ITEM, menuName))).click();
    }

    private boolean isMenuHasSubMenuItems() {
        return driver.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST)).size() > 0;
    }

    private void subMenuItemsTitlesVerify(String menuName) {
        List<String> subMenuNamesList = getSubMenuNamesList();

        for (String submenuName: subMenuNamesList){
            if (isElementDisplayed(By.xpath(String.format(MENU_ITEM, submenuName)))){
                clickOnMenuItem(submenuName);
            } else {
                clickOnMenuItem(menuName);
                clickOnMenuItem(submenuName);
            }
            titleIsDisplayedVerify();
        }
    }

    private List<String> getSubMenuNamesList() {
        List<String> subMenuNamesList = new ArrayList<String>();
        for (WebElement subMenuName: driver.findElements(By.cssSelector(SUB_MENU_ITEMS_LIST))){
            subMenuNamesList.add(subMenuName.getText());
        }
        return subMenuNamesList;
    }

    private void titleIsDisplayedVerify() {
        assertTrue(driver.findElement(By.cssSelector(PAGE_TITLE)).isDisplayed());
    }

    private List<String> getMenuNamesList() {
        List<String> menuNamesList = new ArrayList<String>();
        for (WebElement menuName: driver.findElements(By.className(MENU_NAMES_LIST))){
            menuNamesList.add(menuName.getText());
        }
        return menuNamesList;
    }

    private void login() {
        driver.get("http://localhost:8080/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));
    }
}
