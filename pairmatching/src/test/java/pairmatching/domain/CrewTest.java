package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class CrewTest {

    @ParameterizedTest
    @NullSource
    void 이름이_null인_경우_예외발생(final String input) {
        assertThatThrownBy(() -> new Crew(input))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 이름 null불가");
    }
}
