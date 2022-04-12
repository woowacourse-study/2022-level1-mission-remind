package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mission {

    private final String name;
    private final Level level;
    private final List<Pair> pairs;

    public Mission(final String name, final Level level, final List<Pair> pairs) {
        Objects.requireNonNull(name, "[ERROR] 미션 이름 null불가");
        Objects.requireNonNull(level, "[ERROR] level null 불가");
        Objects.requireNonNull(pairs, "[ERROR] 매칭 pair null 불가");
        this.name = name;
        this.level = level;
        this.pairs = new ArrayList<>(pairs);
    }

    public Mission(final String name, final Level level) {
        this(name, level, new ArrayList<>());
    }

    public boolean containAlreadyPairCrew(final Mission mission) {
        if (!isSameLevel(mission)) {
            return false;
        }
        return mission.pairs
                .stream()
                .noneMatch(this::containPairCrew);
    }

    private boolean isSameLevel(final Mission mission) {
        return this.level == mission.level;
    }

    private boolean containPairCrew(final Pair comparePair) {
        return pairs.stream()
                .anyMatch(comparePair::isSamePair);
    }
}
