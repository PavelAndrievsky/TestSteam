package steam.PageObject.forms;

import framework.BasePage;
import framework.Browser;
import framework.ConfigFileReader;
import framework.elements.Select;
import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;

import steam.PageObject.menu.MainMenu;

public class MainPage extends BasePage {

    private Select selGamesMenu;
    private Select selAction;
    private Select languageSelect;

    private static final String languageProperties = "%s.properties";
    private static final String configLanguage = "localization/%s";

    public MainMenu mainMenu = new MainMenu();

    public MainPage() {
    }

    public void moveToGamesMenu() {
        Browser.waitImplicit();
        Browser.waitPageLoad();
        selGamesMenu = new Select(By.xpath("//*[@id='genre_tab']/span/a"));
        selGamesMenu.moveTo();
    }

    public void changeLanguage() throws UnsupportedEncodingException {

        String language = configFile.getConfigProperty("language");
        languageSelect = new Select(By.xpath("//span[@id='language_pulldown']"));
        String lang = languageSelect.getText();

        if ((language.equals("ru") && !lang.equals("язык")) || (language.equals("en") && !lang.equals("language"))) {
            String filename = String.format(languageProperties, language);
            ConfigFileReader configLan = new ConfigFileReader(String.format(configLanguage, filename));
            String locator = new String(configLan.getConfigProperty("lang").getBytes("ISO-8859-1"), "UTF-8");
            locator = String.format("//a[@href='?l=%s']", locator);
            languageSelect.click();
            Select realLanguageSelect = new Select(By.xpath(locator));
            realLanguageSelect.clickAndWait();
            Browser.waitPageLoad();
        }

    }

    public void clickActionMenu(String id) {
        String locator = null;
        try {
            String actionLocatorKey = "//a[@class='popup_menu_item' and contains(text(),'%s')]";
            locator = new String(actionLocatorKey.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert locator != null;
        selAction = new Select(By.xpath(String.format(locator, id)));
        selAction.click();
    }
}
