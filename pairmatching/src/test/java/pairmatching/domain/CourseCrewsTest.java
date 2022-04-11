package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Course.FRONTEND;

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
}
