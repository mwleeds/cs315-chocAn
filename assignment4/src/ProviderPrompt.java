
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Miranda Hardy
 * @author Matthew Leeds
 */
public class ProviderPrompt extends Prompter {
	
	public void run() {
		/*
        String id = prompt(input, "Enter ID: ");
		if (id.length() != 9) {
			System.out.println("Invalid ID");
            return;
		}
        try {
            Main.providerDatabase.getEntry(Integer.parseInt(id));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid ID");
            return;
        }
        String choice = prompt(input, "1. Access Provider Directory\n2. Bill ChocAn for Service\n3. Request Report\nEnter choice: ");
        switch (choice) {
            case "1":
                ArrayList<Service> services = Main.providerDirectoryDatabase.getEntryList();
                System.out.println("Provider Directory");
                for (int i = 0; i < services.size(); ++i) {
                    Service s = services.get(i);
                    System.out.println("Name: " + s.getName());
                    try { System.out.println("Number: " + s.getId()); } catch (Exception e) {}
                    System.out.println("Fee: " + s.getFee());
                }
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
        */
	}
}
