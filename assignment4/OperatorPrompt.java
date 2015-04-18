import java.util.Scanner;

public class OperatorPrompt {

	public static void operator() {
		//System.out.println("Enter ID: ");
		//Scanner reader = new Scanner(System.in);
		//String s = reader.next();
		//int i = reader.nextInt();
		//System.out.println(s);
		//System.out.println(i);
		//if (s.length() != 9) {
			//System.out.println("Invalid ID");
		//}
		//else {
			System.out.println("Select which database to access:");
			System.out.println("Type 1 for Member Database or type 2 for Provider: ");
			Scanner choice = new Scanner(System.in);
			int i = choice.nextInt();
			if (i==1) {
				//MemberDatabase();
			}
			if (i==2) {
				//ProviderDatabase();
			}
			else {
				System.out.println("Sorry, not a correct number entered :(");
			}
		//}
	}
}
