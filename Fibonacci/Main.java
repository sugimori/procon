import java.util.Scanner;

public class Main {
    static int n;

    static int dp[] = new int[1001];

    public static void main(String[] args) {
        int result ;
        for(int i=0;i<45;i++) {
            dp[i] = -1;
        }
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
 
        result = fib(n);
        
        System.out.println(result);
    }

    static int fib(int n) {
        if(dp[n] != -1) return dp[n];
        if((n == 0) || (n == 1) ) return 1;

        dp[n] = fib(n-1) + fib(n-2); 
        return dp[n];
    }
}
