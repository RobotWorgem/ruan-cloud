package com.ruan.mq;

import org.hamcrest.core.Is;

import java.util.HashMap;
import java.util.Map;

public class StringTest {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "abc";
        String s3 = s1 + "c";
        System.out.println(s2 == s3);
        System.out.println(s2.equals(s3));

        String sss = "nineeightsevensixfivefourthreetwoonezero";
        Map map = new HashMap();
        map.put("nine", 9);
        map.put("eight", 8);
        map.put("seven", 7);
        map.put("six", 6);
        map.put("five", 5);
        map.put("four", 4);
        map.put("three", 3);
        map.put("two", 2);
        map.put("one", 1);
        map.put("zero", 0);

        int i = 1;
        int length = sss.length();
        while (length > 0) {
            String key = sss.substring(0, i);
            while (map.containsKey(key)) {
                System.out.println(map.get(key));
                sss = sss.substring(i, length);
                length = sss.length();
                if (length == 0){
                    break;
                }
                i = 1;
                key = sss.substring(0, i);
            }
            i++;
        }
    }
}
