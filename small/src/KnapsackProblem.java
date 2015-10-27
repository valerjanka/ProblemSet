import java.util.List;

/**
 * Created by valerii.ryzhuk on 10/22/2015.
 */
public class KnapsackProblem {

    /**
     * The most common problem being solved is the 0-1 knapsack problem,
     * which restricts the number xi of copies of each kind of item to zero or one.
     * Given a set of n items numbered from 1 up to n, each with a weight wi and a value vi,
     * along with a maximum weight capacity W, maximize Sum vi*xi, For Sum wi*xi <= wmax
     * All values are positive.
     *
     * @param weights of size n
     * @param values of size n
     * @param wmax
     * @return max values with max items weight <= wmax
     */
    public int zoreOneProblem(int[] weights, int[] values, int wmax) {
        int[][] mas = new int[weights.length+1][wmax+1];
        for(int w = 0; w <= wmax; w++) {
            mas[0][w] = 0;
        }
        for(int i = 1; i <= weights.length; i++) {
            for(int w = 0; w <= wmax; w++) {
                if(weights[i - 1] > w) {
                    mas[i][w] = mas[i-1][w];
                } else {
                    mas[i][w] = Math.max(mas[i-1][w], mas[i-1][w-weights[i-1]] + values[i-1]);
                }
            }
        }
        return mas[weights.length][wmax];
    }

    /**
     * On a given array with N numbers, find subset of size K (exactly K elements) that equal to SUM.
     * @param values
     * @param sum
     * @param k
     * @return
     */
    public List<Integer> findSubsetWithSum(int[] values, int sum, int k) {
        return null;
    }
}
