import java.util.Scanner;

public class Main {
    static int n;
    static int maxw;
    static int v[] = new int[100];
    static int w[] = new int[100];
    static int maxvalue = 0;
    static int dp[][] = new int[1001][];

    public static void main(String[] args) {
        for(int i=0;i<1001;i++) {
            dp[i] = new int[10001];
            for(int j=0;j<10001;j++) {
                dp[i][j] = -1;
            }
        }
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        maxw = sc.nextInt();
        
        for(int i=0;i<n;i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        maxvalue = rec(0,maxw);
        
        System.out.println(maxvalue);
    }

    static int rec(int i,int j) {
        if(dp[i][j] != -1) return dp[i][j];
        int res;
        // System.out.println(i + "," + j + "=");
        if(i == n) {
            return 0;
        } 
        if(w[i] > j) {
            res = rec(i+1,j);
        } else {
            res = Math.max(
                rec(i+1,j),
                rec(i+1,j-w[i]) + v[i]);                
        }
        // System.out.println(i + "," + j + "=" + res);
        dp[i][j] = res;
        return res;
    }
}
