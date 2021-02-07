import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// N+1個 都市 (0 .. N)
// Di 都市の間の距離 (1..N)
// M日
// Cj 天候の悪さ (1<=j<=M)
// 疲労度 Di × Cj

public class Main {
    static final int MAXN = 100;    
    static final int MAXR = 100;
    static final int MAXC = 100;
    static final int INF = Integer.MAX_VALUE/2;
    static long n; 
    static int r[] = new int[MAXR+1]; 
    static int c[] = new int[MAXC+1]; 
    static int dp[][] = new int[MAXR+1][MAXC+1];  // 

    public static void main(String[] args) {
        // 初期化

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextLong();

        System.out.println(solve());
    }

    static int solve() {
        int count = 0;

        for(long i = -n; i<= n;i++) {
            long sum = 0;
            for(long j=i;j<=n;j++) {
                sum += j;
                if(sum == n) {
                    count++;
                    break;
                } else if ( sum > n) {
                    break;
                }
            }
        }
        return count;
    }
}
