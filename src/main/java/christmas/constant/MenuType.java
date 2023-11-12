package christmas.constant;

public enum MenuType {
    APPETIZER("appetizer"),
    MAIN("main"),
    DESSERT("dessert"),
    DRINK("drink");

    private String menuType;

    MenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuType() {
        return menuType;
    }
}
