import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static final int MAXN = 30000;
    static final int INF = Integer.MAX_VALUE/2;
    static int v,e,r; 
    static int cost[][];
    static int d[];
    static boolean used[];
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();
        r = sc.nextInt();

        d = new int[v];
        used = new boolean[v];
        cost = new int[v][v];
        for(int i=0;i<v;i++) {
            Arrays.fill(cost[i],INF);
        }
        Arrays.fill(d,INF);
        Arrays.fill(used,false);

        for(int i=0;i<e;i++){
            int tmps = sc.nextInt();
            int tmpt = sc.nextInt();
            int tmpd = sc.nextInt();
            cost[tmps][tmpt] = tmpd;
        }

        Arrays.stream(solve()).forEach(x->{
            if(x == INF) {
                System.out.println("INF");
            } else {
                System.out.println(x);
            }
        });
    }

    static int[] solve() {
        Queue queue = new PriorityQueue();
        if(debug) System.out.println("solve() IN");
        // 初期化
        d[r] = 0;
        while(true) {
            int minv = -1;
            for(int pos = 0; pos<v; pos++) {
                if(used[pos]) continue;
                if((minv == -1) || (d[pos] < d[minv])) minv = pos;
            }
            if(minv == -1) break;
            used[minv] = true;

            for(int pos = 0; pos < v;pos++) {
                d[pos] = Math.min(d[pos], d[minv] + cost[minv][pos]);
            }
            debugprint();
        }
        return d;
    }

    static void debugprint() {
        if(debug) {
            System.out.println("d:");
            for(int i=0;i<v;i++) {
                System.out.printf(" %d:%d(%d)\n",i,d[i],used[i]?1:0);
            }
            System.out.println("");
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
