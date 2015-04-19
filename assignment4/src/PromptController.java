import java.util.Scanner;
/**
 * Parent class for all the prompt controllers.
 * @author breannatucker
 *
 */
public abstract class PromptController {
	public static String prompt(Scanner input, String promptString) {
		System.out.println(promptString);
		String response = input.next();
		return response;
	}
}
