package com.ruan.polestar.event.listenex;

import java.util.EventObject;

public class MyEventEx extends EventObject {

    private static final long serialVersionUID = 1L;
    private int state;
    private String msg;

    public MyEventEx(Object source) {
        super(source);
        state = ((SourceEx) source).getState();
        msg = ((SourceEx) source).getMessage();
    }

    public int getSourceState() {
        return this.state;
    }

    public String getSourceMessage() {
        return this.msg;
    }
}
