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

    public SpecialGamePage() {
    }

    public boolean isSimilarPriceDiscount(List<String> list) {
        Browser.waitElementsExplicide("//div[@class='discount_pct']");
        discountLabel = new Label(By.xpath("//div[@class='discount_pct']"));
        priceLabel = new Label(By.xpath("//div[@class='discount_prices']"));
        List<String> comparing = new ArrayList<>();
        comparing.add(discountLabel.getText());
        comparing.add(priceLabel.getText().split(" ")[0]);

        return list.equals(comparing);
    }

    public void clickOnInstall()  {
        Browser.waitElementsExplicide("//a[@class='header_installsteam_btn_content']");
        buttonInstall = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));
        buttonInstall.click();
    }

}
