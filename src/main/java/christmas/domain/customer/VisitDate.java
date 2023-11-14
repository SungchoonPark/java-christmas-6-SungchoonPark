package christmas.domain.customer;

import christmas.constant.NumConstant;
import christmas.validator.DateValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private final int visitDate;

    private VisitDate(int visitDate) {
        DateValidator.validateVisitDateBound(visitDate);
        this.visitDate = visitDate;
    }

    public static VisitDate from(int visitDate) {
        return new VisitDate(visitDate);
    }

    public DayOfWeek getDayOfWeek() {
        LocalDate date = LocalDate.of(
                NumConstant.THIS_YEAR.getValue(),
                NumConstant.THIS_MONTH.getValue(),
                visitDate
        );
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
        if(visitDate > NumConstant.CHRISTMAS_DAY.getValue()) {
            return true;
        }
        return false;
    }

    public int calculateDdayEventApplyDay() {
        return visitDate - 1;
    }

    public boolean isSpecialDay() {
        if (visitDate == NumConstant.CHRISTMAS_DAY.getValue() || getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }
}
