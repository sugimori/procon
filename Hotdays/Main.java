import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 200;
    static final int MAXD = 200;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,d; 
    static int a[] = new int[MAXN+1]; 
    static int b[] = new int[MAXN+1]; 
    static int c[] = new int[MAXN+1]; 
    static int t[] = new int[MAXD+1];
    static int dp[][] = new int[MAXD+1][MAXN+1];  // 

    public static void main(String[] args) {
        // 初期化
        for(int[] dpval : dp) {
            Arrays.fill(dpval,-1);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        d = sc.nextInt();
        n = sc.nextInt();

        for(int i=1;i<=d;i++) {
            t[i] = sc.nextInt();
        }

        for(int j = 1; j <= n ; j++) {
            a[j] = sc.nextInt();
            b[j] = sc.nextInt();
            c[j] = sc.nextInt();
        }
        System.out.println(solve());
    }

    static int solve() {
        int answer=0;
        // 1日目
        for(int j=1;j<=n;j++) {
            if((a[j]<=t[1])&&(t[1]<=b[j])) {
                dp[1][j] = 0;
            }
        }
        // debugprint();
        // 2日目以降
        for(int i=2;i<=d;i++) {
            for(int j=1;j<=n;j++){
                if((a[j]<=t[i])&&(t[i]<=b[j])) {
                    for(int k=1;k<=n;k++) {
                        if(dp[i-1][k] != -1) {
                            dp[i][j] = Math.max(dp[i][j],dp[i-1][k] + Math.abs(c[j]-c[k]));
                        }
                    }
                }
            }
        }
        debugprint();
        // answer
        for(int j=1;j<=n;j++) {
            answer = Math.max(answer,dp[d][j]);
        }
        return answer;
    }
    static void debugprint() {
        for(int i=1;i<=d;i++) {
            System.out.print(i + ":");
            for(int j=1;j<=n;j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println("");
        }
        System.out.println("---");
        // System.out.println(Arrays.toString(a));
        // System.out.println(Arrays.toString(b));
        // System.out.println(Arrays.toString(c));
    }
}
