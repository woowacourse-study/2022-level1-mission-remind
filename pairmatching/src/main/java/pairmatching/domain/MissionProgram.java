package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MissionProgram {

    private final Course course;
    private final List<Mission> missions;

    public MissionProgram(final Course course, final List<Mission> missions) {
        Objects.requireNonNull(course, "[ERROR] course null불가");
        Objects.requireNonNull(missions, "[ERROR] missions null불가");
        this.course = course;
        this.missions = new ArrayList<>(missions);
    }
}
