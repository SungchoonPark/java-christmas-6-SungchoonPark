package christmas.domain.order;

import christmas.constant.MenuType;
import christmas.constant.NumConstant;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class OrderStatus {
    private final Map<MenuType, Integer> orderMenuTypeAndNum;

    private OrderStatus() {
        orderMenuTypeAndNum = new EnumMap<>(MenuType.class);
        Arrays.stream(MenuType.values())
                .forEach(menuType -> orderMenuTypeAndNum.put(menuType, NumConstant.ZERO.getValue()));
    }

    public static OrderStatus createInstance() {
        return new OrderStatus();
    }

    public void updateMenuTypeNum(MenuType menuType) {
        orderMenuTypeAndNum.put(menuType, orderMenuTypeAndNum.get(menuType) + 1);
    }

    public boolean isOnlyDrink() {
        for (MenuType menuType : MenuType.values()) {
            if (menuType != MenuType.DRINK && orderMenuTypeAndNum.get(menuType) > NumConstant.ZERO.getValue()) {
                return false;
            }
        }
        return true;
    }
}
