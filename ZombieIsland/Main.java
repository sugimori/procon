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
    static final long INF = Long.MAX_VALUE/2;
    static int town, road, zombi, safeRange, safePrice, riskPrice;
    static Set<Integer> zombiTown;
    static int price[];
    static long d[];
    static Set<Integer> visited;
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        town = sc.nextInt();
        road = sc.nextInt();
        zombi = sc.nextInt();
        safeRange = sc.nextInt();
        safePrice = sc.nextInt();
        riskPrice = sc.nextInt();
        if(debug) System.out.printf("Price safe %d risk %d\n", safePrice, riskPrice);

        zombiTown = new HashSet<>();
        for(int i=0; i< zombi; i++) {
            zombiTown.add(sc.nextInt()-1);
        }
        if(debug) System.out.printf("Zombi %s\n", zombiTown);

        // townの最低コスト
        d = new long[town];
        Arrays.fill(d,INF);

        // 宿泊費
        price = new int[town];
        Arrays.fill(price,safePrice);

        // 訪問フラグ
        visited = new HashSet<>();

        // path初期化
        List<List<Integer>> path = new ArrayList<>();
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

        sc.close();

        makePrice(path);
        System.out.println(solve(path));
        System.gc();

    }

    static void makePrice(List<List<Integer>> path) 
    {
        visited.clear();
        Queue<Integer[]> que = new ArrayDeque<>();
        zombiTown.forEach( pos -> que.add(new Integer[]{pos,1}));
        Integer[] now;
        while((now = que.poll()) != null ) {
            if(debug) System.out.printf("now %d %d\n",now[0],now[1]);
            if(now[1] > safeRange + 1) continue;
            price[now[0]] = riskPrice;                
            visited.add(now[0]);

            for(Integer next : path.get(now[0])) {
                if(visited.contains(next) == true) continue;
                if(zombiTown.contains(next) == true) continue;
                que.offer(new Integer[]{next, now[1]+1});
                visited.add(next);
                if(debug) System.out.printf("que add %d %d\n",next, now[1]+1);
            }
        }
        
        price[0] = 0;
        price[town-1] = 0;
        if(debug) System.out.printf("price %s\n", Arrays.toString(price));
    }

    static long solve(List<List<Integer>> path) {
        visited.clear();
        Queue<long[]> queue = new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1]));
        if(debug) System.out.println("solve() IN");
        // 初期化
        d[0] = 0;
        queue.add(new long[]{0L,0L});
        while(queue.peek()!=null) {
            long[] now = queue.poll();
            int nowpos = (int)now[0];
            long nowcost = now[1];
            if(nowpos == town-1) break;
            visited.add(nowpos);

            if(debug) System.out.printf("MIN: pos %d cost %d\n", nowpos,nowcost);
            debugprint();

            for(Integer next :  path.get(nowpos)) {
                if(visited.contains(next) ==  true) continue;
                if(zombiTown.contains(next) == true) continue;
                long nextcost = nowcost + price[next];

                if(d[next] > nextcost) {
                    if(debug) System.out.println(nowpos + "->" + next);
                    queue.add(new long[] {next, nextcost});
                    d[next] = nextcost;
                }

            }
            debugprint();
        }
        if(debug) System.out.printf("OUT solve() result %d\n", d[town-1]);
        return d[town-1];
    }

    static void debugprint() {
        if(debug) {
            System.out.println("d:");
            for(int i=0;i<town;i++) {
                System.out.printf(" %d:%d\n",i,d[i]);
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
