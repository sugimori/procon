
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
public class Main {
    static int length = 0;
    static int pos[][];
    static int w,h;
    static int INF = 9999;
    
    public static void main(String[] args){

        String[][] maze = new String[50][];
        int[][] dxdy = {{1,0},{0,1},{-1,0},{0,-1}};
        int wall = 0;

		Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
		h = sc.nextInt();
        w = sc.nextInt();
        // System.out.println("w,h = " + w + "," + h);

        //初期化
        for(int i=0;i<w;i++) {
            maze[i] = new String[h];
        }

        for(int j=0;j<h;j++) {
            String tmp = sc.next();
            for(int i=0;i< w;i++) {
                maze[i][j] = tmp.substring(i,i+1);
                if(maze[i][j].equals("#")) wall++;
                // System.out.print(maze[i][j]);
            }
            // System.out.println("");
        }

        Queue<Integer[]> que = new ArrayDeque<>();
        Integer[] zero = {0,0};
        maze[0][0] = "1";
        que.add(zero);
        Integer[] now;
        while((now = que.poll()) != null) { 
            int len = Integer.parseInt(maze[now[0]][now[1]]);
            // System.out.println("len:" + len);
            // System.out.println("now = (" + now[0] + "," + now[1] + ")");
            if((now[0] == w-1)&&(now[1] == h-1)) continue; // ゴール
            for(int i=0;i<4;i++) {
                int newx = now[0] + dxdy[i][0];
                int newy = now[1] + dxdy[i][1];

                if((newx < 0) || (newy < 0) || (newx >= w) || (newy >= h)) continue;
                if(maze[newx][newy].equals("#")) continue; // 壁あり
                if(maze[newx][newy].equals(".")) { //未訪問
                    maze[newx][newy] = Integer.toString(len+1);
                    Integer[] next = {newx, newy};
                    que.add(next);                 
                } else { // 訪問済

                }
            }

        }
        // for(int j=0;j<h;j++) {
        //     for(int i=0;i<w;i++) {
        //         System.out.print(maze[i][j]);
        //     }
        //     System.out.println("");
        // }
        if(maze[w-1][h-1].equals(".")) {
            System.out.println("-1");
        } else {
            System.out.println(w*h - wall - Integer.parseInt(maze[w-1][h-1]));
        }
    }

}



