package pairmatching;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;

public interface PairMatchProgramRunner {

    List<Pair> apply(Course course, Level level, String name);
}
