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
        if(totalBenefitAmount >= NumConstant.STAR_BADGE_AMOUNT.getValue() && totalBenefitAmount < NumConstant.TREE_BADGE_AMOUNT.getValue()) {
            return STAR.name;
        }
        if(totalBenefitAmount >= NumConstant.TREE_BADGE_AMOUNT.getValue() && totalBenefitAmount < NumConstant.SANTA_BADGE_AMOUNT.getValue()) {
            return TREE.name;
        }
        if(totalBenefitAmount >= NumConstant.SANTA_BADGE_AMOUNT.getValue()) {
            return SANTA.name;
        }
        return MISS.name;
    }
}
