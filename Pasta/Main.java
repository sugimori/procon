import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 100;
    static final int MAXLEN = 3;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,k; 
    static int x[] = new int[MAXN+1]; 
    static long dp[][][] = new long[MAXN+1][MAXLEN][MAXLEN];  // 

    public static void main(String[] args) {
        // 初期化
        for(long[][] dp1 : dp) {
            for(long[] dp2 : dp1) {
                Arrays.fill(dp2,0);
            }
        }

        Arrays.fill(x,-1);

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        k = sc.nextInt();

        for(int i = 0; i < k ; i++) {
            int day = sc.nextInt();
            x[day] = sc.nextInt() -1 ;
        }
        System.out.println(solve());
    }

    static long solve() {
        int answer = 0;
        // f(n-1,A,B) = Σf(n,B,C)
        for(int a=0;a<3;a++) {
            for(int b=0;b<3;b++) {
                int tmp = 1;
                if((x[n-1] != -1)&&(x[n-1]!= a)) tmp = 0;
                if((x[n] != -1)&&(x[n] != b)) tmp = 0;
                dp[n][a][b] = tmp;
            }
        }
        for(int i=n-1;i>=2;i--) {
            for(int a=0;a<3;a++) {
                for(int b=0;b<3;b++) {
                    dp[i][a][b] += dp[i+1][b][0];
                    dp[i][a][b] += dp[i+1][b][1];
                    dp[i][a][b] += dp[i+1][b][2];
                    if(a==b) dp[i][a][b] -= dp[i+1][b][b];
                    if((x[i] != -1)&&(b != x[i])) dp[i][a][b] = 0;
                    dp[i][a][b] %= 10000;
                }    
            }
        }
        for(int a=0;a<3;a++) {
            if((x[1] == -1) ||(a == x[1])) {
                answer += dp[2][a][0];
                answer += dp[2][a][1];
                answer += dp[2][a][2];
            }
        }

        // debugprint();
        return answer % 10000;
    }
    static void debugprint() {
        for(int i=0;i<=n;i++) {
            System.out.print(i + ":");
            for(int a=0;a<MAXLEN;a++) {
                for(int b=0;b<MAXLEN;b++) {
                    System.out.print("(" + a+","+b+")=" + dp[i][a][b] + ",");
                }
            }
            System.out.println("");
        }
        System.out.println("---");
    }
}
