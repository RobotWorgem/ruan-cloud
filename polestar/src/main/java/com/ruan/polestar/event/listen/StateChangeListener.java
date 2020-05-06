package com.ruan.polestar.event.listen;

import java.util.EventListener;

public class StateChangeListener  implements EventListener {

    public void handleEvent(RuanEvent event) {
        System.out.println(event.toString() + " fire event " + event.getSourceMsg());
    }
}
