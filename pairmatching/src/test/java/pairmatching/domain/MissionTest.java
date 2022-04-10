package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class MissionTest {

    @ParameterizedTest
    @NullSource
    void 미션_이름이_null인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Mission(name))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 미션 이름 null불가");
    }
}
