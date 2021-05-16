import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main{
	public static void main(String[]$) throws IOException{
		new Main().solve();
	}
	static FastScanner s=new FastScanner(System.in);
	static IntStream REPS(int v){return IntStream.range(0,v);}
	static IntStream REPS(int l,int r){return IntStream.rangeClosed(l,r);}
	static int gInt() throws IOException{return s.nextInt();}
	static long gLong() throws IOException{return s.nextLong();}
	static long gDouble() throws IOException{return s.nextLong();}

	int n,zombie;
	long[]cost,res;
	ArrayList<HashSet<Integer>> e;
	private void solve() throws IOException{
		n=gInt();
		int road=gInt();
		zombie=gInt();
		int dist=gInt(),csafe=gInt(),cdanger=gInt();
		HashSet<Integer> danger=new HashSet<>();
		cost=new long[n];//Lmax:zombie
		res=new long[n];
		e=new ArrayList<>();
		for(int i=0;i<n;++i)
			e.add(new HashSet<>());
		Arrays.fill(res,Long.MAX_VALUE/10);
		res[0]=0;
		for(int i=0;i<zombie;++i) {
			int v=gInt()-1;
			cost[v]=Long.MAX_VALUE/10;
			danger.add(v);
 		}
		for(int i=0;i<road;++i) {
			int f=gInt()-1,t=gInt()-1;
			e.get(f).add(t);
			e.get(t).add(f);
		}

		//Arrays.stream(e).map(String::valueOf).forEach(System.out::println);

		//危険な町の確定
		{
			HashSet<Integer> next=new HashSet<>(),buf=new HashSet<>();/*buf=新しく行ける*/
			next.addAll(danger);
			for(int i=0;i<dist;++i) {
				next.stream()
				.map(e::get)
				.forEach(buf::addAll);
				buf.removeAll(danger);
				danger.addAll(buf);
				next.clear();
				next.addAll(buf);
			}
		}

		//System.out.println(danger);

		for(int i=1;i<n-1;++i) {
			if(cost[i]==0) {
				cost[i]=danger.contains(i)?cdanger:csafe;
			}
		}

		search();

		System.out.println(res[n-1]);
	}
	private void search(){
		BitSet searched=new BitSet();
		PriorityQueue<Edge> edge=new PriorityQueue<>();
		edge.add(new Edge(0,0,0));
		while(!edge.isEmpty()) {
			Edge poll=edge.poll();
			if(res[poll.t]<poll.c){
				continue;
			}
			searched.set(poll.t);
			e.get(poll.t).stream().filter(o->!searched.get(o)).forEach(o->{
				if(res[o]>res[poll.t]+cost[o]) {
					res[o]=res[poll.t]+cost[o];
					edge.add(new Edge(poll.t,o,res[o]));
				}
			});
		}
	}
	private static class Edge implements Comparable<Edge>{
		int f,t;long c;
		public Edge(int from,int to,long cost){f=from;t=to;c=cost;}
		@Override
		public String toString(){return "("+f+","+t+")="+c;}
		@Override
		public int compareTo(Edge o){
			long v=c-o.c;
			return v==0?0:v>0?1:-1;
		}
		@Override
		public int hashCode(){
			final int prime=31;
			int result=prime+(int)(c^(c>>>32));
			result=prime*result+f;
			result=prime*result+t;
			return result;
		}
		@Override
		public boolean equals(Object obj){
			if(this==obj) return true;
			if(obj==null||getClass()!=obj.getClass()) return false;
			Edge other=(Edge)obj;
			if(c!=other.c||f!=other.f||t!=other.t) return false;
			return true;
		}
	}
}
class FastScanner{
	private final BufferedInputStream	in;
	private static final int			bufSize	=1<<16;
	private final byte					buf[]	=new byte[bufSize];
	private int							i		=bufSize;
	private int							k		=bufSize;
	private final StringBuilder			str		=new StringBuilder();

	FastScanner(InputStream in){
		this.in=new BufferedInputStream(in,bufSize);
	}
	int nextInt() throws IOException{
		return (int)nextLong();
	}
	long nextLong() throws IOException{
		int c;
		long x=0;
		boolean sign=true;
		while((c=nextChar())<=32)
			;
		if(c=='-'){
			sign=false;
			c=nextChar();
		}
		if(c=='+'){
			c=nextChar();
		}
		while(c>='0'){
			x=x*10+(c-'0');
			c=nextChar();
		}
		return sign?x:-x;
	}
	int nextChar() throws IOException{
		if(i==k){
			k=in.read(buf,0,bufSize);
			i=0;
		}
		return i>=k?-1:buf[i++];
	}
	String next() throws IOException{
		int c;
		str.setLength(0);
		while((c=nextChar())<=32&&c!=-1)
			;
		if(c==-1){
			return null;
		}
		while(c>32){
			str.append((char)c);
			c=nextChar();
		}
		return str.toString();
	}
	String nextLine() throws IOException{
		int c;
		str.setLength(0);
		while((c=nextChar())<=32&&c!=-1)
			;
		if(c==-1){
			return null;
		}
		while(c!='\n'){
			str.append((char)c);
			c=nextChar();
		}
		return str.toString();
	}
}

