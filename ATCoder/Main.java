import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        String str = sc.next();
        int len=0;
        for(int i=0;i<str.length();i++){
            for(int j=0;j<str.length()-i;j++) {
                String substr = str.substring(i, str.length() - j);
                if(substr.matches("[ACGT]+")) len=Math.max(len,substr.length());
            }
        }
        System.out.println(len);
    }
}
