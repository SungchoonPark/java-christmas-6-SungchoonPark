package christmas.domain.customer;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;

import java.util.List;

public class CustomerInfo {
    private Orders orders;
    private VisitDate visitDate;

    private CustomerInfo(List<Order> orders, int visitDate) {
        this.orders = Orders.from(orders);
        this.visitDate = VisitDate.from(visitDate);
    }

    public static CustomerInfo of(List<Order> orders, int visitDate) {
        return new CustomerInfo(orders, visitDate);
    }
}
