package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PairProgram {

    private final List<MissionProgram> missionPrograms;

    public PairProgram(final List<MissionProgram> missionPrograms) {
        Objects.requireNonNull(missionPrograms, "[ERROR] missionPrograms null불가");
        this.missionPrograms = new ArrayList<>(missionPrograms);
    }
}
