package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;
import christmas.validator.MenuAndNumValidator;

public class InputView {
    private static final String INITIAL_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String READ_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_MENU_AND_NUM_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private InputView() {}

    public static InputView createInstance() {
        return new InputView();
    }

    public void printInitialMessage() {
        System.out.println(INITIAL_MESSAGE);
    }

    public String readVisitDate() {
        System.out.println(READ_DATE_MESSAGE);
        String visitDate = Console.readLine().trim();
        new DateValidator().validate(visitDate);
        return visitDate;
    }

    public String readMenuAndNum() {
        System.out.println(READ_MENU_AND_NUM_MESSAGE);
        String menuAndNum = Console.readLine().trim();
        new MenuAndNumValidator().validate(menuAndNum);
        return menuAndNum;
    }
}
