package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class MissionProgramTest {

    @ParameterizedTest
    @NullSource
    void 미션_이름이_null인_경우_예외발생(final Course course) {
        assertThatThrownBy(() -> new MissionProgram(course, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] course null불가");
    }

    @ParameterizedTest
    @NullSource
    void 미션_리스트가_null인_경우_예외발생(final List<Mission> missions) {
        assertThatThrownBy(() -> new MissionProgram(BACKEND, missions))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] missions null불가");
    }
}
