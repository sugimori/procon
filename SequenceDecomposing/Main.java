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
    static int n; 
    static int dp[];
    static List<List<Integer>> dphistory = new ArrayList<>();
    static int nums[];
    static boolean debug = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        dp = new int[n+1];
        //初期化
        Arrays.fill(dp,0);
        
        nums = IntStream.range(0,n).map(x -> sc.nextInt()).toArray();
        if(debug) System.out.println(Arrays.toString(nums));

        System.out.println(solve());
    
    }

    static int solve() {
        if(debug) System.out.println("IN");
        // 初期化
        int max = 1;
        dp[1] = nums[0];
        List<Integer> firstlist = new ArrayList<>();
        firstlist.add(0);
        dphistory.add(firstlist);

        for(int i=1;i<n;i++){
            if(dp[max] < nums[i]) {
                List<Integer> tmplist = dphistory.get(max-1);
                if(debug) System.out.println("List:"+ tmplist);
                max++;
                dp[max] = nums[i];
                List<Integer> tmp2list = new ArrayList<>(tmplist);
                tmp2list.add(i);
                dphistory.add(tmp2list);
                if(debug) System.out.println("Addlist:" + tmp2list);
            } else {
                int upper = max;
                int lower = 1;
                while(lower <= upper) {
                    int j = (upper + lower)/2;
                    if(debug) System.out.printf("max=%d,nums=%d,%d < %d(%d) < %d\n",max,nums[i],lower,j,dp[j],upper);
                    if((dp[j-1] < nums[i]) &&(nums[i] < dp[j])) {
                        dp[j] = nums[i];
                        List<Integer> tmplist = dphistory.get(j-1);
                        if(debug) System.out.println("orgList:" + tmplist);
                        List<Integer> tmp2list = new ArrayList<>(tmplist);
                        tmp2list.set(tmplist.size()-1,i);
                        if(debug) System.out.println("UpdateList:" + tmp2list);
                        dphistory.set(j-1,tmp2list);
                        break;
                    }
                    if(dp[j] < nums[i]) {
                        lower = j+1;
                    } else {
                        upper = j-1;
                    }
                }
            }
            debugprint();
        }
        if(debug) System.out.println("AnswerList:" + dphistory.get(max-1));
        return max;
    }

    static void debugprint() {
        if(debug) {
            System.out.print("DP:n="+n+":");
            for(int i=1;i<=n;i++) {
                System.out.printf("%d,",dp[i]);
            }
            System.out.println("");
            dphistory.forEach(list -> System.out.println(list));
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
