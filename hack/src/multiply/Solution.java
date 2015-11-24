package multiply;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class Solution {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static long MODULO = 1000_000_007;
    static BigInteger MODULO_BIG = BigInteger.valueOf(MODULO);

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            solve();
            in.close();
            out.close();

        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }

    private static void solve() throws Exception {
        int[] lowestPrimes = lowestPrimes(1000_000);

        int n = nextInt();
        int t = nextInt();
        long sum = 1;
        Multiset allValues  = new Multiset();
        sum = getSumAndUpdateAllValues(lowestPrimes, n, sum, allValues);

        for(int i = 0; i < t; i++) {
            n = nextInt();
            sum = getSumAndUpdateAllValues(lowestPrimes, n, sum, allValues);
            out.println(sum);
        }
    }

    private static long getSumAndUpdateAllValues(int[] lowestPrimes, int n, long sum, Solution.Multiset allValues) {
        if(n > 1) {
            while(n > 1) {
                int prime = lowestPrimes[n];
                int step = 1;
                n = n / prime;
                while(n % prime == 0) {
                    ++step;
                    n = n / prime;
                }
                sum = (sum * calcSigma(prime, step, allValues.getAmount(prime))) % MODULO;
                allValues.add(prime, step);
            }
        }
        return sum;
    }

    private static long calcSigma(int prime, int additional, int prevAmount) {
        return (((pow(prime, prevAmount + additional + 1) + MODULO - 1) % MODULO) *
                inverse((pow(prime, prevAmount + 1) + MODULO - 1) % MODULO)) % MODULO;
    }

    private static long inverse(long value) {
        return pow(value, MODULO - 2);
    }

    private static long pow(long number, long degree) {
        long result = 1;
        while (degree > 0) {
            if ((degree & 1) == 1) {
                result = (result * number) % MODULO;
            }
            number = (number * number) % MODULO;
            degree >>= 1;
        }
        return (int) result;
    }

    static class Multiset implements Iterable<Node>{
        private HashMap<Integer, Node> valueToAmountMap = new HashMap<>();

        public void add(int value, int amount) {
            Node existed = valueToAmountMap.get(value);
            if(existed == null) {
                valueToAmountMap.put(value, new Node(value, amount));
            } else {
                existed.amount+= amount;
            }
        }

        public int getAmount(int value) {
            Node existed = valueToAmountMap.get(value);
            if(existed == null) {
                return 0;
            } else {
                return existed.amount;
            }
        }

        @Override
        public Iterator<Node> iterator() {
            return valueToAmountMap.values().iterator();
        }
    }

    static class Node {
        int value;
        int amount = 1;
        public Node(int value, int amount) {
            this.amount = amount;
            this.value = value;
        }

    }

    private static int[] lowestPrimes(int n) {
        int[] result = new int[n+1];
        int[] primes = new int[n];
        int primeSize = 0;
        for(int i = 2; i <=n; i++) {
            if(result[i] == 0) {
                result[i] = i;
                primes[primeSize++] = i;
            }
            for(int j = 0; j < primeSize && primes[j] <= result[i] && i*primes[j] <=n; j++) {
                result[i*primes[j]] = primes[j];
            }
        }
        return result;
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