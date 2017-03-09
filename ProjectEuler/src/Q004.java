import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Q004 {

	
	private static TreeSet<Integer> set = new TreeSet<Integer>();
	
	Q004(){
		for ( int i = 999 ; i >= 100 ; i--) {
	        for (int j = 999 ; j >=100 ; j-- ) {
	            set.add(i*j);
	        }
	    }
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long t = sc.nextLong();
		while (t-- > 0) {
			int max = sc.nextInt();
			System.out.println(set.lower(max));
		}
	}

	public static boolean isPalindrome(long n) {
		String str = "" + n;
		String reverse = new StringBuilder(str).reverse().toString();
		return str.equals(reverse);
	}
}
