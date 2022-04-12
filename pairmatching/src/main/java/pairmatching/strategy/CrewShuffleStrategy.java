package pairmatching.strategy;

import java.util.List;
import pairmatching.domain.Crew;

public interface CrewShuffleStrategy {

    List<String> shuffledCrewNames(final List<Crew> crews);
}
