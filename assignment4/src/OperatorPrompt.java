import java.util.Scanner;

public class OperatorPrompt extends Prompter{

	public void run() {
		
		goForward("Operator");
		
		//Choose what database to manage
        String choice = prompt("Enter a number to choose what operation to perform\n"
        		+"1. Manage Member Database\n2. Manage Provider Database\n"
        		+ "3. Manage Provider Directory Enter choice: ");
        switch (choice) {
            case "1":
                goForward("Manage Member Database");
                //TODO MemberDatabase();
                
                break;
            case "2":
            	goForward("Manage Provider Database");
                //TODO ProviderDatabase();
                break;
            default:
                System.out.println("Sorry, not a correct number entered :(");
                break;
        }
	}
}
