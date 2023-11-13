package christmas.domain.order;

public class MenuNum {
    private int menuNum;

    private MenuNum(int menuNum) {
        this.menuNum = menuNum;
    }

    public static MenuNum from(int menuNum) {
        return new MenuNum(menuNum);
    }

    public int getMenuNum() {
        return menuNum;
    }
}
