import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// 魔法使いの高橋君は魔物と戦っています。
// 高橋君は 
// N
//  種類の呪文を使うことができます。 
// i
//  番目の呪文は詠唱に 
// X
// i
//  秒かかり、威力は 
// Y
// i
//  です。
// ただし、この魔物は強いので、詠唱に 
// S
//  秒以上かかる呪文や、威力が 
// D
//  以下の呪文ではダメージを与えられません。 また、呪文以外の方法でダメージを与えることもできません。
// 高橋君は魔物にダメージを与えられるでしょうか？


public class Main {
    static final int MAXN = 100;    
    static final int MAXR = 100;
    static final int MAXC = 100;
    static final int INF = Integer.MAX_VALUE/2;
    static int n,s,d; 
    static int x[] = new int[MAXN+1]; 
    static int y[] = new int[MAXN+1]; 
    static int dp[][] = new int[MAXR+1][MAXC+1];  // 

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        n = sc.nextInt();
        s = sc.nextInt();
        d = sc.nextInt();
        

        for(int i=1;i<=n;i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            if(x[i] >= s) continue;
            if(y[i] <= d) continue;
            System.out.println("Yes");
            return ;
        }

        System.out.println("No");
    }
}
