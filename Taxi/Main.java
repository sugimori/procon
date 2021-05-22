import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

// 100000 200000 500 10
// 99856 200000 500 20

public class Main {
    static final int MAXN = 30000;
    static final int INF = Integer.MAX_VALUE/2;
    static int town;    // N
    static int road;    // K
    static int[] price; // Ci
    static int[] distance; // Ri
    static int cost[];
    static List<List<Integer>> path;
    static Set<Integer> visited;
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        town = sc.nextInt();    // N
        road = sc.nextInt();    // K

        price = new int[town];
        distance = new int[town];
        for(int i=0;i<town;i++){
            price[i] = sc.nextInt();
            distance[i] = sc.nextInt();
        }
        // path初期化
        path = new ArrayList<>();
        for(int i=0;i<town;i++) {
            path.add(i,new ArrayList<Integer>());
        }
        for(int i=0;i<road;i++) {
            int start = sc.nextInt()-1;
            int end = sc.nextInt()-1;
            path.get(start).add(end);
            path.get(end).add(start);
        }
        if(debug) System.out.printf("Path size: %d\n",path.size());

        // townの最低コスト
        cost = new int[town];
        Arrays.fill(cost,INF);

        // 訪問フラグ
        visited = new HashSet<>();

        System.out.println(solve());

    }

    static long solve() {
        visited.clear();
        Queue<int[]> queue = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        if(debug) System.out.println("solve() IN");
        // 初期化
        cost[0] = 0;
        queue.add(new int[]{0,0});
        while(queue.peek()!=null) {
            int now = queue.peek()[0];
            int nowcost = queue.poll()[1];
            if(now == town-1) break;
            visited.clear();
            visited.add(now);
            if(debug) System.out.printf("MIN: pos %d cost %d\n", now,nowcost);
            // debugprint();
            dfs(queue, now, cost[now]+price[now],distance[now]);
            // debugprint();
        }
        if(debug) System.out.printf("OUT solve() result %d\n", cost[town-1]);
        return cost[town-1];
    }

    static void dfs(Queue<int[]> queue, int pos, int price, int times)
    {
        if(times < 1) return;
        for(Integer next :  path.get(pos)) {
            if(debug) System.out.printf("%d->%d (times %d)\n",pos,next,times);
            if(visited.contains(next) ==  true) continue;        
            if(cost[next] > price) {
                queue.add(new int[] {next, price});
                cost[next] = price;
            }
            if(next == town -1) continue;
            dfs(queue, next, price, times-1);
        }
    }

    static void debugprint() {
        if(debug) {
            System.out.println("d:");
            for(int i=0;i<town;i++) {
                System.out.printf(" %d:%d\n",i,cost[i]);
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
