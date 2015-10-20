import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/20/2015.
 */
public class XorForNTest {

    @Test
    public void testReduce() throws Exception {
        XorForN xor = new XorForN();
        assertEquals(1, xor.reduce(1));
        assertEquals(0, xor.reduce(3));
        assertEquals(4, xor.reduce(4));
        assertEquals(0, xor.reduce(7));
        assertEquals(1, xor.reduce(9));
        assertEquals(40000000000000000L, xor.reduce(40000000000000000L));
        assertEquals(1, xor.reduce(40000000000000001L));
        assertEquals(40000000000000003L, xor.reduce(40000000000000002L));
        assertEquals(0, xor.reduce(40000000000000003L));
    }
}