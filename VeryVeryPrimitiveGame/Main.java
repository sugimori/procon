import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        // 初期化

        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        String winner;

        if(c==0) {
            if(a > b) {
                winner = "Takahashi";
            } else {
                winner = "Aoki";
            }
        } else {
            if(a >= b) {
                winner = "Takahashi";
            } else {
                winner = "Aoki";
            }
        }

        System.out.println(winner);
        
    }
}
