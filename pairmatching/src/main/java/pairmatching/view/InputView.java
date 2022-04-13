package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_COMMAND_MESSAGE = String.format("%s%n%s%n%s%n%s%n%s%n",
            "기능을 선택하세요.", "1. 페어 매칭", "2. 페어 조회", "3. 페어 초기화", "Q. 종료");

    private static final String INPUT_MISSION_MESSAGE = String.format("%s%n%s%n",
            "과정, 레벨, 미션을 선택하세요.", "ex) 백엔드, 레벨1, 자동차경주");
    private static final String INPUT_MISSION_ERROR_MESSAGE = "미션을 다시 입력해주세요.";
    private static final int INPUT_MISSION_SIZE = 3;

    private static final String INPUT_MATCH_COMMAND_MESSAGE = String.format("%s%n%s%n",
            "매칭 정보가 있습니다. 다시 매칭하시겠습니까?", "네 | 아니오");

    private static final String BACKEND_CREW_FILE_NAME = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_FILE_NAME = "src/main/resources/frontend-crew.md";

    private InputView() {
        throw new AssertionError();
    }

    public static String inputCommand() {
        System.out.print(INPUT_COMMAND_MESSAGE);
        return consoleRead();
    }

    public static List<String> inputMission() {
        System.out.println(INPUT_MISSION_MESSAGE);
        List<String> mission = Arrays.stream(consoleRead().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        if (mission.size() != INPUT_MISSION_SIZE) {
            System.out.println(INPUT_MISSION_ERROR_MESSAGE);
            return inputMission();
        }
        return mission;
    }

    public static String inputMatchCommand() {
        System.out.print(INPUT_MATCH_COMMAND_MESSAGE);
        return consoleRead();
    }

    private static String consoleRead() {
        return Console.readLine();
    }

    public static List<String> inputBackendCrews() {
        try {
            File file = new File(BACKEND_CREW_FILE_NAME);
            Scanner scanner = new Scanner(file);
            List<String> crews = new ArrayList<>();
            while (scanner.hasNextLine()) {
                crews.add(scanner.nextLine());
            }
            return crews;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("[ERROR] 파일 입출력 에러");
        }
    }

    public static List<String> inputFrontendCrews() {
        try {
            File file = new File(FRONTEND_CREW_FILE_NAME);
            Scanner scanner = new Scanner(file);
            List<String> crews = new ArrayList<>();
            while (scanner.hasNextLine()) {
                crews.add(scanner.nextLine());
            }
            return crews;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("[ERROR] 파일 입출력 에러");
        }
    }
}
