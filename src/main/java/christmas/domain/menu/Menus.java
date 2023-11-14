package christmas.domain.menu;

import christmas.constant.MenuType;

import java.util.*;

public class Menus {
    private final List<Menu> menus;
    private static Menus instance;

    private Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public static Menus getInstance() {
        if(instance == null) {
            instance = new Menus(initialMenus());
        }
        return instance;
    }

    private static List<Menu> initialMenus() {
        List<Menu> initialMenus = new ArrayList<>();

        initialMenus.add(Menu.of("양송이수프", 6000, MenuType.APPETIZER));
        initialMenus.add(Menu.of("타파스", 5500, MenuType.APPETIZER));
        initialMenus.add(Menu.of("시저샐러드", 8000, MenuType.APPETIZER));
        initialMenus.add(Menu.of("티본스테이크", 55000, MenuType.MAIN));
        initialMenus.add(Menu.of("바비큐립", 54000, MenuType.MAIN));
        initialMenus.add(Menu.of("해산물파스타", 35000, MenuType.MAIN));
        initialMenus.add(Menu.of("크리스마스파스타", 25000, MenuType.MAIN));
        initialMenus.add(Menu.of("초코케이크", 15000, MenuType.DESSERT));
        initialMenus.add(Menu.of("아이스크림", 5000, MenuType.DESSERT));
        initialMenus.add(Menu.of("제로콜라", 3000, MenuType.DRINK));
        initialMenus.add(Menu.of("레드와인", 60000, MenuType.DRINK));
        initialMenus.add(Menu.of("샴페인", 25000, MenuType.DRINK));

        return initialMenus;
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }

    // menuName으로 Menu객체 리턴해주는 메서드 필요 -> 여기서 메뉴판에 없는지도 체크
    public Optional<Menu> getMenuByMenuName(String menuName) {
        return menus.stream()
                .filter(menu -> menu.getMenuName().equals(menuName))
                .findFirst();

    }

}
