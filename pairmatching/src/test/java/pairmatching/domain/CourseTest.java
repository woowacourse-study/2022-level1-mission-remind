package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    void 존재하지_않는_과정_입력_시_예외발생() {
        assertThatThrownBy(() -> Course.from("모바일"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 과정");
    }
}
