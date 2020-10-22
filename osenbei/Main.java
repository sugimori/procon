import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int max = 0;
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] a = new int[r+1][];
        int[][] b = new int[r+1][];
        for(int i=1;i<=r;i++) {
            a[i] = new int[c+1];
            b[i] = new int[c+1];
        }

        for(int i=1;i<=r;i++) {
            for(int j=1;j<=c;j++) {
                a[i][j] = sc.nextInt();            
            }
        }
        // // debug
        // for(int i=1;i<=r;i++) {
        //     for(int j=1;j<=c;j++) {
        //         System.out.print(a[i][j]);
        //     }
        //     System.out.println("");
        // }

        for(int bit=0; bit < 1 << r; bit++) {
            int sum = 0;
            for(int i=1;i<=r;i++) {
                for(int j=1;j<=c;j++) {
                    b[i][j] = a[i][j];
                }
            }    

            //裏返す
            // System.out.println("bit:" + Integer.toBinaryString(bit));
            for(int i=1;i<=r;i++) {
                // System.out.println("=>" + Integer.toBinaryString(bit >> (i-1) & 1));
                if((bit >> (i-1) & 1) == 1) {
                    for(int j=1;j<=c;j++) {
                        b[i][j] = b[i][j] == 0 ? 1 : 0;
                    }
                }
            }
            // // debug
            // for(int i=1;i<=r;i++) {
            //     for(int j=1;j<=c;j++) {
            //         System.out.print(b[i][j]);
            //     }
            //     System.out.println("");
            // }

            for(int j=1;j<=c;j++) {
                int rowsum = 0;
                for(int i=1;i<=r;i++) {
                    rowsum += b[i][j];
                }
                sum += Math.max(rowsum, r-rowsum);
            }
            // System.out.println("SUM:" + sum);

            max = Math.max(sum,max);
        }
        System.out.println(max);
    }
}