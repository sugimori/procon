import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++) {
            a[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int[] m = new int[q];
        for(int i=0;i<q;i++) {
            m[i] = sc.nextInt();
        }

        for(int i=0;i<q;i++) {
            int sum =0;
            // System.out.println("i:" + i + " " + m[i]);
            for(int bit=0;bit< 1<<n;bit++) {
                sum = 0;
                // System.out.println("bit:" + Integer.toBinaryString(bit));
                for(int k=0;k<n;k++) {
                    // System.out.println("loop:" + Integer.toBinaryString(bit >> k & 1));
                    if((bit >>k & 1) == 1) {
                        sum += a[k];
                        // System.out.println("sum=" + sum + ",a[" + k + "]=" + a[k]);
                    }
                }
                if(sum == m[i]) break;
            }
            if(sum == m[i]) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }

        }
    }
}