import java.util.Scanner;

public class Q005 {

	public Q005() {
		// TODO Auto-generated constructor stub
	}
	
	private static int gcd(int a, int b){
	    if (a<b) return gcd(b,a);
	    if (a%b==0) return b;
	    else return gcd(b, a%b);
	}
	
	private static int lcm(int a, int b){
	    return ((a*b)/gcd(a,b));

	}
	
	private static int lcm(int[] arr, int start, int end){
		if ((end-start)==1) return lcm(arr[start],arr[end-1]);
	    return (lcm (arr[start], lcm(arr, start+1, end)));
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
			int n = sc.nextInt();
			int[] a = new int[n];
			for(int i=0;i<n;i++){
				a[i]=i+1;
			System.out.print(a[i]+",");	
			}
			System.out.println("\n"+lcm(a, 0, a.length));
		}
		sc.close();
	}

}
