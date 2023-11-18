package christmas.handler;

import christmas.constant.EventType;
import christmas.constant.MenuType;
import christmas.domain.customer.CustomerInfo;
import christmas.domain.customer.VisitDate;
import christmas.domain.event.EventStatus;
import christmas.domain.menu.Menu;
import christmas.domain.order.MenuNum;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class EventHandlerTest {
    private EventStatus eventStatus;
    private EventHandler eventHandler;

    @BeforeEach
    void init() {
        eventStatus = EventStatus.createInstance();
        eventHandler = EventHandler.from(eventStatus);
    }

    @ParameterizedTest
    @MethodSource("generateCustomerInfo")
    @DisplayName("디데이 이벤트 적용")
    void 디데이_이벤트_적용(CustomerInfo customerInfo) throws Exception {
        //given
        //when
        eventHandler.applyDdayEvent(customerInfo);
        int eventApplyNum = eventStatus.getEventApplyNum(EventType.CHRISTMAS_DDAY);
        //then
        assertThat(eventApplyNum).isEqualTo(customerInfo.getDdayEventApplyDay());
    }

    @ParameterizedTest
    @MethodSource("generateCustomerInfo")
    @DisplayName("평일 또는 주말 이벤트 적용")
    void 평일_또는_주말_이벤트_적용(CustomerInfo customerInfo) throws Exception {
        //given
        //when
        eventHandler.applyWeekEvent(customerInfo);
        int weekdaysApplyNum = eventStatus.getEventApplyNum(EventType.WEEKDAYS);
        int weekendApplyNum = eventStatus.getEventApplyNum(EventType.WEEKENDS);
        //then
        assertThat(weekdaysApplyNum).isEqualTo(1);
        assertThat(weekendApplyNum).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("generateCustomerInfo")
    @DisplayName("증정 이벤트 적용")
    void 증정_이벤트_적용(CustomerInfo customerInfo) throws Exception {
        //given
        //when
        eventHandler.applyGiveawayEvent(customerInfo);
        //then
        int giveawayNum = eventStatus.getEventApplyNum(EventType.GIVE);
        assertThat(giveawayNum).isEqualTo(1);
    }

    @ParameterizedTest
    @MethodSource("generateCustomerInfo")
    @DisplayName("특별_이벤트_적용")
    void 특별_이벤트_적용(CustomerInfo customerInfo) throws Exception {
        //given
        //when
        eventHandler.applySpecialEvent(customerInfo);
        //then
        int specialEventNum = eventStatus.getEventApplyNum(EventType.SPECIAL_DAY);
        assertThat(specialEventNum).isEqualTo(1);
    }

    static Stream<Arguments> generateCustomerInfo() {
        VisitDate visitDate = VisitDate.from(25);
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.of(Menu.of("티본스테이크", 55000, MenuType.MAIN), MenuNum.from(2)));
        orderList.add(Order.of(Menu.of("타파스", 5500, MenuType.APPETIZER), MenuNum.from(2)));
        orderList.add(Order.of(Menu.of("초코케이크", 15000, MenuType.DESSERT), MenuNum.from(1)));
        Orders orders = Orders.from(orderList);
        return Stream.of(
                Arguments.of(
                        CustomerInfo.of(visitDate, orders)
                )
        );
    }

}