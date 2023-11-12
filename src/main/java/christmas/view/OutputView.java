package christmas.view;

public class OutputView {

    public OutputView() {}

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
