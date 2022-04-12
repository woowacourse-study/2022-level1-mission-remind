package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Level.LEVEL1;

import java.util.ArrayList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class MissionTest {

    @ParameterizedTest
    @NullSource
    void 미션_이름이_null인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Mission(name, LEVEL1, new Pairs(new ArrayList<>())))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 미션 이름 null불가");
    }

    @ParameterizedTest
    @NullSource
    void 매칭_페어가_null인_경우_예외발생(final Pairs pairs) {
        assertThatThrownBy(() -> new Mission("mission", LEVEL1, pairs))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 매칭 pair null 불가");
    }

    @ParameterizedTest
    @NullSource
    void 레벨이_null인_경우_예외발생(final Level level) {
        assertThatThrownBy(() -> new Mission("mission", level, new Pairs(new ArrayList<>())))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] level null 불가");
    }
}
