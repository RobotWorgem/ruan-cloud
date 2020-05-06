package com.ruan.mq;

public class Pass {

    static int j = 20;

    public static void main(String[] args) {
        int i = 10;
        Pass p = new Pass();
        p.method(i);
        System.out.println(i + "and" + j);
    }

    public static void method(int x) {
        x = x * 2;
        j = j * 2;
    }
}
