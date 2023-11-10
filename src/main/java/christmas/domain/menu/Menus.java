package christmas.domain.menu;

import java.util.List;

public class Menus {
    private List<Menu> menus;

    private Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public Menus from(List<Menu> menus) {
        return new Menus(menus);
    }

}
