package com.ruan.polestar.event.listenex2;

public class Source2 {
    private int state = 0;
    private String msg = "";

    private ListenerRegistry2 registry;

    public void registryListener(ListenerRegistry2 registry) {
        this.registry = registry;
    }

    public void changeState() {
        state = (state == 0 ? 1 : 0);
        msg = "State Changed.";
        System.out.println("Source - " + msg);
        try {
            registry.notifyListener("change", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getState() {
        return this.state;
    }

    public String getMessage() {
        return this.msg;
    }
}
