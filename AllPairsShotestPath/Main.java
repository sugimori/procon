import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.String;
import java.util.stream.IntStream;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

// 100000 200000 500 10
// 99856 200000 500 20

public class Main {
    static final int MAXN = 30000;
    static final long INF = Long.MAX_VALUE/2;
    static int V, E;
    static List<Map<Integer,Long>> graph;
    static long[][] dp;  // V x V
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        // grapth初期化
        graph = new ArrayList<>();
        for(int i=0;i<V;i++) {
            graph.add(i,new HashMap<Integer,Long>());
        }

        // dp初期化
        dp = new long[V][V];
        Arrays.stream(dp).forEach(x -> Arrays.fill(x,INF));

        for(int i=0;i<E;i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            long cost = sc.nextLong();
            graph.get(start).put(end,cost);
        }
        debugprint();
        if(solve() == 0) {
            Arrays.stream(dp).forEach( x -> {
                String[] str = Arrays.stream(x).mapToObj(y -> y == INF ? "INF" : Long.toString(y)).toArray(String[]::new);
                System.out.println(String.join(" ",str));
            });
        } else {
            System.out.println("NEGATIVE CYCLE");
        }

    }

    static int solve() {
        // 初期化
        for(int i=0;i < V; i++) {
            for(int j=0; j < V; j++) {
                dp[i][j] = graph.get(i).getOrDefault(j,INF);
            }
        }
        IntStream.range(0, V).forEach(x -> dp[x][x] = 0);

        for(int k=0; k < V; k++) {
            for(int i=0; i < V; i++) {
                for(int j=0; j < V; j++) {
                    if(debug) System.out.printf("PRE:dp[%d][%d]=%d\n",i,j,dp[i][j]);
                    if(debug) System.out.printf("PRE:dp[%d][%d] + dp[%d][%d] = %d\n",i,k,k,j,dp[i][k] + dp[k][j]);
                    if(dp[i][k] != INF && dp[k][j] != INF) {
                        dp[i][j] = Math.min(
                                        dp[i][j],
                                        dp[i][k] + dp[k][j]);
                    }
                    if(debug) System.out.printf("AFT:dp[%d][%d]=%d\n",i,j,dp[i][j]);
                }
            }
            debugprint();
        }

        for(int i=0;i<V;i++) {
            if(dp[i][i] < 0 ) return -1;
        }
        return 0;
    }

    static void debugprint() {
        if(debug) {
            System.out.println("d:");
            for(int i=0;i<V;i++) {
                System.out.printf(" %d:%s\n",i,Arrays.toString(dp[i]));
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
