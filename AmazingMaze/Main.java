
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
public class Main {
    static int length = 0;
    static int pos[][];
    static int w,h;
    static int INF = 9999;
    
    public static void main(String[] args){

        int[][] box = new int[60+1][];
        int[][] dxdy = {{1,0},{0,1},{-1,0},{0,-1}};

		Scanner sc = new Scanner(System.in);
		// スペース区切りの整数の入力
		w = sc.nextInt();
        h = sc.nextInt();
        // System.out.println("w,h = " + w + "," + h);

        while((w!=0)&&(h!=0)) {
            //初期化
            for(int i=0;i<=60;i++) {
                box[i] = new int[60+1];
                for(int j=0; j<=60;j++) {
                    box[i][j] = INF;  //初期化
                }
            }
    
            //最初の横
            for(int i=0;i < w-1; i++) {
                box[2*i +1][0] = sc.nextInt();
                // System.out.println("YOKO " + i+"0=ECHO" + box[2*i +1][0]);
            }
            for(int j=1;j<h;j++) {
                // 縦の壁
                for(int i=0;i< w;i++) {
                    box[2*i][2*j-1] = sc.nextInt();
                    // System.out.println("TATE " + i+","+j+"=ECHO" + box[2*i][2*j-1]);
                }
                // 横の壁
                for(int i=0;i < w-1; i++) {
                    box[2*i +1][2*j] = sc.nextInt();
                    // System.out.println("YOKO " + i+","+j+"=ECHO" + box[2*i +1][2*j]);
                }
            }
            // // debug
            // for(int j=0;j<2*h-1;j++) {
            //     for(int i=0;i<2*w-1;i++) {
            //         System.out.print("(" + i + "," + j + ")=" + box[i][j]);
            //     }
            //     System.out.println("");
            // }

            Queue<Integer[]> que = new ArrayDeque<>();
            Integer[] zero = {0,0};
            box[0][0] = 1;
            que.add(zero);
            Integer[] now;
            while((now = que.poll()) != null) { 
                // System.out.println("now = (" + now[0] + "," + now[1] + ")");
                if((now[0] == 2*w-2)&&(now[1] == 2*h -2)) continue; // ゴール
                for(int i=0;i<4;i++) {
                    int newx = now[0] + dxdy[i][0];
                    int newy = now[1] + dxdy[i][1];
                    int nextx = now[0] + 2*dxdy[i][0];
                    int nexty = now[1] + 2*dxdy[i][1];

                    if((newx < 0) || (newy < 0) || (newx > 2*w -2) || (newy > 2*h -2)) continue;
                    if(box[newx][newy] == 1) continue; // 壁あり
                    if(box[nextx][nexty] != INF) { // 訪問済
                        box[nextx][nexty] = Math.min(box[nextx][nexty],box[now[0]][now[1]] + 1);
                        continue;
                    } else {
                        box[nextx][nexty] = box[now[0]][now[1]] + 1;
                        Integer[] next = {nextx, nexty};
                        que.add(next);                 
                    }
                }

            }
            if(box[2*w-2][2*h-2] == INF) {
                System.out.println("0");
            } else {
                System.out.println(box[2*w-2][2*h-2]);
            }


            w = sc.nextInt();
            h = sc.nextInt();   
            // System.out.println("w,h = " + w + "," + h);
        }

    }

}

