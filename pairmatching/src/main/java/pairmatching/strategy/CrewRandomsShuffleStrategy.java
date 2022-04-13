package pairmatching.strategy;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.crew.Crew;

public class CrewRandomsShuffleStrategy implements CrewShuffleStrategy {

    @Override
    public List<String> shuffledCrewNames(final List<Crew> crews) {
        return Randoms.shuffle(crews.stream()
                .map(Crew::name)
                .collect(Collectors.toList()));
    }
}
