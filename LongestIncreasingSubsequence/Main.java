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

        dp = new int[n+1];
        //初期化
        Arrays.fill(dp,-1);
        
        nums = IntStream.range(0,n).map(x -> sc.nextInt()).toArray();
        if(debug) System.out.println(Arrays.toString(nums));

        System.out.println(solve());
    
    }

    static int solve() {
        if(debug) System.out.println("IN");
        // 初期化
        int max = 1;
        dp[1] = nums[0];

        for(int i=1;i<n;i++){            
            if(dp[max] < nums[i]) {
                max++;
                dp[max] = nums[i];
            } else {
                for(int j=max;j>0;j--) {
                    if((dp[j-1] < nums[i])&&(nums[i] < dp[j])) {
                        dp[j] = nums[i];
                    }
                }
            }
            debugprint();
        }
        return max;
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
