public class DominoSolverTests {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        runTests();
    }

    public static boolean runTests() {
        testIsValidSequence();
        testDominoSolver();

        System.out.println(successes + "/" + attempts + " tests passed.");
        return attempts == successes;
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;
        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    private static void testIsValidSequence() {
        System.out.println("Testing isValidSequence...");

        try {
            int[] doms = {1, 2, 2, 3, 3, 4};
            displaySuccessIfTrue(DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {0, 1, 1, 0, 0, 1};
            displaySuccessIfTrue(DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {5, 10, 10, 7, 7, 6};
            displaySuccessIfTrue(DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10};
            displaySuccessIfTrue(DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2, 2, 3, 3, -4};
            displaySuccessIfTrue(!DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2, 3, 4};
            displaySuccessIfTrue(!DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2, 2};
            displaySuccessIfTrue(!DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2};
            displaySuccessIfTrue(!DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {-1, -2};
            displaySuccessIfTrue(!DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2, 2, 3, 3, 4, 5, 5};
            displaySuccessIfTrue(!DominoSolver.isValidSequence(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            String[] args = {"1", "2", "2", "3.5"};
            DominoSolver.main(args);
            displayFailure();
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        }

        try {
            String[] args = {"1", "2", "2", "a"};
            DominoSolver.main(args);
            displayFailure();
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        }
    }

    private static void testDominoSolver() {
        System.out.println("Testing maxDominoScore...");

        try {
            int[] doms = {5, 10, 7, 6};
            displaySuccessIfTrue(720 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {1, 2, 3, 4};
            displaySuccessIfTrue(32 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {4, 3, 2, 1};
            displaySuccessIfTrue(32 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {2, 4, 6, 100};
            displaySuccessIfTrue(3200 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {10, 100, 5, 50};
            displaySuccessIfTrue(75000 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {2, 5, 9, 6, 4};
            displaySuccessIfTrue(436 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }

        try {
            int[] doms = {10, 8, 15, 12, 6};
            displaySuccessIfTrue(3720 == maxDominoScore(doms));
        } catch (Exception e) {
            displayFailure();
        }
    }

    private static int maxDominoScore(int[] dominoes) {
        int[][]scores = new int[dominoes.length][dominoes.length];
        return DominoSolver.maxDominoScore(dominoes, scores, 1, dominoes.length - 1);
    }
}
