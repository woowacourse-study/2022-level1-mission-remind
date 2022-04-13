package pairmatching;

import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.command.ProgramCommand;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;
import pairmatching.domain.RandomMissionPrograms;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchProgram {

    private final RandomMissionPrograms randomMissionPrograms;

    private PairMatchProgram(final RandomMissionPrograms randomMissionPrograms) {
        this.randomMissionPrograms = randomMissionPrograms;
    }

    public static PairMatchProgram createProgram() {
        Map<String, Level> missions = new HashMap<>();
        missions.put("자동차경주", LEVEL1);
        missions.put("로또", LEVEL1);
        missions.put("숫자야구게임", LEVEL1);
        missions.put("장바구니", LEVEL2);
        missions.put("결제", LEVEL2);
        missions.put("지하철노선도", LEVEL2);
        missions.put("성능개선", LEVEL4);
        missions.put("배포", LEVEL4);
        Map<Course, List<String>> crewNames = new HashMap<>();
        crewNames.put(Course.BACKEND, InputView.inputBackendCrews());
        crewNames.put(Course.FRONTEND, InputView.inputFrontendCrews());

        final RandomMissionPrograms randomMissionPrograms =  new RandomMissionPrograms(missions, crewNames);
        return new PairMatchProgram(randomMissionPrograms);
    }

    public void run() {
        ProgramCommand command = ProgramCommand.from(InputView.inputCommand());
        if (command == ProgramCommand.END) {
            return;
        }
        if (command == ProgramCommand.RESET) {
            randomMissionPrograms.resetAllPair();
        }
        if (command == ProgramCommand.MATCH) {
            List<String> missionValues = InputView.inputMission();
            Course course = Course.from(missionValues.get(0));
            Level level = Level.from(missionValues.get(1));
            String missionName = missionValues.get(2);
            List<Pair> pairs = randomMissionPrograms.matchPair(course, level, missionName);
            OutputView.printCurrentMatchedPairs(pairs);
        }
        if (command == ProgramCommand.SEARCH) {
            List<String> missionValues = InputView.inputMission();
            Course course = Course.from(missionValues.get(0));
            Level level = Level.from(missionValues.get(1));
            String missionName = missionValues.get(2);
            List<Pair> pairs = randomMissionPrograms.currentMatchedPairs(course, level, missionName);
            OutputView.printCurrentMatchedPairs(pairs);
        }
        run();
    }
}
