package pairmatching.domain;

import java.util.Objects;

public class Crew {

    private final String name;

    public Crew(final String name) {
        Objects.requireNonNull(name, "[ERROR] 이름 null불가");
        this.name = name;
    }
}
