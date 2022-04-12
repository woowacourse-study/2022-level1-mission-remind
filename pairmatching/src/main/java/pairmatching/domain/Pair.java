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

    public boolean isSamePair(final Pair pair) {
        if (!isSameSize(pair)) {
            return false;
        }
        return this.crews.equals(pair.crews);
    }

    private boolean isSameSize(final Pair pair) {
        return this.crews.size() == pair.crews.size();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pair pair = (Pair) o;
        return Objects.equals(crews, pair.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crews);
    }
}
