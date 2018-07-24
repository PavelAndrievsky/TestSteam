package steam.PageObject.forms;

import framework.Browser;
import framework.elements.Button;
import org.openqa.selenium.By;

import framework.*;

public class ActionPage extends BasePage {

    private Button btnSpecialsTab;

    public ActionPage() {
    }

    public void clickSpecials() {
        Browser.waitExplicide("//*[@id='specials_container']/div/div[2]/a/span");
        btnSpecialsTab = new Button(By.xpath("//*[@id='specials_container']/div/div[2]/a/span"));
        btnSpecialsTab.click();

    }

}
