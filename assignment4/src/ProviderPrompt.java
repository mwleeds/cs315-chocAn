import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import database.*;

/**
 * @author Miranda Hardy
 * @author Matthew Leeds
 */
public class ProviderPrompt extends Prompter {
	
	public void run() {
		
        goForward("Provider");
        String id = prompt("Enter ID: ");
		if (id.length() != 9) {
			System.out.println("Invalid ID");
            System.out.println("Press enter to continue");
            try { System.in.read(); } catch (IOException e) {}
            return;
		}
        Provider thisProvider = ChocAnMain.providerDatabase.getEntry(Integer.parseInt(id));
        if (thisProvider == null ){
            System.out.println("Invalid ID");
            System.out.println("Press enter to continue");
            try { System.in.read(); } catch (IOException e) {}
            return;
        }
        String choice = "";
        while (!choice.equals("4")) {
            choice = prompt("Enter a number to choose what operation to perform\n1. Access Provider Directory\n2. Bill ChocAn for Service\n3. Request Report\n4. To go back");
            switch (choice) {
                case "1":
                    goForward("Access Provider Directory");
                    ArrayList<Service> services = ChocAnMain.providerDirectoryDatabase.getEntryList();
                    for (int i = 0; i < services.size(); ++i) {
                        Service s = services.get(i);
                        System.out.println("Name: " + s.getName());
                        try { System.out.println("Number: " + s.getId()); } catch (Exception e) {}
                        System.out.println("Fee: " + s.getFee());
                    }
                    System.out.println("Press enter to continue");
                    try { System.in.read(); } catch (IOException e) {}
                    break;
                case "2":
                    goForward("Bill ChocAn for Service");
                    String memberId = prompt("Enter Member ID: ");
                    if (memberId.length() != 9) {
                        System.out.println("Error: Member IDs must be 9 digits long!");
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException e) {}
                        break;
                    }
                    Member thisMember = ChocAnMain.memberDatabase.getEntry(Integer.parseInt(memberId));
                    if (thisMember == null) {
                        System.out.println("No member found for ID " + memberId);
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException e) {}
                        break;
                    }
                    String strDate = prompt("Enter date service provided (MM-DD-YYYY): ");
                    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                    Date dateProvided;
                    try { 
                        dateProvided = dateFormat.parse(strDate);
                    } catch (ParseException e) { 
                        System.out.println("Error parsing date " + strDate);
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException f) {}
                        break;
                    }
                    int serviceId = Integer.parseInt(prompt("Enter service ID: "));
                    Service thisService = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                    if (thisService == null) {
                        System.out.println("Error: Invalid service ID provided.");
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException g) {}
                        break;
                    }
                    System.out.println("Provided service: " + thisService.getName());
                    String confirm = prompt("Is this correct? [Y/n]");
                    if (confirm.substring(0,0).toUpperCase().equals("N")) break;
                    String comments = "";
                    comments = prompt("Comments (optional): ");
                    ProvidedService pS = new ProvidedService(dateProvided, Integer.parseInt(memberId), Integer.parseInt(id), serviceId, comments);
                    ChocAnMain.providedServiceDatabase.addEntry(pS);

                    System.out.println("Record successfully written to the database");
                   

                    System.out.println("Record successfully written to the database. Press enter to continue");
                    try { System.in.read(); } catch (IOException h) {}
                    break;
                case "3":
                    goForward("Request Report");
                    String filename = prompt("Enter report file name?");
                    File reportFile = new File(filename);
                    if (reportFile.exists()) {
                        String response = prompt("File exists. Overwrite? [Y/n]");
                        if (response.substring(0,0).toUpperCase().equals("N"))
                            break;
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
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException i) {}
                        break;
                    }

                    System.out.println("Record successfully written to the database. Press enter to continue");
                    try { System.in.read(); } catch (IOException j) {}
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Invalid selection");
                    goForward("");
                    break;
            }
            goBack();
        } // end while
        goBack();
	}
}
