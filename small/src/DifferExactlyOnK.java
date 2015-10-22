import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of positive, unique, increasingly sorted numbers A, e.g. A = [1, 2, 3, 5, 6, 8, 9, 11, 12, 13].
 * Given a positive value K, e.g. K = 3. Output all pairs in A that differ exactly by K.
 * e.g. 2, 5
 * 3, 6
 * 5, 8
 * 6, 9
 * 8, 11
 * 9, 12
 * what is the runtime for your code?
 * Created by valerii.ryzhuk on 10/22/2015.
 */
public class DifferExactlyOnK {
    /**
     * Returns only the first element from array that has pair that = element + k
     * @param sortedMas
     * @param k
     * @return
     */
    public List<Long> calculate(long[] sortedMas, int k) {
        int prev = 0;
        int cur = 1;
        List<Long> result = new LinkedList<>();
        while(cur < sortedMas.length) {
            if(sortedMas[prev] + k == sortedMas[cur]) {
                result.add(sortedMas[prev]);
                ++cur;
                ++prev;
            } else if(sortedMas[prev] + k < sortedMas[cur]){
                ++prev;
            } else {
                ++cur;
            }
        }
        return result;
    }
}
