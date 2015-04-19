package chocAn;

import java.util.Scanner;

public class OperatorPrompt extends PromptController {

	public static void operator(Scanner input) {
        String id = prompt(input, "Enter ID: ");
		if (id.length() != 9) {
			System.out.println("Invalid ID");
            return;
		}
        String choice = prompt(input, "1. Member Database\n2. Provider Database\nEnter choice: ");
        switch (choice) {
            case "1":
                System.out.println("Member Database");
                //TODO MemberDatabase();
                break;
            case "2":
                System.out.println("Provider Database");
                //TODO ProviderDatabase();
                break;
            default:
                System.out.println("Sorry, not a correct number entered :(");
                break;
        }
	}
}
