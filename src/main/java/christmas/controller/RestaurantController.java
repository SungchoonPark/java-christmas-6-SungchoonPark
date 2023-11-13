package christmas.controller;

import christmas.domain.customer.VisitDate;
import christmas.domain.order.Orders;
import christmas.service.EventService;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RestaurantController {
    private InputView inputView;
    private OutputView outputView;
    private RestaurantService restaurantService;
//    private EventService eventService;

    public RestaurantController(InputView inputView, OutputView outputView, RestaurantService restaurantService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurantService = restaurantService;
//        this.eventService = eventService;
    }

    public void run() {
        inputView.printInitialMessage();
        createCustomerInfo();
    }

    public VisitDate createVisitDate() {
        while(true) {
            try {
                return restaurantService.createVisitDate(Integer.parseInt(inputView.readVisitDate()));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public Orders createOrdersFromMenuAndNum() {
        while(true) {
            try {
                String inputMenuAndNum = inputView.readMenuAndNum();
                return restaurantService.createOrders(inputMenuAndNum);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    public void createCustomerInfo() {
        restaurantService.createCustomerInfo(createVisitDate(), createOrdersFromMenuAndNum());
    }
}
