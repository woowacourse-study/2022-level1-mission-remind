package pairmatching.command;

import java.util.Arrays;

public enum ProgramCommand {

    MATCH("1"),
    SEARCH("2"),
    RESET("3"),
    END("Q"),
    ;

    private final String outputName;

    ProgramCommand(final String outputName) {
        this.outputName = outputName;
    }

    public static ProgramCommand from(final String outputName) {
        return Arrays.stream(values())
                .filter(value -> value.outputName.equals(outputName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 커맨드"));
    }
}
