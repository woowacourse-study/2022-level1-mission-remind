package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import pairmatching.strategy.CrewRandomsShuffleStrategy;

class MissionProgramTest {

    @ParameterizedTest
    @NullSource
    void 크루들_정보가_null인_경우_예외발생(final CourseCrews crews) {
        assertThatThrownBy(() -> new MissionProgram(crews, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] crews null불가");
    }

    @ParameterizedTest
    @NullSource
    void 미션_리스트가_null인_경우_예외발생(final List<Mission> missions) {
        CourseCrews crews = new CourseCrews(List.of(new Crew("crew1", BACKEND),
                new Crew("crew2", BACKEND)), new CrewRandomsShuffleStrategy());

        assertThatThrownBy(() -> new MissionProgram(crews, missions))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] missions null불가");
    }
}
