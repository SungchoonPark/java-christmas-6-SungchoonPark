package christmas.controller;

import christmas.domain.customer.CustomerInfo;
import christmas.domain.customer.VisitDate;
import christmas.domain.order.Orders;
import christmas.service.EventService;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RestaurantController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RestaurantService restaurantService;
    private EventService eventService;

    private RestaurantController(InputView inputView, OutputView outputView, RestaurantService restaurantService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurantService = restaurantService;
    }

    public static RestaurantController of(InputView inputView, OutputView outputView, RestaurantService restaurantService) {
        return new RestaurantController(inputView, outputView, restaurantService);
    }

    public void run() {
        inputView.printInitialMessage();
        createCustomerInfo();
        eventService = EventService.of(
                getCustomerInfoFromRestaurantService()
        );
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

    private CustomerInfo getCustomerInfoFromRestaurantService() {
        return restaurantService.getCustomerInfo();
    }

    public void applyDiscount() {
        eventService.applyDiscount();
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

    private void printPreviewMessage() {
        outputView.printBenefitPreview();
    }

    private void printOrders() {
        outputView.printOrders(eventService.getOrderList());
    }

    private void printTotalOrderAmountWithoutDiscount() {
        outputView.printTotalOrderAmountWithoutDiscount(eventService.getTotalOrderAmount());
    }

    private void printGiveawayMenu() {
        outputView.printGiveawayMenu(eventService.getGiveaway());
    }

    private void printTotalBenefitAmount() {
        outputView.printTotalBenefitAmount(eventService.getTotalBenefitAmount());
    }

    private void printBenefits() {
        if (eventService.getBenefits().size() == 0) {
            outputView.printBenefits();
            return;
        }
        outputView.printBenefits(eventService.getBenefits());
    }

    private void printExpectedPaymentAmount() {
        outputView.printExpectedPaymentAmount(
                expectedPaymentAmount(
                        eventService.getTotalOrderAmount(),
                        eventService.getTotalDiscountAmount()
                ));
    }

    private int expectedPaymentAmount(int orderAmount, int benefitAmount) {
        return orderAmount + benefitAmount;
    }

    private void printEventBadge() {
        outputView.printEventBadge(eventService.getBadge());
    }
}
