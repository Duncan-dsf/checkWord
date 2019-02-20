package dsf.checkWord;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(chkParenthesis("()()(((())))", 12));
    }

    public static boolean chkParenthesis(String A, int n) {
        // write code here
        if(A == null || n%2 != 0)
            return false;
        int count = 0;
        for(int i=0; i<n; i++) {

            if(A.charAt(i) == ')')
                return false;
            else if(A.charAt(i) == '(')
                count++;
        }
        return count == 0;
    }

    public static void run1() {

        // 牛客 放苹果
        Scanner scanner = new Scanner(System.in);
        String line;
        while((line = scanner.nextLine()) != null) {

            String[] data = line.split(" ");
            int m = Integer.valueOf(data[0]);
            int n = Integer.valueOf(data[1]);

            if(n == 1)
                System.out.println(1);
            if(n == 0)
                System.out.println(0);
            if(m == 1)
                System.out.println(1);

            int[][] arr = new int[m+1][n+1];

            for(int i=1; i<=m; i++) {
                arr[i][1] = 1;
            }
            for(int j=1; j<=n; j++) {
                arr[1][j] = 1;

                for(int i=2; i<=m; i++) {

                    arr[i][j] = arr[i-1][j] + arr[1][j-1];
                }
            }
            System.out.println(arr[m][n]);
        }
    }

}