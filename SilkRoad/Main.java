import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// N+1個 都市 (0 .. N)
// Di 都市の間の距離 (1..N)
// M日
// Cj 天候の悪さ (1<=j<=M)
// 疲労度 Di × Cj

public class Main {
    static final int MAXN = 1000;    
    static final int MAXM = 1000;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,m; 
    static int d[] = new int[MAXN+1]; 
    static int c[] = new int[MAXM+1]; 
    static int dp[][] = new int[MAXM+1][MAXN+1];  // 

    public static void main(String[] args) {
        // 初期化
        for(int[] dpval : dp) {
            Arrays.fill(dpval,INF);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i=1;i<=n;i++) {
            d[i] = sc.nextInt();
        }

        for(int j = 1; j <= m ; j++) {
            c[j] = sc.nextInt();
        }
        System.out.println(solve());
    }

    static int solve() {
        int answer=INF;
        // 0日目
        dp[0][0] = 0;
        // debugprint();
        for(int j=1;j<=m;j++) {
            dp[j][0] = 0;
            for(int i=1;i<=n;i++){
                dp[j][i] = Math.min(
                    dp[j-1][i-1] + d[i] * c[j], 
                    dp[j-1][i]);
            }
        }
        // debugprint();
        for(int j=1;j<=m;j++) {
            answer = Math.min(dp[j][n],answer);
        }

        return answer;
    }
    static void debugprint() {
        for(int j=0;j<=m;j++) {
            System.out.print(j + ":");
            for(int i=0;i<=n;i++) {
                System.out.print(dp[j][i] + ",");
            }
            System.out.println("");
        }
        System.out.println("---");
    }
}
