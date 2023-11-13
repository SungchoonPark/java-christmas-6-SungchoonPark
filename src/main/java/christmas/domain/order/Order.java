package christmas.domain.order;

import christmas.constant.MenuType;
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

    public int getOrderAmount() {
        return menu.getMenuPrice() * menuNum.getMenuNum();
    }

    public String getMenuName() {
        return menu.getMenuName();
    }

    public int getMenuNum() {
        return menuNum.getMenuNum();
    }

    public boolean isMatchMenuType(MenuType menuType) {
        if (menu.getMenuType() == menuType) {
            return true;
        }
        return false;
    }

}
