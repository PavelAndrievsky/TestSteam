package framework;

import org.openqa.selenium.WebDriver;

public class BasePage extends BaseEntity{

    private static WebDriver driver;


    public BasePage() {
    }

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void maximiseWindow() {
        Browser.windowMaximise();
    }

    public void navigate(String url) {
        Browser.navigateUrl(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String initConfigProperties(String prop) {
        String file = configFile.getConfigProperty(prop);
        return file;
    }

}
