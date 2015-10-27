import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

/**
 * An interesting application of invariants is in solving the following problem: Suppose you start from the number 0.
 * In a single operation you can either add 40 or subtract 24 from the current number. Is it possible to reach 12?
 * (i.e., is there a sequence of operations such that the final number is 12?) You can spend a lot of time trying out
 * a lot of sequences, or you can instead prove that it is impossible by noting the following invariant about the
 * current number: It is always a multiple of 8 (why?). Since 12 is not a multiple of 8, this shows that 12 is indeed
 * not reachable.
 * <p>
 * Now that you know about invariants, it's time to solve the following problem:
 * <p>
 * The first N natural numbers, 1,2,3…N, are written on the board. In a single operation, you can choose any pair
 * of numbers (x,y) written on the board, wipe out both numbers, and write either x+y or x?y on the board.
 * After N?1 operations, there will be exactly one number left on the board. Is there a sequence of operations
 * such that the final number is K?
 * <p>
 * Input Format
 * <p>
 * The first line of the input contains an integer T, the number of the test cases. The next T lines describe the T test cases.
 * <p>
 * Each test case consists of one line containing two integers separated by a space: N and K.
 * <p>
 * Output Format
 * <p>
 * For each test case, output one line containing the word YES if there is a sequence of operations such that
 * the final number is K, or NO otherwise.
 * <p>
 * Constraints
 * <p>
 * 1<=T<=100
 * 2<=N<=10^5
 * ?10^10<=K<=10^10
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
            next();
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
        long n = nextInt();
        long k = nextLong();
        if(n == 1) {
            return k == 1? "YES" : "NO";
        } else {
            if( k < 0) {
                k = -k;
                return ((((n + 1)/2) & 1) != (k & 1)) || (k > (n+1)*n/2L - 2) ? "NO" : "YES";
            } else {
                return ((((n + 1)/2) & 1) != (k & 1)) || (k > (n+1)*n/2L) ? "NO" : "YES";
            }
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
}