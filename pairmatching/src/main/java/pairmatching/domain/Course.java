package pairmatching.domain;

import java.util.Arrays;

public enum Course {

    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    ;

    private final String name;

    Course(final String name) {
        this.name = name;
    }

    public static Course from(final String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 과정"));
    }
}
