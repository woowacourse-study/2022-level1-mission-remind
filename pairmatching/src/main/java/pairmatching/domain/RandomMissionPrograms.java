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
}
