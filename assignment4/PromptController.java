import java.util.Scanner;
/**
 * Parent class for all the prompt controllers.
 * @author breannatucker
 *
 */
public class PromptController {
	public String prompt(Scanner input, String promptString) {
		System.out.println(promptString);
		String response = input.next();
		return response;
	}
}
