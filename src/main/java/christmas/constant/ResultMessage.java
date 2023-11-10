package christmas.constant;

public enum ResultMessage {
    ORDER_MENU("<주문 메뉴>"),
    ALL_ORDER_PRICE_WITHOUT_DISCOUNT("<할인 전 총주문 금액>"),
    GIVE_MENU("<증정 메뉴>"),
    BENEFIT("<혜택 내역>"),
    ALL_BENEFIT_PRICE("<총혜택 금액>"),
    PAYMENT_PRICE("<할인 후 예상 결제 금액>"),
    BADGE("<12월 이벤트 배지>");

    private String message;

    ResultMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
