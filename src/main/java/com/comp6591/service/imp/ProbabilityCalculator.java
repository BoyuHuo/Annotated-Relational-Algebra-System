/*
package com.comp6591.service.imp;

import com.comp6591.service.CalcultorStrategyInterface;
import com.comp6591.utils.Constants;
import org.apache.commons.lang3.StringUtils;


import java.util.HashSet;
import java.util.Set;

public class ProbabilityCalculator implements CalcultorStrategyInterface {


    @Override
    public String getValue(String polynomial) {

        Set<String> itemSet = new HashSet<>();

        String[] subPls = polynomial.split(Constants.ADD);

        for (String each : subPls) {
            String temp = removeBrackets(each).trim();


        }

        return "";


    }


    private String removeBrackets(String str) {

        str = StringUtils.remove(str, '(');
        str = StringUtils.remove(str, ')');
        return str;
    }






}
*/
