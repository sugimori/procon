import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// N+1個 都市 (0 .. N)
// Di 都市の間の距離 (1..N)
// M日
// Cj 天候の悪さ (1<=j<=M)
// 疲労度 Di × Cj

public class Main {
    static final int MAXN = 100000;    
    static final int MAXR = 100;
    static final int MAXC = 100;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,x; 
    static int a[] = new int[MAXN+1]; 
    // static int c[] = new int[MAXC+1]; 
    // static int dp[][] = new int[MAXR+1][MAXC+1];  // 

    public static void main(String[] args) {
        // 初期化

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        x = sc.nextInt();

        for(int i=1; i<=n;i++) {
            a[i] = sc.nextInt();
        }

        int first = 0;
        for(int i=1;i<=n;i++) {
            if(a[i] != x) {
                if(first == 1) {
                    System.out.print(" ");
                }
                System.out.printf("%d",a[i]);
                first = 1;
            }
        }
        System.out.println("");

    }

}
