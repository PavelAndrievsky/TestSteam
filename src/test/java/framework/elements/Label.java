package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Label extends BaseElement {

    WebElement label;

    public Label() {
    }

    public Label(By by) {
        this.label = findElement(by);
    }

    public Label(WebElement label) {
        this.label = label;
    }


    @Override
    public void click() {
        label.click();
    }

    @Override
    public void clickAndWait(){}

    @Override
    public void moveTo() {
    }

    @Override
    public String getAttribute(String str) {
        return null;
    }

    public Label findElementByInd(List<Label> elements, int ind) {
        return elements.get(ind);

    }

    public String getText() {
        return label.getText();
    }

    public List<Label> getNecessaryElements(String by) {
        List<WebElement> webElementList = super.findElements(by);
        List<Label> labelList = new ArrayList<>();
        for (WebElement webElement : webElementList) {
            labelList.add(new Label(webElement));
        }
        return labelList;
    }
}
