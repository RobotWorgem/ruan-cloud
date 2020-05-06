package com.ruan.polestar.event.listenex;

public class SourceEx {

    private int state = 0;
    private String msg = "";
    private ListenerRegistry registry;

    public void registryListener(ListenerRegistry registry) {
        this.registry = registry;
    }

    public void fireEvent(String event) {
        System.out.println("Source - fire event - notify listener");
        registry.notifyListener(event, this);
    }

    public void changeState() {
        state = (state == 0 ? 1 : 0);
        msg = "State Changed.";
        System.out.println("Source - " + msg);
        fireEvent("change");
    }

    public int getState() {
        return this.state;
    }

    public String getMessage() {
        return this.msg;
    }
}
