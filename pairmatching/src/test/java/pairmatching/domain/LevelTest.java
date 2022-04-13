package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LevelTest {

    @Test
    void 존재하지_않는_레벨_입력_시_예외발생() {
        assertThatThrownBy(() -> Level.from("레벨6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 레벨");
    }
}
