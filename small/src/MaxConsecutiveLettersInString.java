/**
 * * Given a random string S and another string T with unique elements, find the minimum consecutive sub-string of S such that it contains all the elements in T.
 * example:
 * S='adobecodebanc'
 * T='abc'
 * answer='banc'
 * <p>
 * Created by valerii.ryzhuk on 11/5/2015.
 */
public class MaxConsecutiveLettersInString {
    public String solve(String s, String characters) {
        boolean[] existed = new boolean['z' - 'a' + 1];
        for (char c : characters.toCharArray()) {
            existed[c - 'a'] = true;
        }

        int maxSize = 0;
        int maxStartPosition = -1;
        int currentSize = 0;
        int i = 0;
        char[] string = s.toCharArray();
        for (char c : string) {
            if (existed[c - 'a']) {
                ++currentSize;
            } else {
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                    maxStartPosition = i - maxSize;
                }
                currentSize = 0;
            }
            i++;
        }
        if (currentSize > maxSize) {
            maxSize = currentSize;
            maxStartPosition = i - maxSize;
        }
        if (maxStartPosition >= 0) {
            return new String(string, maxStartPosition, maxSize);
        }
        return null;
    }
}
