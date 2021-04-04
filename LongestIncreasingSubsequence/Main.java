import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static final int MAXN = 100000;
    static final int INF = Integer.MAX_VALUE/2;
    static int n; 
    static int dp[];
    static int nums[];
    static boolean debug = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        dp = new int[n];
        //初期化
        Arrays.fill(dp,1);
        
        nums = IntStream.range(0,n).map(x -> sc.nextInt()).toArray();
        if(debug) System.out.println(Arrays.toString(nums));

        System.out.println(solve());
    
    }

    static int solve() {
        if(debug) System.out.println("IN");
        int count=1;
        for(int i=0;i<n-1;i++){
            int tmpdp[] = Arrays.copyOfRange(dp, i+1, n);
            if(debug) System.out.println(Arrays.toString(tmpdp));
            int min = Arrays.stream(tmpdp).min().getAsInt();
            if(dp[i]+1 <= min) continue;
            for(int j=i+1;j<n;j++) {
                if(nums[i] < nums[j]) {
                    dp[j] = Math.max(dp[j],dp[i] + 1);
                    count = Math.max(count, dp[j]);
                    // break;
                }
            }
            debugprint();
        }
        return count;
    }

    static void debugprint() {
        if(debug) {
            System.out.println("DP");
            for(int i=0;i<n;i++) {
                System.out.printf("%d, ",dp[i]);
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
