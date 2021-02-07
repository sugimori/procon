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
    static final int MAXM = 100;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,m,k; 
    static int a[] = new int[MAXM+1]; 
    static int b[] = new int[MAXM+1]; 
    static int cd[][] = new int[16+1][2]; 

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i=1;i<=m;i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        k = sc.nextInt();

        for(int i=1;i<=k;i++) {
            cd[i][0] = sc.nextInt();
            cd[i][1] = sc.nextInt();
        }

        System.out.println(solve());
    }

    static int solve() {
        //初期化
        int dish[] = new int[n+1];
        int result = 0;    
        for(int i = 0; i < Math.pow(2,k); i++) {
            int tmp = 0;
            Arrays.fill(dish,0);    
            for(int j=1;j<=k;j++) {
                    int bin = i>>(j-1) & 1;
                    // System.out.printf("i:%d->j:%d=i>>j:%d\n",i,j,bin);
                    dish[cd[j][bin]]++;
            }
            for(int j=1;j<=m;j++) {
                if((dish[a[j]] >= 1) && (dish[b[j]] >= 1)) {
                    tmp++;
                }
            }
            result = Math.max(result,tmp);
        }

        return result;
    }
}
