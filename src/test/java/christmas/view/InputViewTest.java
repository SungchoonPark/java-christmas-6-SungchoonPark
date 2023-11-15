package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {
    private InputView inputView;

    @BeforeEach
    void init() {
        inputView = InputView.createInstance();
    }

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "39", "42", "51", "63", "29", "31"})
    @DisplayName("방문 날짜가 숫자로 들어온 경우")
    void 정상적으로_날짜가_입력된_경우(String visitDate) throws Exception {
        //given
        System.setIn(readUserInput(visitDate));
        //when
        String readVisitDate = inputView.readVisitDate();
        //then
        assertThat(readVisitDate).isEqualTo(visitDate);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "a", "one"})
    @DisplayName("방문 날짜가 숫자가 아닌 값이 들어왔을때")
    void 방문날짜가_숫자가_아닌_값이_들어온_경우(String visitDate) throws Exception {
        //given
        System.setIn(readUserInput(visitDate));
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readVisitDate())
                .withMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 8, 23, 17, 29, 31, 13})
    @DisplayName("방문 날짜가 1이상 31이하의 날짜가 입력된 경우")
    void 방문날짜가_1이상_31이하의_날짜가_입력된_경우(int visitDate) throws Exception {
        //given
        //when
        //then
        DateValidator.validateVisitDateBound(visitDate);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 32,33,101,39,58})
    @DisplayName("방문 날짜가 범위 밖의 날짜가 입력된 경우")
    void 방문날짜가_범위_밖의_날짜가_입력된_경우(int visitDate) throws Exception {
        //given
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> DateValidator.validateVisitDateBound(visitDate))
                .withMessage("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타-1",
            "티본스테이크-1,초코케이크-3",
            "제로콜라-1,아이스크림-1",
            "티본스테이크-1,아이스크림-3",
            "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
            "타파스-1,제로콜라-1"
    })
    @DisplayName("주문 형식을 잘 입력한 경우")
    void 주문_형식을_잘_입력한_경우(String orders) throws Exception {
        //given
        System.setIn(readUserInput(orders));
        //when
        String readOrders = inputView.readMenuAndNum();
        //then
        assertThat(readOrders).isEqualTo(orders);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "해산물파스타_1",
            "티본스테이크-1.초코케이크-3",
            "제로콜라-one,아이스크림-1",
            "티본스테이크-1,아이스크림=3"
    })
    @DisplayName("주문 형식이 잘못된 경우")
    void 주문_형식이_잘못된_경우(String orders) throws Exception {
        //given
        System.setIn(readUserInput(orders));
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> inputView.readMenuAndNum())
                .withMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public InputStream readUserInput(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }
}