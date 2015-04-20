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
        fW.write("Member name: " + this.member.getName() + "\n");
        int thisMemberId = -1;
        try { 
            thisMemberId = this.member.getId();
            fW.write("Member number: " + thisMemberId + "\n"); 
        } catch (Exception e) {}
        fW.write("Member address: " + this.member.getAddressStreet() + "\n");
        fW.write("Member city: " + this.member.getAddressCity() + "\n");
        fW.write("Member state: " + this.member.getAddressState() + "\n");
        fW.write("Member ZIP code: " + this.member.getAddressZipCode() + "\n");
        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
        for (int i = 0; i < allServices.size(); ++i) {
            ProvidedService s = allServices.get(i);
            if (s.getMemberId() == thisMemberId) {
                fW.write("Service " + i + " date provided: " + s.getDateProvided() + "\n");
                int providerId = s.getProviderId();
                Provider provider = ChocAnMain.providerDatabase.getEntry(providerId);
                fW.write("Service " + i + " provider name: " + provider.getName() + "\n");
                int serviceId = s.getServiceId(); 
                Service service = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                fW.write("Service " + i + " name: " + service.getName() + "\n");
            }
        }
        fW.close();
    }
}
