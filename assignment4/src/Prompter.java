import java.util.Scanner;
/**
 * Parent class for all the prompt controllers.
 * @author breannatucker
 *
 */
public abstract class Prompter {
	
	private String promptPath = "\n";
	
	/**
	 * Prompts the user with a message and returns the response from the message
	 * @param promptString	the string to prompt the user to respond to
	 * @return				the response from the message
	 */
	public static String prompt(String promptString) {
		try{
			System.out.println(promptString);
			String response = ChocAnMain.input.next();
			Thread.sleep(5);
			return response;}
		catch (Exception e){ }
		return null;
	}
	
	/**
	 * Goes further down a level into the next path and prints the path
	 * @param path	the name of the next level
	 */
	public void goForward(String path){
		promptPath += "> "+path;
		System.out.println(promptPath);
	}
	
	/**
	 * Goes back one level to the previous path
	 */
	public void goBack(){
		promptPath = promptPath.substring(0, promptPath.lastIndexOf(">"));
		System.out.println(promptPath);
	}
	
	
	/**
	 * Runs the logic for the prompter
	 */
	public abstract void run();
}
