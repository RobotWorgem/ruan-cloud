package com.ruan.polestar.event.publish;

public class Publisher {

    private EventRegistry events;

    public EventRegistry getEvents() {
        return events;
    }

    public Publisher(String eventType) {
        this.events = new EventRegistry(eventType);
    }

    public Publisher() {
        this.events = new EventRegistry("change");
    }

    public void change(){
        events.notify("change");
    }
}
