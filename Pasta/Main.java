import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 100;
    static final int MAXLEN = 3;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,k; 
    static int x[] = new int[MAXN+1]; 
    static long dp[][] = new long[MAXN+1][MAXLEN];  // 

    public static void main(String[] args) {
        // 初期化
        for(long[] dpval : dp) {
            Arrays.fill(dpval,0);
        }

        Arrays.fill(x,0);

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        k = sc.nextInt();

        for(int i = 0; i < k ; i++) {
            int day = sc.nextInt();
            x[day] = sc.nextInt();
        }
        System.out.println(solve());
    }

    static long solve() {
        int max = 0;
        // 1日目
        if(x[1] != 0) {
            dp[1][x[1]-1] = 1;
        } else {
            Arrays.fill(dp[1],1);
        }

        // 配る
        for(int i = 1;i < n;i++){
            for(int j = 0; j< MAXLEN; j++) {
                if(dp[i][j] != 0) {
                    if(i==1) {
                        dp[i+1][j] += dp[i][j];
                    } else {
                        dp[i+1][j] += dp[i-1][(j+1)%MAXLEN] + dp[i-1][(j+2)%MAXLEN];
                    }
                    dp[i+1][(j+1) % MAXLEN] += dp[i][j];
                    dp[i+1][(j+2) % MAXLEN] += dp[i][j];  
                }
            }
            for(int j=0;j<MAXLEN;j++) {
                dp[i+1][j] = dp[i+1][j] % 10000;
            }
            // debugprint();
            if(x[i+1] != 0 ) {
                dp[i+1][(x[i+1]-1+1) % MAXLEN] = 0;
                dp[i+1][(x[i+1]-1+2) % MAXLEN] = 0;
            }
            System.out.println("LINE:" + i);
            debugprint();
        }

        return (dp[n][0] + dp[n][1] + dp[n][2]) %10000;
    }
    static void debugprint() {
        for(int i=0;i<=n;i++) {
            System.out.print(i + ":");
            for(int j=0;j<MAXLEN;j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println("");
        }
        System.out.println("---");
    }
}
