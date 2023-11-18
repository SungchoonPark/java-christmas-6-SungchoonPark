package christmas.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class BadgeTest {

    @ParameterizedTest
    @CsvSource(delimiter =':',
            value = {"3234:없음", "5000:별", "9999:별", "10000:트리", "19999:트리", "20000:산타", "2929292:산타"}
    )
    @DisplayName("가격별 뱃지 가져오기")
    void 가격별_뱃지_가져오기(int amount, String badge) throws Exception {
        //given
        //when
        String badgeName = Badge.getBadge(amount);
        //then
        assertThat(badgeName).isEqualTo(badge);
    }
}