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

public class Solution {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static long MODULO;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));

            out.println(solve());
            in.close();
            out.close();

        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }

    private static long solve() throws Exception {
        int n = nextInt();
        MODULO = nextInt();
        char[] pattern = next().toCharArray();
        long[][] f = new long[n][n];
        f[1][0] = (pattern[0] == 'G')? 1 : 0;
        f[1][1] = (pattern[0] == 'G')? 0 : 1;
        for(int k = 2; k < n; k++) {
            if(pattern[k - 1] == 'G') {
                f[k][k] = 0;
                for (int x = k - 1; x >=0; x--) {
                    f[k][x] = (f[k][x+1] + f[k-1][x]) % MODULO;
                }
            } else {
                f[k][0] = 0;
                for(int x = 1; x <= k; x++) {
                    f[k][x] = (f[k][x-1] + f[k-1][x-1]) % MODULO;
                }
            }
        }
        long sum = 0;
        for(int x = 0; x < n; x++) {
            sum = (sum + f[n-1][x]) % MODULO;
        }
        return sum;
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