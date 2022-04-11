package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MissionProgram {

    private final CourseCrews crews;
    private final List<Mission> missions;

    public MissionProgram(final CourseCrews crews, final List<Mission> missions) {
        Objects.requireNonNull(crews, "[ERROR] crews null불가");
        Objects.requireNonNull(missions, "[ERROR] missions null불가");
        this.crews = crews;
        this.missions = new ArrayList<>(missions);
    }
}
