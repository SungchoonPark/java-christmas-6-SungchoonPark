package christmas.view;

import christmas.constant.ResultMessage;

import java.util.Map;

public class OutputView {
    private static final String ERROR = "[ERROR] ";
    private static final String MONEY_MESSAGE = "원";
    private static final String NUM_MESSAGE = "개";
    private static final String NO_RESULT_MESSAGE = "없음";

    private OutputView() {
    }

    public static OutputView createInstance() {
        return new OutputView();
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR + e.getMessage());
    }

    public void printBenefitPreview() {
        System.out.println(ResultMessage.BENEFIT_PREVIEW.getMessage());
    }

    public void printOrders(Map<String, Integer> orderList) {
        System.out.println(ResultMessage.ORDER_MENU.getMessage());
        for (Map.Entry<String, Integer> order : orderList.entrySet()) {
            System.out.println(order.getKey() + " " + order.getValue() + NUM_MESSAGE);
        }
    }

    public void printTotalOrderAmountWithoutDiscount(int totalAmount) {
        System.out.println(ResultMessage.ALL_ORDER_PRICE_WITHOUT_DISCOUNT.getMessage());
        System.out.println(priceFormatting(totalAmount));
    }

    public void printGiveawayMenu(String giveaway) {
        System.out.println(ResultMessage.GIVE_MENU.getMessage());
        System.out.println(giveaway);
    }

    public void printBenefits(Map<String, Integer> benefits) {
        System.out.println(ResultMessage.BENEFIT.getMessage());
        if(benefits.size() == 0) {
            System.out.println(NO_RESULT_MESSAGE);
            return;
        }
        for (Map.Entry<String, Integer> benefit : benefits.entrySet()) {
            System.out.println(benefit.getKey() + priceFormatting(benefit.getValue()));
        }

    }

    public void printTotalBenefitAmount(int totalAmount) {
        System.out.println(ResultMessage.ALL_BENEFIT_AMOUNT.getMessage());
        System.out.println(priceFormatting(totalAmount));
    }

    public void printExpectedPaymentAmount(int paymentAmount) {
        System.out.println(ResultMessage.PAYMENT_PRICE.getMessage());
        System.out.println(priceFormatting(paymentAmount));
    }

    public void printEventBadge(String badge) {
        System.out.println(ResultMessage.BADGE.getMessage());
        System.out.println(badge);
    }

    private String priceFormatting(int price) {
        return String.format("%,d", price) + MONEY_MESSAGE;
    }
}
