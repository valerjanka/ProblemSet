import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by valerii.ryzhuk on 10/20/2015.
 */
public class PatternStringTest {

    @Test
    public void testIsMatch() throws Exception {
        PatternString patternString = new PatternString();
        assertTrue(patternString.isMatch("aa","a*"));
        assertTrue(patternString.isMatch("aa","aa"));
        assertTrue(patternString.isMatch("a","b*a"));
        assertTrue(patternString.isMatch("a","a*a"));
        assertTrue(patternString.isMatch("aab","c*a*b*"));
    }
}