import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXV = 15;
    static final int MAXE = (int)Math.pow(15,2);
    static final int INF = Integer.MAX_VALUE/2;
    static int v,e; 
    static int d[][] = new int[MAXV+1][MAXV+1]; 
    static int dp[][] = new int[1 << MAXV][MAXV+1];  
    static boolean debug = false;

    // v=2 都市2個 11(3) 10(2) 01(1) 00(0)

    public static void main(String[] args) {
        //初期化
        for(int[] tmpdp : dp) {
            Arrays.fill(tmpdp,INF);
        }
        for(int[] tmpd : d) {
            Arrays.fill(tmpd,-1);
        }

        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();
        for(int i=0;i<=e-1;i++) {
            int tmps = sc.nextInt();
            int tmpt = sc.nextInt();
            int tmpd = sc.nextInt();
            d[tmps][tmpt] = tmpd;
        }

        dp[0][0] = 0;
        int result = solve((1<<v)-1,0);
        if(result == INF ) result = -1;
        System.out.println(result);
    
    }

    static int solve(int sub,int end) {
        if(debug) System.out.printf("solve(%d,%d)\n",sub,end);
        debugprint();
        if(dp[sub][end] != INF) {
            return dp[sub][end];
        }
        if(debug) System.out.println("A:" + (sub&(1<<end)));
        if(contain(sub,end)) {
            for(int u=0;u<=v-1;u++) {
                if(contain(sub & ~(1 << end), u)) {
                    if(d[u][end] != -1) {
                        int result = solve(sub & ~(1 << end),u);
                        if(result != -1) {
                            dp[sub][end] = Math.min(dp[sub][end],result+d[u][end]);
                        }
                    }
                }
            }
        } else {
            return -1;
        }
        debugprint();
        return dp[sub][end];
    }
    static boolean contain(int s,int v){
        if(s == 0) return true;
        return (s & (1 << v)) != 0;
    }
    static void debugprint() {
        if(debug) {
            for(int i=0;i<=(1<<v);i++) {
                System.out.printf("%s:",d2bin(i));
                for(int j=0;j<=v-1;j++) {
                    if(dp[i][j] == INF) {
                        System.out.printf("---,");
                    } else {
                        System.out.printf("%3d,",dp[i][j]);
                    }
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
