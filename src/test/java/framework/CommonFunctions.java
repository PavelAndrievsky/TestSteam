package framework;

import framework.elements.Label;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFunctions {

    public List<Integer> getListOfDiscounts(List<Label> list){
        List<Integer> specials = new ArrayList<>();
        Pattern pat = Pattern.compile("[0-9]+");
        for (Label el : list) {
            Matcher matcher = pat.matcher(el.getText());
            while (matcher.find()) {
                specials.add(new Integer(matcher.group()));
            }
        }

        return specials;
    }

    public int getIndMaxDiscount(List<Integer> discounts){
        int max = discounts.get(0);
        int ind = 0;
        int  maxInd = 0;
        for (Integer discount : discounts) {
            if (discount > max) {
                max = discount;
                maxInd = ind;
            }
            ind ++;
        }
        return maxInd;
    }

    public boolean checkFullDownLoad(String filePath,long size){
        File f = new File(filePath);
        if (f.exists()) {
            return f.length()==size;
        } else {
            return false;
        }

    }
}
