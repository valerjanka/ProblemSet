/**
 * Given n, return 1 ^ 2 ^ 3 ^ ... ^ n
 * Where ^ is binary xor.
 * Note: n is a 64-bit number, and 1<<63 is a valid n for this problem.
 * <p>
 * Examples:
 * <p>
 * <p>
 * >>> reduce(lambda a,b:a^b, [1,2,3])
 * 0
 * >>> reduce(lambda a,b:a^b, [1,2,3,4])
 * 4
 * >>> reduce(lambda a,b:a^b, [1,2,3,4,5,6,7])
 * 0
 * >>> reduce(lambda a,b:a^b, [1,2,3,4,5,6,7,8,9])
 * 1
 * <p>
 * Created by valerii.ryzhuk on 10/20/2015.
 */
public class XorForN {
    public long reduce(long n) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 3;
        if(n == 3)
            return 0;
        return (n % 4 == 0)? n :
                (n % 4 == 1)? 1 :
                        ( n % 4 == 2)? n + 1 :
                                0;
    }
}
