package christmas.domain.order;

import christmas.constant.MenuType;

import java.util.List;

public class Orders {
    private List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(List<Order> orders) {
        return new Orders(orders);
    }

    public int getTotalOrderAmount() {
        return orders.stream()
                .map(Order::getOrderAmount)
                .reduce(0, Integer::sum);
    }

    public int getOrderNumByMenuType(MenuType menuType) {
        return (int) orders.stream()
                .filter(order -> order.isMatchMenuType(menuType))
                .count();
    }
}
