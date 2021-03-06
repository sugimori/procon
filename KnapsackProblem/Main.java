import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 100;
    static final int MAXW = 10000;
    static int n; // 1<=N<=100
    static int maxweight; // 1<=W<=10000
    static int v[] = new int[100]; // 1<=v<=1000
    static int w[] = new int[100]; // 1<=w<=1000
    static int maxvalue = 0;
    static int dp[][] = new int[MAXN+1][MAXW+1];  // 

    public static void main(String[] args) {
        for(int[] dpval : dp) {
            Arrays.fill(dpval,0);
        }
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        maxweight = sc.nextInt();
        
        for(int i=0;i<n;i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        System.out.println(solve());
    }

    static int solve() {

        for(int i = 0;i < n;i++){
            for(int j = 0; j<=maxweight; j++) {
                if(j < w[i]) {
                    dp[i+1][j] = dp[i][j];
                } else {
                    dp[i+1][j] = Math.max(
                        dp[i][j], 
                        dp[i+1][j-w[i]]+v[i]);
                }
            }
        }

        return dp[n][maxweight];
    }
}
