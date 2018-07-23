package framework.elements;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Image extends BaseElement {

    WebElement image;

    public Image(){}

    public Image(WebElement image){
        this.image=image;
    }

    @Override
    public void click() {
        image.click();
    }

    @Override
    public void clickAndWait(){}

    public Image findElementByInd(List<Image> elements, int ind) {
        return  elements.get(ind);

    }

    public List<Image> getConvertedElements(String by) {
        List<WebElement> webElementList = super.findElements(by);
        List<Image> imageList= new ArrayList<>();
        for(WebElement webEl: webElementList){
            imageList.add(new Image(webEl));
        }
        return imageList;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getAttribute(String str) {
        return null;
    }

    @Override
    public void moveTo() {}
}
