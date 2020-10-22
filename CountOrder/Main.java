import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static double allsum = 0;
    static int allnum = 0;
    public static void main(String[] args) {
        int num = 1;
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        int n = sc.nextInt();


        String p = "";
        String q = "";

        for(int i=1;i<=n;i++) {
            p += sc.next();
        }
        for(int i=1;i<=n;i++) {
            q += sc.next();
        }
        // System.out.println(p + "," + q);
        
        List<String> list = makepermu(n);
        list.sort(Comparator.naturalOrder());
        // System.out.println(list.toString());
        int result = Math.abs(list.indexOf(p) - list.indexOf(q));
        System.out.println(result);
        
    }

    static List<String> makepermu(int n) {
        List<String> list = new ArrayList<>();
        String str  = "";
        for(int i = 1;i<=n;i++) {
            str += i;
        }
        permu(list,"",str);
        return list;
    }
    static void permu(List<String> list, String target, String rest){
        if(rest.length() == 1) {
            list.add(target + rest);
        } else {
            for(int i=0;i<rest.length();i++) {
                permu(list,target + rest.substring(i, i+1), rest.substring(0,i) + rest.substring(i+1));
            }
        }
        return;
    }
}