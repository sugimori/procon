import "dart:io";
import "dart:math";

main(List<String> args) {

  const int INF = 9223372036854775807;
  const bool debug = false;

  List<int> A = stdin.readLineSync().split(" ").map((x) => int.parse(x)).toList();

  final N = A.elementAt(0);
  final M = A.elementAt(1);
  
  var dp = List<List<int>>.generate(N, (i) => List.generate(N, (j) => i==j? 0 : INF));

  if(debug) print(dp);

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
    return dp.map((list) => list.reduce(max)).reduce(min);

  }

  for(var i=0;i<M;i++) {
    List<int> A = stdin.readLineSync().split(" ").map((x) => int.parse(x)).toList();
    final from = A[0]-1;
    final to   = A[1]-1;
    final cost = A[2];
    dp[from][to] = dp[to][from] = cost;
    if(debug) print(dp);
  }
  print(solve());

}

