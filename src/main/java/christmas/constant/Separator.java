package christmas.constant;

public enum Separator {
    ORDER_SEPARATOR(","),
    MENU_NAME_NUM_SEPARATOR("-");

    private String separator;

    Separator(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}
