package christmas.domain.menu;


public class MenuName {
    private final String menuName;

    private MenuName(String menuName) {
        this.menuName = menuName;
    }

    public static MenuName from(String menuName) {
        return new MenuName(menuName);
    }

    public String getMenuName() {
        return menuName;
    }
}
