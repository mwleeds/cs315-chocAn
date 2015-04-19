package chocAn;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to ChocAn!");
		System.out.println("1. Provider\n2. Operator\n3. Manager\n4. Member\nEnter choice: ");
		Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                ProviderPrompt.provider(input);
                break;
            case 2:
                OperatorPrompt.operator(input);
                break;
            case 3:
                ManagerPrompt.manager(input);
                break;
            case 4:
                //MemberPrompt.member(input);
                break;
            default:
                System.out.println("Sorry, not a correct number entered :(");
                break;
		}
	}
}
