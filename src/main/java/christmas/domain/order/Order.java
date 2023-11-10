package christmas.domain.order;

import christmas.domain.menu.Menu;

public class Order {
    private Menu menu;
    private MenuNum menuNum;

    private Order(Menu menu, MenuNum menuNum) {
        this.menu = menu;
        this.menuNum = menuNum;
    }

    public static Order of(Menu menu, MenuNum menuNum) {
        return new Order(menu, menuNum);
    }
}
