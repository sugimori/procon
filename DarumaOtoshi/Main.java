import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 300;    
    static final int MAXW = 1000;
    static final int INF = Integer.MAX_VALUE/2;
    static int n; 
    static int w[] = new int[MAXN+1]; 
    static int dp[][] = new int[MAXN+1][MAXN+1];  // 

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true) {
            // 初期化
            for(int[] dpval : dp) {
                Arrays.fill(dpval,0);
            }
            // スペース区切りの整数の入力
            n = sc.nextInt();
            if(n==0) break;

            for(int i=1;i<=n;i++) {
                w[i] = sc.nextInt();
            }

            System.out.println(solve());
        }
    
    }

    static int solve() {
        // 初期値
        for(int i=1;i+1<=n;i++) {
            if(Math.abs(w[i]-w[i+1]) <= 1) {
                dp[i][i+1] = 2;
            }
        }
        // debugprint();
        for(int l=1;l+1<=n;l++) {
            for(int i=1;i+l<=n;i++){
                if(dp[i][i+l] != 0) {
                    // 前後に１つずつ追加
                    if((i-1 >= 1)&&(i+l+1 <=n)&&(Math.abs(w[i-1]-w[i+l+1])<=1)){
                        dp[i-1][i+l+1] = Math.max(dp[i-1][i+l+1],dp[i][i+l]+2);
                    }
                    // 前につなげる
                    if(i-2 >= 1) {
                        for(int k=i-2;k>=1;k--) {
                            // 直前
                            if(dp[k][i-1]>0) {
                                dp[k][i+l] = Math.max(dp[k][i+l],dp[i][i+l]+dp[k][i-1]);
                            }
                            // // １つ離れ
                            // if(dp[k][i-2]>0) {
                            //     dp[k][i+l] = Math.max(dp[k][i+l],dp[i][i+l]+dp[k][i-2]);
                            // }
                        }
                    }
                    // 後ろにつなげる
                    if(i+l+2 <= n){
                        for(int k=i+l+2;k<=n;k++) {
                            // 直後
                            if(dp[i+l+1][k] > 0) {
                                dp[i][k] = Math.max(dp[i][k],dp[i][i+l]+dp[i+l+1][k]);
                            } 
                            // else if((dp[i+l+1][k] == 0)&&(dp[i+l+2][k] > 0)) {
                            //     dp[i][k] = Math.max(dp[i][k],dp[i][i+l]+dp[i+l+2][k]);
                            // }
                        }
                    }
                    // // 前に１つだけ拡張
                    // if(i-1 >= 1) {
                    //     dp[i-1][i+l] = Math.max(dp[i-1][i+l],dp[i][i+l]);
                    // }
                    // // 後ろに１つだけ拡張
                    // if(i+l+1 <=n) {
                    //     dp[i][i+l+1] = Math.max(dp[i][i+l+1],dp[i][i+l]);
                    // }
                }
            }
            // debugprint();
        }

        for(int l=1;l+1<=n;l++) {
            for(int i=1;i+l<=n;i++){
                if(dp[i][i+l] != 0) {
                    if(i-2 >= 1) {
                        for(int k=i-2;k>=1;k--) {
                            // 直前
                            if(dp[k][i-2]>0) {
                                dp[k][i+l] = Math.max(dp[k][i+l],dp[i][i+l]+dp[k][i-2]);
                            }
                        }
                    }
                    // 後ろにつなげる
                    if(i+l+2 <= n){
                        for(int k=i+l+2;k<=n;k++) {
                            // 直後
                            if(dp[i+l+2][k] > 0) {
                                dp[i][k] = Math.max(dp[i][k],dp[i][i+l]+dp[i+l+2][k]);
                            }
                        }
                    }
                    // 前に１つだけ拡張
                    if(i-1 >= 1) {
                        dp[i-1][i+l] = Math.max(dp[i-1][i+l],dp[i][i+l]);
                    }
                    // 後ろに１つだけ拡張
                    if(i+l+1 <=n) {
                        dp[i][i+l+1] = Math.max(dp[i][i+l+1],dp[i][i+l]);
                    }
                }
            }
        }
        debugprint();

        // int result = 0;
        // for(int i=1;i<=n-1;i++){
        //     for(int j=n;j>i;j--) {
        //         if(dp[i][j] > 0) {
        //             result += dp[i][j];
        //             System.out.printf("sum(%d,%d)=%d (%d)\n",i,j,dp[i][j],result);
        //             i=j;
        //             break;
        //         }
        //     }
        // }
        // System.out.println(result);
        // int result2 = 0;
        // for(int j=n;j>1;j--){
        //     for(int i=1;j>i;i++) {
        //         if(dp[i][j] > 0) {
        //             result2 += dp[i][j];
        //             System.out.printf("sum(%d,%d)=%d (%d)\n",i,j,dp[i][j],result2);
        //             j=i;
        //             break;
        //         }
        //     }
        // }
        // System.out.println(result2);
        // return Math.max(result,result2);
        return dp[1][n];
    }
    static void debugprint() {
        boolean debug = false;
        if(debug) {
            for(int i=1;i<=n;i++) {
                System.out.printf("%2d:",i);
                for(int j=1;j<=n;j++) {
                    System.out.printf("%3d,",dp[i][j]);
                }
                System.out.println("");
            }
            System.out.println("---");
        }
    }
}
