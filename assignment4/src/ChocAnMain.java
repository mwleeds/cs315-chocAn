import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import database.*;

public class ChocAnMain {

    //The file names for all the databases
    private static final String memberDatabaseFile = "member_database.dat";
    private static final String providerDatabaseFile = "provider_database.dat";
    private static final String serviceDatabaseFile = "service_database.dat";
    private static final String providedServiceDatabaseFile = "provided_service_database.dat";

    //The databases for logging all necessary information
    public static final Database<Member> memberDatabase = new Database<Member>(memberDatabaseFile);
    public static final Database<Provider> providerDatabase = new Database<Provider> (providerDatabaseFile);
    public static final Database<Service> providerDirectoryDatabase = new Database<Service>(serviceDatabaseFile);
    public static final Database<ProvidedService> providedServiceDatabase = new Database<ProvidedService>(providedServiceDatabaseFile);

    //Instantiate the input scanner
    public static final Scanner input = new Scanner(System.in);
    
    public static void main(String []args) {

        /* mock data
        Service s1 = new Service("just eat less chocolate", 42);
        providerDirectoryDatabase.addEntry(s1);
        Provider p1 = new Provider("Obamacare", "addr1", "city", "zip", "state");
        providerDatabase.addEntry(p1);
        Member m1 = new Member("Jacob Farmer", "Suspended", "123 Maple Street", "Farmington", "12345", "WI");
        memberDatabase.addEntry(m1);
        ProvidedService pS = null;
        try { pS = new ProvidedService(new Date(), m1.getId(), p1.getId(), s1.getId(), "good stuff man"); } 
        catch (Exception e) {System.out.println(e.getMessage());}
        providedServiceDatabase.addEntry(pS);
        end mock data */

        Prompter prompt;
        String choice = "";
        while (!choice.equals("5")) {
            System.out.println("\nWelcome to ChocAn!\n");
            // Choose which user to log in as
            System.out.println("Log in:\n1. Provider\n2. Operator\n3. Manager\n4. Member\n5. Exit ChocAn\nEnter choice: ");
            choice = input.next();
            switch (choice) {
                case "1":
                    prompt = new ProviderPrompt();
                    prompt.run();
                    break;
                case "2":
                    prompt = new OperatorPrompt();
                    prompt.run();
                    break;
                case "3":
                    prompt = new ManagerPrompt();
                    prompt.run();
                    break;
                case "4":
                    prompt = new MemberPrompt();
                    prompt.run();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Invalid option entered. Press enter to continue");
                    try { System.in.read(); } catch (IOException e) {}
                    break;
            }
        }
    } // end main

        //Example for querying information
        //Get all services provided to member #0
        /*
            int memberId = 0;
            ArrayList<ProvidedService> listOfServices = new ArrayList<ProvidedService>();
            for (int i=0;i<dbProvidedService.size();i++){

                ProvidedService service = dbProvidedService.getEntry(i);
                if (service.getMemberId() == memberId){
                    listOfServices.add(service);
                }
            }
        */

        //Example get an entry's id
        /*
            Member m4 = new Member("Ryan", "OK","Address", "City", "Zip", "State");
            int memberId = dbMember.addEntry(m4);


            Member m5 = dbMember.getEntry(0);
            int memberId2;
            try{
                memberId2 = m5.getId();}
            catch (Exception e){
                System.out.println(e);
            }
        */

        //Example updating an entry
        /*
            Member mem = dbMember.getEntry(0);
            mem.setName("New Name");
            dbMember.updateEntry(mem);
         */

}
