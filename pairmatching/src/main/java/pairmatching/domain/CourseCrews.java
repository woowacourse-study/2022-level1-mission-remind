package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseCrews {

    private final List<Crew> crews;

    public CourseCrews(final List<Crew> crews) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        this.crews = new ArrayList<>(crews);
        validateEmptyCrews(this.crews);
        validateAnotherCourse(this.crews);
    }

    private void validateEmptyCrews(final List<Crew> crews) {
        if (crews.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 크루는 불가");
        }
    }

    private void validateAnotherCourse(final List<Crew> crews) {
        if (containAnotherCourse(crews)) {
            throw new IllegalArgumentException("[ERROR] 다른 과정의 크루가 존재");
        }
    }

    private boolean containAnotherCourse(final List<Crew> crews) {
        final Crew firstCrew = crews.get(0);
        return crews.stream()
                .anyMatch(crew -> crew.isAnotherCrew(firstCrew));
    }
}
