import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static final int MAXN = 100000;
    static final int MAXM = 20;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,m; 
    static int dp[];
    static int toycache[][];
    static int toys[];
    static Map<Integer,Integer> countmap = new HashMap<>();
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        dp = new int[1<<m];
        //初期化
        Arrays.fill(dp,INF);
        
        toycache = new int[m][n];
        for(int[] tmp : toycache) {
            Arrays.fill(tmp,-1);
        }

        toys = IntStream.range(0,n).map(x -> sc.nextInt()-1).toArray();
        if(debug) System.out.println(Arrays.toString(toys));

        Arrays.stream(toys).forEach(num -> countmap.put(num,countmap.getOrDefault(num, 0)+1));
        if(debug) System.out.printf("countmap:%s",countmap);
        System.out.println(solve());
    
    }

    static int solve() {
        if(debug) System.out.println("IN");
        dp[0] = 0;
        for(int s=0;s<(1<<m);s++) {
            for(int i=0;(i<m)&&((s | (1<<i)) < (1<<m));i++) {
                int nextset = s | (1<<i);
                if( s == nextset) continue;
                if(nextset >= (1<<m)) continue;
                if(debug) System.out.printf("set: %s -> nextset: %s\n",d2bin(s),d2bin(nextset));
                int total = totalnum(s);
                dp[nextset] = Math.min(dp[nextset],
                                dp[s] + counttoy(i, total, total + countmap.get(i)-1));
            }
        }
        debugprint();
        return dp[(1 << m) -1];
        
    }

    static int counttoy(int toynum,int from, int to){
        int count=0;
        if(toycache[toynum][from] != -1) return toycache[toynum][from];
        // int[] subtoys = Arrays.copyOfRange(toys, from, to+1);
        // count = (int)Arrays.stream(subtoys).filter(toy -> toy != toynum).count();

        for(int i=from;i<=to;i++){
            if(toys[i] != toynum) count++;
        }
        if(debug) System.out.printf("counttoy(%d,%d,%d)=%d\n",toynum,from,to,count);
        toycache[toynum][from] = count;
        return count;
    }

    static int totalnum(int set) {
        int total = 0;
        for(int i=0;i<m;i++) {
            if((set & (1 << i)) != 0) {
                total += countmap.get(i);
            }
        }
        return total;
    }

    static void debugprint() {
        if(debug) {
            for(int i=0;i<(1<<m);i++) {
                System.out.printf("%s(%d)\n",d2bin(i),dp[i]);
            }
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
