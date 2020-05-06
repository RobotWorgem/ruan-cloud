package com.ruan.mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompressTest {

    public static void main(String[] args) {
        System.out.println(compressStr("aaacccdefaacc"));
    }

    public static String compressStr(String str) {
        List<String> list = new ArrayList();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int count = 0;
            int j = i;
            for (; j < chars.length; j++, count++) {
                if (chars[i] != chars[j]) {
                    break;
                }
            }
            if (count > 1) {
                list.add(String.valueOf(chars[i]) + count);
            } else {
                list.add(String.valueOf(chars[i]));
            }
            i = j - 1;
        }
        StringBuilder strB = new StringBuilder();
        list.forEach(e->{
            strB.append(e);
        });
        return strB.toString();
    }
}
