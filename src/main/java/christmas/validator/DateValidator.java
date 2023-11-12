package christmas.validator;

import christmas.constant.ErrorMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator extends Validator {
    private static final Pattern VALID_DATE = Pattern.compile("^[0-9]+$");

    @Override
    public void validate(String inputVisitDate) {
        validateEmptyInput(inputVisitDate);
        validateVisitDate(inputVisitDate);
    }

    private void validateVisitDate(String inputVisitDate) {
        if(!isValidVisitDate(inputVisitDate)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }

    private boolean isValidVisitDate(String inputVisitDate) {
        Matcher matcher = VALID_DATE.matcher(inputVisitDate);
        return matcher.matches();
    }

    public static void validateVisitDateBound(int visitDate) {
        if(visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }

}
