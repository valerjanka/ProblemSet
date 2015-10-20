import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Write a function to check if a string matches a regex patter. Note that you only have to deal with patterns containing "*". Also, note that the pattern can't start with "*".
 * <p>
 * Some examples:
 * isMatch(“aa”,”a”) ? false
 * isMatch(“aa”,”aa”) ? true
 * isMatch(“aaa”,”aa”) ? false
 * isMatch(“aa”, “a*”) ? true
 * isMatch(“aa”, “*”) ? true
 * isMatch(“ab”, “*”) ? true
 * isMatch(“ab”, “*”) ? true
 * isMatch(“a”, “b*a”) ? true
 * isMatch(“a”, “a*a”) ? true
 * isMatch(“aab”, “c*a*b”) ? true
 * <p>
 * - diwash.timilsina 3 months ago in United States | Report Duplicate | Flag
 * <p>
 * Created by valerii.ryzhuk on 10/20/2015.
 */
public class PatternString {
    public boolean isMatch(String source, String pattern) {
        if (source == pattern)
            return true;
        if (source == null || pattern == null)
            return false;
        List<Letter> sourceLetters = groupString(source);
        List<Letter> patternLetters = groupString(pattern);
        Iterator<Letter> sourceIterator = sourceLetters.iterator();
        Iterator<Letter> patternIterator = patternLetters.iterator();
        Letter sourceLetter;
        Letter patternLetter;
        while (sourceIterator.hasNext() && patternIterator.hasNext()) {
            sourceLetter = sourceIterator.next();
            patternLetter = patternIterator.next();
            while (patternLetter.minAmount == 0 && sourceLetter.letter != patternLetter.letter && patternIterator.hasNext()) {
                patternLetter = patternIterator.next();
            }
            if (sourceLetter.letter == patternLetter.letter) {
                if (patternLetter.many) {
                    if (patternLetter.minAmount > sourceLetter.minAmount) {
                        return false;
                    }
                } else {
                    if (patternLetter.minAmount != sourceLetter.minAmount) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        if (sourceIterator.hasNext()) {
            return false;
        }
        while (patternIterator.hasNext()) {
            patternLetter = patternIterator.next();
            if (patternLetter.minAmount > 0) {
                return false;
            }
        }
        return true;
    }

    private List<Letter> groupString(String string) {
        List<Letter> result = new ArrayList<>();
        char prevChar = 0;
        Letter currentLetter = null;
        for (char c : string.toCharArray()) {
            if (prevChar == c) {
                ++currentLetter.minAmount;
            } else if (c == '*') {
                currentLetter.many = true;
                --currentLetter.minAmount;
            } else {
                currentLetter = new Letter(c);
                result.add(currentLetter);
                prevChar = c;
            }
        }
        return result;
    }

    static class Letter {
        private boolean many = false;
        private int minAmount = 1;
        private char letter;

        public Letter(char letter) {
            this.letter = letter;
        }

    }
}
