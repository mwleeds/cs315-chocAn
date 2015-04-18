import java.util.Scanner;

public class PromptPick {

	public static void main(String[] args) {
		System.out.println("Welcome to ChocAn!");
		System.out.println("Select who you are:");
		System.out.println("Type 1 for Provider, type 2 for Operator, type 3 for Manager, or type 4 for Member.");
		Scanner person = new Scanner(System.in);
		int i = person.nextInt();
		if (i == 1) {
			ProviderPrompt.provider();
		}
		if (i == 2) {
			OperatorPrompt.operator();
		}
		if (i == 3) {
			ManagerPrompt.manager();
		}
		if (i == 4) {
			MemberPrompt.member();
		}
		else {
			System.out.println("Sorry, not a correct number entered :(");
		}
	}
}
