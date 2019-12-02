package com.comp6591.service.imp;

import com.comp6591.service.CalcultorStrategy;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;

import java.util.Stack;

public class ProbabilityCalculator implements CalcultorStrategy {

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
                    plStack.push(probCalculator(element1, operator, element2));
                }
            }
        }



        if (plStack.size() != 1) {
            throw new RuntimeException("Error occurs in calculte the Bag semantics!");
        } else {
            return String.valueOf(plStack.pop());
        }
    }


    private String probCalculator(String element1, String operator, String element2) {

        Float num1 = strToFloat(element1);
        Float num2 = strToFloat(element2);
        Float result;

        if (operator.equals(Constants.ADD)) {
            result = num1 + num2;
        } else if (operator.equals(Constants.DOT)) {
            result = num1 * num2;
        } else {
            throw new RuntimeException("Invailid Operator!");
        }
        return String.valueOf(result);
    }

    private float strToFloat(String str) {
        return Util.isTag(str) ? Constants.TAGS.get(str).getProbability() : Float.valueOf(str);
    }
}
