package christmas.view;

import christmas.constant.ResultMessage;

import java.util.Map;

public class OutputView {

    public OutputView() {
    }

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printBenefitPreview() {
        System.out.println(ResultMessage.BENEFIT_PREVIEW.getMessage());
    }

    public void printOrders(Map<String, Integer> orderList) {
        System.out.println(ResultMessage.ORDER_MENU.getMessage());
        for (Map.Entry<String, Integer> order : orderList.entrySet()) {
            System.out.println(order.getKey() + " " + order.getValue() + "개");
        }
    }

    public void printTotalOrderAmountWithoutDiscount(int totalAmount) {
        System.out.println(ResultMessage.ALL_ORDER_PRICE_WITHOUT_DISCOUNT.getMessage());
        System.out.println(priceFormatting(totalAmount));
    }

    public void printGiveawayMenu() {
        System.out.println(ResultMessage.GIVE_MENU.getMessage());
    }

    public void printBenefits(Map<String, Integer> benefits) {
        System.out.println(ResultMessage.BENEFIT.getMessage());
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
        return String.format("%,d", price) + "원";
    }
}
