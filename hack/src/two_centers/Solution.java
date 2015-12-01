package two_centers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class Solution {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;

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

    private static int solve() throws Exception {
        int n = nextInt();
        UnDiGraph graph = new UnDiGraphImpl(n);
        while(n-- > 1) {
            int v = nextInt() - 1;
            int w = nextInt() - 1;
            graph.addEdge(v, w);
        }
        TwoCentersOfTree twoCentersOfTree = new TwoCentersOfTree(graph);
        return twoCentersOfTree.getOptimumRadius();
    }

    static class TwoCentersOfTree {
        private int firstCenter;
        private int secondCenter;
        private int optimumRadius;


        public TwoCentersOfTree(UnDiGraph graph) {
            if(graph.vertices() == 1) {
                firstCenter = 0;
                secondCenter = 0;
                return;
            }
            ArrayList<Integer> diameter = new ArrayList<>(DiameterOfTree.getDiameter(graph));
            int maxRadius = (diameter.size() - 1) / 2;
            int[] handsDist = calculateHands(graph, diameter);
            int[] leftHands = Arrays.copyOfRange(handsDist, 0, maxRadius + 1);
            int[] rightHands = Arrays.copyOfRange(handsDist, handsDist.length - maxRadius - 1, handsDist.length);
            reverse(rightHands);
            int radiusForLeftPart = calculateRadius(leftHands);
            int radiusForRightPart = calculateRadius(rightHands);
            optimumRadius = Math.max(radiusForLeftPart, radiusForRightPart);
            firstCenter = optimumRadius;
            secondCenter = diameter.size() - optimumRadius - 1;
        }

        private int[] calculateHands(UnDiGraph graph, ArrayList<Integer> diameter) {
            int[] handsPerGraph = new int[graph.vertices()];
            removeAdjFromDiameter(graph, diameter);
            BreadthFirstPathsForNotConnectedComponents bfs = new BreadthFirstPathsForNotConnectedComponents(graph, diameter);
            for (int v = 0; v < graph.vertices(); v++) {
                int onDiameter = bfs.getParentStart()[v];
                if (onDiameter != v) {
                    handsPerGraph[onDiameter] = Math.max(handsPerGraph[onDiameter], bfs.getDistTo()[v]);
                }
            }
            int[] hands = new int[diameter.size()];
            for(int v = 0; v < diameter.size(); v++) {
                hands[v] = handsPerGraph[diameter.get(v)];
            }
            return hands;
        }

        private void removeAdjFromDiameter(UnDiGraph graph, ArrayList<Integer> diameter) {
            for (int i = 1; i < diameter.size(); i++) {
                graph.removeAdj(diameter.get(i - 1), diameter.get(i));
            }
        }

        private void reverse(int[] mas) {
            int first = 0;
            int last = mas.length - 1;
            while (first < last) {
                int x = mas[first];
                mas[first] = mas[last];
                mas[last] = x;
                ++first;
                --last;
            }
        }

        private int calculateRadius(int[] hands) {
            int resultRadius = hands.length - 1;
            int maxHand = hands[hands.length - 1];
            int v = hands.length - 1;
            while (maxHand < resultRadius && v > 0) {
                v--;
                maxHand = Math.max(maxHand + 1, hands[v]);
                resultRadius = Math.max(maxHand, resultRadius - 1);
            }
            return resultRadius;
        }

        public int getFirstCenter() {
            return firstCenter;
        }

        public int getSecondCenter() {
            return secondCenter;
        }

        public int getOptimumRadius() {
            return optimumRadius;
        }
    }

    static class DiameterOfTree {
        public static List<Integer> getDiameter(UnDiGraph graph) {
            BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graph, 0);
            int onDiameter = findFarthest(breadthFirstPaths);
            breadthFirstPaths = new BreadthFirstPaths(graph, onDiameter);
            int anotherOnDiameter = findFarthest(breadthFirstPaths);
            List<Integer> diameter = new LinkedList<>();
            int currentV = anotherOnDiameter;
            while (currentV != onDiameter) {
                diameter.add(currentV);
                currentV = breadthFirstPaths.getParent()[currentV];
            }
            diameter.add(onDiameter);
            return diameter;
        }

        private static int findFarthest(BreadthFirstPaths breadthFirstPaths) {
            int[] len = breadthFirstPaths.getDistTo();
            int maxLenIndex = 0;
            for (int v = 1; v < len.length; v++) {
                if (len[v] > len[maxLenIndex]) {
                    maxLenIndex = v;
                }
            }
            return maxLenIndex;
        }

    }

    static class BreadthFirstPaths {
        private boolean[] marked;
        private int[] distTo;
        private int[] parent;
        private UnDiGraph graph;
        private int start;

        public BreadthFirstPaths(UnDiGraph graph, int start) {
            this.graph = graph;
            this.start = start;
            distTo = new int[graph.vertices()];
            parent = new int[graph.vertices()];
            marked = new boolean[graph.vertices()];

            bfs();
        }

        private void bfs() {
            marked[start] = true;
            parent[start] = start;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            while(!queue.isEmpty()) {
                int v = queue.poll();
                for(int w : graph.adj(v)) {
                    if(!marked[w]) {
                        marked[w] = true;
                        distTo[w] = distTo[v] + 1;
                        parent[w] = v;
                        queue.offer(w);
                    }
                }
            }
        }

        public boolean[] getMarked() {
            return marked;
        }

        public int[] getDistTo() {
            return distTo;
        }

        public int[] getParent() {
            return parent;
        }
    }

    static class BreadthFirstPathsForNotConnectedComponents {
        private boolean[] marked;
        private int[] distTo;
        private int[] parent;
        private int[] parentStart;
        private UnDiGraph graph;
        private List<Integer> componentStarts;

        public BreadthFirstPathsForNotConnectedComponents(UnDiGraph graph, List<Integer> componentStarts) {
            this.graph = graph;
            this.componentStarts = componentStarts;
            distTo = new int[graph.vertices()];
            parent = new int[graph.vertices()];
            marked = new boolean[graph.vertices()];
            parentStart = new int[graph.vertices()];
            bfs();
        }

        private void bfs() {
            for(int start : componentStarts) {
                marked[start] = true;
                parent[start] = start;
                parentStart[start] = start;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(start);

                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    for (int w : graph.adj(v)) {
                        if (!marked[w]) {
                            marked[w] = true;
                            distTo[w] = distTo[v] + 1;
                            parent[w] = v;
                            parentStart[w] = start;
                            queue.offer(w);
                        }
                    }
                }
            }
        }

        public boolean[] getMarked() {
            return marked;
        }

        public int[] getDistTo() {
            return distTo;
        }

        public int[] getParent() {
            return parent;
        }

        public int[] getParentStart() {
            return parentStart;
        }
    }

    interface UnDiGraph {
        int degree(int v);
        List<Integer> adj(int v);
        boolean removeAdj(int v, int w);
        void addEdge(int v, int w);
        int vertices();
        int edges();
    }

    static class UnDiGraphImpl implements UnDiGraph {
        private int edges;

        public UnDiGraphImpl(int vertices) {
            verticesToAdjacencyList = (LinkedList<Integer>[]) new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                verticesToAdjacencyList[i] = new LinkedList<>();
            }
        }

        protected LinkedList<Integer>[] verticesToAdjacencyList;

        @Override
        public List<Integer> adj(int v) {
            return verticesToAdjacencyList[v];
        }

        @Override
        public int vertices() {
            return verticesToAdjacencyList.length;
        }

        @Override
        public void addEdge(int v, int w) {
            verticesToAdjacencyList[v].add(w);
            verticesToAdjacencyList[w].add(v);
            edges++;
        }

        @Override
        public boolean removeAdj(int v, int w) {
            verticesToAdjacencyList[v].removeFirstOccurrence(w);
            return verticesToAdjacencyList[w].removeFirstOccurrence(v);
        }

        @Override
        public int edges() {
            return edges;
        }

        @Override
        public int degree(int v) {
            return verticesToAdjacencyList[v].size();
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