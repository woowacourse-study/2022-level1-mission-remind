package pairmatching.domain;

import java.util.Objects;

public class Mission {

    private final String name;

    public Mission(final String name) {
        Objects.requireNonNull(name, "[ERROR] 미션 이름 null불가");
        this.name = name;
    }
}
