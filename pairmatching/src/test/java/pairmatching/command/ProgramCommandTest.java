package pairmatching.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ProgramCommandTest {

    @Test
    void 존재하지_않는_커맨드_입력_시_예외발생() {
        assertThatThrownBy(() -> ProgramCommand.from("4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 커맨드");
    }
}
