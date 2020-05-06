package com.ruan.mq;

public class SynTest {

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println("a start lock syn class");
            synchronized (SynTest.class) {
                System.out.println("a locked syn class");
                System.out.println("I WILL lock the class 1min");
                try {
                    Thread.sleep(60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        SynTest synTest = new SynTest();
        a.start();

        synTest.locklock();
    }

    public void locklock() {

        Thread b = new Thread(() -> {
            System.out.println("B want to lock syn class");
            long s = System.currentTimeMillis();
            synchronized (SynTest.class) {
                System.out.println("B GET lock syn class");
            }
            long e = System.currentTimeMillis();
            System.out.println(e-s);
        });
        b.start();
    }

    ;

}
