package com.ruan.polestar.event.listenex2;

import java.util.EventListener;
import java.util.concurrent.Callable;

public interface CallableEventListener extends EventListener, Callable<String> {
}
