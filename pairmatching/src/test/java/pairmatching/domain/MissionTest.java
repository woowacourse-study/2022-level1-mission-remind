package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class MissionTest {

    @ParameterizedTest
    @NullSource
    void 미션_이름이_null인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Mission(name, LEVEL1, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 미션 이름 null불가");
    }

    @ParameterizedTest
    @NullSource
    void 매칭_페어가_null인_경우_예외발생(final List<Pair> pairs) {
        assertThatThrownBy(() -> new Mission("mission", LEVEL1, pairs))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 매칭 pair null 불가");
    }

    @ParameterizedTest
    @NullSource
    void 레벨이_null인_경우_예외발생(final Level level) {
        assertThatThrownBy(() -> new Mission("mission", level, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] level null 불가");
    }

    @Test
    void 같은_레벨이_아닌_경우_페어계산_제외() {
        final Mission mission = new Mission("mission1", LEVEL1);
        final Mission compareMission = new Mission("mission2", LEVEL2);

        assertThat(mission.containAlreadyPairCrew(compareMission)).isFalse();
    }

    @Test
    void 이미_페어매칭_이력이_있는_경우_false() {
        final Set<Crew> pairs = Set.of(new Crew("crew1", BACKEND),
                new Crew("crew2", BACKEND));
        final Mission mission = new Mission("mission1", LEVEL1, List.of(new Pair(pairs)));
        final Mission compareMission = new Mission("mission2", LEVEL2, List.of(new Pair(pairs)));

        assertThat(mission.containAlreadyPairCrew(compareMission)).isFalse();
    }
}
