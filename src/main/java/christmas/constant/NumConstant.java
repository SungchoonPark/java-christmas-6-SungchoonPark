package christmas.constant;

public enum NumConstant {

    MIN_EVENT_APPLY_AMOUNT(10_000),
    MIN_GIVEAWAY_AMOUNT(120_000),
    THIS_YEAR(2023),
    THIS_MONTH(12),
    CHRISTMAS_DAY(25),
    ZERO(0),
    MAX_ORDER_NUM(20),
    STAR_BADGE_AMOUNT(5000),
    TREE_BADGE_AMOUNT(10000),
    SANTA_BADGE_AMOUNT(20000),
    ;

    private int value;

    NumConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
