package christmas.controller;

import christmas.domain.customer.VisitDate;
import christmas.domain.order.Orders;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RestaurantController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RestaurantService restaurantService;

    public RestaurantController(InputView inputView, OutputView outputView, RestaurantService restaurantService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurantService = restaurantService;
    }

    public void run() {
        inputView.printInitialMessage();
        createCustomerInfo();
        applyDiscount();
        printApplyEvent();
    }

    public void createCustomerInfo() {
        restaurantService.createCustomerInfo(createVisitDate(), createOrdersFromMenuAndNum());
    }

    private VisitDate createVisitDate() {
        while (true) {
            try {
                return restaurantService.createVisitDate(Integer.parseInt(inputView.readVisitDate()));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private Orders createOrdersFromMenuAndNum() {
        while (true) {
            try {
                String inputMenuAndNum = inputView.readMenuAndNum();
                return restaurantService.processCreateOrders(inputMenuAndNum);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public void applyDiscount() {
        restaurantService.applyDiscount();
    }

    public void printApplyEvent() {
        printPreviewMessage();
        printOrders();
        printTotalOrderAmountWithoutDiscount();
        printGiveawayMenu();
        printBenefits();
        printTotalBenefitAmount();
        printExpectedPaymentAmount();
        printEventBadge();
    }

    public void printPreviewMessage() {
        outputView.printBenefitPreview();
    }

    public void printOrders() {
        outputView.printOrders(restaurantService.getOrderList());
    }

    public void printTotalOrderAmountWithoutDiscount() {
        outputView.printTotalOrderAmountWithoutDiscount(restaurantService.getTotalOrderAmount());
    }

    public void printGiveawayMenu() {
        outputView.printGiveawayMenu(restaurantService.getGiveaway());
    }

    public void printTotalBenefitAmount() {
        outputView.printTotalBenefitAmount(restaurantService.getTotalBenefitAmount());
    }

    public void printBenefits() {
        if (restaurantService.getBenefits().size() == 0) {
            outputView.printBenefits();
            return;
        }
        outputView.printBenefits(restaurantService.getBenefits());
    }

    public void printExpectedPaymentAmount() {
        outputView.printExpectedPaymentAmount(
                expectedPaymentAmount(
                        restaurantService.getTotalOrderAmount(),
                        restaurantService.getTotalDiscountAmount()
                ));
    }

    private int expectedPaymentAmount(int orderAmount, int benefitAmount) {
        return orderAmount + benefitAmount;
    }

    public void printEventBadge() {
        outputView.printEventBadge(restaurantService.getBadge());
    }
}
