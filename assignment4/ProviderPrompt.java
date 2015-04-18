import java.util.Scanner;

public class ProviderPrompt {
	
	public static void provider() {
		System.out.println("Enter ID: ");
		Scanner reader = new Scanner(System.in);
		String s = reader.next();
		System.out.println(s);
	
		if (s.length() != 9) {
			System.out.println("Invalid ID");
		}
		else {
			System.out.println("Select reason for using ChocAn:");
			System.out.println("Type 1 for Accessing Provider Directory, type 2 for Bill ChocAn for Service or type 3 for Request Report: ");
			Scanner choice = new Scanner(System.in);
			int i = choice.nextInt();
			if (i == 1) {
				//ProviderDirectory();
			}
			if (i == 2) {
				//BillChocAN();
			}
			if (i == 3) {
				//ProviderReport();
			}
			else {
				System.out.println("Sorry, not a correct number entered :(");
			}
		}
	}
}
