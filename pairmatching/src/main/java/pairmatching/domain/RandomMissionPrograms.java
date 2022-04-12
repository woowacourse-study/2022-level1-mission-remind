package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import pairmatching.strategy.CrewRandomsShuffleStrategy;

public class RandomMissionPrograms {

    private final List<MissionProgram> missionPrograms;

    public RandomMissionPrograms(final List<MissionProgram> missionPrograms) {
        Objects.requireNonNull(missionPrograms, "[ERROR] missionPrograms null불가");
        this.missionPrograms = new ArrayList<>(missionPrograms);
    }

    public RandomMissionPrograms(final Map<String, Level> missions, final Map<Course, List<String>> crewNames) {
        this(createMissionPrograms(missions, crewNames));
    }

    private static List<MissionProgram> createMissionPrograms(final Map<String, Level> missions,
                                                   final Map<Course, List<String>> crewNames) {
        return crewNames.keySet()
                .stream()
                .map(course -> createCourseCrews(crewNames, course))
                .map(crews -> new MissionProgram(crews, new CrewRandomsShuffleStrategy(), missions))
                .collect(Collectors.toList());
    }

    private static List<Crew> createCourseCrews(final Map<Course, List<String>> crewNames, final Course course) {
        return crewNames.get(course)
                .stream()
                .map(name -> new Crew(name, course))
                .collect(Collectors.toList());
    }

    public List<Pair> matchPair(final Course course, final Level level, final String missionName) {
        final MissionProgram missionProgram = findMissionProgramByCourse(course);
        return missionProgram.matchPair(missionName, level);
    }

    private MissionProgram findMissionProgramByCourse(final Course course) {
        return missionPrograms.stream()
                .filter(missionProgram -> missionProgram.isSameCourse(course))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 과정"));
    }

    public void resetPair(final Course course, final Level level, final String missionName) {
        final MissionProgram missionProgram = findMissionProgramByCourse(course);
        missionProgram.resetPair(missionName, level);
    }

    public List<Pair> currentMatchedPairs(final Course course, final Level level, final String missionName) {
        final MissionProgram missionProgram = findMissionProgramByCourse(course);
        return missionProgram.currentMatchedPairs(missionName, level);
    }
}
