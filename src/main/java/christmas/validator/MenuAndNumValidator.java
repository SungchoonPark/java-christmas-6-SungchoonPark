package christmas.validator;

import christmas.constant.ErrorMessage;
import christmas.constant.NumConstant;
import christmas.constant.Separator;
import christmas.domain.order.OrderStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuAndNumValidator extends Validator {
    private static final Pattern VALID_MENU_AND_NUM = Pattern.compile("^([가-힣]+)-([0-9]+)$");

    @Override
    public void validate(String inputMenuAndNum) {
        validateEmptyInput(inputMenuAndNum);
        validateMenuAndNumFormat(inputMenuAndNum);
    }

    private void validateMenuAndNumFormat(String menuAndNum) {
        List<String> orders = splitOrders(menuAndNum);
        for (String order : orders) {
            if(!isValidOrderFormat(order)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
            }
        }
    }

    private List<String> splitOrders(String orders) {
        return Arrays.stream(orders.split(ORDER_SEPARATOR)).toList();
    }

    private boolean isValidOrderFormat(String order) {
        Matcher matcher = VALID_MENU_AND_NUM.matcher(order);
        return matcher.matches();
    }

    public static Map<String, Integer> validateCustomerOrders(List<String> customerOrders) {
        Map<String, Integer> splittedMenuAndNum = new HashMap<>();
        int menuNumCnt = NumConstant.ZERO.getValue();

        processCustomerOrders(customerOrders, splittedMenuAndNum, menuNumCnt);
        validateMenuNumBound(menuNumCnt);
        validateDuplicateMenu(customerOrders.size(), splittedMenuAndNum.size());

        return splittedMenuAndNum;
    }

    private static void processCustomerOrders(List<String> customerOrders, Map<String, Integer> splittedMenuAndNum, int menuNumCnt) {
        for (String menu : customerOrders) {
            String[] split = splitMenuNameAndNumWithSeparator(menu);
            splittedMenuAndNum.put(split[0], Integer.parseInt(split[1]));
            menuNumCnt += Integer.parseInt(split[1]);
        }
    }

    public static void validateMenuNumBound(int menuNum) {
        if(menuNum > NumConstant.MAX_ORDER_NUM.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static String[] splitMenuNameAndNumWithSeparator(String menuAndNum) {
        return menuAndNum.split(MENU_AND_NUM_SEPARATOR);
    }

    private static void validateDuplicateMenu(int customerOrderSize, int splittedOrderSize) {
        if (customerOrderSize != splittedOrderSize) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    public static void validateOrderMenuType(OrderStatus orderStatus) {
        if (orderStatus.isOnlyDrink()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

}
