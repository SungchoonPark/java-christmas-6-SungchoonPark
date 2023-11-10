package christmas.domain.customer;

public class VisitDate {
    private int visitDate;

    private VisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public static VisitDate from(int visitDate) {
        return new VisitDate(visitDate);
    }
}
