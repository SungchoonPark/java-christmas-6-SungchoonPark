package christmas.handler;

import christmas.constant.EventType;
import christmas.constant.MenuType;
import christmas.domain.customer.CustomerInfo;
import christmas.domain.event.EventStatus;

public class EventHandler {
    /***
     * D-Day 이벤트를 제외한 나머지 이벤트는 12월 내내 진행합니다.
     * 10,000원 이상 주문해야 이벤트 적용이 됩니다.
     */
    private final EventStatus eventStatus;

    public EventHandler(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void applyDiscount(CustomerInfo customerInfo) {
        if (!customerInfo.isOverMinAmount()) {
            return;
        }
        applyDdayEvent(customerInfo);
        applyWeekEvent(customerInfo);
        applySpecialEvent(customerInfo);
        applyGiveawayEvent(customerInfo);
    }

    /***
     *  25일까지 1000원으로 시작하여 매일 100원씩 할인 금액 증가
     */
    public void applyDdayEvent(CustomerInfo customerInfo) {
        if (customerInfo.isOverChristmas()) {
            return;
        }
        updateEventNum(EventType.CHRISTMAS_DDAY, customerInfo.getDdayEventApplyDay());
    }

    public void applyWeekEvent(CustomerInfo customerInfo) {
        if (customerInfo.isVisitDateWeekend()) {
            applyWeekendEvent(customerInfo);
        }
        applyWeeklyEvent(customerInfo);
    }

    /***
     *  평일(일 ~ 목) 이벤트 적용 해주는 메서드
     *  디저트 메뉴 1개당 2023원 할인
     */
    public void applyWeeklyEvent(CustomerInfo customerInfo) {
        updateEventNum(EventType.WEEKDAYS, MenuType.DESSERT, customerInfo);
    }

    //

    /***
     *  주말(금 ~ 토) 이벤트 적용 해주는 메서드
     *  메인 메뉴 1개당 2023원 할인
     */
    public void applyWeekendEvent(CustomerInfo customerInfo) {
        updateEventNum(EventType.WEEKENDS, MenuType.MAIN, customerInfo);
    }

    /***
     *  할인 전 총 주문금액이 12만원 이상이면, 샴페인 1개 증정
     */
    public void applyGiveawayEvent(CustomerInfo customerInfo) {
        if (customerInfo.isApplyGiveawayEvent()) {
            updateEventNum(EventType.GIVE, 1);
        }
    }

    /***
     *  12월 중 별이 있는 날(일요일, 25일)에 총 주문금액에서 1000원 할인
     */
    public void applySpecialEvent(CustomerInfo customerInfo) {
        if (customerInfo.isSpecialDay()) {
            updateEventNum(EventType.SPECIAL_DAY, 1);
        }
    }

    private void updateEventNum(EventType eventType, MenuType menuType, CustomerInfo customerInfo) {
        eventStatus.updateEventTypeNum(eventType, customerInfo.getOrderNumByMenuType(menuType));
    }

    private void updateEventNum(EventType eventType, int value) {
        eventStatus.updateEventTypeNum(eventType, value);
    }

    /***
     * 할인 총액 구하기
     */

}
