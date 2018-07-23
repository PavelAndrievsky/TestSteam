package framework.elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    WebElement button;

    public Button() {}

    public Button(By by) {
        this.button = findElement(by);
    }

    @Override
    public void click() {
        button.click();
    }

    @Override
    public void clickAndWait(){}

    public String getAttribute(String name) {
        return button.getAttribute(name);
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void moveTo() {}

}
