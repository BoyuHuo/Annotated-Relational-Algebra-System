package com.comp6591.service.imp;

import com.comp6591.entity.Annotation;
import com.comp6591.utils.Constants;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class CalculatorTest {

    @BeforeAll
    public static void beforeAll() {
        Annotation annotation = Annotation.builder()
                .bag(2)
                .maybe(true)
                .certainty(0.2f)
                .build();
        Constants.TAGS.put("tagP", annotation);

        annotation = Annotation.builder()
                .bag(5)
                .maybe(false)
                .certainty(0.7f)
                .build();
        Constants.TAGS.put("tagR", annotation);

        annotation = Annotation.builder()
                .bag(1)
                .maybe(true)
                .certainty(0.4f)
                .build();
        Constants.TAGS.put("tagS", annotation);
    }

    @Test
    public void getBagValue() {

        BagCalculator bagCalculator = new BagCalculator();

        String query = "( ( tagP x tagP ) + ( tagP x tagP ) )";
        String result = "8";
        Assert.assertEquals(result, bagCalculator.getValue(query));

        query = "( tagP x tagR )";
        result = "10";
        Assert.assertEquals(result, bagCalculator.getValue(query));

        query = "( tagP x tagR )";
        result = "10";
        Assert.assertEquals(result, bagCalculator.getValue(query));

        query = "( ( ( tagR x tagR ) + ( tagR x tagR ) ) + ( tagR x tagS ) )";
        result = "55";
        Assert.assertEquals(result, bagCalculator.getValue(query));

        query = "( ( ( tagS x tagS ) + ( tagS x tagS ) ) + ( tagR x tagS ) )";
        result = "7";
        Assert.assertEquals(result, bagCalculator.getValue(query));
    }


    @Test
    public void getCertaintyValue() {

        CertaintyCalculator calculator = new CertaintyCalculator();

        String query = "( ( tagP x tagP ) + ( tagP x tagP ) )";
        String result = "0.2";
        Assert.assertEquals(result, calculator.getValue(query));

        query = "( tagP x tagR )";
        result = "0.2";
        Assert.assertEquals(result, calculator.getValue(query));

        query = "( tagP x tagR )";
        result = "0.2";
        Assert.assertEquals(result, calculator.getValue(query));

        query = "( ( ( tagR x tagR ) + ( tagR x tagR ) ) + ( tagR x tagS ) )";
        result = "0.7";
        Assert.assertEquals(result, calculator.getValue(query));

        query = "( ( ( tagS x tagS ) + ( tagS x tagS ) ) + ( tagR x tagS ) )";
        result = "0.4";
        Assert.assertEquals(result, calculator.getValue(query));
    }

    @Test
    public void getMaybeValue() {

        MaybeCalculator maybeCalculator = new MaybeCalculator();

        String query = "( ( tagP x tagP ) + ( tagP x tagP ) )";
        String result = "1";
        Assert.assertEquals(result, maybeCalculator.getValue(query));

        query = "( tagP x tagR )";
        result = "0";
        Assert.assertEquals(result, maybeCalculator.getValue(query));

        query = "( tagP x tagR )";
        result = "0";
        Assert.assertEquals(result, maybeCalculator.getValue(query));

        query = "( ( ( tagR x tagR ) + ( tagR x tagR ) ) + ( tagR x tagS ) )";
        result = "0";
        Assert.assertEquals(result, maybeCalculator.getValue(query));

        query = "( ( ( tagS x tagS ) + ( tagS x tagS ) ) + ( tagR x tagS ) )";
        result = "1";
        Assert.assertEquals(result, maybeCalculator.getValue(query));
    }



}
