package christmas.domain.order;

import java.util.List;

public class Orders {
    private List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(List<Order> orders) {
        return new Orders(orders);
    }
}
