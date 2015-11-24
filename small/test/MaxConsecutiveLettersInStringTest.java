import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Given a random string S and another string T with unique elements, find the minimum consecutive sub-string of S such that it contains all the elements in T.
 * example:
 * S='adobecodebanc'
 * T='abc'
 * answer='banc'
 * <p>
 * Created by valerii.ryzhuk on 11/5/2015.
 */
public class MaxConsecutiveLettersInStringTest {

    @Test
    public void testSolve() throws Exception {
        MaxConsecutiveLettersInString task = new MaxConsecutiveLettersInString();
        assertEquals("banc", task.solve("adobecodebanc", "abc"));
    }
}