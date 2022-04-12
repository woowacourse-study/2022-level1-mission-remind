package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mission {

    private final String name;
    private final Level level;
    private final Pairs pairs;

    public Mission(final String name, final Level level, final Pairs pairs) {
        Objects.requireNonNull(name, "[ERROR] 미션 이름 null불가");
        Objects.requireNonNull(level, "[ERROR] level null 불가");
        Objects.requireNonNull(pairs, "[ERROR] 매칭 pair null 불가");
        this.name = name;
        this.level = level;
        this.pairs = pairs;
    }

    public Mission(final String name, final Level level) {
        this(name, level, new Pairs(new ArrayList<>()));
    }

    public boolean containAlreadyPairCrew(final Pairs pairs) {
        return this.pairs.containAlreadyPair(pairs);
    }

    public boolean isSameLevel(final Mission mission) {
        return this.level == mission.level;
    }

    public boolean isSameMission(final String name, final Level level) {
        return this.name.equals(name) && this.level == level;
    }

    public List<Pair> matchPair(final Pairs pairs) {
        return this.pairs.replaceAllPairs(pairs);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Mission mission = (Mission) o;
        return Objects.equals(name, mission.name) && level == mission.level && Objects
                .equals(pairs, mission.pairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, pairs);
    }
}
