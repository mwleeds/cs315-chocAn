import java.io.*;
import java.util.Scanner;

/**
 *Allows manager to write out a report file.
 * @author Breanna Tucker
 * @author Matthew Leeds
 *
 */
public class ManagerPrompt extends Prompter {

	public void run() {
		String filename = prompt("Enter report file name?");
		File ReportFile = new File(filename);
		if (ReportFile.exists()) {
			String response = prompt("File exists. Overwrite? [Y/n]");
			if (response.substring(0,0).toUpperCase() == "N")
				return;
		} else {
			// instantiate w.e kind of report you would like
		}
	}
}
