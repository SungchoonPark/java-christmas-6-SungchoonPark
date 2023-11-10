package christmas.constant;

public enum ErrorMessage {
    COMMON_ERROR("[ERROR] "),
    INVALID_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_ERROR("유효하지 않은 주문입니다. 다시 입력해주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.join(COMMON_ERROR.message, message);
    }
}
