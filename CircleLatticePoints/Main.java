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
    static double x,y,r; 
    static int a[] = new int[MAXN+1]; 
    // static int c[] = new int[MAXC+1]; 
    // static int dp[][] = new int[MAXR+1][MAXC+1];  // 

    public static void main(String[] args) {
        // 初期化

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        x = sc.nextDouble();
        y = sc.nextDouble();
        r = sc.nextDouble();

        int count = 0;

        int xmin = (int)Math.floor(x-r);
        int xmax = (int)Math.ceil(x+r);
        int ymin = (int)Math.floor(y-r);
        int ymax = (int)Math.ceil(y+r);
        // System.out.printf("%d %d %d %d\n", xmin,xmax,ymin,ymax);

        for(int i=xmin; i<=xmax;i++) {
            for(int j=ymin;j<=ymax;j++) {
                // System.out.printf("i=%d,j=%d\n",i,j);
                // System.out.println(Math.pow((double)i - x,2) + Math.pow((double) j - y, 2));
                if( Math.pow((double)i - x,2) + Math.pow((double) j - y, 2) <= Math.pow(r,2)) {
                    count++;
                    // System.out.printf("%d %d\n",i,j);
                }
            }
        }
        System.out.println(count);
    }

}
