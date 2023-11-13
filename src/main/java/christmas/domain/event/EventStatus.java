package christmas.domain.event;

import christmas.constant.EventType;

import java.util.EnumMap;
import java.util.Map;

public class EventStatus {
    private final Map<EventType, Integer> eventStatus;

    public EventStatus() {
        eventStatus = new EnumMap<>(EventType.class);

        for (EventType event : EventType.values()) {
            eventStatus.put(event, 0);
        }
    }

    public void updateEventTypeNum(EventType eventType, int updateNum) {
        eventStatus.put(eventType, eventStatus.get(eventType) + updateNum);
    }

    public int getTotalBenefitAmount() {
        int totalAmount = 0;
        for (EventType eventType : EventType.values()) {
            int eventAmount = eventType.getEventAmount(eventType, eventStatus.get(eventType));
//            totalAmount += eventType.getEventAmount(eventType, eventStatus.get(eventType));
            totalAmount += eventAmount;
        }

        return totalAmount;
    }

}
