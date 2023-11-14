package christmas.domain.order;

import christmas.constant.MenuType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class OrderStatus {
    private final Map<MenuType, Integer> orderMenuTypeAndNum;

    public OrderStatus() {
        orderMenuTypeAndNum = new EnumMap<>(MenuType.class);
        for (MenuType menuType : MenuType.values()) {
            orderMenuTypeAndNum.put(menuType, 0);
        }
    }

    public void updateMenuTypeNum(MenuType menuType) {
        orderMenuTypeAndNum.put(menuType, orderMenuTypeAndNum.get(menuType) + 1);
    }

    public boolean isOnlyDrink() {
        for (MenuType menuType : MenuType.values()) {
            if (menuType != MenuType.DRINK && orderMenuTypeAndNum.get(menuType) > 0) {
                return false;
            }
        }
        return true;
    }
}
