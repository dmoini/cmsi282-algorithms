public class RootFinder {
    private static double sqrt(double x, double epsilon) {
        if (x < 0) {
            throw new IllegalArgumentException("Please input a positive number.");
        }
        if (x == 1) {
            return 1;
        }
        double y;
        double lowerBound = toPrecision(x - (x * epsilon), 3);
        double upperBound = toPrecision(x + (x * epsilon), 3);
        if (x > 0 && x < 1) {
            y = (x + 1) / 2;
        } else {  // x > 1 or x == 0
            y = x / 2;
        }
        while (toPrecision(Math.pow(y, 2), 3) < lowerBound || toPrecision(Math.pow(y, 2), 3) > upperBound) {
            if (Math.pow(y, 2) > x) {
                y /= 2;
            } else if (Math.pow(y, 2) < x) {
                y += y / 2;
            }
        }
        return toPrecision(y, 3);
    }

    private static double toPrecision(double number, int places) {
        int multiplier = (int) Math.pow(10, places);
        return (double) ((long) (number * multiplier)) / multiplier;
    }
}
