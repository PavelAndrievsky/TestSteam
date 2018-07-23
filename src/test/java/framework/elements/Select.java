package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Select extends BaseElement {

    WebElement select;
    private List<WebElement> list;

    public Select(By by) {
        this.select= findElement(by);
    }

    @Override
    public void click() {
        Actions action = new Actions(super.getDriver());
        action.moveToElement(select).build().perform();
        select.click();
    }

    @Override
    public void clickAndWait() {
        select.click();
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configFile.getConfigProperty("timeout")));
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public String getText(){
        return select.getText();
    }


    @Override
    public void moveTo() {
        Actions action = new Actions(super.getDriver());
        action.moveToElement(select).build().perform();
    }

    @Override
    public String getAttribute(String str) {
        return null;
    }

}
