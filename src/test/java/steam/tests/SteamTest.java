package steam.tests;

import framework.BaseEntity;
import org.testng.annotations.*;
import steam.PageObject.forms.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class SteamTest extends BaseEntity {

    @Test
    public void Steam() {

        MainPage mainPage = new MainPage();
        try {
            mainPage.changeLanguage();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mainPage.moveToGamesMenu();
        String action = mainPage.mainMenu.action;
        mainPage.clickActionMenu(action);

        ActionPage actionPage = new ActionPage();
        actionPage.clickSpecials();
        SpecialsPage specialsPage = new SpecialsPage();
        List<String> list = specialsPage.getDiscount();

        SpecialGamePage specialGamePage = new SpecialGamePage();
        specialsPage.chooseAge();
        specialsPage.chooseWarning();

        baseEntity.assertTrue(specialGamePage.isSimilarPriceDiscount(list));
        specialGamePage.clickOnInstall();

        DownloadSteamPage downloadSteamPage = new DownloadSteamPage();
        baseEntity.assertTrue(downloadSteamPage.checkFullDownload());
    }

}
