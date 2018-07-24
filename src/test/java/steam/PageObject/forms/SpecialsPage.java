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
    private Select yearSelect;
    private Select realYearSelect;
    private Button buttonEnter;
    private Select check;

    private static final String langConfig = "%s.properties";
    private static final String configLanguage = "localization/%s";


    public SpecialsPage() {
    }

    public List getDiscount() {
        Browser.waitPageLoad();
        CommonFunctions commonFunctions = new CommonFunctions();
        Label labelSecond = new Label();
        List<Label> list = labelSecond.getNecessaryElements("//div[@class='col search_discount responsive_secondrow']");
        List<Integer> discounts = commonFunctions.getListOfDiscounts(list);
        int maxIndex = commonFunctions.getIndMaxDiscount(discounts);
        List<String> listDiscount_price = new ArrayList<>();
        List<Label> listFirst = labelSecond.getNecessaryElements("//div[@class='col search_discount responsive_secondrow']");
        List<Label> listSecond = labelSecond.getNecessaryElements("//div[@class='col search_price discounted responsive_secondrow']");
        Label labelDiscount = labelSecond.findElementByInd(listFirst, maxIndex);
        Label labelPrice = labelSecond.findElementByInd(listSecond, maxIndex);
        listDiscount_price.add(labelDiscount.getText());
        listDiscount_price.add(labelPrice.getText());
        Image image = new Image();
        List<Image> gameImages = image.getConvertedElements("//div[@class='col search_capsule']");
        imgWithMaxDiscount = image.findElementByInd(gameImages, maxIndex);
        imgWithMaxDiscount.click();

        return listDiscount_price;
    }

    public void chooseAge() {

        Browser.waitIgnoring();
        try {
            Browser.waitElementsExplicide("//select[@name='ageYear']");
            yearSelect = new Select(By.xpath("//select[@name='ageYear']"));
            yearSelect.click();
            yearSelect.click();

            Browser.waitElementsExplicide("//select[@name='ageYear']");
            realYearSelect = new Select(By.xpath("//select[@name='ageYear']/option[@value='1997']"));
            realYearSelect.click();
            String lang= configFile.getConfigProperty("language");

            String nameFile=String.format(langConfig,lang);

            ConfigFileReader language = new ConfigFileReader(String.format(configLanguage, nameFile));
            String prop = new String (language.getConfigProperty("enterButton").getBytes("ISO-8859-1"),"UTF-8");

            String locator = String.format("//span[contains(text(),'%s')]", prop);

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
            Browser.waitElementsExplicide("//*[@id='app_agegate']/div[3]/a[1]/span");
            check = new Select(By.xpath("//*[@id='app_agegate']/div[3]/a[1]/span"));
            check.click();
        } catch (TimeoutException | NoSuchElementException ignored) {

        }
    }
}
