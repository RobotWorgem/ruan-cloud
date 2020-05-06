package com.ruan.polestar.event.listenex;

import java.util.EventListener;
import java.util.EventObject;

public interface ExEventListener extends EventListener {
    void handleEvent(EventObject event);
}
