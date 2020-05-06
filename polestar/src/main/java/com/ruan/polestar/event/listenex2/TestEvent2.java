package com.ruan.polestar.event.listenex2;

import com.ruan.polestar.event.listenex.ExEventListener;
import com.ruan.polestar.event.listenex.ExStateChangeListener;

public class TestEvent2 {

    public static void main(String[] args) throws ClassNotFoundException {
        Source2 source = new Source2();
        ListenerRegistry2 listenerRegistry2 = new ListenerRegistry2("change");
        listenerRegistry2.addListener("change", Class.forName("com.ruan.polestar.event.listenex2.StateChangeListener2"));
        source.registryListener(listenerRegistry2);

        source.changeState();
    }
}
