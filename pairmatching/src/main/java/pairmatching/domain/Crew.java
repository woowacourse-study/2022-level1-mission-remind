package pairmatching.domain;

import java.util.Objects;

public class Crew {

    private final String name;

    public Crew(final String name) {
        Objects.requireNonNull(name, "[ERROR] 이름 null불가");
        validateEmptyName(name);
        this.name = name;
    }

    private void validateEmptyName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름 공백불가");
        }
    }
}
