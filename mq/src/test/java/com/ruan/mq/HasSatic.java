package com.ruan.mq;

public class HasSatic {

    private static int x =100;

    public static void main(String[] args) {
        HasSatic h1 = new HasSatic();
        h1.x++;
        HasSatic h2 = new HasSatic();
        h2.x++;
        h1 = new HasSatic();
        h1.x++;
        HasSatic.x--;
        System.out.println(x);
    }
}
