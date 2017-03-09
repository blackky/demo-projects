import java.util.Scanner;

public class Q003 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		long t = sc.nextLong();
		while(t-->0){
			long n = sc.nextInt(),  maxFact = 0;
			long temp = n;
			int counter = 2;
			while (counter * counter <= temp) {
			    if (temp % counter == 0) {
			        temp = temp / counter;
			        maxFact = counter;
			    } else {
			        counter++;
			    }
			}
			if (temp > maxFact) {
			    maxFact = temp;
			}
			System.out.println(maxFact);
		}
		sc.close();
	}

}
