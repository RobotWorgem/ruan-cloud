package com.ruan.polestar.event.listenex;

import java.util.EventObject;

public class ExStateChangeListener implements ExEventListener {


    @Override
    public void handleEvent(EventObject event) {
        System.out.println("listener-" + ((MyEventEx) event).getSourceMessage() + " handled.");
    }
}
