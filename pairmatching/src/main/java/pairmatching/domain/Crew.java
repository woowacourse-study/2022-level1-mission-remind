package pairmatching.domain;

import java.util.Objects;

public class Crew {

    private final String name;
    private final Course course;

    public Crew(final String name, final Course course) {
        Objects.requireNonNull(name, "[ERROR] 이름 null불가");
        Objects.requireNonNull(course, "[ERROR] 코스 null불가");
        validateEmptyName(name);
        this.name = name;
        this.course = course;
    }

    private void validateEmptyName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름 공백불가");
        }
    }
}
