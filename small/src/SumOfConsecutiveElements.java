/**
 * Given an array of positive integers (excluding zero) and a target number. Detect whether there is a set of consecutive elements in the array that add up to the target.
 * <p>
 * Example: a = {1, 3, 5, 7, 9}
 * target = 8
 * <p>
 * output = true ({3, 5})
 * <p>
 * or target = 15
 * output = true : {3, 5, 8}
 * <p>
 * but if target = 6, output would be false. since 1 and 5 are not next to each other.
 * <p>
 * Created by valerii.ryzhuk on 10/29/2015.
 */
public class SumOfConsecutiveElements {
    public boolean isContainsSum(int[] elements, int sum) {
        if(elements == null || elements.length == 0) {
            return false;
        }
        int curSum = 0;
        int firstElementPosition = 0;
        for(int curPos = 0; curPos < elements.length; curPos++) {
            curSum = curSum + elements[curPos];
            if(curSum == sum) {
                return true;
            } else if (curSum > sum) {
                while(curSum > sum && firstElementPosition <= curPos) {
                    curSum -= elements[firstElementPosition++];
                }
                if(firstElementPosition <= curPos && curSum == sum) {
                    return true;
                }
            }
        }
        return false;
    }
}
