package com.ruan.polestar.event.publish;

public class TestPublish {

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.getEvents().subscriber("change", new Subscriber());
        publisher.change();
    }
}
