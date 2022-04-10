package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class PairTest {

    @ParameterizedTest
    @NullSource
    void 페어가_null인_경우_예외발생(final List<Crew> crews) {
        assertThatThrownBy(() -> new Pair(crews))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] crews null불가");
    }
}
