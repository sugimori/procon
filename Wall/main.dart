import "dart:io";
import "dart:math";

main(List<String> args) {

  const int INF = 9223372036854775807;
  const bool debug = false;

  List<int> A = stdin.readLineSync().split(" ").map((x) => int.parse(x)).toList();

  final H = A[0];
  final W = A[1];
  final N = 10;
  
  var dp = List.generate(N, (i) => stdin.readLineSync().split(" ").map((x) => int.parse(x)).toList());

  var wall = List.generate(H, (i) => stdin.readLineSync().split(" ").map((x) => int.parse(x)).toList());
  
  int solve()
  {
    for(int k=0;k<N;k++) {
      for(int i=0;i<N;i++) {
        for(int j=0;j<N;j++) {
          if(dp[i][k] != INF && dp[k][j] != INF) {
            dp[i][j] = min(dp[i][j],dp[i][k] + dp[k][j]);
          }
        }
      }
    }
    if(debug) print(dp);

    if(debug) print(wall);
    //wall.map((list) => list.map((x) => dp[x][1]).reduce((a,b) => a + b)).reduce((a,b) => a + b);
    return wall.expand((list) => list).toList()
              .where((x) => x != -1)
              .map((x) => dp[x][1])
              .reduce((a,b) => a + b);
  }

  print(solve());

}

