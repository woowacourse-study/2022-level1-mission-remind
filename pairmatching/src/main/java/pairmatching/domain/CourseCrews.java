package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pairmatching.strategy.CrewShuffleStrategy;

public class CourseCrews {

    private final List<Crew> crews;
    private final CrewShuffleStrategy crewShuffleStrategy;

    public CourseCrews(final List<Crew> crews, final CrewShuffleStrategy crewShuffleStrategy) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        Objects.requireNonNull(crewShuffleStrategy, "[ERROR] 크루 셔플 전략 null불가");
        this.crews = new ArrayList<>(crews);
        this.crewShuffleStrategy = crewShuffleStrategy;
        validateEmptyCrews(this.crews);
        validateAnotherCourse(this.crews);
    }

    private void validateEmptyCrews(final List<Crew> crews) {
        if (crews.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 크루는 불가");
        }
    }

    private void validateAnotherCourse(final List<Crew> crews) {
        if (containAnotherCourse(crews)) {
            throw new IllegalArgumentException("[ERROR] 다른 과정의 크루가 존재");
        }
    }

    private boolean containAnotherCourse(final List<Crew> crews) {
        final Crew firstCrew = crews.get(0);
        return crews.stream()
                .anyMatch(firstCrew::isAnotherCrew);
    }

    public Course course() {
        return crews.stream()
                .findAny()
                .map(Crew::course)
                .orElseThrow(() -> new IllegalStateException("[ERROR] 과정을 반환할 수 없음"));
    }

    public List<String> shuffledCrewNames() {
        return crewShuffleStrategy.shuffledCrewNames(crews);
    }
}
