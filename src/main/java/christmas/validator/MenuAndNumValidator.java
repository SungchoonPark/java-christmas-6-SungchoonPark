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
        return Arrays.stream(orders.split(Separator.ORDER_SEPARATOR.getSeparator())).toList();
    }

    private boolean isValidOrderFormat(String order) {
        Matcher matcher = VALID_MENU_AND_NUM.matcher(order);
        return matcher.matches();
    }

    public static Map<String, Integer> validateCustomerOrders(List<String> customerOrders) {
        Map<String, Integer> splittedMenuAndNum;
        int menuNumCnt;

        menuNumCnt = getMenuNumUseCustomerOrder(customerOrders);
        splittedMenuAndNum = getSplitMenuNameAndNum(customerOrders);
        validateMenuNumBound(menuNumCnt);
        validateDuplicateMenu(customerOrders.size(), splittedMenuAndNum.size());

        return splittedMenuAndNum;
    }

    private static int getMenuNumUseCustomerOrder(List<String> customerOrders) {
        int menuNumCnt = 0;
        for (String menu : customerOrders) {
            String[] split = splitMenuNameAndNumWithSeparator(menu);
            menuNumCnt += Integer.parseInt(split[1]);
        }
        return menuNumCnt;
    }

    private static Map<String, Integer> getSplitMenuNameAndNum(List<String> customerOrders) {
        Map<String, Integer> splitMenuAndNum = new HashMap<>();
        for (String menu : customerOrders) {
            String[] split = splitMenuNameAndNumWithSeparator(menu);
            splitMenuAndNum.put(split[0], Integer.parseInt(split[1]));
        }
        return splitMenuAndNum;
    }

    private static void validateMenuNumBound(int menuNum) {
        if(menuNum > NumConstant.MAX_ORDER_NUM.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static String[] splitMenuNameAndNumWithSeparator(String menuAndNum) {
        return menuAndNum.split(Separator.MENU_NAME_NUM_SEPARATOR.getSeparator());
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
