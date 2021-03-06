package com.comp6591.service.imp;

import com.comp6591.service.CalcultorStrategy;
import com.comp6591.utils.Constants;
import com.comp6591.utils.Util;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Stack;


@SpringBootTest
public class BagCalculator implements CalcultorStrategy {

    @Override
    public String getValue(String polynomial) {

        Stack<String> plStack = new Stack<>();

        String[] plItems = polynomial.split(" ");

        if (plItems.length == 1) {

            plStack.push(strToNum(plItems[0]) + "");

        } else {
            for (String s : plItems) {

                if (!s.equals(")")) {
                    plStack.push(s);
                } else {

                    String element1 = plStack.pop().trim();
                    String operator = plStack.pop().trim();
                    String element2 = plStack.pop().trim();

                    plStack.pop();
                    plStack.push(bagCalculator(element1, operator, element2));
                }
            }
        }



        if (plStack.size() != 1) {
            throw new RuntimeException("Error occurs in calculte the Bag semantics!");
        } else {
            return String.valueOf(plStack.pop());
        }
    }

    private String bagCalculator(String element1, String operator, String element2) {

        Integer num1 = strToNum(element1);
        Integer num2 = strToNum(element2);
        Integer result;

        if (operator.equals(Constants.ADD)) {
            result = Math.addExact(num1, num2);
        } else if (operator.equals(Constants.DOT)) {
            result = Math.multiplyExact(num1, num2);
        } else {
            throw new RuntimeException("Invailid Operator!");
        }
        return String.valueOf(result);
    }

    private Integer strToNum(String str) {

        return Util.isTag(str)? Constants.TAGS.get(str).getBag() : Integer.valueOf(str);
    }
}
