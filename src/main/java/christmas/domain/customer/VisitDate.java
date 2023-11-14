package christmas.domain.customer;

import christmas.validator.DateValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private static final int THIS_YEAR = 2023;
    private static final int THIS_MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;
    private final int visitDate;

    private VisitDate(int visitDate) {
        DateValidator.validateVisitDateBound(visitDate);
        this.visitDate = visitDate;
    }

    public static VisitDate from(int visitDate) {
        return new VisitDate(visitDate);
    }

    public DayOfWeek getDayOfWeek() {
        LocalDate date = LocalDate.of(THIS_YEAR, THIS_MONTH, visitDate);
        return date.getDayOfWeek();
    }

    public boolean isVisitDateWeekend() {
        DayOfWeek dayOfWeek = getDayOfWeek();
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            return true;
        }
        return false;
    }

    public boolean isOverChristmas() {
        if(visitDate > CHRISTMAS_DAY) {
            return true;
        }
        return false;
    }

    public int calculateDdayEventApplyDay() {
        return visitDate - 1;
    }

    public boolean isSpecialDay() {
        if (visitDate == CHRISTMAS_DAY || getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }
}
