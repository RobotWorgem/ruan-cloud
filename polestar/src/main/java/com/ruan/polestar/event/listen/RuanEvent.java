package com.ruan.polestar.event.listen;

import java.util.EventObject;

public class RuanEvent extends EventObject {

    private int state;
    private String msg;

    public int getSourceState() {
        return state;
    }

    public String getSourceMsg() {
        return msg;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RuanEvent(Object source) {
        super(source);
        this.state = ((RuanSource) source).getState();
        this.msg = ((RuanSource) source).getMsg();
    }


}
