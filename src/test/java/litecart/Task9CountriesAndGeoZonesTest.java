package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task9CountriesAndGeoZonesTest extends BaseTest {

    private static final String COUNTRIES_PAGE_URL = BASE_URL_ADMIN + "?app=countries&doc=countries";
    private static final String GEO_ZONES_PAGE_URL = BASE_URL_ADMIN + "?app=geo_zones&doc=geo_zones";
    private static final String COUNTRY_NAME_IN_TABLE_ROW = "td:nth-child(5)";
    private static final String COUNTRY_NAME_LIST = ".dataTable .row a:not([title=Edit])";
    private static final String COUNTRY_NAME = "//*[@class='dataTable']//*[@class='row']//a[text()='%s']";
    private static final String ZONES_NAMES_LIST = "//table[@id='table-zones']//input[@type='hidden']/../../td[3]";
    private static final String SELECTED_ZONES_NAMES_LIST = ZONES_NAMES_LIST + "//option[@selected]";
    private static final String ROW = "row";
    private static final String ZONES_QUANTITY_IN_TABLE_ROW = "td:nth-child(6)";

    @Test
    public void countriesTest() {
        loginToAdmin();
        goToPage(COUNTRIES_PAGE_URL);
        sortingIsCorrectVerify(getCountriesList());

        for (String country: getCountriesListWithMoreThanZeroTimeZones()){
            goToPage(COUNTRIES_PAGE_URL);
            clickOnCountryName(country);
            sortingIsCorrectVerify(getZonesList());
        }
    }

    @Test
    public void geoZonesTest() {
        loginToAdmin();
        goToPage(GEO_ZONES_PAGE_URL);

        for (String country: getCountriesList()){
            goToPage(GEO_ZONES_PAGE_URL);
            clickOnCountryName(country);
            sortingIsCorrectVerify(getSelectedZonesList());
        }
    }

    private void clickOnCountryName(String country) {
        driver.findElement(By.xpath(String.format(COUNTRY_NAME, country))).click();
    }

    private void sortingIsCorrectVerify(List<String> countriesList) {
        List<String> sortedCountriesList = new ArrayList<String>(countriesList);
        Collections.sort(sortedCountriesList);
        assertEquals(countriesList, sortedCountriesList);
    }

    private List<String> getCountriesList() {
        List<String> countriesList = new ArrayList<String>();
        for (WebElement countryName: driver.findElements(By.cssSelector(COUNTRY_NAME_LIST))){
            countriesList.add(countryName.getText());
        }
        return countriesList;
    }

    private List<String> getZonesList() {
        List<String> zonesList = new ArrayList<String>();
        for (WebElement zoneName: driver.findElements(By.xpath(ZONES_NAMES_LIST))){
            zonesList.add(zoneName.getText());
        }
        return zonesList;
    }

    private List<String> getCountriesListWithMoreThanZeroTimeZones() {
        List<String> countriesList = new ArrayList<String>();
        for (WebElement row: driver.findElements(By.className(ROW))){
            if (!row.findElement(By.cssSelector(ZONES_QUANTITY_IN_TABLE_ROW)).getText().equals("0")){
                countriesList.add(row.findElement(By.cssSelector(COUNTRY_NAME_IN_TABLE_ROW)).getText());
            }
        }
        return countriesList;
    }

    public List<String> getSelectedZonesList() {
        List<String> zonesList = new ArrayList<String>();
        for (WebElement zoneName: driver.findElements(By.xpath(SELECTED_ZONES_NAMES_LIST))){
            zonesList.add(zoneName.getText());
        }
        return zonesList;
    }
}
