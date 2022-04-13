package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_COMMAND_MESSAGE = String.format("%s%n%s%n%s%n%s%n%s%n",
            "기능을 선택하세요.", "1. 페어 매칭", "2. 페어 조회", "3. 페어 초기화", "Q. 종료");

    private InputView() {
        throw new AssertionError();
    }

    public static String inputCommand() {
        System.out.print(INPUT_COMMAND_MESSAGE);
        return Console.readLine();
    }
}
