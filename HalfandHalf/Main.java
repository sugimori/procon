import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        long min = 0;

        for(int i=0;i<=Math.max(2*x,2*y);i++) {
            long anum = Math.max((long)Math.ceil(x - i/2.0),0);
            long bnum = Math.max((long)Math.ceil(y - i/2.0),0);
            long total = a* anum + b*bnum + c*i;
            if((min == 0 ) || (total < min)) {
                min = total;
                // System.out.println(anum + "," + bnum + "," + i + "=>" + total);
            }
        }
        System.out.println(min);
    }
}
