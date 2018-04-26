public class DiceSolver{

    private static final int defaultNumberOfGames = 1000000;
    private static final int defaultNumberOfDice = 5;

    public static double diceSolver(int numOfDice) {
        int wins = 0, losses = 0, gamesRemaining = defaultNumberOfGames, numberOfSixes = 0;
        int[] dice = new int[numOfDice];
        while (gamesRemaining > 0) {
            roll(dice);
            numberOfSixes = numberOfSixes(dice);
            if (numberOfSixes < 2) {
                if (numberOfSixes == 1) {
                    wins++;
                } else {
                    losses++;
                }
                gamesRemaining--;
            }
        }
        return (double) wins / defaultNumberOfGames;
    }

    private static int numberOfSixes(int[] dice) {
        int numOfSixes = 0;
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] == 6) {
                numOfSixes++;
            }
        }
        return numOfSixes;
    }

    private static void roll(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int) Math.floor(Math.random() * 6 + 1);
        }
    }

    public static void main(String[] args) {
        int numberOfDice = args.length == 0 ? defaultNumberOfDice : Integer.parseInt(args[0]);
        if (numberOfDice <= 0) {
            throw new IllegalArgumentException("Please input a number greater than 0");
        }
        double winPercentage = diceSolver(numberOfDice);
        System.out.println(winPercentage);
    }
}
