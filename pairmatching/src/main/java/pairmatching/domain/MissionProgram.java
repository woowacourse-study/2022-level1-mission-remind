package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import pairmatching.strategy.CrewShuffleStrategy;

public class MissionProgram {

    private final Crews crews;
    private final List<Mission> missions;

    public MissionProgram(final Crews crews, final List<Mission> missions) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        Objects.requireNonNull(missions, "[ERROR] missions null불가");
        this.crews = crews;
        this.missions = new ArrayList<>(missions);
    }

    public MissionProgram(final List<Crew> crews, final CrewShuffleStrategy strategy,
                          final Map<String, Level> missionMap) {
        this(new Crews(crews, strategy), convertMissions(missionMap));
    }

    private static List<Mission> convertMissions(final Map<String, Level> missions) {
        return missions.entrySet()
                .stream()
                .map(entry -> new Mission(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public boolean isMatched(final String missionName, final Level level) {
        final Mission mission = findMission(missionName, level);
        return mission.isMatched();
    }

    public List<Pair> matchPair(final String missionName, final Level level) {
        final Mission mission = findMission(missionName, level);
        Pairs pairs = IntStream.range(0, 3)
                .mapToObj(index -> crews.shuffledCrewNames())
                .map(names -> Pairs.createPairs(names, crews.course()))
                .filter(createPairs -> isAvalibalePair(createPairs, mission))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 3회 매칭 실패"));
        return mission.matchPair(pairs);
    }

    private Mission findMission(final String missionName, final Level level) {
        return missions.stream()
                .filter(mission -> mission.isSameMission(missionName, level))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 미션"));
    }

    private boolean isAvalibalePair(final Pairs pairs, final Mission currentMission) {
        return missions.stream()
                .filter(currentMission::isSameLevel)
                .filter(mission -> !currentMission.equals(mission))
                .noneMatch(mission -> mission.containAlreadyPairCrew(pairs));
    }

    public boolean isSameCourse(final Course course) {
        return crews.course() == course;
    }

    public void resetAllPair() {
        for (Mission mission : missions) {
            mission.resetPair();
        }
    }

    public List<Pair> currentMatchedPairs(final String missionName, final Level level) {
        final Mission mission = findMission(missionName, level);
        return mission.currentMatchedPairs();
    }
}
