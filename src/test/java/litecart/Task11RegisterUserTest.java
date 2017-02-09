package litecart;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Task11RegisterUserTest extends BaseTest {

    private static final By REGISTER_LINK = By.xpath("//form[@name='login_form']//a[text()='New customers click here']");
    private static final By FIRST_NAME_INPUT = By.cssSelector("input[name=firstname]");
    private static final By LAST_NAME_INPUT = By.cssSelector("input[name=lastname]");
    private static final By ADDRESS_1_INPUT = By.cssSelector("input[name=address1]");
    private static final By POSTCODE_INPUT = By.cssSelector("input[name=postcode]");
    private static final By CITY_INPUT = By.cssSelector("input[name=city]");
    private static final By COUNTRY_SELECT = By.cssSelector("select[name=country_code]");
    private static final By ZONE_SELECT = By.cssSelector("select[name=zone_code]");
    private static final By EMAIL_INPUT = By.cssSelector("input[name=email]");
    private static final By PHONE_INPUT = By.cssSelector("input[name=phone]");
    private static final By PASSWORD_INPUT = By.cssSelector("input[name=password]");
    private static final By CONFIRM_PASSWORD_INPUT = By.cssSelector("input[name=confirmed_password]");
    private static final By CREATE_ACCOUNT_BUTTON = By.cssSelector("button[name=create_account]");
    private static final By LOGOUT_BUTTON = By.xpath("//*[@id='box-account']//a[text()='Logout']");
    private static final By LOGIN_BUTTON = By.cssSelector("button[name=login]");

    private String email;

    @Test
    public void registerUserTest() {
        email = generateEmail();
        goToPage(BASE_URL);
        driver.findElement(REGISTER_LINK).click();
        wait.until(ExpectedConditions.titleContains("Create Account"));
        fillRegistrationForm();
        logout();
        login(email, "Password1");
        logout();
    }

    private void fillRegistrationForm() {
        driver.findElement(FIRST_NAME_INPUT).sendKeys("Test First Name");
        driver.findElement(LAST_NAME_INPUT).sendKeys("Test Last Name");
        driver.findElement(ADDRESS_1_INPUT).sendKeys("Test Address, 1");
        driver.findElement(POSTCODE_INPUT).sendKeys("12345");
        driver.findElement(CITY_INPUT).sendKeys("Test City");
        new Select(driver.findElement(COUNTRY_SELECT)).selectByVisibleText("United States");
        waitFor(1000);
        new Select(driver.findElement(ZONE_SELECT)).selectByVisibleText("California");
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PHONE_INPUT).sendKeys("1234567890");
        driver.findElement(PASSWORD_INPUT).sendKeys("Password1");
        driver.findElement(CONFIRM_PASSWORD_INPUT).sendKeys("Password1");
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
    }

    private void login(String email, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOGIN_BUTTON));
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    private void logout() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOGOUT_BUTTON));
        driver.findElement(LOGOUT_BUTTON).click();
    }

    private void waitFor(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String generateEmail() {
        return RandomStringUtils.randomAlphanumeric(8).toLowerCase() + "@testemail.com";
    }

}
