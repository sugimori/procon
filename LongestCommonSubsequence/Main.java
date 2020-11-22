import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// 	    0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15
// 1	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15
// 2	0	1	1	2	2	3	3	4	4	5	5	6	6	7	7	8
// 7	0	1	1	2	2	3	3	1	2	2	3	3	4	4	2	3
// 8	0	1	1	2	2	3	3	1	1	2	2	3	3	4	2	2
// 12	0	1	1	2	2	3	3	1	1	2	2	3	1	2	2	2
// 50																

public class Main {
    static final int MAXQ = 150;
    static final int MAXLEN = 1000;
    static final int INF = Integer.MAX_VALUE/2;
    static int q; 
    static String x,y; 
    static int dp[][] = new int[MAXLEN+1][MAXLEN+1];  // 

    public static void main(String[] args) {
        for(int[] dpval : dp) {
            Arrays.fill(dpval,0);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        q = sc.nextInt();

        for(int i = 0; i < q ; i++) {
            x = sc.next();
            y = sc.next();
            System.out.println(solve());
        }
    }

    static int solve() {
        int max = 0;
        // Y
        for(int i = 1;i <= y.length();i++){
            // X
            for(int j = 1; j<= x.length(); j++) {
                if(x.charAt(j-1) == y.charAt(i-1)) {
                    int count = 0;
                    for(int k=1;k<j;k++) {
                        count = Math.max(count,dp[i-1][k]);
                    }
                    dp[i][j] = Math.max(count+1,dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        debugprint();

        return max;
    }
    static void debugprint() {
        for(int i=1;i<=y.length();i++) {
            for(int j=1;j<=x.length();j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println("");
        }
    }
}
