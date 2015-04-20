import java.io.*;
import java.util.ArrayList;
import database.*;

/**
 * The ProviderReport class generates report files for providers with their info, info for 
 * all the services they provided, and totals for number of consultations and fees.
 * @author Matthew Leeds
 */
public class ProviderReport extends Report {
    private static Provider provider;
    public ProviderReport(File f, Provider provider) {
        this.file = f;
        this.provider = provider;
    }
    public void generateReport() throws IOException {
        FileWriter fW = new FileWriter(this.file);
        fW.write("Provider name: " + this.provider.getName() + "\n");
        int thisProviderId = -1;
        try { 
            thisProviderId = this.provider.getId();
            fW.write("Provider number: " + thisProviderId + "\n"); 
        } catch (Exception e) {}
        fW.write("Provider address: " + this.provider.getAddressStreet() + "\n");
        fW.write("Provider city: " + this.provider.getAddressCity() + "\n");
        fW.write("Provider state: " + this.provider.getAddressState() + "\n");
        fW.write("Provider ZIP code: " + this.provider.getAddressZipCode() + "\n");
        int numServicesProvided = 0;
        double totalFees = 0;
        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
        for (int i = 0; i < allServices.size(); ++i) {
            ProvidedService s = allServices.get(i);
            if (s.getProviderId() == thisProviderId) {
                numServicesProvided++;
                fW.write("Service " + i + " date provided: " + s.getDateProvided() + "\n");
                fW.write("Service " + i + " date input: " + s.getDateInput() + "\n");
                int memberId = s.getMemberId();
                Member m = ChocAnMain.memberDatabase.getEntry(memberId);
                fW.write("Member name: " + m.getName() + "\n");
                try { fW.write("Member number: " + m.getId() + "\n"); } catch (Exception e) {}
                int serviceId = s.getServiceId(); 
                Service service = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                try { fW.write("Service " + i + " code: " + service.getId() + "\n"); } catch (Exception e) {}
                double thisFee = service.getFee();
                totalFees += thisFee;
                fW.write("Service " + i + " fee: " + thisFee + "\n");
            }
        }
        fW.write("Total number of consultations: " + numServicesProvided + "\n");
        fW.write("Total fees: " + totalFees + "\n");
        fW.close();
    }
}
