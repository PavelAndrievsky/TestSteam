package framework.elements;

import framework.BaseEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BaseElement extends BaseEntity {

    private WebElement element;

    public BaseElement() {
    }

    public abstract void click();

    public abstract void clickAndWait();

    public abstract String getText();

    public abstract String getAttribute(String str);

    public abstract void moveTo();

    public List<WebElement> findElements(String by) {
        return getDriver().findElements(By.xpath(by));

    }

    public WebElement findElement(By locator) {

        return getDriver().findElement(locator);
    }

}