import java.util.ArrayList;


public class TestMain {

    public static void main(String []args) {

        Member m1 = new Member("Ryan", "OK","Address", "City", "Zip", "State");
        Member m2 = new Member("Matthew","OK", "Address2", "City2", "Zip2", "State2");
        Member m3 = new Member("Bre","OK", "Address3", "City3", "Zip3", "State3");

        Database<Member> dbMember = new Database<Member>("member_database.dat");
        Database<Provider> dbProvider = new Database<Provider> ("provider_database.dat");
        Database<Service> dbService = new Database<Service>("service_database.dat");
        Database<ProvidedService> dbProvidedService = new Database<ProvidedService>("provided_service_database.dat");

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
}