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
                dp[i][j] = 0;
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

    static int rec(int a,int b) {

        for(int i = n-1;i>=0;i--){
            for(int j = 0; j<=b; j++) {
                if(j < w[i]) {
                    dp[i][j] = dp[i+1][j];
                } else {
                    dp[i][j] = Math.max(
                        dp[i+1][j], 
                        dp[i+1][j-w[i]]+v[i]);
                }
            }
        }

        return dp[a][b];
    }
}
