package christmas.constant;

public enum ResultMessage {
    BENEFIT_PREVIEW("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("\n<주문 메뉴>"),
    ALL_ORDER_PRICE_WITHOUT_DISCOUNT("\n<할인 전 총주문 금액>"),
    GIVE_MENU("\n<증정 메뉴>"),
    BENEFIT("\n<혜택 내역>"),
    ALL_BENEFIT_AMOUNT("\n<총혜택 금액>"),
    PAYMENT_PRICE("\n<할인 후 예상 결제 금액>"),
    BADGE("\n<12월 이벤트 배지>");

    private String message;

    ResultMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
