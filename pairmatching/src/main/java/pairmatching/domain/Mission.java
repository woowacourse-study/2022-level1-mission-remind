package pairmatching.domain;

import java.util.List;
import java.util.Objects;

public class Mission {

    private final String name;
    private final List<Pair> pairs;

    public Mission(final String name, final List<Pair> pairs) {
        Objects.requireNonNull(name, "[ERROR] 미션 이름 null불가");
        this.name = name;
        this.pairs = pairs;
    }
}
