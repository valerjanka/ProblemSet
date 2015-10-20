import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class Solution {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
    static long[][] solutions = new long[10][10];

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            test = 1;
            calcSolutions();
            while(hasNext()) {
                out.println("Case " + test + ": " + solve());

                ++test;
            }
            in.close();
            out.close();

        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }

    private static void calcSolutions() {
        for(int people = 1; people < solutions.length; people++) {
            for(int group = 1; group <= people; group++) {
                solutions[people][group] = calcSolution(people, group);
            }
        }
    }

    private static long calcSolution(int people, int group) {
        long result = 1;
        while(!isGoal(result, people, group)) ++result;
        return result;
    }

    private static boolean isGoal(long result, int people, int group) {

        return false;
    }

    private static String solve() throws Exception {
        return "" + solutions[nextInt()][nextInt()];
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
            if(line != null) {
                tok = new StringTokenizer(line.trim());
                return hasNext();
            } else {
                return false;
            }
        }
        return true;
    }
}