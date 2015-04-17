import java.util.Scanner;
/**
 * Parent class for all the prompt controllers.
 * @author breannatucker
 *
 */
public class PromptController {
	public String prompt(String promptString){
		System.out.println(promptString);
		Scanner user_input = new Scanner(System.in);
		String response = user_input.next();
		user_input.close();
		return response;
	}
}
