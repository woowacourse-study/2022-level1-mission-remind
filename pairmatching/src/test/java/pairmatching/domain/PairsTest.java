package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Course.BACKEND;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PairsTest {

    @ParameterizedTest
    @MethodSource("createInvalidPairSize")
    void 제한된_인원인_경우_예외발생(final List<String> names) {
        assertThatThrownBy(() -> Pairs.createPairs(names, BACKEND))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createInvalidPairSize() {
        return Stream.of(Arguments.of(List.of("name1")), Arguments.of(new ArrayList<>()));
    }

    @Test
    void 짝수_페어_생성() {
        final List<String> names = List.of("name1", "name2", "name3", "name4");
        final Pairs expected = new Pairs(List.of(new Pair(BACKEND, "name1", "name2"),
                new Pair(BACKEND, "name3", "name4")));

        assertThat(Pairs.createPairs(names, BACKEND)).isEqualTo(expected);
    }

    @Test
    void 홀수_페어_생성() {
        final List<String> names = List.of("name1", "name2", "name3", "name4", "name5");
        final Pairs expected = new Pairs(List.of(new Pair(BACKEND, "name1", "name2"),
                new Pair(BACKEND, "name3", "name4", "name5")));

        assertThat(Pairs.createPairs(names, BACKEND)).isEqualTo(expected);
    }

    @Test
    void 이미_페어매칭_이력이_있는_경우_true() {
        final Pairs pairs = new Pairs(List.of(new Pair(BACKEND, "name1", "name2"),
                new Pair(BACKEND, "name4", "name5")));
        final Pairs comparePairs = new Pairs(List.of(new Pair(BACKEND, "name1", "name2"),
                new Pair(BACKEND, "name4", "name3")));

        assertThat(pairs.containAlreadyPair(comparePairs)).isTrue();
    }

    @Test
    void 페어매칭_이력이_없는_경우_false() {
        final Pairs pairs = new Pairs(List.of(new Pair(BACKEND, "name1", "name2"),
                new Pair(BACKEND, "name3", "name4")));
        final Pairs comparePairs = new Pairs(List.of(new Pair(BACKEND, "name1", "name4"),
                new Pair(BACKEND, "name2", "name3")));

        assertThat(pairs.containAlreadyPair(comparePairs)).isFalse();
    }


    @Test
    void 매칭된_페어가_없는데_초기화하는_경우_예외발생() {
        final Pairs pairs = new Pairs(new ArrayList<>());

        assertThatThrownBy(() -> pairs.resetPair())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 매칭된 이력이 없어 초기화 불가");
    }

    @Test
    void 매칭된_페어_초기화() {
        final Pairs pairs = new Pairs(List.of(new Pair(BACKEND, "name1", "name2"),
                new Pair(BACKEND, "name4", "name5")));
        pairs.resetPair();

        assertThat(pairs).isEqualTo(new Pairs(new ArrayList<>()));
    }

    @Test
    void 매칭된_페어가_없는데_현재_페어를_받으려는_경우_예외발생() {
        final Pairs pairs = new Pairs(new ArrayList<>());

        assertThatThrownBy(() -> pairs.currentMatchedPairs())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 매칭된 이력이 없어 현재 페어 반환 불가");
    }
}
