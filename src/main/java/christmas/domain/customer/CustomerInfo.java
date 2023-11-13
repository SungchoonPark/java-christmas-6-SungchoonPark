package christmas.domain.customer;

import christmas.constant.MenuType;
import christmas.domain.order.Orders;

import java.time.DayOfWeek;
import java.util.Map;

public class CustomerInfo {
    private static final int MIN_EVENT_APPLY_AMOUNT = 10000;

    private VisitDate visitDate;
    private Orders orders;

    private CustomerInfo(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    public static CustomerInfo of(VisitDate visitDate, Orders orders) {
        return new CustomerInfo(visitDate, orders);
    }

    public boolean isOverMinAmount() {
        if (getTotalOrderAmount() > MIN_EVENT_APPLY_AMOUNT) {
            return true;
        }
        return false;
    }

    public boolean isVisitDateWeekend() {
        return visitDate.isVisitDateWeekend();
    }

    public boolean isOverChristmas() {
        return visitDate.isOverChristmas();
    }

    public int getTotalOrderAmount() {
        return orders.getTotalOrderAmount();
    }

    public int getDdayEventApplyDay() {
        return visitDate.calculateDdayEventApplyDay();
    }

    public int getOrderNumByMenuType(MenuType menuType) {
        return orders.getOrderNumByMenuType(menuType);
    }

    public boolean isApplyGiveawayEvent() {
        if (orders.getTotalOrderAmount() >= 120000) {
            return true;
        }
        return false;
    }

    public boolean isSpecialDay() {
        return visitDate.isSpecialDay();
    }

    public Map<String, Integer> getOrderList() {
        return orders.getOrderList();
    }
}
