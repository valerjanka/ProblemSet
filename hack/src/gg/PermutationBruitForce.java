package gg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class PermutationBruitForce {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            test = 128;

            while(test > 1) {
                out.println("Case " + stringOf(test) + ": " + solve());

                test--;
            }
            in.close();
            out.close();

        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }

    private static String stringOf(int test) {
        StringBuilder result = new StringBuilder();
        while(test > 1) {
            if(test % 2 == 0) {
                result.append('L');
            } else {
                result.append('G');
            }
            test = test >> 1;
        }
        return result.toString();
    }

    private static String solve() throws Exception {
        int test = PermutationBruitForce.test;
        int n = 1;
        String s = "a";
        while(test > 1) {
            n++;
            s += (char)('a' + n - 1);
            test = test >> 1;
        }
        return "" + perm2(s);
    }

    public static int perm2(String s) {
        int N = s.length();
        char[] a = new char[N];
        for (int i = 0; i < N; i++)
            a[i] = s.charAt(i);
        return perm2(a, N);
    }

    private static int perm2(char[] a, int n) {
        if (n == 1) {
            if(isGood(a))
                return 1;
            else
                return 0;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            result += perm2(a, n-1);
            swap(a, i, n-1);
        }
        return result;
    }

    private static boolean isGood(char[] a) {
        int test = PermutationBruitForce.test;
        int i = a.length - 2;
        while(test > 1) {
            if(test % 2 == 0) {
                if(a[i] > a[i+1])
                    return false;
            } else {
                if(a[i] < a[i+1])
                    return false;
            }
            i--;
            test = test >> 1;
        }
        return true;
    }

    // swap the characters at indices i and j
    private static void swap(char[] a, int i, int j) {
        char c = a[i];
        a[i] = a[j];
        a[j] = c;
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