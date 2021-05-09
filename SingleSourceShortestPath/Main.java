import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static final int MAXN = 30000;
    static final int INF = Integer.MAX_VALUE/2;
    static int v,e,r; 
    static int d[];
    // static boolean used[];
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Map<Integer,Integer>> cost;

        v = sc.nextInt();
        e = sc.nextInt();
        r = sc.nextInt();

        d = new int[v];
        // used = new boolean[v];
        cost = new ArrayList<Map<Integer,Integer>>();
        for(int i=0;i<v;i++) {
            cost.add(i,new HashMap<Integer,Integer>());
        }
        Arrays.fill(d,INF);
        // Arrays.fill(used,false);

        for(int i=0;i<e;i++){
            int tmps = sc.nextInt();
            int tmpt = sc.nextInt();
            int tmpd = sc.nextInt();
            cost.get(tmps).put(tmpt,tmpd);
        }

        Arrays.stream(solve(cost)).forEach(x->{
            if(x == INF) {
                System.out.println("INF");
            } else {
                System.out.println(x);
            }
        });
    }

    static int[] solve(List<Map<Integer,Integer>> cost) {
        Queue<int[]> queue = new PriorityQueue<>((a,b)->a[1] - b[1]);
        if(debug) System.out.println("solve() IN");
        // 初期化
        d[r] = 0;
        queue.add(new int[]{r, d[r]});
        while(queue.peek()!=null) {
            int[] minvalue = queue.poll();
            int now = minvalue[0];
            int nowcost = minvalue[1];
            // for(int pos = 0; pos<v; pos++) {
            //     if(used[pos]) continue;
            //     if((minv == -1) || (d[pos] < d[minv])) minv = pos;
            // }
            // if(minv == -1) break;
            // used[minvalue[0]] = true;
            if(debug) System.out.printf("MIN: pos %d d %d\n", minvalue[0], minvalue[1]);
            debugprint();


            for(Map.Entry<Integer,Integer> next :  cost.get(now).entrySet()) {
                int tmpval = d[now] + next.getValue();

                if(d[next.getKey()] > tmpval) {
                    queue.remove(new int[]{next.getKey(), d[next.getKey()]});
                    queue.add(new int[]{next.getKey(), tmpval});
                    d[next.getKey()] = tmpval;
                }
                // d[pos] = Math.min(d[pos], d[minv] + cost[minv][pos]);

            }
            debugprint();
        }
        return d;
    }

    static void debugprint() {
        if(debug) {
            System.out.println("d:");
            for(int i=0;i<v;i++) {
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
