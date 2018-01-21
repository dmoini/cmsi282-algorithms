public class RootFinder {
    private static double root(double x, double epsilon, int exponent) {
        if (x < 0) {
            throw new IllegalArgumentException("Please input a positive number.");
        }
        if (x == 1) {
            return 1;
        }
        double root;
        double lowerBound = toPrecision(x - (x * epsilon), 3);
        double upperBound = toPrecision(x + (x * epsilon), 3);
        if (x > 0 && x < 1) {
            root = (x + 1) / 2;
        } else {  // x > 1 or x == 0
            root = x / 2;
        }
        while (toPrecision(Math.pow(root, exponent), 3) < lowerBound || toPrecision(Math.pow(root, exponent), 3) > upperBound) {
            if (Math.pow(root, exponent) > x) {
                root /= 2;
            } else if (Math.pow(root, exponent) < x) {
                root += root / 2;
            }
        }
        return toPrecision(root, 3);
    }

    private static double toPrecision(double number, int places) {
        int multiplier = (int) Math.pow(10, places);
        return (double) ((long) (number * multiplier)) / multiplier;
    }
}
