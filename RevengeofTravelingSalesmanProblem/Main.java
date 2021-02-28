import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 16;
    static final int MAXM = (int)Math.pow(16,2);
    static final int INF = Integer.MAX_VALUE/2;
    static int n,m; 
    static int distance[][] = new int[MAXN+1][MAXN+1]; 
    static int timeout[][] = new int[MAXN+1][MAXN+1]; 
    static int dp[][][] = new int[1 << MAXN][MAXN+1][2];  
    static boolean debug = true;
    static int count = 0;

    // v=2 都市2個 11(3) 10(2) 01(1) 00(0)

    public static void main(String[] args) {
        //初期化
        for(int[][] tmpdp : dp) {
            for(int[] pair : tmpdp) {
                pair[0] = INF;
                pair[1] = 0;
            }
        }
        for(int[] tmpd : distance) {
            Arrays.fill(tmpd,-1);
        }
        for(int[] tmpt : timeout) {
            Arrays.fill(tmpt,-1);
        }

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        for(int i=1;i<=m;i++) {
            int tmps = sc.nextInt();
            int tmpt = sc.nextInt();
            int tmpd = sc.nextInt();
            int tmptime = sc.nextInt();
            distance[Math.min(tmps,tmpt)][Math.max(tmps,tmpt)] = tmpd;
            timeout[Math.min(tmps,tmpt)][Math.max(tmps,tmpt)] = tmptime;
        }

        for(int i=2;i<=n;i++) {
            dp[1<<(i-1)][i][0] = distance[1][i];
        }
        int[] result = solve((1<<n)-1,1);
        if(result[0] == INF ) result[0] = -1;
        System.out.println(result);
    
    }

    static int[] solve(int sub,int end) {
        int count = 0;
        if(debug) System.out.printf("IN(%s,%d)\n",d2bin(sub),end);
        debugprint();
        if(dp[sub][end][0] != INF) {
            return dp[sub][end];
        }
        if(debug) System.out.printf("sub:%s -> end:%s\n",d2bin(sub),d2bin(sub&(1<<(end-1))));
        if(contain(sub,end)) {
            if((sub & ~(1 << (end-1)) )!= 0) {
                count = 0;
                for(int u=1;u<=n;u++) {
                    if(contain(sub,u)) {
                        if(distance[Math.min(u,end)][Math.max(u,end)] != -1) {
                            int[] result = solve(sub & ~(1 << (end-1)),u);
                            if(result[0] != -1) {
                                if(dp[sub][end][0] > result[0]+distance[Math.min(u,end)][Math.max(u,end)]) {
                                    dp[sub][end][0] = result[0]+distance[Math.min(u,end)][Math.max(u,end)];
                                    dp[sub][end][1] = result[1];
                                } else if(dp[sub][end][0] == result[0]+distance[Math.min(u,end)][Math.max(u,end)]) {
                                    dp[sub][end][1] += result[1];
                                }                                
                            }
                        }
                    }
                }
            } else {
                dp[sub][end][0] = distance[1][end];
                dp[sub][end][1] = 1;
            }
        } else {
            int[] res = {-1,0};
            return res;
        }
        if(debug) System.out.printf("OUT(%s,%d)=%d\n",d2bin(sub),end,dp[sub][end]);
        debugprint();
        return dp[sub][end];
    }
    static boolean contain(int s, int v) {
        return (s & ~(1 << (v-1)) )!= 0;
    }
    static void debugprint() {
        if(debug) {
            for(int i=0;i<=(1<<n);i++) {
                System.out.printf("%s:",d2bin(i));
                for(int j=1;j<=n;j++) {
                    if(dp[i][j][0] == INF) {
                        System.out.printf("---,");
                    } else {
                        System.out.printf("%3d,",dp[i][j][0]);
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
