package dsf.checkWord;

import java.util.*;

/**
 * @author 董少飞
 * @date 2018/11/30
 */
public class Cards {
    public int cardGame(int[] A, int n) {
        // write code here
        if(A == null || A.length == 0)
            return 0;
        return Math.max(f(A, 0, A.length-1), l(A, 0, A.length-1));
    }

    int f(int[] arr, int left, int right) {

        if(right == left)
            return arr[right];
        return Math.max( arr[left]+l(arr, left+1, right), arr[right]+l(arr, left, right-1));
    }

    int l(int[] arr, int left, int right) {

        if(right == left)
            return 0;
        return Math.min( f(arr, left+1, right), f(arr, left, right-1));
    }

    public static void main(String[] a) {

        int[] arr = {1,100,2};
        int i = new Cards().cardGame(arr, 3);
        System.out.println(i);
    }
}
