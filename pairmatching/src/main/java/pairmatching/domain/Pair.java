package pairmatching.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Pair {

    private final Set<Crew> crews;

    public Pair(final Set<Crew> crews) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        this.crews = new LinkedHashSet<>(crews);
        validatePairSize(crews);
    }

    private void validatePairSize(final Set<Crew> crews) {
        if (crews.size() != 2 && crews.size() != 3) {
            throw new IllegalArgumentException("[ERROR] 페어는 2명 또는 3명만 가능");
        }
    }
}
