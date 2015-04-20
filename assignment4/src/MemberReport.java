import java.io.*;
import java.util.ArrayList;
import database.*;

/**
 * The MemberReport class generates report files for members with their info,
 * and info for all the services provided to them.
 * @author Matthew Leeds
 */
public class MemberReport extends Report {
    private static Member member;
    public MemberReport(File f, Member member) {
        this.file = f;
        this.member = member;
    }
    public void generateReport() throws IOException {
        FileWriter fW = new FileWriter(this.file);
        fW.write(this.member.getName() + "\n");
        int thisMemberId = -1;
        try { 
            thisMemberId = this.member.getId();
            fW.write(thisMemberId + "\n"); 
        } catch (Exception e) {}
        fW.write(this.member.getAddressStreet() + "\n");
        fW.write(this.member.getAddressCity() + "\n");
        fW.write(this.member.getAddressZipCode() + "\n");
        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
        for (int i = 0; i < allServices.size(); ++i) {
            ProvidedService s = allServices.get(i);
            if (s.getMemberId() == thisMemberId) {
                fW.write(s.getDateProvided() + "\n");
                int providerId = s.getProviderId();
                Provider provider = ChocAnMain.providerDatabase.getEntry(providerId);
                fW.write(provider.getName() + "\n");
                int serviceId = s.getServiceId(); 
                Service service = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                fW.write(service.getName() + "\n");
            }
        }
        fW.close();
    }
}
