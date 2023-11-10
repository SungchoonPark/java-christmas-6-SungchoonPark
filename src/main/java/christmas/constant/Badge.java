package christmas.constant;

public enum Badge {
    MISS("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");
    private String name;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
