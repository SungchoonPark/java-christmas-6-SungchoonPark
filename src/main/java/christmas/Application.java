package christmas;

import christmas.controller.RestaurantController;
import christmas.handler.EventHandler;
import christmas.service.EventService;
import christmas.service.RestaurantService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        RestaurantController restaurantController;

        restaurantController = RestaurantController.of(
                InputView.createInstance(),
                OutputView.createInstance(),
                RestaurantService.createInstance()
        );

        restaurantController.run();
    }
}
