import java.io.*;
import java.util.Scanner;
import java.lang.Math;
import database.*;

/**
 *Allows members to write out a report file.
 * @author Matthew Leeds
 *
 */
public class MemberPrompt extends Prompter {

	public void run() {
        String id = prompt("Enter Member ID: ");
        if (id.length() != 9) {
            System.out.println("Error: Member IDs must be 9 digits long!");
            System.out.println("Press enter to continue");
            try { System.in.read(); } catch (IOException e) {}
            return;
        }
        Member thisMember = ChocAnMain.memberDatabase.getEntry(Integer.parseInt(id));
        if (thisMember == null) {
            System.out.println("No member found for ID " + id);
            System.out.println("Press enter to continue");
            try { System.in.read(); } catch (IOException f) {}
            return;
        }
		String filename = prompt("Enter report file name?");
		File reportFile = new File(filename);
		if (reportFile.exists()) {
			String response = prompt("File exists. Overwrite? [Y/n]");
			if (response.substring(0,1).toUpperCase().equals("N"))
				return;
            else 
                reportFile.delete();
		}
        try {
            reportFile.createNewFile();
            MemberReport mReport = new MemberReport(reportFile, thisMember);
            mReport.generateReport();
        } catch (IOException e) {
            System.out.println("Error writing to file " + filename);
            System.out.println(e.getMessage());
            System.out.println("Press enter to continue");
            try { System.in.read(); } catch (IOException g) {}
            return;
        }
        System.out.println("Report successfully written to the disk. Press enter to continue");
        try { System.in.read(); } catch (IOException h) {}
        return;
	}
}
