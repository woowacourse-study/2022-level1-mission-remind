package pairmatching;

import static pairmatching.command.MatchCommand.NO;
import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.command.MatchCommand;
import pairmatching.command.ProgramCommand;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;
import pairmatching.domain.RandomMissionProgram;
import pairmatching.view.ErrorView;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchProgram {

    private final RandomMissionProgram randomMissionProgram;

    private PairMatchProgram(final RandomMissionProgram randomMissionProgram) {
        this.randomMissionProgram = randomMissionProgram;
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

        final RandomMissionProgram randomMissionProgram = new RandomMissionProgram(missions, crewNames);
        return new PairMatchProgram(randomMissionProgram);
    }

    public void run() {
        ProgramCommand command = inputProgramCommand();
        if (command == ProgramCommand.END) {
            return;
        }
        if (command == ProgramCommand.RESET) {
            randomMissionProgram.resetAllPair();
        }
        if (command == ProgramCommand.MATCH) {
            List<Pair> pairs = runMatchCommand();
            OutputView.printCurrentMatchedPairs(pairs);
        }
        if (command == ProgramCommand.SEARCH) {
            List<Pair> pairs = runSearchCommand();
            OutputView.printCurrentMatchedPairs(pairs);
        }
        run();
    }

    private ProgramCommand inputProgramCommand() {
        try {
            return ProgramCommand.from(InputView.inputCommand());
        } catch (IllegalArgumentException e) {
            ErrorView.printError(e);
            return inputProgramCommand();
        }
    }

    private List<Pair> runMatchCommand() {
        try {
            List<String> missionValues = InputView.inputMission();
            Course course = Course.from(missionValues.get(0));
            Level level = Level.from(missionValues.get(1));
            String missionName = missionValues.get(2);
            if (randomMissionProgram.isMatched(course, level, missionName) && inputMatchCommand() == NO) {
                return runMatchCommand();
            }
            return randomMissionProgram.matchPair(course, level, missionName);
        } catch (IllegalArgumentException | IllegalStateException e) {
            ErrorView.printError(e);
            return runMatchCommand();
        }
    }

    private MatchCommand inputMatchCommand() {
        try {
            return MatchCommand.from(InputView.inputMatchCommand());
        } catch (IllegalArgumentException e) {
            ErrorView.printError(e);
            return inputMatchCommand();
        }
    }

    private List<Pair> runSearchCommand() {
        try {
            List<String> missionValues = InputView.inputMission();
            Course course = Course.from(missionValues.get(0));
            Level level = Level.from(missionValues.get(1));
            String missionName = missionValues.get(2);
            return randomMissionProgram.currentMatchedPairs(course, level, missionName);
        } catch (IllegalArgumentException | IllegalStateException e) {
            ErrorView.printError(e);
            return runSearchCommand();
        }
    }
}
