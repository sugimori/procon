import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
		int n = sc.nextInt();
        int num = 0;
        for(int i=1;i<=n;i=i+2) {
            int yakusuu = 1;
            int target = i;
            // System.out.println("FOR" + i);
            for(int j=2;j<=target;j++) {
                if(target % j == 0) {
                    // System.out.println(target + "/" + j);
                    yakusuu++;
                }
            }
            if(yakusuu == 8) num++;
        }
        System.out.println(num);
    }
}
