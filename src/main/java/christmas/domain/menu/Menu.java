package christmas.domain.menu;

import christmas.constant.MenuType;

public class Menu {
    private final MenuName menuName;
    private final MenuPrice menuPrice;
    private final MenuType menuType;

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

    public MenuType getMenuType() {
        return menuType;
    }

    public int getMenuPrice() {
        return menuPrice.getMenuPrice();
    }
}
