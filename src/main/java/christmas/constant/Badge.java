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

    public static String getBadge(int totalBenefitAmount) {
        totalBenefitAmount = Math.abs(totalBenefitAmount);
        if(totalBenefitAmount >= 5000 && totalBenefitAmount < 10000) {
            return STAR.name;
        }
        if(totalBenefitAmount >= 10000 && totalBenefitAmount < 20000) {
            return TREE.name;
        }
        if(totalBenefitAmount >= 20000) {
            return SANTA.name;
        }
        return MISS.name;
    }
}
