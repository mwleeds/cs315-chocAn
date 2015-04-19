package chocAn;

import java.io.*;
import java.util.Scanner;

/**
 *Allows members to write out a report file.
 * @author mleeds95
 *
 */
public class MemberPrompt extends PromptController {

	public static void member(Scanner input) {
        System.out.println("Enter Member ID: ");
        int id = input.nextInt();
        Member m = MemberDatabase.getMember(id);  
        if (m == null) {
            System.out.println("No member found for ID " + id);
            return;
        }
		String filename = prompt(input, "Enter report file name?");
		File ReportFile = new File(filename);
		if (ReportFile.exists()) {
			String response = prompt(input, "File exists. Overwrite? [Y/n]");
			if (response.substring(0,0).toUpperCase() == "N")
				return;
		} else {
            MemberReport m = new MemberReport(filename, id);
            m.generateReport();
		}
	}
}
