
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    //The file names for all the databases
    private static final String memberDatabaseFile = "member_database.dat";
    private static final String providerDatabaseFile = "provider_database.dat";
    private static final String serviceDatabaseFile = "service_database.dat";
    private static final String providedServiceDatabaseFile = "provided_service_database.dat";

    //The databases
    public static final Database<Member> memberDatabase = new Database<Member>(memberDatabaseFile);
    public static final Database<Provider> providerDatabase = new Database<Provider> (providerDatabaseFile);
    public static final Database<Service> serviceDatabase = new Database<Service>(serviceDatabaseFile);
    public static final Database<ProvidedService> providedServiceDatabase = new Database<ProvidedService>(providedServiceDatabaseFile);

    public static void main(String []args) {


        System.out.println("Welcome to ChocAn!");
        System.out.println("1. Provider\n2. Operator\n3. Manager\n4. Member\nEnter choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                ProviderPrompt.provider(input);
                break;
            case 2:
                OperatorPrompt.operator(input);
                break;
            case 3:
                ManagerPrompt.manager(input);
                break;
            case 4:
                MemberPrompt.member(input);
                break;
            default:
                System.out.println("Sorry, not a correct number entered :(");
                break;
        }

        }

        //Example for quereying information
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
