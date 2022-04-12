package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pairs {

    private final List<Pair> pairs;

    public Pairs(final List<Pair> pairs) {
        this.pairs = new ArrayList<>(pairs);
    }

    public static Pairs createPairs(final List<String> names, final Course course) {
        if (names.size() % 2 == 0) {
            return new Pairs(createEvenPairs(names, course));
        }
        return new Pairs(createOddPairs(names, course));
    }

    private static List<Pair> createEvenPairs(final List<String> names, final Course course) {
        if (names.size() < 2) {
            throw new IllegalArgumentException("[ERROR] 2인 미만은 존재할 수 없음");
        }
        return IntStream.range(0, names.size() / 2)
                .map(index -> index * 2)
                .mapToObj(index -> new Pair(course, names.get(index), names.get(index + 1)))
                .collect(Collectors.toList());
    }

    private static List<Pair> createOddPairs(final List<String> names, final Course course) {
        if (names.size() < 3) {
            throw new IllegalArgumentException("[ERROR] 3인 미만은 존재할 수 없음");
        }
        List<Pair> pairs = createEvenPairs(names.subList(0, names.size() - 3), course);
        final int lastPairIndex = names.size() - 3;
        pairs.add(
                new Pair(course, names.get(lastPairIndex), names.get(lastPairIndex + 1), names.get(lastPairIndex + 2)));
        return pairs;
    }

    public boolean containAlreadyPair(final Pairs pairs) {
        return this.pairs
                .stream()
                .anyMatch(pairs::containPair);
    }

    private boolean containPair(final Pair pair) {
        return pairs.stream()
                .anyMatch(pair::isSamePair);
    }

    public List<Pair> replaceAllPairs(Pairs pairs) {
        this.pairs.clear();
        this.pairs.addAll(pairs.pairs);
        return List.copyOf(this.pairs);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Pairs pairs1 = (Pairs) o;
        return Objects.equals(pairs, pairs1.pairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pairs);
    }
}
