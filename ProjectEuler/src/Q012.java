import java.util.HashMap;
import java.util.Map;

public class Q012 {

	private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	private static boolean isPrime(long n) {
		if (n < 2)
			return false;
		if (n == 2 || n == 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		long sqrtN = (long) Math.sqrt(n) + 1;
		for (long i = 6L; i <= sqrtN; i += 6) {
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		}
		return true;
	}

	private static void getFactorCount(int n) {
		if (n == 1) {
			map.put(1, 1);
			return;
		}
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (isPrime(i)) {
					int t = map.get(n)+1;
					map.put(n, t);
				}
			}
		}
		int t = map.get(n)+2;
		map.put(n, t);
	}

	public static void main(String[] args) {

		for (int i = 1; i <= 1000; i++) {
			int val = (i * (i + 1)) / 2;
			map.put(28, 0);
			getFactorCount(28);
		}
		for(int k: map.keySet()){
			System.out.println(map.get(k)+" "+k);
		}

	}

}
