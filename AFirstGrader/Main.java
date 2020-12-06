import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 100;
    static final int MAXLEN = 20;
    static final int INF = Integer.MAX_VALUE/2;
    static int n; 
    static int x[] = new int[MAXN]; 
    static long dp[][] = new long[MAXN][MAXLEN+1];  // 

    public static void main(String[] args) {
        // 初期化
        for(long[] dpval : dp) {
            Arrays.fill(dpval,0);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();

        for(int i = 0; i < n ; i++) {
            x[i] = sc.nextInt();
        }
        System.out.println(solve());
    }

    static long solve() {
        int max = 0;
        // Y
        dp[0][x[0]] = 1;
        for(int i = 1;i < n-1;i++){
            // X
            for(int j = 0; j<= MAXLEN; j++) {
                if(j - x[i] < 0) {
                    dp[i][j] = dp[i-1][j+x[i]];
                } else if( j + x[i] > MAXLEN) {
                    dp[i][j] = dp[i-1][j - x[i]];
                } else {
                    dp[i][j] = dp[i-1][j - x[i]] + dp[i-1][j+x[i]];
                }
            }
        }
        // debugprint();

        return dp[n-2][x[n-1]];
    }
    static void debugprint() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<=MAXLEN;j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println("");
        }
    }
}
