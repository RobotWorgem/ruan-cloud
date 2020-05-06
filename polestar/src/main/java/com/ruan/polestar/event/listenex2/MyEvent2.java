package com.ruan.polestar.event.listenex2;

import java.util.EventObject;

public class MyEvent2 extends EventObject {

    private static final long serialVersionUID = 1L;
    private int state;
    private String msg;

    public MyEvent2(Object source) {
        super(source);
        state = ((Source2) source).getState();
        msg = ((Source2) source).getMessage();
    }

    public int getSourceState() {
        return this.state;
    }

    public String getSourceMessage() {
        return this.msg;
    }
}
