package christmas.service;

import christmas.domain.menu.Menus;
import christmas.handler.EventHandler;

public class EventService {
    private EventHandler eventHandler;
    private Menus menus;

    private EventService(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public static EventService from(EventHandler eventHandler) {
        return new EventService(eventHandler);
    }

}
