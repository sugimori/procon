import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final int MAXN = 16;
    static final int MAXM = (int)Math.pow(16,2);
    static final long INF = Long.MAX_VALUE/2;
    static int n,m; 
    static long distance[][] = new long[MAXN+1][MAXN+1]; 
    static long timeout[][] = new long[MAXN+1][MAXN+1]; 
    static long dpcount[][] = new long[1 << MAXN][MAXN+1];  
    static long dpcost[][] = new long[1 << MAXN][MAXN+1];  
    static boolean debug = false;

    public static void main(String[] args) {
        //初期化
        for(long[] tmpdp : dpcount) {
            Arrays.fill(tmpdp,0);            
        }
        for(long[] tmpdp : dpcost) {
            Arrays.fill(tmpdp,INF);            
        }
        
        for(long[] tmpd : distance) {
            Arrays.fill(tmpd,-1);
        }
        for(long[] tmpt : timeout) {
            Arrays.fill(tmpt,-1);
        }

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        for(int i=1;i<=m;i++) {
            int tmps = sc.nextInt();
            int tmpt = sc.nextInt();
            long tmpd = sc.nextLong();
            long tmptime = sc.nextLong();
            distance[tmps][tmpt] = tmpd;
            distance[tmpt][tmps] = tmpd;
            timeout[tmps][tmpt] = tmptime;
            timeout[tmpt][tmps] = tmptime;
        }

        //初期値
        dpcost[0][1] = 0; // まだどこも行ってない
        dpcount[0][1] = 1;
        long[] result = solve((1<<n)-1,1);
        if(result[0] == INF ) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result[0] + " " + result[1]);
        }
    
    }

    static long[] solve(int sub,int end) {
        if(debug) System.out.printf("IN(%s,%d)\n",d2bin(sub),end);
        debugprint();
        if(dpcost[sub][end] != INF) {
            return new long[]{dpcost[sub][end],dpcount[sub][end]};
        }
        if(debug) System.out.printf("sub:%s -> end:%s\n",d2bin(sub),d2bin(sub&(1<<(end-1))));
        if(contain(sub,end)) {
            int nextsub = sub & ~(1<<(end-1));
            for(int u=1;u<=n;u++) {     // 直前の到着地
                if(contain(nextsub,u)) {
                    if(distance[u][end] != -1) {    // 行ける
                        long[] result = solve(nextsub,u);
                        if((result[0] != -1)&&(result[0]+distance[u][end] <= timeout[u][end])) {
                            if(dpcost[sub][end] > result[0]+distance[u][end]) {
                                dpcost[sub][end] = result[0]+distance[u][end];
                                dpcount[sub][end] = result[1];
                            } else if(dpcost[sub][end] == result[0]+distance[u][end]) {
                                dpcount[sub][end] += result[1];
                            }                                
                        }
                    }
                }
            }
        } else {
            return new long[]{-1,0};
        }
        if(debug) System.out.printf("OUT(%s,%d)=%d\n",d2bin(sub),end,dpcost[sub][end]);
        debugprint();
        return new long[]{dpcost[sub][end],dpcount[sub][end]};
    }
    static boolean contain(int s, int v) {
        if((s == 0) && (v == 1)) return true; // START
        return (s & (1 << (v-1))) != 0;
    }
    static void debugprint() {
        if(debug) {
            for(int i=0;i<=(1<<n);i++) {
                System.out.printf("%s:",d2bin(i));
                for(int j=1;j<=n;j++) {
                    if(dpcost[i][j] == INF) {
                        System.out.printf("---,");
                    } else {
                        System.out.printf("%3d(%d),",dpcost[i][j],dpcount[i][j]);
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
