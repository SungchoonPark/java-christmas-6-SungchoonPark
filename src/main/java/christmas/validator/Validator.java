package christmas.validator;

import christmas.constant.ErrorMessage;

public abstract class Validator {
    public abstract void validate(String inputString);

    public void validateEmptyInput(String inputVisitDate) {
        if(inputVisitDate.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }
}
