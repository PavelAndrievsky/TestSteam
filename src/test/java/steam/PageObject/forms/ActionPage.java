package steam.PageObject.forms;

import framework.Browser;
import framework.elements.Button;
import org.openqa.selenium.By;

import framework.*;

public class ActionPage extends BasePage {

    Button btnSpecialsTab;
    String specialsLocatorKey = "//*[@id='specials_container']/div/div[2]/a/span";

    public ActionPage() {
    }

    public void clickSpecials() {
        Browser.waitExplicide(specialsLocatorKey);
        btnSpecialsTab = new Button(By.xpath(specialsLocatorKey));
        btnSpecialsTab.click();

    }

}
