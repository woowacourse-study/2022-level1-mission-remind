package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class RandomMissionProgramsTest {

    @ParameterizedTest
    @NullSource
    void 미션_프로그램_리스트가_null인_경우_예외발생(final List<MissionProgram> missionProgramss) {
        assertThatThrownBy(() -> new RandomMissionPrograms(missionProgramss))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] missionPrograms null불가");
    }
}
