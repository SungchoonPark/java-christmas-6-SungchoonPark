package christmas.controller;

import christmas.service.EventService;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class RestaurantController {
    private InputView inputView;
    private OutputView outputView;
    private RestaurantService restaurantService;
    private EventService eventService;

    public RestaurantController(InputView inputView, OutputView outputView, RestaurantService restaurantService, EventService eventService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.restaurantService = restaurantService;
        this.eventService = eventService;
    }

    public void run() {}
}
