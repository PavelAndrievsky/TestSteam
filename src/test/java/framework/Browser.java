package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Browser extends BaseEntity {

    private static WebDriver driver;
    private static Browser instance;
    private static String OS = System.getProperty("os.name").toLowerCase();

    private static final String chromeDriverPath = "./src/test/resources/chromedriver";
    private static final String geckoDriverPath = "./src/test/resources/geckodriver";

    private static String browser = getParameter("browser");
    
    private static String downloadPath;

    static {
        try {
            downloadPath = new File("src/test/resources/SteamSetup.exe").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String getParameter(String name) {
        String value = System.getProperty(name);
        if (value == null)
            throw new RuntimeException(name + " is not a parameter!");

        if (value.isEmpty())
            throw new RuntimeException(name + " is empty!");

        return value;
    }
    
    private Browser(){}

    public static Browser getInstance() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public WebDriver getDriver() {
        String pathChrome = null;
        try {
            pathChrome = String.valueOf(new File(chromeDriverPath).getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pathGecko = null;
        try {
            pathGecko = String.valueOf(new File(geckoDriverPath).getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (OS){
            case "windows 10":
                pathChrome = String.format("%s.exe", pathChrome);
                pathGecko = String.format("%s.exe", pathGecko);
                break;
            case "linux":
                break;
        }
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", pathChrome);
                driver = new ChromeDriver(setChromeCapabilities());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", pathGecko);
                driver = new FirefoxDriver(setFirefoxCapabilities());
                break;
        }
        return driver;
    }

    private static FirefoxOptions setFirefoxCapabilities() {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", downloadPath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/octet-stream, application/zip ,application/x-rar-compressed, application/x-gzip, application/msword ");

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        return options;

    }

    private static DesiredCapabilities setChromeCapabilities() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadPath);
        prefs.put("safebrowsing.enabled", "true");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        return cap;
    }

    public static void windowMaximise() {
        driver.manage().window().maximize();
    }

    public static void exit() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            instance = null;
        }
    }

    public static void waitIgnoring(){
        Wait<WebDriver> wait = new WebDriverWait(driver, getTimeForLoadElement())
                .ignoring(java.util.NoSuchElementException.class, ElementNotVisibleException.class);
    }

    public static void waitImplicit(){
        driver.manage().timeouts().implicitlyWait(getTimeForLoadElement(), TimeUnit.SECONDS);
    }

    public static void waitExplicide(String locator){
        WebDriverWait wait = new WebDriverWait(driver, getTimeForLoadElement());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public static void waitElementsExplicide(String locator){
        WebDriverWait wait = new WebDriverWait(driver, getTimeForLoadElement());
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
    }


    public static void navigateUrl(String url){
        driver.navigate().to(url);
    }



    public static Long getTimeForLoad(){
        return Long.parseLong(configFile.getConfigProperty("timeoutJs"));

    }

    public static Long getTimeForLoadElement(){
        return Long.parseLong(configFile.getConfigProperty("timeout"));
    }

    public static void waitPageLoad(){
        try {
            Thread.sleep(getTimeForLoad());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
