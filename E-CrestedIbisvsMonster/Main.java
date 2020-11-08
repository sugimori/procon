import java.util.Scanner;

public class Main {
    static final int MAXN = 1000;
    static final int MAXH = 10000;
    static final int INF = 100000000;
    static int n; // 1<=N<=1000
    static int maxhp; // 1<=W<=10000
    static int a[] = new int[MAXN+1]; 
    static int b[] = new int[MAXN+1];
    static int maxattack = 0;
    static int dp[][] = new int[MAXN+1][];  // 

    public static void main(String[] args) {
        for(int i=0;i<=MAXN;i++) {
            dp[i] = new int[MAXH*2+1];
            for(int j=0;j<=MAXH * 2 ;j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        maxhp = sc.nextInt();
        n = sc.nextInt();
        
        for(int i=0;i<n;i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            maxattack = Math.max(maxattack, a[i]);
        }
        //System.out.println("maxattack:" + maxattack);

        System.out.println(solve());
    }

    static int solve() {
        for(int i = 0;i < n;i++){
            for(int j = 0; j<=maxhp + maxattack; j++) {
                if( j >= a[i]) {
                    dp[i+1][j] = Math.min(
                        dp[i][j], 
                        dp[i+1][j-a[i]]+b[i]);
                } else {
                    dp[i+1][j] = dp[i][j];
                }
                // System.out.println("(" + (i+1) + "," + j + ") =" + dp[i+1][j] );
            }
        }
        printdp();

        int min = INF;
        for(int j = maxhp; j<=maxhp + maxattack;j++) {
            // System.out.println("j=" + j + ":" + dp[n][j]);
            min = Math.min(min, dp[n][j]);
        }
        return min;
    }

    static void printdp() {
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=maxhp+maxattack;j++) {
                System.out.print("(" + i + "," + j + ")=" + dp[i][j] + ",");
            }
            System.out.println("");
        }
    }
}
