import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/29/2015.
 */
public class ZeroesGoRightTest {

    @Test
    public void testSolve() throws Exception {
        ZeroesGoRight task = new ZeroesGoRight();
        assertArrayEquals(new int[]{0}, task.solve(new int[]{0}));
        assertArrayEquals(new int[]{1}, task.solve(new int[]{1}));
        assertArrayEquals(new int[]{1, 0}, task.solve(new int[]{0, 1}));
        assertArrayEquals(new int[]{1, 1, 0}, task.solve(new int[]{1, 0, 1}));
        assertArrayEquals(new int[]{1, 1, 0, 0}, task.solve(new int[]{1, 0, 1, 0}));
        assertArrayEquals(new int[]{1, 1, 1, 0, 0}, task.solve(new int[]{1, 1, 0, 0, 1}));
    }
}