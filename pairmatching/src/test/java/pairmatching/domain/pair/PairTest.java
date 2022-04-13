package pairmatching.domain.pair;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import pairmatching.domain.crew.Crew;

class PairTest {

    @ParameterizedTest
    @NullSource
    void 페어가_null인_경우_예외발생(final List<Crew> crews) {
        assertThatThrownBy(() -> new Pair(crews))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] crews null불가");
    }

    @ParameterizedTest
    @MethodSource("createInvalidCountCrews")
    void 페어가_정해진_수로_입력되지_않을때_예외발생(final List<Crew> crews) {
        assertThatThrownBy(() -> new Pair(crews))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 페어는 2명 또는 3명만 가능");
    }

    private static Stream<Arguments> createInvalidCountCrews() {
        return Stream.of(
                Arguments.of(List.of(new Crew("crew1", BACKEND))),
                Arguments.of(List.of(new Crew("crew1", BACKEND),
                        new Crew("crew2", BACKEND),
                        new Crew("crew3", BACKEND),
                        new Crew("crew4", BACKEND)))
        );
    }
}
