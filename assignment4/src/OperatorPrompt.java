import database.Member;

public class OperatorPrompt extends Prompter{

	public void run() {
		
		goForward("Operator");
		
		//Choose what database to manage
		String choice = " ";
		while (!choice.equals("4")) {
			choice = prompt("Enter a number to choose what operation to perform\n"
        		+"1. Manage Member Database\n2. Manage Provider Database\n"
        		+"3. Manage Provider Directory\n4. To go back");
	        switch (choice) {
	            case "1":
	                goForward("Manage Member Database");
	                manageMembers();
	                break;
	            case "2":
	            	goForward("Manage Provider Database");
	                //TODO
	                break;
                case "3":
                    //TODO manage provider directory
                    break;
                case "4":
                    break;
	            default:
	                System.out.println("Invalid selection");
	                break;
	        }
        }
        goBack();
	}
	
	public void manageMembers(){
		String choice = " ";
		while (!choice.equals("")){
			choice = prompt("Choose an action\n1. Add a new member\n2. Get a member's information\n"
					+"3. Update a members information\n4. Remove a member\n5. To go back");
			switch (choice){
			case "1":
				goForward("New Member");
				
				//Get the properties of the new member
				String name = prompt("Name:");
				String status = prompt("Status:");
				String street = prompt("Address Street:");
				String city = prompt("Address City:");
				String zip = prompt("Address Zip Code:");
				String state = prompt("Address State:");
				
				//Add the new member to the member database
				Member newMember = new Member(name,status,street,city,zip,state);
				ChocAnMain.memberDatabase.addEntry(newMember);
				
				//Output that the member has been created
				try{
					System.out.println("The new member has been created.\nThe member id is "+newMember.getId());}
				catch (Exception e) {}
				break;
			case "2":
				goForward("Get Member");
				
				//Get the member with the specified ID
				int id = Integer.parseInt(prompt("Member ID:"));
				Member getMember = ChocAnMain.memberDatabase.getEntry(id);
				if (getMember == null){
					System.out.println("The member with the specified ID does not exist");
					break;
				}
				
				//Print out the member's properties
				System.out.println("Name:\t\t"+getMember.getName());
				System.out.println("Status:\t\t"+getMember.getStatus());
				System.out.println("Street:\t\t"+getMember.getAddressStreet());
				System.out.println("City:\t\t"+getMember.getAddressCity());
				System.out.println("Zip Code:\t"+getMember.getAddressZipCode());
				System.out.println("State:\t\t"+getMember.getAddressState());
				
				break;
			case "3":
				goForward("Update Member");
				
				//Get the member with the specified ID
				int id2 = Integer.parseInt(prompt("Member ID:"));
				Member updateMember = ChocAnMain.memberDatabase.getEntry(id2);
				if (updateMember == null){
					System.out.println("The member with the specified ID does not exist");
					break;
				}
				
				//Loop and allow the operator to update the values until they decide to go back
				String property = "";
				while (!property.equals("7")){
					property = prompt("What would you like to update?\n1. Name\n2. Status\n3. Street\n"
						+"4. City\n5. Zip Code\n6. State\n7. To go back");
					switch (property){
					case "1":
						updateMember.setName(prompt("New name:"));
						System.out.println("Name updated");
						break;
					case "2":
						updateMember.setStatus(prompt("New status:"));
						System.out.println("Status updated");
						break;
					case "3":
						updateMember.setAddressStreet(prompt("New street:"));
						System.out.println("Street updated");
						break;
					case "4":
						updateMember.setAddressCity(prompt("New city:"));
						System.out.println("City updated");
						break;
					case "5":
						updateMember.setAddressZipCode(prompt("New zip code:"));
						System.out.println("Zip code updated");
						break;
					case "6":
						updateMember.setAddressState(prompt("New state:"));
						System.out.println("State updated");
						break;
					case "7":
						break;
					default:
						System.out.println("Invalid selection");
					}
				}
				ChocAnMain.memberDatabase.updateEntry(updateMember);
				break;
			case "4":
				goForward("Remove Member");
				
				//Get the member with the specified ID
				int memberid = Integer.parseInt(prompt("Member ID:"));
				Member removeMember = ChocAnMain.memberDatabase.getEntry(memberid);
				if (removeMember == null){
					System.out.println("The member with the specified ID does not exist");
					break;
				}
				
				//Delete member if the user confirms
				String conformation = "";
				while (conformation.equals("")){
					conformation = prompt("Are you sure?\n1. Yes\n2. No");
					switch (conformation){
						case "1":
							ChocAnMain.memberDatabase.removeEntry(removeMember);
							System.out.println("The member was deleted");
							break;
						case "2":
							System.out.println("The member was not deleted");
							break;
						default:
							System.out.println("Invalid selection");
							conformation = "";
							break;}				
				}
				break;
			case "5":
				choice = "";
				break;
			default:
				System.out.println("Invalid selection");
				break;
			}
			goBack();
		}
	}
}
