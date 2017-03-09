import java.util.Scanner;

public class Q006 {

	public Q006() {
		// TODO Auto-generated constructor stub
	}

	 public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);
	        long t = sc.nextLong();
	        while(t-->0){
	        	long n = sc.nextLong();
	        	long sum=0;
		        long sumOfSqr =0;
		        for (long i = 1; i <= n; i++) {
		            sum+=i;
		            sumOfSqr+=(i*i);            
		        }

		        long sumSqr = sum*sum;
		        
		        System.out.println(sumSqr-sumOfSqr);
	        }

	    }

}
