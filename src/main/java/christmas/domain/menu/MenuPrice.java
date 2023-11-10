package christmas.domain.menu;

public class MenuPrice {
    private int menuPrice;

    private MenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public static MenuPrice from(int menuPrice) {
        return new MenuPrice(menuPrice);
    }
}
