import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/22/2015.
 */
public class DifferExactlyOnKTest {

    @Test
    public void testCalculate() throws Exception {
        DifferExactlyOnK problem = new DifferExactlyOnK();
        assertArrayEquals(new Long[]{2L, 3L, 5L, 6L, 8L, 9L}, problem.calculate(new long[]{1, 2, 3, 5, 6, 8, 9, 11, 12, 13}, 3).toArray(new Long[0]));
    }
}