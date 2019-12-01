package com.comp6591.service.imp;

import com.comp6591.service.CalcultorStrategy;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;

import java.util.Stack;

public class CertaintyCalculator implements CalcultorStrategy {

    @Override
    public String getValue(String polynomial) {

        Stack<String> plStack = new Stack<>();

        String[] plItems = polynomial.split(" ");

        if (plItems.length == 1) {

            plStack.push(strToFloat(plItems[0]) + "");

        } else {
            for (String s : plItems) {
                if (!s.equals(")")) {
                    plStack.push(s);
                } else {

                    String element1 = plStack.pop().trim();
                    String operator = plStack.pop().trim();
                    String element2 = plStack.pop().trim();

                    plStack.pop();
                    plStack.push(certaintyCalculator(element1, operator, element2));
                }
            }
        }

        if (plStack.size() != 1) {
            throw new RuntimeException("Error occurs in calculate the Bag semantics!");
        } else {
            return String.valueOf(plStack.pop());
        }
    }


    private String certaintyCalculator(String element1, String operator, String element2) {

        float certainty1 = strToFloat(element1);
        float certainty2 = strToFloat(element2);
        float result;

        if (operator.equals(Constants.DOT)) {
            result = Math.min(certainty1, certainty2);
        } else if (operator.equals(Constants.ADD)) {
            result = Math.max(certainty1, certainty2);
        } else {
            throw new RuntimeException("Invailid Operator!");
        }
        return String.valueOf(result);
    }

    private float strToFloat(String str) {
        return Util.isTag(str) ? Constants.TAGS.get(str).getCertainty() : Float.valueOf(str);
    }

}
