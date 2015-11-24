/**
 * Given an array of integers. Modify the array by moving all the zeros to the end (right side). The order of the other elements doesn't matter.
 * <p>
 * Created by valerii.ryzhuk on 10/29/2015.
 */
public class ZeroesGoRight {
    public int[] solve(int[] mas){
        if(mas == null || mas.length==0) {
            return mas;
        }
        int lastNotZeroPosition = mas.length - 1;
        for(int i = mas.length -1; i >=0; i--) {
            if(mas[i] == 0 && i != lastNotZeroPosition) {
                mas[i] = mas[lastNotZeroPosition];
                mas[lastNotZeroPosition--] = 0;
            }
        }
        return mas;
    }
}
