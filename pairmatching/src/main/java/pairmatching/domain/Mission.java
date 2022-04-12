package pairmatching.domain;

import java.util.ArrayList;
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
}
