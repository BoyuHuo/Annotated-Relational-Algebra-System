package com.comp6591.service.imp;

import com.comp6591.service.CalcultorStrategyInterface;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;
import org.apache.commons.lang3.StringUtils;


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ProbabilityCalculator implements CalcultorStrategyInterface {


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
                plStack.push(simplify(element1, operator, element2));
            }
        }

        if (plStack.size() != 1) {
            throw new RuntimeException("Error occurs in calculte the Bag semantics!");
        } else {

            String simplePoly = plStack.pop();
            plStack.clear();

            plItems = simplePoly.trim().split(" ");
            if (plItems.length == 1) {
                return String.valueOf(Constants.TAGS.get(plItems[0]).getProbability());
            } else {

                for (String s : plItems) {

                    if (!s.equals(")")) {
                        plStack.push(s);
                    } else {

                        String element1 = plStack.pop().trim();
                        String operator = plStack.pop().trim();
                        String element2 = plStack.pop().trim();

                        plStack.pop();
                        plStack.push(compute(element1, operator, element2));
                    }
                }

                if (plStack.size() != 1) {
                    throw new RuntimeException("Error occurs in calculte the Bag semantics!");
                } else {
                    return plStack.pop();
                }
            }
        }
    }


    private String compute(String element1, String operator, String element2) {

        float prob1 = strToFloat(element1);
        float prob2 = strToFloat(element2);
        float result;

        if (operator.equals(Constants.DOT)) {
            result = prob1 * prob2;
        } else if (operator.equals(Constants.ADD)) {
            result = prob1 + prob2;
        } else {
            throw new RuntimeException("Invailid Operator!");
        }
        return String.valueOf(result);
    }

    private float strToFloat(String str) {

        return Util.isTag(str)? Constants.TAGS.get(str).getProbability() : Float.valueOf(str);
    }


    private String simplify(String element1, String operator, String element2) {

        if (element1.trim().equals(element2.trim())) {
            return element1;
        }

        if (element1.length() < element2.length()) {
            return absorb(element1, operator, element2);
        } else {
            return absorb(element2, operator, element1);
        }

    }

    private String absorb(String element1, String operator, String element2) {

        if (operator.equals(Constants.ADD)) {

            return StringUtils.contains(element2, element1)? element1 : combineStr(element1, operator, element2);

        } else if (operator.equals(Constants.DOT)) {

            return StringUtils.contains(element2, element1)? element2 : combineStr(element1, operator, element2);

        } else {
            throw new RuntimeException("Invalid operator!");
        }
    }

    private String combineStr(String element1, String operator, String element2) {
        return " ( " + element1 + " " + operator + " " + element2 + " ) ";
    }






}
