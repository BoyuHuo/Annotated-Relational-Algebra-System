package com.comp6591.service.imp;

import com.comp6591.service.CalcultorStrategyInterface;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;

import java.util.Stack;

public class MaybeCalculator implements CalcultorStrategyInterface {


    @Override
    public String getValue(String polynomial) {

        Stack<String> plStack = new Stack<>();

        String[] plItems = polynomial.split(" ");

        for (String s : plItems) {

            if (!s.equals(")")) {
                plStack.push(s);
            } else {

                String element1 = plStack.pop().trim();
                String operator = plStack.pop().trim();
                String element2 = plStack.pop().trim();

                plStack.pop();
                plStack.push(maybeCalculator(element1, operator, element2));
            }
        }

        if (plStack.size() != 1) {
            throw new RuntimeException("Error occurs in calculte the Bag semantics!");
        } else {
            return String.valueOf(plStack.pop());
        }
    }


    private String maybeCalculator(String element1, String operator, String element2) {

        boolean bool1 = strToBool(element1);
        boolean bool2 = strToBool(element2);
        boolean result;

        if (operator.equals(Constants.DOT)) {
            result = bool1 && bool2;
        } else if (operator.equals(Constants.ADD)) {
            result = bool1 || bool2;
        } else {
            throw new RuntimeException("Invailid Operator!");
        }
        return String.valueOf(result);
    }

    private boolean strToBool(String str) {

        return Boolean.valueOf(Util.isTag(str)? Constants.TAGS.get(str) : str);
    }

}
