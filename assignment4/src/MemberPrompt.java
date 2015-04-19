import java.io.*;
import java.util.Scanner;
import java.lang.Math;

/**
 *Allows members to write out a report file.
 * @author Matthew Leeds
 *
 */
public class MemberPrompt extends PromptController {

	public static void member(Scanner input) {
        System.out.println("Enter Member ID: ");
        int id = input.nextInt();
        if ((int)Math.ceil(Math.log10(id)) != 9) {
            System.out.println("Error: Member IDs must be 9 digits long!");
            return;
        }
        /*Member thisMember;
        try {
            thisMember = MemberDatabase.getMember(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if (thisMember == null) {
            System.out.println("No member found for ID " + id);
            return;
        }
		String filename = prompt(input, "Enter report file name?");
		File reportFile = new File(filename);
		if (reportFile.exists()) {
			String response = prompt(input, "File exists. Overwrite? [Y/n]");
			if (response.substring(0,0).toUpperCase() == "N")
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
            return;
        }*/
	}
}
