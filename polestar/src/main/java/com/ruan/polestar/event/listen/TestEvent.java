package com.ruan.polestar.event.listen;

public class TestEvent {

    public static void main(String[] args) {
        RuanSource source = new RuanSource();
        source.addStateChangeListener(new StateChangeListener());
        source.changeState();
    }
}
