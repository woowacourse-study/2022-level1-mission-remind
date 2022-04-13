package pairmatching.view;

import java.util.List;
import pairmatching.domain.pair.Pair;

public class OutputView {

    private OutputView() {
        throw new AssertionError();
    }

    public static void printCurrentMatchedPairs(final List<Pair> pairs) {
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairs) {
            printPairNames(pair);
        }
    }

    private static void printPairNames(final Pair pair) {
        System.out.println(convertPairName(pair));
    }

    private static String convertPairName(final Pair pair) {
        return String.join(" : ", pair.pairNames());
    }
}
