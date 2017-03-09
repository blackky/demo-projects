import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q007 {

	private static ArrayList<Long> primes = new ArrayList<Long>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long t = sc.nextLong();
		while(t-->0){
			getPrime(sc.nextLong());
		}
		sc.close();
		

	}
	
	private static boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	    return true;
	}
	
	private static void getPrime(long n){
		long count = 0;
		if(primes.size()==0){
			count=2;
		}else{
			count = primes.get(primes.size()-1)+1;
		}
		while(primes.size()<n){
				if(isPrime(count)){
					primes.add(count);
				}
				count++;
		}
		
		System.out.println(primes.get((int)n-1));
	}
}
