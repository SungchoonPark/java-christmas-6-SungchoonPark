package christmas.domain.customer;

import christmas.constant.MenuType;
import christmas.constant.NumConstant;
import christmas.domain.order.Orders;

import java.time.DayOfWeek;
import java.util.Map;

public class CustomerInfo {
    private final VisitDate visitDate;
    private final Orders orders;

    private CustomerInfo(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    public static CustomerInfo of(VisitDate visitDate, Orders orders) {
        return new CustomerInfo(visitDate, orders);
    }

    public boolean isOverMinAmount() {
        if (getTotalOrderAmount() > NumConstant.MIN_EVENT_APPLY_AMOUNT.getValue()) {
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
        if (orders.getTotalOrderAmount() >= NumConstant.MIN_GIVEAWAY_AMOUNT.getValue()) {
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
