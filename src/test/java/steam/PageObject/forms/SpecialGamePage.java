package steam.PageObject.forms;

import framework.BasePage;
import framework.Browser;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class SpecialGamePage extends BasePage {

    private Button buttonInstall;
    private Label discountLabel;
    private Label priceLabel;

    private String installButtonLocatorKey = "//a[@class='header_installsteam_btn_content']";
    private String discountLocatorExpected = "//div[@class='discount_pct']";
    private String finalPriceLocatorKey = "//div[@class='discount_prices']";

    public SpecialGamePage() {
    }

    public boolean isSimilarPriceDiscount(List<String> list) {

        Browser.waitElementsExplicide(discountLocatorExpected);
        discountLabel = new Label(By.xpath(discountLocatorExpected));
        priceLabel = new Label(By.xpath(finalPriceLocatorKey));
        List<String> comparing = new ArrayList<>();
        comparing.add(discountLabel.getText());
        comparing.add(priceLabel.getText().split(" ")[0]);

        return list.equals(comparing);
    }

    public void clickOnInstall()  {
        Browser.waitElementsExplicide(installButtonLocatorKey);
        String str = new String(installButtonLocatorKey);
        buttonInstall = new Button(By.xpath(str));
        buttonInstall.click();
    }

}
