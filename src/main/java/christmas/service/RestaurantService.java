package christmas.service;

import christmas.constant.Badge;
import christmas.constant.ErrorMessage;
import christmas.domain.customer.CustomerInfo;
import christmas.domain.customer.VisitDate;
import christmas.domain.event.EventStatus;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.domain.order.MenuNum;
import christmas.domain.order.Order;
import christmas.domain.order.OrderStatus;
import christmas.domain.order.Orders;
import christmas.handler.EventHandler;
import christmas.validator.MenuAndNumValidator;

import java.util.*;


public class RestaurantService {
    private CustomerInfo customerInfo;
    private final Menus menus;
    private final OrderStatus orderStatus;
    private final EventHandler eventHandler;

    public RestaurantService() {
        menus = Menus.getInstance();
        orderStatus = new OrderStatus();
        eventHandler = new EventHandler(new EventStatus());
    }

    public VisitDate createVisitDate(int visitDate) {
        return VisitDate.from(visitDate);
    }

    // 메서드 역할에 맞는 네이밍이 필요함
    public Orders createOrders(String menuAndNum) {
        List<String> inputMenuAndNums = Arrays.stream(menuAndNum.split(",")).toList();

        Map<String, Integer> splittedMenuAndNum = new HashMap<>();
        int menuNumCnt = 0;
        for (String menu : inputMenuAndNums) {
            String[] split = menu.split("-");
            splittedMenuAndNum.put(split[0], Integer.parseInt(split[1]));
            menuNumCnt += Integer.parseInt(split[1]);
        }
        MenuAndNumValidator.validateMenuNumBound(menuNumCnt);

        if (inputMenuAndNums.size() != splittedMenuAndNum.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }

        List<Order> orders = new ArrayList<>();
        for (Map.Entry<String, Integer> splitCustomerOrder : splittedMenuAndNum.entrySet()) {
            // 아래의 1 2 번으로 이미 유효성 검사가 끝나야 하며 CustomerInfo를 만들기 전에 재입력 받아야함
            // 1. Key를 이용하여 Menu객체 리턴받기
            Menu menu = getMenuByMenuName(splitCustomerOrder.getKey());
            // 1.5 MenuType을 통해 체킹
            orderStatus.updateMenuTypeNum(menu.getMenuType());
            // 2. MenuNum 객체 생성
            MenuNum menuNum = MenuNum.from(splitCustomerOrder.getValue());
            // 이 두개로 Order객체 만들기
            orders.add(Order.of(menu, menuNum));
        }

        if (orderStatus.isOnlyDrink()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }

        return Orders.from(orders);
    }

    public void createCustomerInfo(VisitDate visitDate, Orders orders) {
        customerInfo = CustomerInfo.of(visitDate, orders);
    }

    public Menu getMenuByMenuName(String menuName) {
        Optional<Menu> menuByMenuName = menus.getMenuByMenuName(menuName);
        if (menuByMenuName.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
        return menuByMenuName.get();
    }

    public void applyDiscount() {
        eventHandler.applyDiscount(customerInfo);
    }

    public int getTotalBenefitAmount() {
        if(customerInfo.isOverMinAmount()) {
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

    public String getBadge() {
        return Badge.getBadge(eventHandler.getTotalBenefitAmount());
    }


}
