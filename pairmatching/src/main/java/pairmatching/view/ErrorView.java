package pairmatching.view;

public class ErrorView {

    private ErrorView() {
        throw new AssertionError();
    }

    public static void printError(final RuntimeException runtimeException) {
        System.out.println(runtimeException.getMessage());
    }
}
