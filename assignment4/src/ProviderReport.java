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
        fW.write(this.provider.getName() + "\n");
        int thisProviderId = -1;
        try { 
            thisProviderId = this.provider.getId();
            fW.write(thisProviderId + "\n"); 
        } catch (Exception e) {}
        fW.write(this.provider.getAddressStreet() + "\n");
        fW.write(this.provider.getAddressCity() + "\n");
        fW.write(this.provider.getAddressZipCode() + "\n");
        int numServicesProvided = 0;
        double totalFees = 0;
        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
        for (int i = 0; i < allServices.size(); ++i) {
            ProvidedService s = allServices.get(i);
            if (s.getProviderId() == thisProviderId) {
                numServicesProvided++;
                fW.write(s.getDateProvided() + "\n");
                fW.write(s.getDateInput() + "\n");
                int memberId = s.getMemberId();
                Member m = ChocAnMain.memberDatabase.getEntry(memberId);
                fW.write(m.getName() + "\n");
                try { fW.write(m.getId() + "\n"); } catch (Exception e) {}
                int serviceId = s.getServiceId(); 
                Service service = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                try { fW.write(service.getId() + "\n"); } catch (Exception e) {}
                double thisFee = service.getFee();
                totalFees += thisFee;
                fW.write(thisFee + "\n");
            }
        }
        fW.write(numServicesProvided + "\n");
        fW.write(totalFees + "\n");
        fW.close();
    }
}
