package christmas.domain.customer;

import christmas.validator.DateValidator;

public class VisitDate {
    private int visitDate;

    private VisitDate(int visitDate) {
        DateValidator.validateVisitDateBound(visitDate);
        this.visitDate = visitDate;
    }

    public static VisitDate from(int visitDate) {
        return new VisitDate(visitDate);
    }

    public int getVisitDate() {
        return visitDate;
    }
}
