import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 2000;    
    static final int MAXA = 1000000000;
    static final int INF = Integer.MAX_VALUE/2;
    static int n; 
    static long a[] = new long[MAXN+1]; 
    static long dp[][] = new long[MAXN+1][MAXN+1];  // 

    public static void main(String[] args) {
        // 初期化
        for(long[] dpval : dp) {
            Arrays.fill(dpval,0);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();

        for(int i=1;i<=n;i++) {
            a[i] = sc.nextLong();
        }

        System.out.println(solve());
    }

    static long solve() {
        // 初期値
        boolean joi = (n%2 == 1);
        if(joi) {
            for(int i=1;i<=n;i++) {
                dp[i][i] = a[i]; 
            }
        }
        joi = !joi;
        // debugprint();
        for(int l=1;l<=n-1;l++) {
            for(int i=1;i<=n;i++){
                if(joi) {
                    dp[i][rotation(i+l)] = Math.max(
                                        dp[i][rotation(i+l-1)] + a[rotation(i+l)],
                                        dp[rotation(i+1)][rotation(i+l)] + a[i]);                                        
                } else {
                    if(a[i] > a[rotation(i+l)]) {
                        dp[i][rotation(i+l)] = dp[rotation(i+1)][rotation(i+l)];
                    } else {
                        dp[i][rotation(i+l)] = dp[i][rotation(i+l-1)];                        
                    }
                }
                // System.out.printf("l:%d i:%d\n",l,i);
                // debugprint();
            }
            joi = !joi;
            debugprint();
        }
        debugprint();
        long weight = 0;
        for(int i=1;i<=n;i++){
            weight = Math.max(weight,dp[i][rotation(i+n-1)]);
        }

        return weight;
    }
    static void debugprint() {
        boolean debug = false;
        if(debug) {
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
    static int rotation(int i) {
        return i>n ? i-n : i;
    }
}
