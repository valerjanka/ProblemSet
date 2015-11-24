import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/29/2015.
 */
public class SumOfConsecutiveElementsTest {

    @Test
    public void testIsContainsSum() throws Exception {
        SumOfConsecutiveElements task = new SumOfConsecutiveElements();
        assertTrue(task.isContainsSum(new int[]{1, 2, 3, 4, 5}, 15));
        assertTrue(task.isContainsSum(new int[]{1, 2, 3, 4, 5}, 7));
        assertTrue(task.isContainsSum(new int[]{1, 2, 3, 4, 5}, 1));
        assertTrue(task.isContainsSum(new int[]{1, 2, 3, 4, 5}, 2));
        assertTrue(task.isContainsSum(new int[]{1, 2, 3, 4, 5}, 5));
        assertFalse(task.isContainsSum(new int[]{1, 2, 3, 4, 5}, 16));
        assertFalse(task.isContainsSum(new int[]{1, 3, 5, 7, 9}, 6));
    }
}