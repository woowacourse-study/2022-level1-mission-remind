package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import pairmatching.domain.crew.Crew;
import pairmatching.domain.pair.Pair;
import pairmatching.strategy.CrewRandomsShuffleStrategy;

public class RandomMissionProgram {

    private final List<CourseMissions> courseMissions;

    public RandomMissionProgram(final List<CourseMissions> courseMissions) {
        Objects.requireNonNull(courseMissions, "[ERROR] courseMissions null불가");
        this.courseMissions = new ArrayList<>(courseMissions);
    }

    public RandomMissionProgram(final Map<String, Level> missions, final Map<Course, List<String>> crewNames) {
        this(createCourseMissions(missions, crewNames));
    }

    private static List<CourseMissions> createCourseMissions(final Map<String, Level> missions,
                                                             final Map<Course, List<String>> crewNames) {
        return crewNames.keySet()
                .stream()
                .map(course -> createCourseCrews(crewNames, course))
                .map(crews -> new CourseMissions(crews, new CrewRandomsShuffleStrategy(), missions))
                .collect(Collectors.toList());
    }

    private static List<Crew> createCourseCrews(final Map<Course, List<String>> crewNames, final Course course) {
        return crewNames.get(course)
                .stream()
                .map(name -> new Crew(name, course))
                .collect(Collectors.toList());
    }

    public boolean isMatched(final Course course, final Level level, final String missionName) {
        final CourseMissions courseMissions = findCourseMissionsByCourse(course);
        return courseMissions.isMatched(missionName, level);
    }

    public List<Pair> matchPair(final Course course, final Level level, final String missionName) {
        final CourseMissions courseMissions = findCourseMissionsByCourse(course);
        return courseMissions.matchPair(missionName, level);
    }

    private CourseMissions findCourseMissionsByCourse(final Course course) {
        return courseMissions.stream()
                .filter(courseMissions -> courseMissions.isSameCourse(course))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 과정"));
    }

    public void resetAllPair() {
        for (CourseMissions courseMissions : this.courseMissions) {
            courseMissions.resetAllPair();
        }
    }

    public List<Pair> currentMatchedPairs(final Course course, final Level level, final String missionName) {
        final CourseMissions courseMissions = findCourseMissionsByCourse(course);
        return courseMissions.currentMatchedPairs(missionName, level);
    }
}
