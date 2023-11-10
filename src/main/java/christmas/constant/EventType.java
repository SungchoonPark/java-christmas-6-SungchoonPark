package christmas.constant;

public enum EventType {
    CHRISMAS_DDAY("크리스마스 디데이 할인: ", -1000),
    WEEKDAYS("평일 할인: ", -2023),
    WEEKENDS("주말 할인: ", -2023),
    SPECIAL_DAY("특별 할인: ", -1000),
    GIVE("증정 이벤트: ", -25000);
    private String eventName;
    private int amount;

    EventType(String eventName, int amount) {
        this.eventName = eventName;
        this.amount = amount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAmount() {
        return amount;
    }
}
