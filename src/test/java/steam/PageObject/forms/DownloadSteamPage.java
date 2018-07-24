package steam.PageObject.forms;

import framework.BasePage;
import framework.Browser;
import framework.CommonFunctions;
import framework.elements.Button;
import framework.HttpUtils;
import org.openqa.selenium.By;

public class DownloadSteamPage extends BasePage {

    private static final String locator = "//div[@id='about_greeting_ctn']//*//a[@id='about_install_steam_link']";

    private static final String downloadFile = "src/test/resources/SteamSetup.exe";

    private Button btnDownload;
    public DownloadSteamPage() {}

    public boolean checkFullDownload() {
        btnDownload = new Button(By.xpath((locator)));
        String link =  btnDownload.getAttribute("href");
        String filePath = downloadFile;
        CommonFunctions commonFunctions = new CommonFunctions();
        HttpUtils utils = new HttpUtils();
        long size = utils.getSizeOfContent(link, filePath);
        Browser.waitPageLoad();
        return commonFunctions.checkFullDownLoad(filePath, size);
    }

}
