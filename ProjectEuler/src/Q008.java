import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Q008 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		while(t-->0){
			sc.nextInt();
			int k = sc.nextInt();
			int[] arr =  Arrays.stream(sc.next().split("")).mapToInt(Integer::parseInt).toArray();
			ArrayList<Long> set = new ArrayList<Long>();
			for(int i=0;i<arr.length-k;i++){
				int temp=k;
				int pointer =i;
				long p=1;
				while(temp-->0){
					p*=arr[pointer];
					pointer++;
				}
				set.add(p);
			}
			Collections.sort(set);
			System.out.println(set.get(set.size()-1));
		}

	}

}
