package steam.PageObject.forms;

import framework.BasePage;
import framework.Browser;
import framework.CommonFunctions;
import framework.ConfigFileReader;
import framework.elements.Button;
import framework.elements.Image;
import framework.elements.Label;
import framework.elements.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SpecialsPage extends BasePage {

    private Image imgWithMaxDiscount;
    private String discountLocatorKey = "//div[@class='col search_discount responsive_secondrow']";
    private String imageLocatorKey = "//div[@class='col search_capsule']";
    private String priceLabelLocatorKey = "//div[@class='col search_price discounted responsive_secondrow']";

    private Select yearSelect;
    private Select realYearSelect;
    private Button buttonEnter;
    private Select check;

    private String yearSelectLocator = "//select[@name='ageYear']";
    private String yearOptionLocator = "//select[@name='ageYear']/option[@value='1997']";
    private String enterButtonLocator = "//span[contains(text(),'%s')]";
    private String checkAge = "//*[@id='app_agegate']/div[3]/a[1]/span";

    private static final String langConfig = "%s.properties";
    private static final String configLanguage = "localization/%s";


    public SpecialsPage() {
    }

    public List getDiscount() {
        Browser.waitPageLoad();
        CommonFunctions commonFunctions = new CommonFunctions();
        Label labelSecond = new Label();
        List<Label> list = labelSecond.getNecessaryElements(discountLocatorKey);
        List<Integer> discounts = commonFunctions.getListOfDiscounts(list);
        int maxIndex = commonFunctions.getIndMaxDiscount(discounts);
        List<String> listDiscount_price = new ArrayList<>();
        List<Label> listFirst = labelSecond.getNecessaryElements(discountLocatorKey);
        List<Label> listSecond = labelSecond.getNecessaryElements(priceLabelLocatorKey);
        Label labelDiscount = labelSecond.findElementByInd(listFirst, maxIndex);
        Label labelPrice = labelSecond.findElementByInd(listSecond, maxIndex);
        listDiscount_price.add(labelDiscount.getText());
        listDiscount_price.add(labelPrice.getText());
        Image image = new Image();
        List<Image> gameImages = image.getConvertedElements(imageLocatorKey);
        imgWithMaxDiscount = image.findElementByInd(gameImages, maxIndex);
        imgWithMaxDiscount.click();

        return listDiscount_price;
    }

    public void chooseAge() {

        Browser.waitIgnoring();
        try {
            Browser.waitElementsExplicide(yearSelectLocator);
            yearSelect = new Select(By.xpath(yearSelectLocator));
            yearSelect.click();
            yearSelect.click();

            Browser.waitElementsExplicide(yearSelectLocator);
            realYearSelect = new Select(By.xpath(yearOptionLocator));
            realYearSelect.click();
            String lang= configFile.getConfigProperty("language");

            String nameFile=String.format(langConfig,lang);

            ConfigFileReader language = new ConfigFileReader(String.format(configLanguage, nameFile));
            String prop = new String (language.getConfigProperty("enterButton").getBytes("ISO-8859-1"),"UTF-8");

            String locator = new String(String.format(enterButtonLocator,prop));

            buttonEnter = new Button(By.xpath(locator));
            buttonEnter.click();

        } catch (TimeoutException | NoSuchElementException ignored) {

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void chooseWarning(){
        Browser.waitIgnoring();
        try {
            Browser.waitElementsExplicide(checkAge);
            check = new Select(By.xpath(checkAge));
            check.click();
        } catch (TimeoutException | NoSuchElementException ignored) {

        }
    }
}
