import java.util.Scanner;
import java.util.stream.IntStream;
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
import java.lang.Long;

public class Main {
    static final int MAXN = 30000;
    static final long INF = Long.MAX_VALUE/2;
    static int town, road, zombi, safeRange, safePrice, riskPrice;
    static List<Set<Integer>> path;
    static boolean zombiTown[];
    static int zombiTownArray[];
    static int price[];
    static boolean used[];
    static long d[];
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

        zombiTown = new boolean[town];
        zombiTownArray = new int[zombi];
        Arrays.fill(zombiTown, false);
        for(int i=0; i< zombi; i++) {
            zombiTownArray[i] = sc.nextInt()-1;
            zombiTown[zombiTownArray[i]] = true;
        }
        if(debug) System.out.printf("Zombi %s\n", Arrays.toString(zombiTown));

        used = new boolean[town];
        Arrays.fill(used, false);

        d = new long[town];
        Arrays.fill(d,INF);
        price = new int[town];
        Arrays.fill(price,safePrice);
        path = new ArrayList<Set<Integer>>();
        for(int i=0;i<town;i++) {
            path.add(i,new HashSet<Integer>());
        }

        for(int i=0;i<road;i++) {
            int start = sc.nextInt()-1;
            int end = sc.nextInt()-1;
            path.get(start).add(end);
            path.get(end).add(start);
        }
        makePrice();
        System.out.println(solve());

    }

    static void makePrice() 
    {
        if(debug) System.out.printf("ZombiTown %s\n",Arrays.toString(zombiTown));
        for(int i=0;i<zombi;i++) {
            boolean[] done = new boolean[town];
            Arrays.fill(done,false);

            if(debug) System.out.printf("Town Check %d\n",i);
            Queue<Integer[]> que = new ArrayDeque<>();
            que.add(new Integer[]{zombiTownArray[i],0});

            Integer[] now;
            while((now = que.poll()) != null ) {
                if(debug) System.out.printf("now %d %d\n",now[0],now[1]);
                if(now[1] > safeRange) continue;
                price[now[0]] = riskPrice;                
                done[now[0]] = true;

                for(Integer next : path.get(now[0])) {
                    if(done[next] == true) continue;
                    if(now[1] < safeRange) {
                        que.add(new Integer[]{next, now[1]+1});
                        if(debug) System.out.printf("que add %d %d\n",next, now[1]+1);
                    }
                }
            }

        }
        price[0] = 0;
        price[town-1] = 0;
        if(debug) System.out.printf("price %s\n", Arrays.toString(price));
    }

    static long solve() {
        Queue<Long[]> queue = new PriorityQueue<>((a,b) -> {
            if(a[1] > b[1]) return 1;
            else if(a[1] < b[1]) return -1;
            else return 0;
        });
        if(debug) System.out.println("solve() IN");
        // 初期化
        d[0] = 0;
        queue.add(new Long[]{Long.valueOf(0), Long.valueOf(0)});
        while(queue.peek()!=null) {
            Long[] now = queue.poll();
            int nowpos = now[0].intValue();
            long nowval = now[1].longValue();
            if(nowpos==town-1) break;
            used[nowpos] = true;

            if(debug) System.out.printf("MIN: pos %d\n", nowpos);
            debugprint();

            for(Integer next :  path.get(nowpos)) {
                if(zombiTown[next] == true) continue;
                if(used[next] == true) continue;
                long nextval = d[nowpos] + price[next];

                if(d[next] > nextval) {
                    queue.add(new Long[]{Long.valueOf(next), Long.valueOf(nextval)});
                    d[next] = nextval;
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
