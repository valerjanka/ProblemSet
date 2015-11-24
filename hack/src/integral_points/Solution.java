package integral_points;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

/**
 * ou are given a triangle where the vertices are A(x1,y1),B(x2,y2) and C(x3,y3).
 * All three vertices have integral coordinates. An integral point is defined as the point with both
 * X and Y coordinates as integers. Cay you find the number of integral points inside the triangle using
 * Pick's theorem?
 * <p>
 * Input Format
 * <p>
 * The first line of input contains T i.e. number of test cases.
 * The next T lines will contain 6 integers x1,y1,x2,y2,x3,y3.
 * <p>
 * Constraints
 * 1?T?100
 * 0?x1,y1,x2,y2,x3,y3?109
 * <p>
 * Output Format
 * <p>
 * Print T lines each containing the number of integral points in that triangle.
 * <p>
 * Sample Input
 * <p>
 * 2
 * 0 0 0 2 2 0
 * 0 0 3 0 0 3
 * Sample Output
 * <p>
 * 0
 * 1
 * Explanation
 * <p>
 * In the second test case, (1,1) is the only integral point inside the triangle.
 */
public class Solution {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            test = 1;
            nextInt();
            while (hasNext()) {

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

    private static String solve() throws Exception {
        long x1 = nextInt();
        long y1 = nextInt();
        long x2 = nextInt();
        long y2 = nextInt();
        long x3 = nextInt();
        long y3 = nextInt();
        long s2 = calc2S(x1, y1, x2, y2, x3, y3);
        //out.println("s=" + s2);
        long b = calcB(Math.abs(x1 - x2), Math.abs(y1 - y2)) + calcB(Math.abs(x1 - x3), Math.abs(y1 - y3)) + calcB(Math.abs(x3 - x2), Math.abs(y3 - y2)) + 3;
        //out.println("b=" + b);
        return "" + (s2 - b + 2) / 2L;
    }

    private static long calcB(long a, long b) {
        if (a > 0 && b > 0) {
            return gcd(a, b) - 1;
        } else {
            return a + b - 1;
        }
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    private static long calc2S(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Math.abs((x1 * y2 - x2 * y1 + x2 * y3 - x3 * y2 + x3 * y1 - x1 * y3));
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
}