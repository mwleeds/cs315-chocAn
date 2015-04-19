package chocAn;

import java.io.*;
import java.util.Scanner;

/**
 *Allows manager to write out a report file.
 * @author Breanna Tucker
 * @author Matthew Leeds
 *
 */
public class ManagerPrompt extends PromptController {

	public static void manager(Scanner input) {
		String filename = prompt(input, "Enter report file name?");
		File ReportFile = new File(filename);
		if (ReportFile.exists()) {
			String response = prompt(input, "File exists. Overwrite? [Y/n]");
			if (response.substring(0,0).toUpperCase() == "N")
				return;
		} else {
			// instantiate w.e kind of report you would like
		}
	}
}
