import database.*;
import java.io.IOException;
/**
 * 
 * @author Ryan Mitchell
 *
 */
public class OperatorPrompt extends Prompter{
	
	//The different type of databases
	public enum EntryType{
		MEMBER, PROVIDER, SERVICE
	}

	public void run() {
		
		goForward("Operator");
		
		//Choose what database to manage
		String choice = "";
		while (!choice.equals("4")) {
			choice = prompt("Enter a number to choose what operation to perform\n"
        		+"1. Manage Member Database\n2. Manage Provider Database\n"
        		+"3. Manage Provider Directory\n4. To go back");
	        switch (choice) {
	            case "1":
	                goForward("Manage Member Database");
	                manageEntries(EntryType.MEMBER);
	                break;
	            case "2":
	            	goForward("Manage Provider Database");
	            	manageEntries(EntryType.PROVIDER);
	                break;
                case "3":
                	goForward("Manage Provider Directory Database");
                	manageEntries(EntryType.SERVICE);
                    break;
                case "4":
                    break;
	            default:
	                System.out.println("Invalid selection");
                    System.out.println("Press enter to continue");
                    try { System.in.read(); } catch (IOException f) {}
	                break;
	        }
        }
        goBack();
	}
	
	public void manageEntries(EntryType entry){
		
		//Get the string for each type of entry
		String type = "", typeLower = "";
		switch (entry){
		case MEMBER:
			type  = "Member";
			break;
		case PROVIDER:
			type = "Provider";
			break;
		case SERVICE:
			type = "Service";
			break;
		}
		typeLower = type.toLowerCase();
		
		//Choose what operation to perform
		String choice = " ";
		while (!choice.equals("")){
			choice = prompt(String.format("Choose an action\n1. Add a new "+typeLower+"\n2. Get an "+typeLower+"'s information\n"
					+"3. Update an "+typeLower+"'s information\n4. Remove an "+typeLower+"\n5. To go back"));
			switch (choice){
			case "1":
				goForward("New "+type);
				
				int id = 0;
				if (entry == EntryType.MEMBER){
					//Get the properties of the new member
					String name = prompt("Name:", 25);
					String status = prompt("Status:");
					String street = prompt("Address Street:", 25);
					String city = prompt("Address City:", 14);
					String zip = prompt("Address Zip Code:", 5);
					String state = prompt("Address State:", 2);
					
					//Add the member to the database
					Member newMember = new Member(name,status,street,city,zip,state);
					id = ChocAnMain.memberDatabase.addEntry(newMember);}
				
				else if (entry == EntryType.PROVIDER){
					//Get the properties for the provider
					String name = prompt("Name:", 25);
					String street = prompt("Address Street:", 25);
					String city = prompt("Address City:", 14);
					String zip = prompt("Address Zip Code:", 5);
					String state = prompt("Address State:", 2);
					
					//Add the provider to the database
					Provider newProvider = new Provider(name,street,city,zip,state);
					id = ChocAnMain.providerDatabase.addEntry(newProvider);}
				
				else if (entry == EntryType.SERVICE){
					//Get the properties for the service
					String name = prompt("Name:", 20);
					Double fee = null;
					while (fee == null){
						try {
							fee = Double.parseDouble(prompt("Fee:"));
                        } catch (Exception e) {
							fee = null;
							System.out.print("Invalid Fee");
                        }
                    }
					
					//Add the service to the database
					Service newService = new Service(name,fee);
					id = ChocAnMain.providerDirectoryDatabase.addEntry(newService);}
				
				//Output that the entry has been created
				System.out.println("The new entry has been created. It's id is "+id);
                System.out.println("Press enter to continue");
                try { System.in.read(); } catch (IOException e) {}
				break;
			case "2":
				goForward("Get "+type);
				
				int idGet = Integer.parseInt(prompt(type+" ID:"));
				
				//Print out the member's properties
				if (entry == EntryType.MEMBER){	
					
					//Get the member with the specified ID
					Member getMember = ChocAnMain.memberDatabase.getEntry(idGet);
					if (getMember == null){
						System.out.println("The member with the specified ID does not exist");
						break;
					}
					//Print out the member properties
					System.out.println("Name:\t\t"+getMember.getName());
					System.out.println("Status:\t\t"+getMember.getStatus());
					System.out.println("Street:\t\t"+getMember.getAddressStreet());
					System.out.println("City:\t\t"+getMember.getAddressCity());
					System.out.println("Zip Code:\t"+getMember.getAddressZipCode());
					System.out.println("State:\t\t"+getMember.getAddressState());}
				else if (entry == EntryType.PROVIDER){
					
					//Get the provider with the specified ID
					Provider getProvider = ChocAnMain.providerDatabase.getEntry(idGet);
					if (getProvider == null){
						System.out.println("The provider with the specified ID does not exist");
						break;
					}
					//Print out the provider properties
					System.out.println("Name:\t\t"+getProvider.getName());
					System.out.println("Street:\t\t"+getProvider.getAddressStreet());
					System.out.println("City:\t\t"+getProvider.getAddressCity());
					System.out.println("Zip Code:\t"+getProvider.getAddressZipCode());
					System.out.println("State:\t\t"+getProvider.getAddressState());}
				else if (entry == EntryType.SERVICE){
					
					//Get the member with the specified ID
					Service getService = ChocAnMain.providerDirectoryDatabase.getEntry(idGet);
					if (getService == null){
						System.out.println("The service with the specified ID does not exist");
						break;
					}
					//Print out the service properties
					System.out.println("Name:\t\t"+getService.getName());
					System.out.println("Fee:\t\t"+getService.getFee());}
				
                System.out.println("Press enter to continue");
                try { System.in.read(); } catch (IOException e) {}
				break;
			case "3":
				goForward("Update "+type);
				
				//Get the member with the specified ID
				int id2 = Integer.parseInt(prompt(type+" ID:"));
				if (entry == EntryType.MEMBER){
					Member updateMember = ChocAnMain.memberDatabase.getEntry(id2);
					if (updateMember == null){
						System.out.println("The member with the specified ID does not exist");
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException e) {}
						break;
					}
					
					//Loop and allow the operator to update the values until they decide to go back
					String property = "";
					while (!property.equals("7")){
						property = prompt("What would you like to update?\n1. Name\n2. Status\n3. Street\n"
							+"4. City\n5. Zip Code\n6. State\n7. To go back");
						switch (property){
						case "1":
							updateMember.setName(prompt("New name:", 25));
							System.out.println("Name updated");
							break;
						case "2":
							updateMember.setStatus(prompt("New status:"));
							System.out.println("Status updated");
							break;
						case "3":
							updateMember.setAddressStreet(prompt("New street:", 25));
							System.out.println("Street updated");
							break;
						case "4":
							updateMember.setAddressCity(prompt("New city:", 14));
							System.out.println("City updated");
							break;
						case "5":
							updateMember.setAddressZipCode(prompt("New zip code:", 5));
							System.out.println("Zip code updated");
							break;
						case "6":
							updateMember.setAddressState(prompt("New state:", 2));
							System.out.println("State updated");
							break;
						case "7":
							break;
						default:
							System.out.println("Invalid selection");
						}
					}
					ChocAnMain.memberDatabase.updateEntry(updateMember);
                } else if (entry == EntryType.PROVIDER){
					Provider updateProvider = ChocAnMain.providerDatabase.getEntry(id2);
					if (updateProvider == null){
						System.out.println("The provider with the specified ID does not exist");
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException e) {}
						break;
					}
					
					//Loop and allow the operator to update the values until they decide to go back
					String property = "";
					while (!property.equals("6")){
						property = prompt("What would you like to update?\n1. Name\n2. Street\n"
							+"3. City\n4. Zip Code\n5. State\n6. To go back");
						switch (property){
						case "1":
							updateProvider.setName(prompt("New name:", 25));
							System.out.println("Name updated");
							break;
						case "2":
							updateProvider.setAddressStreet(prompt("New street:", 25));
							System.out.println("Street updated");
							break;
						case "3":
							updateProvider.setAddressCity(prompt("New city:", 14));
							System.out.println("City updated");
							break;
						case "4":
							updateProvider.setAddressZipCode(prompt("New zip code:", 5));
							System.out.println("Zip code updated");
							break;
						case "5":
							updateProvider.setAddressState(prompt("New state:", 2));
							System.out.println("State updated");
							break;
						case "6":
							break;
						default:
							System.out.println("Invalid selection");
						}
					}
					ChocAnMain.providerDatabase.updateEntry(updateProvider);
				} else if (entry == EntryType.SERVICE) {
					Service updateService = ChocAnMain.providerDirectoryDatabase.getEntry(id2);
					if (updateService == null){
						System.out.println("The service with the specified ID does not exist");
                        System.out.println("Press enter to continue");
                        try { System.in.read(); } catch (IOException e) {}
						break;
					}
					
					//Loop and allow the operator to update the values until they decide to go back
					String property = "";
					while (!property.equals("3")){
						property = prompt("What would you like to update?\n1. Name\n2. Fee\n3. To go back");
						switch (property){
						case "1":
							updateService.setName(prompt("New name:", 20));
							System.out.println("Name updated");
							break;
						case "2":
							Double fee = null;
							while (fee == null){
								try{
									fee = Double.parseDouble(prompt("New Fee:"));}
								catch (Exception e){
									fee = null;
									System.out.print("Invalid Fee");}}
							updateService.setFee(fee);
							break;
						case "3":
							break;
						default:
							System.out.println("Invalid selection");
							break;
                        }
                    }
                }
                System.out.println("Press enter to continue");
                try { System.in.read(); } catch (IOException e) {}
				break;
			case "4":
				goForward("Remove "+type);
				
				//Get the member with the specified ID
				int memberid = Integer.parseInt(prompt(type+" ID:"));
				Database.DatabaseEntry removeEntry = null;
				if (entry == EntryType.MEMBER){
					removeEntry = ChocAnMain.memberDatabase.getEntry(memberid);}
				else if (entry == EntryType.PROVIDER){
					removeEntry = ChocAnMain.providerDatabase.getEntry(memberid);}
				else if (entry == EntryType.SERVICE){
					removeEntry = ChocAnMain.providerDirectoryDatabase.getEntry(memberid);}
				if (removeEntry == null){
					System.out.println("The "+typeLower+" with the specified ID does not exist");
                    System.out.println("Press enter to continue");
                    try { System.in.read(); } catch (IOException e) {}
					break;
				}
				
				//Delete member if the user confirms
				String confirm = "";
				while (confirm.equals("")){
					confirm = prompt("Are you sure?\n1. Yes\n2. No");
					switch (confirm) {
						case "1":
							if (entry == EntryType.MEMBER){
								ChocAnMain.memberDatabase.removeEntry(removeEntry);}
							else if (entry == EntryType.PROVIDER){
								ChocAnMain.providerDatabase.removeEntry(removeEntry);}
							else if (entry == EntryType.SERVICE){
								ChocAnMain.providerDirectoryDatabase.removeEntry(removeEntry);}
							System.out.println("The "+typeLower+" was deleted");
							break;
						case "2":
							System.out.println("The "+typeLower+" was not deleted");
							break;
						default:
							System.out.println("Invalid selection");
							confirm = "";
							break;}				
				}
                System.out.println("Press enter to continue");
                try { System.in.read(); } catch (IOException e) {}
				break;
			case "5":
				choice = "";
				break;
			default:
				System.out.println("Invalid selection");
                System.out.println("Press enter to continue");
                try { System.in.read(); } catch (IOException e) {}
				break;
			}
			goBack();
		}
	}
}
