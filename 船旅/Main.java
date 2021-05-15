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
    static int number, request; 
    static List<Map<Integer,Integer>> cost;
    static int d[];
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        number = sc.nextInt();
        request = sc.nextInt();

        d = new int[number];
        cost = new ArrayList<Map<Integer,Integer>>();
        for(int i=0;i<number;i++) {
            cost.add(i,new HashMap<Integer,Integer>());
        }

        for(int i=0;i<request;i++){
            int flag = sc.nextInt();
            int reqstart, reqend;           
            Arrays.fill(d,INF);
            if(flag == 0) {
                reqstart = sc.nextInt()-1;
                reqend = sc.nextInt()-1;
                if(debug) System.out.printf("Request start %d end %d\n", reqstart, reqend);
                int result = solve(reqstart, reqend);
                if(result == INF) {
                    System.out.println("-1");
                } else {
                    System.out.println(result);
                }
            } else {
                int start = sc.nextInt()-1;
                int end = sc.nextInt()-1;
                int charge = sc.nextInt();
                if(debug) System.out.printf("Path start %d end %d charge %d\n", start, end, charge);
                if(cost.get(start).getOrDefault(end,INF) > charge) {
                    cost.get(start).put(end,charge);    
                    cost.get(end).put(start,charge);    
                }

            }
        }

    }

    static int solve(int start, int end) {
        Queue<int[]> queue = new PriorityQueue<>((a,b)->a[1] - b[1]);
        if(debug) System.out.println("solve() IN");
        // 初期化
        d[start] = 0;
        queue.add(new int[]{start, d[start]});
        while(queue.peek()!=null) {
            int[] minvalue = queue.poll();
            int now = minvalue[0];
            int nowcost = minvalue[1];

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
        if(debug) System.out.printf("OUT start %d end %d result %d\n", start, end, d[end]);
        return d[end];
    }

    static void debugprint() {
        if(debug) {
            System.out.println("d:");
            for(int i=0;i<number;i++) {
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
