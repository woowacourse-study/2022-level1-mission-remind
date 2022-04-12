package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import pairmatching.strategy.CrewRandomsShuffleStrategy;

public class MissionProgram {

    private final CourseCrews crews;
    private final List<Mission> missions;

    public MissionProgram(final CourseCrews crews, final List<Mission> missions) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        Objects.requireNonNull(missions, "[ERROR] missions null불가");
        this.crews = crews;
        this.missions = new ArrayList<>(missions);
    }

    public MissionProgram(final List<Crew> crews, final Map<String, Level> missionMap) {
        this(new CourseCrews(crews, new CrewRandomsShuffleStrategy()), convertMissions(missionMap));
    }

    private static List<Mission> convertMissions(final Map<String, Level> missions) {
        return missions.entrySet()
                .stream()
                .map(entry -> new Mission(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
