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
}
