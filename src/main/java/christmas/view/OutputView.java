package christmas.view;

import christmas.constant.ResultMessage;

public class OutputView {

    public OutputView() {}

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printTotalBenefitAmount(int totalAmount) {
        System.out.println(ResultMessage.ALL_BENEFIT_AMOUNT);
        System.out.println(totalAmount);
    }
}
