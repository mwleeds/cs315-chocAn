import java.io.*;
import java.util.Scanner;

/**
 * @author mkhardy17
 * @author mleeds95
 */
public class ProviderPrompt extends PromptController {
	
	public void provider(Scanner input) {
        String id = prompt(input, "Enter ID: ");
		if (id.length() != 9) {
			System.out.println("Invalid ID");
            return;
		}
        String choice = prompt(input, "1. Access Provider Directory\n2. Bill ChocAn for Service\n3. Request Report\nEnter choice: ");
        switch (choice) {
            case "1":
                System.out.println("Member Database");
                //TODO
                break;
            case "2":
                System.out.println("Provider Database");
                //TODO
                break;
            case "3":
                System.out.println("Provider Report");
                //TODO
                break;
            default:
                System.out.println("Sorry, not a correct number entered :(");
                break;
        }
	}
}
