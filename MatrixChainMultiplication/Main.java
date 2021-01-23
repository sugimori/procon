import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// N+1個 都市 (0 .. N)
// Di 都市の間の距離 (1..N)
// M日
// Cj 天候の悪さ (1<=j<=M)
// 疲労度 Di × Cj

public class Main {
    static final int MAXN = 100;    
    static final int MAXR = 100;
    static final int MAXC = 100;
    static final int INF = Integer.MAX_VALUE/2;
    static int n; 
    static int r[] = new int[MAXR+1]; 
    static int c[] = new int[MAXC+1]; 
    static int dp[][] = new int[MAXR+1][MAXC+1];  // 

    public static void main(String[] args) {
        // 初期化
        for(int[] dpval : dp) {
            Arrays.fill(dpval,0);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();

        for(int i=1;i<=n;i++) {
            r[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }

        System.out.println(solve());
    }

    static int solve() {
        // 初期値
        for(int i=1;i<n;i++) {
            dp[i][i+1] = r[i] * r[i+1] * c[i+1];
        }
        // debugprint();
        for(int k=2;k<=n-1;k++) {
            for(int i=1;i+k<=n;i++){
                // dp[i][i+k] = Math.min(
                //                 dp[i][i+k-1] + r[i]*r[i+k]*c[i+k], 
                //                 dp[i+1][i+k] + r[i]*c[i]*c[i+k]
                //                 );
                int cost = INF;
                for(int j=i;j<i+k;j++) {
                    // int tmp = dp[i][j]+dp[j+1][i+k] + r[i]*c[j]*c[i+k];
                    cost = Math.min(cost,dp[i][j]+dp[j+1][i+k] + r[i]*c[j]*c[i+k]);
                    // System.out.println("cost:" + i+","+j +"=" + tmp);
                }
                dp[i][i+k] = cost;
                // debugprint();
            }

        }
        // debugprint();

        return dp[1][n];
    }
    static void debugprint() {
        for(int i=1;i<=n;i++) {
            System.out.printf("%2d:",i);
            for(int j=1;j<=n;j++) {
                System.out.printf("%7d,",dp[i][j]);
            }
            System.out.println("");
        }
        System.out.println("---");
    }
}
