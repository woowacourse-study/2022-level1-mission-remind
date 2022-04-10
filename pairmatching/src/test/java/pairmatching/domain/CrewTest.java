package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class CrewTest {

    @ParameterizedTest
    @NullSource
    void 이름이_null인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Crew(name, BACKEND))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 이름 null불가");
    }

    @ParameterizedTest
    @EmptySource
    void 이름이_공백인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Crew(name, BACKEND))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름 공백불가");
    }

    @ParameterizedTest
    @NullSource
    void 코스가_null인_경우_예외발생(final Course course) {
        assertThatThrownBy(() -> new Crew("crew", course))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 코스 null불가");
    }
}
