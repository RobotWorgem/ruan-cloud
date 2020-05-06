package com.ruan.mq;

public class Tess {
    String string = new String("good");

    char[] chars = {'a', 'b', 'c'};


    public static void main(String[] args) {
        Tess t = new Tess();
        t.change(t.string, t.chars);
        System.out.print(t.string + " and ");
        System.out.print(t.chars);
    }

    public void change(String str, char[] c) {
        str = "test ok";
        c[0] = 'g';
    }
}
