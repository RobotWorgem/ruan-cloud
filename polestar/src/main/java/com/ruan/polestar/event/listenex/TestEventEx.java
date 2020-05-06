package com.ruan.polestar.event.listenex;

public class TestEventEx {
    public static void main(String[] args) {
        SourceEx source = new SourceEx();
        ExEventListener listener = new ExStateChangeListener();
        ListenerRegistry registry = new ListenerRegistry("change");
        registry.addListener("change", listener);
        source.registryListener(registry);

        source.changeState();
    }
}
