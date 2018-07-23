package framework;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseEntity {

    protected static WebDriver driver;
    protected static BaseEntity baseEntity;

    protected static ConfigFileReader configFile = new ConfigFileReader("config.properties");
    private static final String configLanguage = "localization/";
    private static final String languageName = ".properties";

    private static String lang = configFile.getConfigProperty("language");
    protected static ConfigFileReader language = new ConfigFileReader(String.format("%s%s" + languageName, configLanguage, lang));

    @BeforeTest
    public void setUp() {
        BasePage basePage = new BasePage();
        driver = Browser.getInstance().getDriver();
        String mainPage = basePage.initConfigProperties("main_page_url");
        basePage = new BasePage(driver);
        basePage.maximiseWindow();
        basePage.navigate(mainPage);
        baseEntity = new BaseEntity();
    }

    public BaseEntity() {}

    public BaseEntity(WebDriver driver1) {
        driver = driver1;
    }

    public  WebDriver getDriver() {
        return driver;
    }

    public void assertTrue(boolean isTrue){
        Assert.assertTrue(isTrue);
    }

    public static void exit() {
        Browser.exit();
    }

    @AfterTest
    public void tearDown() {
        exit();
    }

}
