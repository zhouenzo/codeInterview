package array;


import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 * @author enzo
 * @date 2019/4/4 12:53
 **/

@RunWith(Parameterized.class)
public class FindDupCountInArrayTest {

    int[] input = {};
    Integer[] expect = {};

    @Parameterized.Parameters
    public static Collection data(){
        int[] inputArray1 = {0,2,4,5,2,4,1};
        Integer[] outputArray1 = {2,4};
        int[] inputArray2 = {0,0,0,0,0};
        Integer[] outputArray2 = {0};
        int[] inputArray3 = {0,2,4,5,2,-1,2};
        Integer[] outputArray3 = {};
        int[] inputArray4 = {0,2,4,5,2,4,8};
        Integer[] outputArray4 = {};
        return Arrays.asList(new Object[][]{
                {inputArray1,outputArray1},
                {inputArray2,outputArray2},
                {inputArray3,outputArray3},
                {inputArray4,outputArray4}

        });
    }

    public FindDupCountInArrayTest(int[] input,Integer[] expect){
        this.expect = expect;
        this.input = input;
    }

    @Test
    public void testFindDupCount() {

        try {
            assertArrayEquals(expect, new FindDupCountInArray().findDupCount(input).toArray());
            //fail("a inputError have been thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(),containsString("input error"));
        }


    }

    @Test
    public void testFindAnyDupCount(){
        Set s = new HashSet();
        //s.add(2);
        s.add(-1);
        int[] input = {1,8,5,4,3,2,6,7};
        boolean signal = s.contains(new FindDupCountInArray().findAnyDupCount(input));
        assertTrue(signal);
    }

}