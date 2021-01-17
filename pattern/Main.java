import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// 5 × N マス
// 1<=i<=5
// 1<=j<=N
// 1<= N <= 999
// Sij R(赤)、B(青)、W(白)、#(黒)


public class Main {
    static final int MAXN = 999;    
    static final int INF = Integer.MAX_VALUE/2;
    static int n; 
    static String s[][] = new String[5+1][MAXN+1];
    static int pattern[][] = new int[MAXN+1][3];
    static int dp[][] = new int[MAXN+1][3];  // 

    public static void main(String[] args) {
        // 初期化
        for(int[] dpval : dp) {
            Arrays.fill(dpval,0);
        }

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();

        for(int i=1;i<=5;i++) {
            String tmp = sc.next();
            for(int j=1;j<=n;j++) {
                s[i][j] = tmp.split("")[j-1];
            }
        }
        System.out.println(solve());
    }

    static int solve() {
        for(int j=1;j<=n;j++) {
            int r=0,b=0,w=0;
            for(int i=1;i<=5;i++) {
                if(s[i][j].equals("R")) r++;
                if(s[i][j].equals("B")) b++;
                if(s[i][j].equals("W")) w++;
            }
            pattern[j][0] = 5-r;
            pattern[j][1] = 5-b;
            pattern[j][2] = 5-w;
        }

        // j=1
        dp[1][0] = pattern[1][0];
        dp[1][1] = pattern[1][1];
        dp[1][2] = pattern[1][2];
            

        // debugprint();
        for(int j=2;j<=n;j++) {
            for(int i=0;i<=2;i++) {
                dp[j][i] = Math.min(dp[j-1][(i+1) % 3],dp[j-1][(i+2) % 3]) + pattern[j][i];
            }
        }
        // debugprint();

        return Arrays.stream(dp[n]).min().getAsInt();
    }
    static void debugprint() {
        for(int j=1;j<=n;j++) {
            System.out.print(j + ":");
            for(int i=0;i<=2;i++) {
                System.out.print(dp[j][i] + ",");
            }
            System.out.println("");
        }
        System.out.println("---");
    }
}
