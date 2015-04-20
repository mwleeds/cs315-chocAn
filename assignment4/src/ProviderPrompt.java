import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import database.*;

/**
 * @author Miranda Hardy
 * @author Matthew Leeds
 */
public class ProviderPrompt extends Prompter {
	
	public void run() {
        String id = prompt("Enter ID: ");
		if (id.length() != 9) {
			System.out.println("Invalid ID");
            return;
		}
        Provider thisProvider;
        try {
            thisProvider = ChocAnMain.providerDatabase.getEntry(Integer.parseInt(id));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid ID");
            return;
        }
        String choice = prompt("1. Access Provider Directory\n2. Bill ChocAn for Service\n3. Request Report\nEnter choice: ");
        switch (choice) {
            case "1":
                ArrayList<Service> services = ChocAnMain.providerDirectoryDatabase.getEntryList();
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
                String filename = prompt("Enter report file name?");
                File reportFile = new File(filename);
                if (reportFile.exists()) {
                    String response = prompt("File exists. Overwrite? [Y/n]");
                    if (response.substring(0,0).toUpperCase() == "N")
                        return;
                    else 
                        reportFile.delete();
                }
                try {
                    reportFile.createNewFile();
                    ProviderReport pReport = new ProviderReport(reportFile, thisProvider);
                    pReport.generateReport();
                } catch (IOException e) {
                    System.out.println("Error writing to file " + filename);
                    System.out.println(e.getMessage());
                    return;
                }
                break;
            default:
                System.out.println("Sorry, not a correct number entered :(");
                break;
        }
	}
}
