package christmas.service;

import christmas.constant.Badge;
import christmas.domain.customer.CustomerInfo;
import christmas.domain.event.EventStatus;
import christmas.handler.EventHandler;

import java.util.Collections;
import java.util.Map;

public class EventService {
    private final EventHandler eventHandler;
    private final CustomerInfo customerInfo;

    private EventService(CustomerInfo customerInfo) {
        eventHandler = new EventHandler(new EventStatus());
        this.customerInfo = customerInfo;
    }

    public static EventService of(CustomerInfo customerInfo) {
        return new EventService(customerInfo);
    }

    public void applyDiscount() {
        eventHandler.applyDiscount(customerInfo);
    }

    public int getTotalBenefitAmount() {
        if (customerInfo.isOverMinAmount()) {
            return eventHandler.getTotalBenefitAmount();
        }
        return 0;
    }

    public Map<String, Integer> getBenefits() {
        if (customerInfo.isOverMinAmount()) {
            return eventHandler.getBenefits();
        }
        return Collections.emptyMap();
    }

    public Map<String, Integer> getOrderList() {
        return customerInfo.getOrderList();
    }

    public String getGiveaway() {
        if (customerInfo.isApplyGiveawayEvent()) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public int getTotalOrderAmount() {
        return customerInfo.getTotalOrderAmount();
    }

    public int getTotalDiscountAmount() {
        if (customerInfo.isOverMinAmount()) {
            return eventHandler.getTotalDiscountAmount();
        }
        return 0;
    }

    public String getBadge() {
        return Badge.getBadge(eventHandler.getTotalBenefitAmount());
    }

}
