package steam.PageObject.forms;

import framework.BasePage;
import framework.Browser;
import framework.ConfigFileReader;
import framework.elements.Select;
import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;

import steam.PageObject.menu.MainMenu;

public class MainPage extends BasePage {

    private Select selGamesmenu;
    private Select selAction;
    private Select languageSelect;
    private Select reallanguageSelect;

    private static final String languageProperties = "%s.properties";
    private static final String configLanguage = "localization/%s";
    private String gamesMenuLocatorKey = "//*[@id='genre_tab']/span/a";
    private String actionLocatorKey = "//a[@class='popup_menu_item' and contains(text(),'%s')]";
    private String languageLocatorKey = "//span[@id='language_pulldown']";
    private String russionOptionLocatorKey = "//a[@href='?l=%s']";

    public MainMenu mainMenu = new MainMenu();

    public MainPage() {
    }

    public void moveToGamesMenu() {
        Browser.waitImplicit();
        Browser.waitPageLoad();
        selGamesmenu = new Select(By.xpath(gamesMenuLocatorKey));
        selGamesmenu.moveTo();
    }

    public void changeLanguage() throws UnsupportedEncodingException {

        String language = configFile.getConfigProperty("language");
        String locat = languageLocatorKey;
        languageSelect = new Select(By.xpath(locat));
        String lang = languageSelect.getText();

        if ((language.equals("ru") && !lang.equals("язык")) || (language.equals("en") && !lang.equals("language"))) {
            String filename = String.format(languageProperties, language);
            ConfigFileReader configLan = new ConfigFileReader(String.format(configLanguage, filename));
            String locator = new String(configLan.getConfigProperty("lang").getBytes("ISO-8859-1"), "UTF-8");
            locator = String.format(russionOptionLocatorKey, locator);
            languageSelect.click();
            reallanguageSelect = new Select(By.xpath(locator));
            reallanguageSelect.clickAndWait();
        }

    }

    public void clickActionMenu(String id) {
        String locator = null;
        try {
            locator = new String(actionLocatorKey.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        selAction = new Select(By.xpath(String.format(locator, id)));
        selAction.click();
    }
}
