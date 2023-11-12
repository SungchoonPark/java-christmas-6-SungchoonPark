package christmas.domain.menu;

import christmas.constant.MenuType;

public class Menu {
    private MenuName menuName;
    private MenuPrice menuPrice;
    private MenuType menuType;

    private Menu(String menuName, int menuPrice, MenuType menuType) {
        this.menuName = MenuName.from(menuName);
        this.menuPrice = MenuPrice.from(menuPrice);
        this.menuType = menuType;
    }

    public static Menu of(String menuName, int menuPrice, MenuType menuType) {
        return new Menu(menuName, menuPrice, menuType);
    }

    public String getMenuName() {
        return menuName.getMenuName();
    }
}
