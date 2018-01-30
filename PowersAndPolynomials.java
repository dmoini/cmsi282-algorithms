public class PowersAndPolynomials {
    public static int xToPowerN(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int halfPowerTimesHalfPower = xToPowerN(x, n / 2) * xToPowerN(x, n / 2);
        return (n % 2 == 0) ? halfPowerTimesHalfPower : x * halfPowerTimesHalfPower;
    }

    public static int polynomialEvaluation(int[] polynomial, int x) {
        int result = polynomial[0];
        for (int i = 1; i < polynomial.length; i++) {
            result = result * x + polynomial[i];
        }
        return result;
    }

    public static long gcd(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }

    public static long lcm(int x, int y) {
        return (x * y) / gcd(x, y);
    }
}
