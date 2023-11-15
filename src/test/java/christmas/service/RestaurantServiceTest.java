package christmas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @BeforeEach
    void init() {
        restaurantService = RestaurantService.createInstance();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "초코소라빵-1,피자-1,티본스테이크-3,콜라-1",
            "초코스무디-1,샌드위치-2",
            "맥북-1,에어팟-4"
    })
    @DisplayName("메뉴판에 없는 메뉴를 주문한 경우")
    void 메뉴판에_없는_메뉴를_주문한_경우(String customerOrders) throws Exception {
        //given
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> restaurantService.processCreateOrders(customerOrders))
                .withMessage("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}