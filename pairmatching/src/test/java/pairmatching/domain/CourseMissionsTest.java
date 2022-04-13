package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Level.LEVEL1;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import pairmatching.strategy.CrewRandomsShuffleStrategy;

class CourseMissionsTest {

    private final Crew crew1 = new Crew("crew1", BACKEND);
    private final Crew crew2 = new Crew("crew2", BACKEND);
    private final Crew crew3 = new Crew("crew3", BACKEND);
    private final Crew crew4 = new Crew("crew4", BACKEND);

    @ParameterizedTest
    @NullSource
    void 크루들_정보가_null인_경우_예외발생(final Crews crews) {
        assertThatThrownBy(() -> new CourseMissions(crews, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] crews null불가");
    }

    @ParameterizedTest
    @NullSource
    void 미션_리스트가_null인_경우_예외발생(final List<Mission> missions) {
        Crews crews = new Crews(List.of(new Crew("crew1", BACKEND),
                new Crew("crew2", BACKEND)), new CrewRandomsShuffleStrategy());

        assertThatThrownBy(() -> new CourseMissions(crews, missions))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] missions null불가");
    }

    @Test
    void 존재하지_않는_미션_매칭_예외발생() {
        final Crews crews = new Crews(
                List.of(crew1, crew2, crew3, crew4),
                crew -> List.of("crew1", "crew2", "crew3", "crew4"));
        final List<Mission> missions = List.of(
                new Mission("mission1", LEVEL1, new Pairs(List.of(new Pair(crew1, crew2), new Pair(crew3, crew4)))),
                new Mission("mission2", LEVEL1, new Pairs(new ArrayList<>())));
        final CourseMissions courseMissions = new CourseMissions(crews, missions);

        assertThatThrownBy(() -> courseMissions.matchPair("mission3", LEVEL1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 존재하지 않는 미션");
    }

    @Test
    void 매칭_시도횟수가_3회가_넘는_경우_예외발생() {
        final Crews crews = new Crews(
                List.of(crew1, crew2, crew3, crew4),
                crew -> List.of("crew1", "crew2", "crew3", "crew4"));
        final List<Mission> missions = List.of(
                new Mission("mission1", LEVEL1, new Pairs(List.of(new Pair(crew1, crew2), new Pair(crew3, crew4)))),
                new Mission("mission2", LEVEL1, new Pairs(new ArrayList<>())));
        final CourseMissions courseMissions = new CourseMissions(crews, missions);

        assertThatThrownBy(() -> courseMissions.matchPair("mission2", LEVEL1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 3회 매칭 실패");
    }

    @Test
    void 새로운_페어매칭_성공() {
        final Crews crews = new Crews(
                List.of(crew1, crew2, crew3, crew4),
                crew -> List.of("crew1", "crew3", "crew4", "crew2"));
        final List<Mission> missions = List.of(
                new Mission("mission1", LEVEL1, new Pairs(List.of(new Pair(crew1, crew2), new Pair(crew3, crew4)))),
                new Mission("mission2", LEVEL1, new Pairs(new ArrayList<>())));
        final CourseMissions courseMissions = new CourseMissions(crews, missions);

        final List<Pair> expected = List.of(new Pair(crew1, crew3), new Pair(crew4, crew2));

        assertThat(courseMissions.matchPair("mission2", LEVEL1)).isEqualTo(expected);
    }
}
