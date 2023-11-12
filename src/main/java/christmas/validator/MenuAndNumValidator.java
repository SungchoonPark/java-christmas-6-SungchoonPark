package christmas.validator;

import christmas.constant.ErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuAndNumValidator extends Validator {
    private static final Pattern VALID_MENU_AND_NUM_SEPARATOR = Pattern.compile("^[가-힣0-9-]+$");

    @Override
    public void validate(String inputMenuAndNum) {
        validateEmptyInput(inputMenuAndNum);
        validateMenuAndNumFormat(inputMenuAndNum);
    }

    public static void validateMenuNumBound(int menuNum) {
        if(menuNum > 20) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }
    
    private void validateMenuAndNumFormat(String menuAndNum) {
        List<String> orders = splitOrders(menuAndNum);
        for (String order : orders) {
            System.out.println("order = " + order);
            if(!isValidOrderFormat(order)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
            }
        }
    }
    
    private List<String> splitOrders(String orders) {
        return Arrays.stream(orders.split(",")).toList();
    }
    
    private boolean isValidOrderFormat(String order) {
        Matcher matcher = VALID_MENU_AND_NUM_SEPARATOR.matcher(order);
        return matcher.matches();
    }

}
