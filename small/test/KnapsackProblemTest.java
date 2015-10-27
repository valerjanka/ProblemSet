import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/22/2015.
 */
public class KnapsackProblemTest {

    @Test
    public void testZoreOneProblem() throws Exception {
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        assertEquals(23,knapsackProblem.zoreOneProblem(new int[]{7, 5, 8, 3, 9, 1}, new int[]{7, 5, 8, 3, 9, 1}, 23));
    }
}