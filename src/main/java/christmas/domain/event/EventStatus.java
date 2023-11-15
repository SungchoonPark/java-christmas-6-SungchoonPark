package christmas.domain.event;

import christmas.constant.EventType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EventStatus {
    private final Map<EventType, Integer> eventStatus;

    private EventStatus() {
        eventStatus = new EnumMap<>(EventType.class);

        for (EventType event : EventType.values()) {
            eventStatus.put(event, 0);
        }
    }

    public int getEventApplyNum(EventType eventType) {
        return eventStatus.get(eventType);
    }

    public static EventStatus createInstance() {
        return new EventStatus();
    }

    public void updateEventTypeNum(EventType eventType, int updateNum) {
        eventStatus.put(eventType, eventStatus.get(eventType) + updateNum);
    }

    public int getTotalBenefitAmount() {
        return Arrays.stream(EventType.values())
                .mapToInt(this::getEventAmount)
                .sum();
    }

    public int getTotalDiscountAmount() {
        return Arrays.stream(EventType.values())
                .filter(eventType -> eventType != EventType.GIVE)
                .mapToInt(this::getEventAmount)
                .sum();
    }

    public Map<String, Integer> getEachApplyEvent() {
        return Arrays.stream(EventType.values())
                .filter(eventType -> getEventAmount(eventType) != 0)
                .collect(Collectors.toMap(EventType::getEventName, this::getEventAmount));
    }

    public int getEventAmount(EventType eventType) {
        return eventType.getEventAmount(eventType, eventStatus.get(eventType));
    }

}
