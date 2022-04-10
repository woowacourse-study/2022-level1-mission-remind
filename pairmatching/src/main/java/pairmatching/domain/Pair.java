package pairmatching.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Pair {

    private static final int MIN_PAIR_SIZE = 2;
    private static final int MAX_PAIR_SIZE = 3;

    private final Set<Crew> crews;

    public Pair(final Set<Crew> crews) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        this.crews = new LinkedHashSet<>(crews);
        validatePairSize(crews);
    }

    private void validatePairSize(final Set<Crew> crews) {
        if (crews.size() < MIN_PAIR_SIZE || MAX_PAIR_SIZE < crews.size()) {
            throw new IllegalArgumentException("[ERROR] 페어는 2명 또는 3명만 가능");
        }
    }
}
