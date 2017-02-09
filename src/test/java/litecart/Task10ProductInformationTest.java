package litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task10ProductInformationTest extends BaseTest {

    private static final String PRODUCT = ".product";
    private static final String GREY_COLOR_RGBA = "rgba(119, 119, 119, 1)";
    private static final String RED_COLOR_RGBA = "rgba(204, 0, 0, 1)";
    private static final String GREY_COLOR_RGBA_PDP = "rgba(102, 102, 102, 1)";
    private static final String LINE_THROUGH = "line-through";
    private static final String BOLD = "bold";

    @Test
    public void productInformationTest() {
        goToPage(BASE_URL);

        WebElement product = driver.findElements(By.cssSelector("#box-campaigns " + PRODUCT)).get(0);
        String productName = product.findElement(By.className("name")).getText();

        WebElement regularPrice = getRegularPrice(product);
        String regularPriceText = regularPrice.getText();
        assertEquals(GREY_COLOR_RGBA, getColorInRGBA(regularPrice));
        assertEquals(LINE_THROUGH, regularPrice.getCssValue("text-decoration"));

        WebElement campaignPrice = getCampaignPrice(product);
        String campaignPriceText = campaignPrice.getText();
        assertEquals(RED_COLOR_RGBA, getColorInRGBA(campaignPrice));
        String campaignPriceFontWeight = campaignPrice.getCssValue("font-weight");
        assertTrue(campaignPriceFontWeight.equals(BOLD) || campaignPriceFontWeight.equals("900"));

        assertTrue(getFormattedFontSize(regularPrice.getCssValue("font-size")) < getFormattedFontSize(campaignPrice.getCssValue("font-size")));

        // go to product details page:
        product.click();
        wait.until(ExpectedConditions.titleContains(productName));

        String productNamePDP = driver.findElement(By.cssSelector("[itemprop=name]")).getText();
        assertEquals(productName, productNamePDP);

        WebElement regularPricePDP = driver.findElement(By.className("regular-price"));
        assertEquals(regularPriceText, regularPricePDP.getText());
        assertEquals(GREY_COLOR_RGBA_PDP, getColorInRGBA(regularPricePDP));
        assertEquals(LINE_THROUGH, regularPricePDP.getCssValue("text-decoration"));

        WebElement campaignPricePDP = driver.findElement(By.className("campaign-price"));
        assertEquals(campaignPriceText, campaignPricePDP.getText());
        assertEquals(RED_COLOR_RGBA, getColorInRGBA(campaignPricePDP));
        String campaignPriceFontWeightPDP = campaignPricePDP.getCssValue("font-weight");
        assertTrue(campaignPriceFontWeightPDP.equals(BOLD) || campaignPriceFontWeightPDP.equals("700"));

        assertTrue(getFormattedFontSize(regularPricePDP.getCssValue("font-size")) < getFormattedFontSize(campaignPricePDP.getCssValue("font-size")));
    }

    String getColorInRGBA(WebElement element) {
        String color = element.getCssValue("color");
        if (color.startsWith("rgba")){
            return color;
        } else {
            return color.replace("rgb", "rgba").replace(")", ", 1)");
        }
    }

    private double getFormattedFontSize(String regularPriceFontSize) {
        return Double.parseDouble(regularPriceFontSize.replace("px", ""));
    }

    private WebElement getCampaignPrice(WebElement product) {
        return product.findElement(By.className("campaign-price"));
    }

    private WebElement getRegularPrice(WebElement product) {
        return product.findElement(By.className("regular-price"));
    }
}
