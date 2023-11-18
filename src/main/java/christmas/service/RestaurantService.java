package christmas.service;

import christmas.constant.ErrorMessage;
import christmas.constant.MenuType;
import christmas.constant.Separator;
import christmas.domain.customer.CustomerInfo;
import christmas.domain.customer.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.domain.order.MenuNum;
import christmas.domain.order.Order;
import christmas.domain.order.OrderStatus;
import christmas.domain.order.Orders;
import christmas.validator.MenuAndNumValidator;

import java.util.*;


public class RestaurantService {
    private CustomerInfo customerInfo;
    private final Menus menus;
    private final OrderStatus orderStatus;

    private RestaurantService() {
        menus = Menus.getInstance();
        orderStatus = OrderStatus.createInstance();
    }

    public static RestaurantService createInstance() {
        return new RestaurantService();
    }

    public VisitDate createVisitDate(int visitDate) {
        return VisitDate.from(visitDate);
    }

    public Orders processCreateOrders(String menuAndNum) {
        Map<String, Integer> splittedMenuAndNum =
                MenuAndNumValidator.validateCustomerOrders(splitCustomerOrders(menuAndNum));

        List<Order> orderList = processCreateOrderList(splittedMenuAndNum);

        MenuAndNumValidator.validateOrderMenuType(orderStatus);

        return Orders.from(orderList);
    }

    private List<String> splitCustomerOrders(String customerOrder) {
        return Arrays.stream(customerOrder.split(Separator.ORDER_SEPARATOR.getSeparator())).toList();
    }

    private List<Order> processCreateOrderList(Map<String, Integer> splittedMenuAndNum) {
        List<Order> orders = new ArrayList<>();
        for (Map.Entry<String, Integer> splitCustomerOrder : splittedMenuAndNum.entrySet()) {
            createOrder(splitCustomerOrder, orders);
        }
        return orders;
    }

    private void createOrder(Map.Entry<String, Integer> customerOrder, List<Order> orders) {
        // 1. Map의 Key를 이용하여 Menu객체 리턴받기
        Menu menu = getMenuByMenuName(customerOrder.getKey());
        // 2. MenuType을 통해 메뉴 타입의 개수 증가
        increaseMenuTypeNum(menu.getMenuType());
        // 3. MenuNum 객체 생성
        MenuNum menuNum = createMenuNum(customerOrder.getValue());
        // 1과 3으로 Order객체 생성
        orders.add(createOrder(menu, menuNum));
    }

    private void increaseMenuTypeNum(MenuType menuType) {
        orderStatus.updateMenuTypeNum(menuType);
    }

    private MenuNum createMenuNum(int menuNum) {
        return MenuNum.from(menuNum);
    }

    private Order createOrder(Menu menu, MenuNum menuNum) {
        return Order.of(menu, menuNum);
    }

    public void createCustomerInfo(VisitDate visitDate, Orders orders) {
        customerInfo = CustomerInfo.of(visitDate, orders);
    }

    private Menu getMenuByMenuName(String menuName) {
        Optional<Menu> menuByMenuName = menus.getMenuByMenuName(menuName);
        if (menuByMenuName.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
        return menuByMenuName.get();
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
}