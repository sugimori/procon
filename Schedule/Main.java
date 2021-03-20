import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 10000;
    static final long INF = Long.MAX_VALUE/2;
    static int n; 
    static long dp[][] = new long[MAXN+1][1 << 3];
    static int ld[] = new int[MAXN+1];
    static boolean debug = false;

    public static void main(String[] args) {
        //初期化
        for(long[] tmpdp : dp) {
            Arrays.fill(tmpdp,0);            
        }

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        String str = sc.next();
        for(int i=1;i<=n;i++) {
            switch(str.charAt(i-1)) {
                case 'J':
                    ld[i] = 1;
                    break;
                case 'O':
                    ld[i] = 1 << 1;
                    break;
                case 'I':
                    ld[i] = 1 << 2;
                    break;
            }
        }

        //初期値
        for(int i=0;i<(1<<3);i++) {
            if((i & 1) == 0) continue;      // 最初はJくんが鍵を持っている。
            if((i & ld[1]) == 0) continue;
            dp[1][i] = 1;
        }
        System.out.println(solve());
    
    }

    static int solve() {
        int count = 0;
        for(int i=2;i<=n;i++) {
            count = 0;
            for(int s=0;s< (1<<3);s++) {
                if((s & ld[i]) == 0) continue;
                for(int prev=0;prev<(1<<3);prev++) {
                    if((s & prev) != 0) {
                        dp[i][s] += dp[i-1][prev];
                    }
                }
                dp[i][s] %= 10007;
                count += dp[i][s];
            }
            debugprint();
        }
        return count % 10007;
    }

    static void debugprint() {
        if(debug) {
            for(int i=1;i<=n;i++) {
                System.out.printf("%d:",i);
                for(int j=0;j<(1<<3);j++) {
                    System.out.printf("%s(%d),",d2bin(j),dp[i][j]);
                }
                System.out.println("");
            }
            System.out.println("---");
        }
    }
    static String d2bin(int d) {
        String str = "";
        int tmp = d;
        while(true) {
            str = (tmp % 2) + str;
            if(tmp >= 2) {
                tmp /= 2;
            } else {
                break;
            }
        }
        return str;
    }
}
