package com.ruan.polestar.event.listen;

import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

public class RuanSource {

    private int state = 0;

    private String msg = "";

    Set<EventListener> listeners = new HashSet<EventListener>();

    public void addStateChangeListener(StateChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyListener() {
        for (EventListener listener : listeners) {
            try {
                ((StateChangeListener) listener).handleEvent(new RuanEvent(this));
            } catch (Exception e) {

            }
        }
    }

    public void changeState() {
        state = (state == 0 ? 1 : 0);
        msg = "state changed";
        notifyListener();
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }
}
