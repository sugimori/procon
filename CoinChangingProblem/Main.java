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
    static final int MAXN = 50000;
    static final int MAXM = 20;
    static final int INF = Integer.MAX_VALUE/2;
    static int n; // 1<=N<=100
    static int m; // 1<=W<=10000
    static int c[] = new int[MAXM+1];
    static int dp[][] = new int[MAXM+1][MAXN+1];  // 

    public static void main(String[] args) {
        for(int[] dpval : dp) {
            Arrays.fill(dpval,INF);
        }
        dp[0][0] = 0;

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        m = sc.nextInt();

        c[0] = 0;
        for(int i = 1; i <=m ; i++) {
            c[i] = sc.nextInt();
        }
        System.out.println(solve());
    }

    static int solve() {

        for(int i = 1;i <= m;i++){
            for(int j = 0; j<=n; j++) {
                if(j < c[i]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(
                        dp[i-1][j], 
                        dp[i][j-c[i]]+1);
                }
            }
        }

        return dp[m][n];
    }
}
