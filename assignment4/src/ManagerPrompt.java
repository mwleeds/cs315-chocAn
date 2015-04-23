
import java.io.*;
import java.util.Scanner;

/**
 * Allows managers to write out a report file.
 * @author Breanna Tucker
 * @author Matthew Leeds
 */
public class ManagerPrompt extends Prompter {

	public void run() {
		String filename = prompt("Enter report file name?");
		File reportFile = new File(filename);
		if (reportFile.exists()) {
			String response = prompt("File exists. Overwrite? [Y/n]");
			if (response.substring(0,1).toUpperCase().equals("N"))
				return;
            else
                reportFile.delete();
		}
        SummaryReport sReport;
        try {
            reportFile.createNewFile();
            sReport = new SummaryReport(reportFile);
            sReport.generateReport();
        } catch (IOException e) {
            System.out.println("Error writing to file " + filename);
            System.out.println(e.getMessage());
            return;
        }
		filename = prompt("Enter EFT file name?");
		File eftFile = new File(filename);
		if (eftFile.exists()) {
			String response = prompt("File exists. Overwrite? [Y/n]");
			if (response.substring(0,1).toUpperCase().equals("N"))
				return;
            else
                eftFile.delete();
		}
        try {
            eftFile.createNewFile();
            sReport.generateEFTReport(eftFile);
        } catch (IOException e) {
            System.out.println("Erorr writing to file " + filename);
            System.out.println(e.getMessage());
        }
	}
}
