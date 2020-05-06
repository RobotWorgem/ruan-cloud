package com.ruan.polestar.event.listenex2;

public class StateChangeListener2 implements CallableEventListener {
    MyEvent2 event;

    public StateChangeListener2(MyEvent2 event) {
        this.event = event;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Listener - Processing");
        System.out.println("Listener -  fire event " + event.getSourceMessage());
        return "SUCCESS";
    }
}
