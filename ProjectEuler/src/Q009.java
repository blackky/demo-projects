import java.util.Scanner;

public class Q009 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
			long n= sc.nextLong();
			long ans = -1;
			for(long a=1;a<=n/3;a++){
				long b=((n-a)+((a*a)/(a-n)))/2;
                long c= ((n-a)-((a*a)/(a-n)))/2;
				long sqAB = (a*a)+(b*b);
				if(sqAB == (c*c) && b>0 && c>0){
					long p=a*b*c;
					if(p>ans){
						ans=p;
					}
				}
			}
			System.out.println(ans);
		}

	}

}
