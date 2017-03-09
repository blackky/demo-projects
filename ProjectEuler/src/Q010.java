import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Q010 {
	private static long[] sums = new long[1000001];
	private static boolean[] compArr;

	private static void getSum(int x) {
		long sum = 0;
		
		Long.parseLong(Integer.toString(1000000,2));
		
		if (!compArr[x]) {
			sum += x;
		}
		sum+=sums[x-1];
		sums[x] = sum;
	}

	private static void fillPrimes(long max) {
		compArr = new boolean[(int) max + 1];
		for (int i = 2; i <= Math.sqrt(max); i++) {
			if (!compArr[i]) {
				for (int j = i; i * j <= max; j++) {
					compArr[i * j] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		fillPrimes(1000000);
		for (int x = 2; x <= 1000000; x++) {
			getSum(x);
		}
		long t = sc.nextLong();
		while (t-- > 0) {
			int n = sc.nextInt();
			System.out.println(sums[n]);
		}
	}
}
