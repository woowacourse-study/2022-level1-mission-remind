package pairmatching.command;

import java.util.Arrays;

public enum MatchCommand {

    YES("예"),
    NO("아니오"),
    ;

    private final String name;

    MatchCommand(final String name) {
        this.name = name;
    }

    public static MatchCommand from(final String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 커맨드"));
    }
}
