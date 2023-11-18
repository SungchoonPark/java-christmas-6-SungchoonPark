package christmas.domain.menu;

public class MenuPrice {
    private final int menuPrice;

    private MenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public static MenuPrice from(int menuPrice) {
        return new MenuPrice(menuPrice);
    }

    public int getMenuPrice() {
        return menuPrice;
    }
}
