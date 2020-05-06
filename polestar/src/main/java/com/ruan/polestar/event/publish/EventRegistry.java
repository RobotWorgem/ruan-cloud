package com.ruan.polestar.event.publish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRegistry {

    Map<String, List<Subscriber>> subscribers = new HashMap<>();

    public EventRegistry(String eventType) {
        this.subscribers.put(eventType, new ArrayList<>());
    }

    public void subscriber(String eventType, Subscriber subscriber) {
        List<Subscriber> users = this.subscribers.get(eventType);
        users.add(subscriber);
    }

    public void notify(String eventType){
        List<Subscriber> users = this.subscribers.get(eventType);
        for (Subscriber user : users) {
            user.handle(eventType);
        }
    }
}
