import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q150 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] array = new int[n][];
		int[][] rsum = new int[n][];
		int k = sc.nextInt();
		sc.nextLine();
		int count = 0;
		while (count < n) {
			int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			array[count] = new int[count + 1];
			System.arraycopy(arr, 0, array[count], 0, arr.length);
			count++;
		}

		for (int i = 0; i < rsum.length; i++) {
			rsum[i] = new int[array[i].length + 1];
			for (int j = 0; j <= i; j++)
				rsum[i][j + 1] = rsum[i][j] + array[i][j];
		}

		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				long sum = 0;
				for (int x = i; x < array.length; x++) {
					sum = sum + rsum[x][x + j - i + 1] - rsum[x][j];
					list.add(sum);
				}
			}
		}
		Collections.sort(list);
		for(int i=0;i<k;i++){
			System.out.println(list.get(i));
		}
		sc.close();
		
		
	}

}
