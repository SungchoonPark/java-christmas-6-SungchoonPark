package christmas.validator;

import christmas.constant.MenuType;
import christmas.domain.order.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


class MenuAndNumValidatorTest {

    @ParameterizedTest
    @MethodSource("generateDuplicatedMenuList")
    @DisplayName("중복된 메뉴를 입력한 경우")
    void 중복된_메뉴를_입력한_경우(List<String> customerOrders) throws Exception {
        //given
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> MenuAndNumValidator.validateCustomerOrders(customerOrders))
                .withMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @MethodSource("generateOverOrderMenuList")
    @DisplayName("주문 개수를 초과한 경우")
    void 주문_개수를_초과한_경우(List<String> customerOrders) throws Exception {
        //given
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> MenuAndNumValidator.validateCustomerOrders(customerOrders))
                .withMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @MethodSource("generateOnlyOrderDrinkMenuList")
    @DisplayName("음료만 주문한 경우")
    void 음료만_주문한_경우(OrderStatus orderStatus) throws Exception {
        //given
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> MenuAndNumValidator.validateOrderMenuType(orderStatus))
                .withMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }



    static Stream<Arguments> generateDuplicatedMenuList() {
        return Stream.of(
                Arguments.of(Arrays.asList("티본스테이크-1", "티본스테이크-3", "제로콜라-1")),
                Arguments.of(Arrays.asList("초코케이크-1", "초코케이크-1")),
                Arguments.of(Arrays.asList("해산물파스타-1", "해산물파스타-4", "타파스-1"))
        );
    }

    static Stream<Arguments> generateOverOrderMenuList() {
        return Stream.of(
                Arguments.of(Arrays.asList("티본스테이크-21")),
                Arguments.of(Arrays.asList("초코케이크-17", "제로콜라-4")),
                Arguments.of(Arrays.asList("해산물파스타-6", "타파스-9", "제로콜라-2", "시저샐러드-4"))
        );
    }

    static Stream<Arguments> generateOnlyOrderDrinkMenuList() {
        OrderStatus orderStatus = OrderStatus.createInstance();
        orderStatus.updateMenuTypeNum(MenuType.DRINK);
        return Stream.of(
                Arguments.of(
                        orderStatus
                )
        );
    }
}