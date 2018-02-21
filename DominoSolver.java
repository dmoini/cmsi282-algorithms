public class DominoSolver {

    public static int dominoSolver(int[] dominoes, int[][] highestPoints, int i, int j) {
        if (highestPoints[i][j] > 0) {
            return highestPoints[i][j];
        }
        if (i == j) {
            return 0;
        }
        for (int k = i; k < j; k++) {
            int points = dominoSolver(dominoes, highestPoints, i, k) + dominoSolver(dominoes, highestPoints, k + 1, j) +
                         dominoes[i - 1] * dominoes[j] * dominoes[k];
            if (points > highestPoints[i][j]) {
                highestPoints[i][j] = points;
            }
        }
        return highestPoints[i][j];
    }

    public static boolean isValidSequence(int[] dominoes) {
        if (dominoes.length < 4 || dominoes.length % 2 == 1) {
            return false;
        }
        for (int i = 1; i < dominoes.length - 1; i = i + 2) {
            if (dominoes[i] != dominoes[i + 1] || dominoes[i] < 1) {
                return false;
            }
        }
        if (dominoes[0] < 1 || dominoes[dominoes.length - 1] < 1) {
            return false;
        }
        return true;
    }

    private static int[] convertDominoesIntoValuesArray(int[] dominoes) {
        int[] dominoValues = new int[dominoes.length / 2 + 1];
        for (int i = 0; i < dominoValues.length - 1; i++) {
            dominoValues[i] = dominoes[i * 2];
        }
        dominoValues[dominoValues.length - 1] = dominoes[dominoes.length - 1];
        return dominoValues;
    }

    public static void main(String[] args) {
        int[] dominoes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            dominoes[i] = Integer.parseInt(args[i]);
        }
        if (!isValidSequence(dominoes)) {
            throw new IllegalArgumentException("Invalid domino sequence");
        }
        dominoes = convertDominoesIntoValuesArray(dominoes);
        int[][] highestPoints = new int[dominoes.length][dominoes.length];
        System.out.println(dominoSolver(dominoes, highestPoints, 1, dominoes.length - 1));
    }
}
