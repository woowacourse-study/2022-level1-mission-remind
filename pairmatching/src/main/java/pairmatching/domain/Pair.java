package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pair {

    private final List<Crew> crews;

    public Pair(final List<Crew> crews) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        this.crews = new ArrayList<>(crews);
    }
}
