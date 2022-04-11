package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Course.FRONTEND;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CourseCrewsTest {

    @Test
    void 다른_과정의_크루가_있는_경우_예외발생() {
        List<Crew> crews = List.of(new Crew("crew1", BACKEND),
                new Crew("crew2", FRONTEND));

        assertThatThrownBy(() -> new CourseCrews(crews))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 다른 과정의 크루가 존재");
    }

    @Test
    void 크루가_비어있는_경우_예외발생() {
        assertThatThrownBy(() -> new CourseCrews(new ArrayList<>()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 빈 크루는 불가");
    }

    @Test
    void 현재_크루들의_코스를_반환() {
        CourseCrews crews = new CourseCrews(List.of(new Crew("crew1", BACKEND),
                new Crew("crew2", BACKEND)));

        assertThat(crews.course()).isEqualTo(BACKEND);
    }

    @Test
    void 셔플된_크루의_이름을_반환() {
        CourseCrews crews = new CourseCrews(List.of(new Crew("crew1", BACKEND),
                new Crew("crew2", BACKEND),
                new Crew("crew3", BACKEND)));

        assertThat(crews.shuffledCrewNames()).hasSize(3);
    }
}
