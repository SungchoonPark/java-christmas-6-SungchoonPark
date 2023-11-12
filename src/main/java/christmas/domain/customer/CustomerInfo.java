package christmas.domain.customer;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;

import java.util.List;

public class CustomerInfo {
    private VisitDate visitDate;
    private Orders orders;

    private CustomerInfo(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    public static CustomerInfo of(VisitDate visitDate, Orders orders) {
        return new CustomerInfo(visitDate, orders);
    }

    @Override
    public String toString() {
        return orders.toString() + "\n" + visitDate.getVisitDate();
    }
}
