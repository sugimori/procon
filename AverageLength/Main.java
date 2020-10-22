import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static double allsum = 0;
    static int allnum = 0;
    static int[] x;
    static int[] y;
    public static void main(String[] args) {
        int num = 1;
        Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
        int n = sc.nextInt();


        x = new int[n+1];
        y = new int[n+1];

        for(int i=1;i<=n;i++) {
            x[i] = sc.nextInt();            
            y[i] = sc.nextInt();
        }

        List<String> list = makepermu(n);
        allnum = list.size();

        for(String str: list) {
            for(int i=0;i<str.length()-1;i++) {
                int from = Integer.parseInt(str.substring(i, i+1));
                int to = Integer.parseInt(str.substring(i+1, i+2));
                allsum += distance(from, to);
            }
        }

        System.out.println(allsum / allnum);
        
    }

    public static double distance(int from, int to) {
        return Math.sqrt(Math.pow(x[to] - x[from],2) + Math.pow(y[to] - y[from],2));
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