package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class MissionTest {

    @ParameterizedTest
    @NullSource
    void 미션_이름이_null인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Mission(name, new ArrayList<>()))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 미션 이름 null불가");
    }

    @ParameterizedTest
    @NullSource
    void 매칭_페어가_null인_경우_예외발생(final List<Pair> pairs) {
        assertThatThrownBy(() -> new Mission("mission", pairs))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 매칭 pair null 불가");
    }
}
