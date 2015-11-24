package movement_in_tree_2;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class Solution {
    static long MODULO = 1000_000_007;
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            test = 1;
            int tests = nextInt();
            while (test <= tests) {
                out.println(solve());
                ++test;
            }
            in.close();
            out.close();

        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }

    private static long solve() throws Exception {
        long d = nextInt();
        long k = nextInt();
        long h = nextInt();
        long d_minus_1_inverse = calcInverse(d - 1, MODULO);
        if (k < h) {
            long d_pow_k_plus_1 = pow(d, k + 1, MODULO);
            return ((((d_pow_k_plus_1 + MODULO - 1) % MODULO) * d_minus_1_inverse) % MODULO);
        } else {
            long d_pow_2_minus_1_inverse = calcInverse(d * d - 1, MODULO);
            long d_pow_h = pow(d, h, MODULO);
            long d_pow_k_minus_h_plus_1 = pow(d, k - h + 1, MODULO);
            long d_pow_k_minus_h_plus_2 = (d_pow_k_minus_h_plus_1 * d) % MODULO;
            if( (k - h) % 2 == 0) {
                return (((((d_pow_k_minus_h_plus_2 + MODULO - 1) % MODULO) * d_pow_2_minus_1_inverse) % MODULO) +
                        ((((d_pow_k_minus_h_plus_1 * ((d_pow_h + MODULO - 1) % MODULO)) % MODULO) *
                                d_minus_1_inverse) % MODULO)) % MODULO;
            } else {
                return (((((d_pow_k_minus_h_plus_2 + MODULO - d) % MODULO) * d_pow_2_minus_1_inverse) % MODULO) +
                        ((((d_pow_k_minus_h_plus_1 * ((d_pow_h + MODULO - 1) % MODULO)) % MODULO) *
                                d_minus_1_inverse) % MODULO)) % MODULO;
            }
        }
    }

    private static long pow(long d, long degree, long modulo) {
        long result = 1;
        long cur = d;
        while (degree > 0) {
            if ((degree & 1) == 1) {
                result = (result * cur) % modulo;
            }
            cur = (cur * cur) % modulo;
            degree >>= 1;
        }
        return result;
    }

    private static long calcInverse(long a, long modulo) {
        return ((gcd(a, modulo).x % modulo) + modulo) % modulo;
    }

    private static EuclidResult gcd(long a, long b) {
        if (a == 0) {
            return new EuclidResult(b, 0, 1);
        } else {
            EuclidResult result = gcd(b % a, a);
            result.recalculate(a, b);
            return result;
        }
    }

    private static int nextInt() throws IOException {
        return parseInt(next());
    }

    private static long nextLong() throws IOException {
        return parseLong(next());
    }

    private static double nextDouble() throws IOException {
        return parseDouble(next());
    }

    private static String next() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine().trim());
        }
        return tok.nextToken();
    }

    private static boolean hasNext() throws Exception {
        if (tok == null || !tok.hasMoreTokens()) {
            String line = in.readLine();
            if (line != null) {
                tok = new StringTokenizer(line.trim());
                return hasNext();
            } else {
                return false;
            }
        }
        return true;
    }

    static class EuclidResult {
        long gcd = 1;
        long x, y;
        private long x1, y1;

        EuclidResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }

        public void recalculate(long a, long b) {
            x1 = x;
            y1 = y;
            x = y1 - (b / a) * x1;
            y = x1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EuclidResult that = (EuclidResult) o;

            if (gcd != that.gcd) return false;
            if (x != that.x) return false;
            if (y != that.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (int) (gcd ^ (gcd >>> 32));
            result = 31 * result + (int) (x ^ (x >>> 32));
            result = 31 * result + (int) (y ^ (y >>> 32));
            return result;
        }

        @Override
        public String toString() {
            return "EuclidResult{" +
                    "gcd=" + gcd +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}